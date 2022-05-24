package sg.iss.vttp.VTTP.Mini.Project.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import sg.iss.vttp.VTTP.Mini.Project.Models.Restaurant;
import sg.iss.vttp.VTTP.Mini.Project.Services.AppUserService;
import sg.iss.vttp.VTTP.Mini.Project.Services.ApplicationService;

@Controller
@RequestMapping("/")
public class TemplateController {
  
  @Autowired
  ApplicationService appServ;

  @Autowired
  AppUserService appUserServ;

  @ModelAttribute("auth")
  Authentication authentication(Authentication auth){
    return auth;
  }

  // @ModelAttribute("restaurants")
  // List<Restaurant> getRestaurants(){
  //   List<Restaurant> rests = appServ.getAllRestaurantDetails();
  //   return rests;
  // }

  @GetMapping(path="")
  public ModelAndView index() {
    return new ModelAndView("redirect:/login");
  }

  @GetMapping("login")
  public ModelAndView getLoginView(ModelAndView mav) {
    mav.setViewName("login");
    return mav;
  }

  @GetMapping("processing")
  public ModelAndView getLoginCreds(ModelAndView mav, @ModelAttribute("auth") Authentication auth) {

    if (auth.getAuthorities().toString().equals("[ROLE_MANAGER]")) {
      mav.addObject("role", "manager");
      mav.setViewName("redirect:landing/manager");
      return mav;
    }

    else if (auth.getAuthorities().toString().equals("[ROLE_USER]")) {
      mav.addObject("role", "user");
      mav.setViewName("redirect:landing/user");
      return mav;
    }

    mav.setViewName("redirect:logout");
    return mav;
  }

  @GetMapping(path="landing/user")
  public ModelAndView getUserLandingView(ModelAndView mav, @ModelAttribute("auth") Authentication auth) {
    System.out.println(auth.getName()+ " the " + auth.getAuthorities().toString() +  " has logged in!");
    mav.setViewName("UserPages/UserLandingPage");
    return mav;
  }

  @GetMapping(path="landing/manager")
  public ModelAndView getManagerLandingView(ModelAndView mav, @ModelAttribute("auth") Authentication auth) {
    System.out.println(auth.getName()+ " the " + auth.getAuthorities().toString() +  " has logged in!");
    mav.setViewName("ManagerPages/ManagerLandingPage");
    return mav;
  }

  //User Pages Mappings

  @PostMapping(path = "landing/user/make")
  public ModelAndView getUserMakeReservationsPage(ModelAndView mav){
    
    List<Restaurant> rests = appServ.getAllRestaurantDetails();
    mav.addObject("restaurants", rests);
    System.out.println(rests.get(0).name);
    System.out.println(rests.get(0).description);
    mav.setViewName("UserPages/MakeReservation");
    return mav;
  }

  @PostMapping(path = "landing/user/manage")
  public ModelAndView getUserManageReservationsPage(ModelAndView mav){


    mav.setViewName("UserPages/ExistingReservations");
    return mav;
  }

  @GetMapping(path= "landing/user/make/{restaurant}")
  @ResponseBody
  public ModelAndView getRestaurantPage(ModelAndView mav, @PathVariable String restaurant, @ModelAttribute("restaurants") List<Restaurant> rests){
    Restaurant current = new Restaurant();
    for (Restaurant rest : rests){
      if (restaurant.equals(rest.getName())){
        current = rest;
      }
      else {
        mav.setViewName("error");
        return mav;
      }
    }
    mav.addObject("currentRest", current);
    mav.setViewName("UserPages/RestaurantPage");
    return mav;
  }

}
