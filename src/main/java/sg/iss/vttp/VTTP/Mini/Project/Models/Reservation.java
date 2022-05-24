package sg.iss.vttp.VTTP.Mini.Project.Models;


import java.util.UUID;

public class Reservation {
  
  private String reservationId; 
  private String customerName;
  private String restaurantName;
  private int customerPax;
  private String reservationDate;
  private String reservationTime;
  private boolean reservationStatus = false;

  public Reservation(String customerName, String restaurantName, int customerPax, String reservationDate,
      String reservationTime) {
    this.reservationId = UUID.randomUUID().toString().substring(0,8);
    this.customerName = customerName;
    this.restaurantName = restaurantName;
    this.customerPax = customerPax;
    this.reservationDate = reservationDate;
    this.reservationTime = reservationTime;
  }

  public Reservation() {
    this.reservationId = UUID.randomUUID().toString().substring(0,8);
  }

  public boolean isReservationStatus() {
    return reservationStatus;
  }

  public void setReservationStatus(boolean reservationStatus) {
    this.reservationStatus = reservationStatus;
  }

  public String getReservationId() {
    return reservationId;
  }
  public void setReservationId(String reservationId) {
    this.reservationId = reservationId;
  }
  public String getCustomerName() {
    return customerName;
  }
  public void setCustomerName(String customerName) {
    this.customerName = customerName;
  }
  public String getRestaurantName() {
    return restaurantName;
  }
  public void setRestaurantName(String restaurantName) {
    this.restaurantName = restaurantName;
  }
  public int getCustomerPax() {
    return customerPax;
  }
  public void setCustomerPax(int customerPax) {
    this.customerPax = customerPax;
  }
  public String getReservationDate() {
    return reservationDate;
  }
  public void setReservationDate(String reservationDate) {
    this.reservationDate = reservationDate;
  }
  public String getReservationTime() {
    return reservationTime;
  }
  public void setReservationTime(String reservationTime) {
    this.reservationTime = reservationTime;
  }

  

}
