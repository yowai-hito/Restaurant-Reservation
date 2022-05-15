package sg.iss.vttp.VTTP.Mini.Project.Repositories;

public class UsersRepository {
  
  //Basic Features: add user/management account, login authentication, get user details.

  final String SQL_USERS_LOGIN = "SELECT * from users where username = ? and password = SHA2(?,256)";

  final String SQL_USERS_CREATE_USER_ACCOUNT = "INSERT INTO USERS(Account_Id, account_name, account_password, account_type, account_restaurant " + 
  "VALUES (?,?,SHA2(?,256),?,?)";

}
