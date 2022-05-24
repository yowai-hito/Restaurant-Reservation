package sg.iss.vttp.VTTP.Mini.Project.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.iss.vttp.VTTP.Mini.Project.Models.Reservation;
import sg.iss.vttp.VTTP.Mini.Project.Models.Restaurant;
import sg.iss.vttp.VTTP.Mini.Project.Repositories.ApplicationRepository;



@Service
public class ApplicationService {

  @Autowired
  ApplicationRepository appRepo;

  public Optional<List<Restaurant>> getAllRestaurantDetails(){
    return appRepo.selectAllRestaurants();
  }

  public int createReservation(Reservation resv){
    return appRepo.insertNewReservation(resv);
  }

  public Optional<List<Reservation>> getReservationsByCustName(String custName){
    return appRepo.selectAllReservationsByUsername(custName);
  }

  public int deleteReservationWithId(String id){
    return appRepo.deleteReservation(id);
  }
}
