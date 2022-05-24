package sg.iss.vttp.VTTP.Mini.Project.Models;

public class LoginCred {
  String username;
  String password;

  public LoginCred() {
  }

  public LoginCred(String username, String password) {
    this.username = username;
    this.password = password;
  }
  public String getUsername() {
    return username;
  }
  public void setUsername(String username) {
    this.username = username;
  }
  public String getPassword() {
    return password;
  }
  public void setPassword(String password) {
    this.password = password;
  }
}
