package sg.iss.vttp.VTTP.Mini.Project;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import sg.iss.vttp.VTTP.Mini.Project.Repositories.AppUserRepository;
import sg.iss.vttp.VTTP.Mini.Project.Repositories.ApplicationRepository;
import sg.iss.vttp.VTTP.Mini.Project.Services.AppUserService;
import sg.iss.vttp.VTTP.Mini.Project.Services.ApplicationService;
import sg.iss.vttp.VTTP.Mini.Project.Services.LocationService;

@SpringBootTest
class VttpMiniProjectApplicationTests {

	@Autowired
	AppUserService appUserService;

	@Autowired
	ApplicationService appService;

	@Autowired
	LocationService locationService;

	@Autowired
	ApplicationRepository appRepo;

	@Autowired
	AppUserRepository appUserRepo;

	@Test
	void contextLoads() {
	}

}
