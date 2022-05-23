package sg.iss.vttp.VTTP.Mini.Project.Dao;

import java.util.Optional;

import sg.iss.vttp.VTTP.Mini.Project.Models.AppUser;

public interface AppUserDao {
  
  public static final String SQL_USERS_SELECT_USER_BY_USERNAME = "SELECT * from users where account_name = ?";

  public static final String SQL_USERS_LOGIN = "SELECT * from users where account_name = ? and account_password = ?";

  public static final String SQL_USERS_CREATE_USER_ACCOUNT = "INSERT INTO USERS(Account_Id, account_name, account_password, account_type, account_restaurant " + 
  "VALUES (?,?,?,?,?)";

  public Optional<AppUser> selectAppUserByUsername(String username);
}
