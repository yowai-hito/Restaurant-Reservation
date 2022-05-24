package sg.iss.vttp.VTTP.Mini.Project.Repositories;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import sg.iss.vttp.VTTP.Mini.Project.Dao.ApplicationDao;
import sg.iss.vttp.VTTP.Mini.Project.Models.Reservation;
import sg.iss.vttp.VTTP.Mini.Project.Models.Restaurant;

@Repository
public class ApplicationRepository implements ApplicationDao {
  
  //Basic Features: create, view by user, view by restaurant, view by restaurant and datetime, update status, delete reservation"

  @Autowired
  JdbcTemplate jdbc;
  
  public Optional<List<Restaurant> > selectAllRestaurants() {
    
    return jdbc.query(SQL_REST_SELECT_ALL, 
    (ResultSet rs) -> {
        List<Restaurant> rests = new ArrayList<Restaurant>();
        while (rs.next()){
          Restaurant rest = new Restaurant();
          rest.setName(rs.getString("Restaurant_Name"));
          rest.setTableCount(rs.getInt("table_count"));
          rest.setCurrentlyFilled(rs.getInt("currently_filled"));
          rest.setLocation(rs.getString("rest_location"));
          rest.setDescription(rs.getString("rest_description"));
          rests.add(rest);
        }
        return Optional.of(rests);
    });
  }

  public int insertNewReservation(Reservation resv){
    
    return jdbc.update(SQL_RESV_CREATE,
      resv.getReservationId(),
      resv.getRestaurantName(),
      resv.getCustomerName(),
      resv.getCustomerPax(),
      resv.getReservationDate(),
      resv.getReservationTime()
    );
  }

  public Optional<List<Reservation>> selectAllReservationsByUsername(String custName){
    
    return jdbc.query(SQL_RESV_VIEW_RESERVATIONS_BY_CNAME, 
    (ResultSet rs) -> {
        List<Reservation> resvs = new ArrayList<Reservation>();
        boolean check = false;
        while (rs.next()){
          check = true;
          Reservation resv = new Reservation();
          resv.setReservationId(rs.getString("Reservation_Id"));
          resv.setCustomerName(rs.getString("customer_name"));
          resv.setRestaurantName(rs.getString("restaurant_name"));
          resv.setReservationDate(rs.getString("reservation_date"));
          resv.setReservationTime(rs.getString("reservation_time"));
          resv.setReservationStatus(rs.getBoolean("reservation_status"));
          resv.setCustomerPax(rs.getInt("customer_pax"));
          resvs.add(resv);
        }
        if (check == false){return Optional.empty();}
        return Optional.of(resvs);
    }, custName);
  }

  public int deleteReservation(String id){
    return jdbc.update(SQL_RESV_DELETE_RESERVATION_WITH_ID,id
    );
  }
}
