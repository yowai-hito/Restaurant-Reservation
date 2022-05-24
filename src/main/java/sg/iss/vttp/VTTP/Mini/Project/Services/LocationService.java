package sg.iss.vttp.VTTP.Mini.Project.Services;

import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class LocationService {
  
  String GOOGLEMAPS_API_KEY = "AIzaSyBFQR9zxOpKgJZb62a4AYyzeI5ebTxZ8m0";

  public String getGoogleMapsEmbedLink(String location) {
    
    String uri = "https://www.google.com/maps/embed/v1/place";
    //Testing
    // String addr = "Tampines Mall";

    String endpoint = UriComponentsBuilder
      .fromUriString(uri)
      .queryParam("key", GOOGLEMAPS_API_KEY)
      .queryParam("q",location)
      .build().toString();

    return endpoint;
  }
}
