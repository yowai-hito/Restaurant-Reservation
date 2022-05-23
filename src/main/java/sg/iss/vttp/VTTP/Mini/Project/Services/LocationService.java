package sg.iss.vttp.VTTP.Mini.Project.Services;

import java.io.StringReader;

import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonReader;

@Service
public class LocationService {
  
  public JsonArray positionStackGeocoding(String addr) {
    
    RestTemplate template = new RestTemplate();

    String PSTACK_API_KEY = "5d93ad21dd389367dca397ca5d7d7ab2";
    String uri = "http://api.positionstack.com/v1/forward";
    //Testing
    // String addr = "Tampines Mall";

    String endpoint = UriComponentsBuilder
    
      .fromUriString(uri)
      .queryParam("access_key", PSTACK_API_KEY)
      .queryParam("query",addr)
      .queryParam("country","SG")
      .queryParam("limit",1)
      .build().toString();

    RequestEntity<Void> req = RequestEntity
      .get(endpoint)
      .accept(MediaType.APPLICATION_JSON)
      .build();

    ResponseEntity<String> resp = template.exchange(req,String.class);
    JsonReader jsonReader = Json.createReader(new StringReader(resp.getBody()));
    JsonArray locationData = jsonReader.readObject().getJsonArray("data");
    return locationData;
  }
}
