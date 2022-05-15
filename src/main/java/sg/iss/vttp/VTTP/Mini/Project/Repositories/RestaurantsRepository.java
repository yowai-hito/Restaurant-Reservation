package sg.iss.vttp.VTTP.Mini.Project.Repositories;

import org.springframework.stereotype.Repository;

@Repository
public class RestaurantsRepository {
  //Basic features: get table count from restaurant name.

  final String SQL_REST_FIND_TABLE_DETAILS_WITH_REST_NAME = "SELECT table_count, currently_filled from restaurants WHERE Restaurant_Name = ?";
}
