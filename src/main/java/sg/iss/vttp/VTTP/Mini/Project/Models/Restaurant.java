package sg.iss.vttp.VTTP.Mini.Project.Models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Restaurant implements Serializable {
  
  public String name;

  public List<RestaurantTable> tables = new ArrayList<RestaurantTable>();

  public int tableCount=0;

  public int currentlyFilled=0;

  public String description;

  public String location;

  public int getCurrentlyFilled() {
    return currentlyFilled;
  }

  public void setCurrentlyFilled(int currentlyFilled) {
    this.currentlyFilled = currentlyFilled;
  }

  public void setTableCount(int tableCount) {
    this.tableCount = tableCount;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  
  public int getTableCount() {
    return tableCount;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<RestaurantTable> getTables() {
    return tables;
  }

  public void setTables(List<RestaurantTable> tables) {
    this.tables = tables;
    this.tableCount = this.tables.size();
  }

  public void addTables(RestaurantTable table) {
    this.tables.add(table);
    this.tableCount = this.tables.size();
  }

  public void addTables(List<RestaurantTable> tables){
    for (RestaurantTable table : tables){
      this.tables.add(table);
    }

    this.tableCount = this.tables.size();
  }

  public String getLocationWithoutSpaces(){
    String result = this.location.trim();
    return result.replaceAll("\\s","+");
  }

  @Override
  public String toString() {
    return "Restaurant [description=" + description + ", location=" + location + ", name=" + name + ", tableCount="
        + tableCount + ", tables=" + tables + "]";
  }

  
}
