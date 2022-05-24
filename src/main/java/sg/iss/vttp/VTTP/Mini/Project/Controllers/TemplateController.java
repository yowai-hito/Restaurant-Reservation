package sg.iss.vttp.VTTP.Mini.Project.Controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import sg.iss.vttp.VTTP.Mini.Project.Models.Reservation;
import sg.iss.vttp.VTTP.Mini.Project.Models.Restaurant;
import sg.iss.vttp.VTTP.Mini.Project.Services.AppUserService;
import sg.iss.vttp.VTTP.Mini.Project.Services.ApplicationService;
import sg.iss.vttp.VTTP.Mini.Project.Services.LocationService;

@Controller
@RequestMapping("/")
public class TemplateController {
  
  @Autowired
  ApplicationService appServ;

  @Autowired
  AppUserService appUserServ;

  @Autowired
  LocationService locationServ;

  @ModelAttribute("auth")
  Authentication authentication(Authentication auth){
    return auth;
  }

  @ModelAttribute("restaurants")
  List<Restaurant> getRestaurants(){
    Optional<List<Restaurant>> rests = appServ.getAllRestaurantDetails();
    return rests.get();
  }

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

  //User Pages Make Reservation Mappings

  @PostMapping(path = "landing/user/make")
  public ModelAndView getUserMakeReservationsPage(ModelAndView mav, @ModelAttribute("restaurants") ArrayList<Restaurant> rests){
    
    System.out.println(rests.get(0).name);
    System.out.println(rests.get(0).description);
    mav.setViewName("UserPages/MakeReservation");
    return mav;
  }

  @GetMapping(path="landing/user/make/{restaurant}")
  public ModelAndView getRestaurantPage(ModelAndView mav, @PathVariable String restaurant, 
  @ModelAttribute("restaurants") ArrayList<Restaurant> rests){
    Restaurant current = new Restaurant();
    boolean check = false;
    for (Restaurant rest : rests){
      if (restaurant.equals(rest.getName())){
        mav.addObject("currentRest", rest);
        current = rest;
        check = true;
      }
    }
    if (!check){
      mav.setStatus(HttpStatus.NOT_FOUND);
      mav.setViewName("error");
    }
    String embedSrc = locationServ.getGoogleMapsEmbedLink(current.getLocationWithoutSpaces());
    mav.addObject("embedSrc",embedSrc);
    mav.setViewName("UserPages/RestaurantPage");
    return mav;
  }

  @GetMapping(path="landing/user/make/{restaurant}/reserve")
  public ModelAndView getReservationForm(ModelAndView mav, @PathVariable String restaurant,
  @ModelAttribute("auth") Authentication auth){
    mav.addObject("custName", auth.getName());
    mav.addObject("currentRestName", restaurant);
    mav.setViewName("UserPages/ReservationForm");
    return mav;
  }

  @PostMapping(path="landing/user/submit")
  public ModelAndView postReservationForm(ModelAndView mav, 
  @RequestParam MultiValueMap<String, String> formData){
    // System.out.println(formData.getFirst("customer_name"));
    Reservation resv = new Reservation(
      formData.getFirst("customer_name"), 
      formData.getFirst("restaurant_name"), 
      Integer.parseInt(formData.getFirst("customer_pax")), 
      formData.getFirst("reservation_date"), 
      formData.getFirst("reservation_time"));

    int updated = appServ.createReservation(resv);
    System.out.println(updated + " line(s) were successfully updated");

    mav.setViewName("redirect:/landing/user");
    return mav;
  }

  //User Pages Manage Existing Reservation Mappings
  @PostMapping(path = "landing/user/manage")
  public ModelAndView getUserManageReservationsPage(ModelAndView mav,@ModelAttribute("auth") Authentication auth){
    Optional<List<Reservation>> resvs = appServ.getReservationsByCustName(auth.getName());
    // if (resvs.isEmpty()){
      
    // }
    mav.addObject("reservations", resvs.get());
    mav.setViewName("UserPages/ExistingReservations");
    return mav;
  }

  @GetMapping(path="landing/user/manage/delete")
  public ModelAndView deleteReservationPage(ModelAndView mav, @RequestParam String resvId) {
    int deleted = appServ.deleteReservationWithId(resvId);
    System.out.println(deleted + " line(s) were successfully deleted");
    mav.setViewName("redirect:/landing/user");
    return mav;
  }

}
