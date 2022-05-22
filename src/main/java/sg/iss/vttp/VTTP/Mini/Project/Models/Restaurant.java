package sg.iss.vttp.VTTP.Mini.Project.Models;

import java.util.ArrayList;
import java.util.List;

public class Restaurant {
  
  private String name;

  private List<RestaurantTable> tables = new ArrayList<RestaurantTable>();

  private int tableCount=0;

  private String description;

  private String location;

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

  @Override
  public String toString() {
    return "Restaurant [description=" + description + ", location=" + location + ", name=" + name + ", tableCount="
        + tableCount + ", tables=" + tables + "]";
  }

  
}
