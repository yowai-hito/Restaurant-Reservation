package sg.iss.vttp.VTTP.Mini.Project.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import sg.iss.vttp.VTTP.Mini.Project.Dao.AppUserDao;

@Service
public class AppUserService implements UserDetailsService {

  private final AppUserDao appUserDao;

  @Autowired
  public AppUserService(@Qualifier("UserRepo") AppUserDao appUserDao) {
    this.appUserDao = appUserDao;
  }

  
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return appUserDao.selectAppUserByUsername(username)
              .orElseThrow( () -> new UsernameNotFoundException(String.format("Username %s not found", username)));
  }
  
}
