package sg.iss.vttp.VTTP.Mini.Project;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

import java.net.http.HttpHeaders;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.util.Assert;

import sg.iss.vttp.VTTP.Mini.Project.Models.Reservation;
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
	void controllerTesting() throws Exception {
		RequestBuilder initialRedirectReq = MockMvcRequestBuilders.get("/");
		mav.perform(initialRedirectReq).andExpect(MockMvcResultMatchers.redirectedUrl("http://localhost/login"));
		
		RequestBuilder loginFormReq = MockMvcRequestBuilders.get("/login");
		mav.perform(loginFormReq).andExpect(MockMvcResultMatchers.view().name("login"));

		RequestBuilder loginReq = MockMvcRequestBuilders.post("/login")
		.param("username", "World")
		.param("password","Hello");
		mav.perform(loginReq).andExpect(MockMvcResultMatchers.redirectedUrl("/processing"));

		RequestBuilder processingReq = MockMvcRequestBuilders.get("/processing");
		mav.perform(processingReq);

		RequestBuilder userLandingReq = MockMvcRequestBuilders.get("/landing/user");
		mav.perform(userLandingReq);

		RequestBuilder managerLandingReq = MockMvcRequestBuilders.get("/landing/manager");
		mav.perform(managerLandingReq);

		RequestBuilder userMakeResvReq = MockMvcRequestBuilders.get("/landing/user/make");
		mav.perform(userMakeResvReq);

		RequestBuilder pandasRestPageReq = MockMvcRequestBuilders.get("/landing/user/make/Pandas");
		mav.perform(pandasRestPageReq);
	}

	@Test
	void failLogin() throws Exception {
		RequestBuilder loginReq = MockMvcRequestBuilders.post("/login")
		.param("username", "Fail")
		.param("password","Login");
		mav.perform(loginReq);
	}

	@Test
	void googeEmbed() throws Exception {

		Assert.notNull(locationService.getGoogleMapsEmbedLink("Tampines+Mall"), "Not Empty");
		
	}

	@Test
	void applicationServiceTests() throws Exception {
		Reservation sample = new Reservation();
		sample.setCustomerName("Hello");
		sample.setCustomerPax(4);
		sample.setReservationDate("2022-11-22");
		sample.setReservationId("FAKEUUID");
		sample.setReservationStatus(false);
		sample.setReservationTime("0830");
		sample.setRestaurantName("Pandas");
		appService.createReservation(sample);

		appService.getReservationsByCustName("Hello");

		appService.deleteReservationWithId("FAKEUUID");
	}

}
