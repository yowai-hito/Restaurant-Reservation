package sg.iss.vttp.VTTP.Mini.Project.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.iss.vttp.VTTP.Mini.Project.Models.Restaurant;
import sg.iss.vttp.VTTP.Mini.Project.Repositories.ApplicationRepository;



@Service
public class ApplicationService {

  @Autowired
  ApplicationRepository appRepo;

  public List<Restaurant> getAllRestaurantDetails(){
    return appRepo.selectAllRestaurants().get();
  }

  
}
