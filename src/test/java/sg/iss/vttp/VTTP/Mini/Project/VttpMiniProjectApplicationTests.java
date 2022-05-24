package sg.iss.vttp.VTTP.Mini.Project;

import java.net.http.HttpHeaders;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import sg.iss.vttp.VTTP.Mini.Project.Models.Restaurant;
import sg.iss.vttp.VTTP.Mini.Project.Repositories.AppUserRepository;
import sg.iss.vttp.VTTP.Mini.Project.Repositories.ApplicationRepository;
import sg.iss.vttp.VTTP.Mini.Project.Services.AppUserService;
import sg.iss.vttp.VTTP.Mini.Project.Services.ApplicationService;
import sg.iss.vttp.VTTP.Mini.Project.Services.LocationService;

@SpringBootTest
@AutoConfigureMockMvc
class VttpMiniProjectApplicationTests {

	@Autowired
	AppUserService appUserService;

	@Autowired
	ApplicationService appService;

	@Autowired
	LocationService locationService;

	@Autowired
	MockMvc mav;
	
	@Test
	void shouldRedirect() throws Exception {
		RequestBuilder initialRedirectReq = MockMvcRequestBuilders.get("/");
		mav.perform(initialRedirectReq);
		
		RequestBuilder loginFormReq = MockMvcRequestBuilders.get("/login");
		mav.perform(loginFormReq).andExpect(MockMvcResultMatchers.view().name("login"));

		RequestBuilder loginReq = MockMvcRequestBuilders.post("/login")
		.param("username", "World")
		.param("password","Hello");
		mav.perform(loginReq).andExpect(MockMvcResultMatchers.view().name("http://localhost/processing"));
	}
}
