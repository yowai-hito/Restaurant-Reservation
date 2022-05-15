package sg.iss.vttp.VTTP.Mini.Project.Repositories;

import org.springframework.stereotype.Repository;

@Repository
public class ReservationsRepository {
  
  //Basic Features: create, view by user, view by restaurant, view by restaurant and datetime, update status, delete reservation"
  final String SQL_RESV_CREATE = "INSERT INTO reservations(Reservation_Id, restaurant_name ,customer_name, customer_pax, reservation_datetime)" +
  " VALUES (?,?,?,?,?) ";

  final String SQL_RESV_VIEW_RESERVATIONS_BY_REST_NAME = "SELECT Reservation_Id, customer_name, customer_pax, reservation_datetime, reservation_status" +
  "WHERE restaurant_name = ? ORDER BY reservation_datetime ASC, reservation_status ASC, created_datetime ASC";

  final String SQL_RESV_VIEW_RESERVATIONS_BY_CNAME_AND_DATETIME = "SELECT Reservation_Id, restaurant_name, customer_name, customer_pax, reservation_datetime, reservation_status" +
  "WHERE reservation_datetime = ? AND customer_name = ?";

  final String SQL_RESV_CONFIRM_RESERVATION_WITH_ID = "UPDATE reservations SET reservation_status = 1 WHERE Reservation_Id = ?";

  final String SQL_RESV_DELETE_RESERVATION_WITH_ID = "DELTE FROM reservations WHERE Reservationd_Id = ?";

}
