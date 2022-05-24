package sg.iss.vttp.VTTP.Mini.Project.Dao;

public interface ApplicationDao {
  
  // Reservations
  public static final String SQL_RESV_CREATE = "INSERT INTO reservations(Reservation_Id, restaurant_name ,customer_name, customer_pax, reservation_date, reservation_time)" +
  " VALUES (?,?,?,?,?,?) ";

  public static final String SQL_RESV_VIEW_RESERVATIONS_BY_REST_NAME = "SELECT Reservation_Id, customer_name, customer_pax, reservation_datetime, reservation_status from reservations" +
  "WHERE restaurant_name = ? ORDER BY reservation_date ASC, reservation_time ASC, reservation_status ASC, created_datetime ASC";

  public static final String SQL_RESV_VIEW_RESERVATIONS_BY_CNAME = "SELECT Reservation_Id, restaurant_name, customer_name, customer_pax, reservation_date, reservation_time, reservation_status " +
  "from reservations WHERE customer_name = ? ORDER BY reservation_date ASC";

  public static final String SQL_RESV_CONFIRM_RESERVATION_WITH_ID = "UPDATE reservations SET reservation_status = 1 WHERE Reservation_Id = ?";

  public static final String SQL_RESV_DELETE_RESERVATION_WITH_ID = "DELETE FROM reservations WHERE Reservation_Id = ?";

  //Restaurants
  public static final String SQL_REST_FIND_TABLE_DETAILS_WITH_REST_NAME = "SELECT table_count, currently_filled from restaurants WHERE Restaurant_Name = ?";

  public static final String SQL_REST_SELECT_ALL = "SELECT * from restaurants";

  //Tables
  public static final String SQL_REST_TABLE_GET_ALL_TABLES_WITH_REST_NAME = "SELECT seat_count where restaurant_name = ?";
}
