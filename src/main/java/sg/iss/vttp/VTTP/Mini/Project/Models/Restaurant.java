package sg.iss.vttp.VTTP.Mini.Project.Models;

import java.util.ArrayList;
import java.util.List;

public class Restaurant {
  
  private String name;

  private List<RestaurantTable> tables = new ArrayList<RestaurantTable>();

  private int tableCount=0;

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

}
