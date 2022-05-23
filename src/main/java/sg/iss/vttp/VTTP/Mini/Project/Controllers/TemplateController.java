package sg.iss.vttp.VTTP.Mini.Project.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class TemplateController {
  
  @GetMapping("")
  public String index() {
    return "forward:/login";
  }
  // @GetMapping("login")
  // public ModelAndView getLoginView(ModelAndView mav) {
  //   mav.setViewName("login");
  //   return mav;
  // }

  @GetMapping("landing")
  public ModelAndView getLandingView(ModelAndView mav) {
    mav.setViewName("UserPages/UserLandingPage");
    return mav;
  }
}
