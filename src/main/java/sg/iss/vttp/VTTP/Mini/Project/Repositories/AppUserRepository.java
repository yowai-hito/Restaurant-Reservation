package sg.iss.vttp.VTTP.Mini.Project.Repositories;

import java.sql.ResultSet;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import sg.iss.vttp.VTTP.Mini.Project.Configurations.AppUserRole;
import sg.iss.vttp.VTTP.Mini.Project.Dao.AppUserDao;
import sg.iss.vttp.VTTP.Mini.Project.Models.AppUser;

@Repository("UserRepo")
public class AppUserRepository implements AppUserDao {

  //Basic Features: add user/management account, login authentication, get user details.

  @Autowired
  JdbcTemplate jdbc;

  // @Override
  // @Transactional
  // public Optional<AppUser> loginFunction(String username, String password) {
    
  //   return jdbc.query(SQL_USERS_LOGIN, 
  //   (ResultSet rs) -> {
  //       if (!rs.next()){return Optional.empty();}
  //       AppUser user = new AppUser(
  //         AppUserRole.getRole(rs.getString("account_role")),
  //         rs.getString("account_name"),
  //         rs.getString("account_password")
  //       );
  //       return Optional.of(user);
  //   },
  //   username,password);
  // }

  @Override
  public Optional<AppUser> selectAppUserByUsername(String username) {
    
    return jdbc.query(SQL_USERS_SELECT_USER_BY_USERNAME, 
    (ResultSet rs) -> {
        if (!rs.next()){return Optional.empty();}
        AppUser user = new AppUser(
          AppUserRole.getRole(rs.getString("account_role")),
          rs.getString("account_name"),
          rs.getString("account_password")
        );
        return Optional.of(user);
    },
    username);
  }
  
}
