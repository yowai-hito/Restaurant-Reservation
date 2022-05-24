package sg.iss.vttp.VTTP.Mini.Project.Repositories;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import sg.iss.vttp.VTTP.Mini.Project.Dao.ApplicationDao;
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
}
