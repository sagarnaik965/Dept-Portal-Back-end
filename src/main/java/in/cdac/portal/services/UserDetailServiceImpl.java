package in.cdac.portal.services;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.InvalidResultSetAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import in.cdac.portal.dao.UserDao;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

	@Autowired
	private UserDao userDao;
	static HttpSession sess;
//	private final static Logger logger = Logger.getLogger(UserDetailServiceImpl.class);
	private final  static Logger logger = LogManager.getLogger( UserDetailServiceImpl.class );

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		in.cdac.portal.entities.User user;
		try {
			user = userDao.getUser(userName).get(0);
			logger.info("Login UserName ::" + user);
			if (user == null) {
				throw new UsernameNotFoundException("Username not found");
			}
		} catch (IndexOutOfBoundsException ix) {
			throw new UsernameNotFoundException("Username not found");
		} catch (InvalidResultSetAccessException ex) {
			throw new UsernameNotFoundException("database error ");
		} catch (DataAccessException dataex) {
			throw new UsernameNotFoundException("database error ");
		}
		return buildUserFromUserEntity(user);
	}

	private User buildUserFromUserEntity(in.cdac.portal.entities.User userEntity) {
		String username = userEntity.getUsername();
		String password = userEntity.getPasswd();
		boolean enabled = userEntity.isActive();
		boolean accountNonExpired = true;
		boolean credentialsNonExpired = true;
		boolean accountNonLocked = true;
		List<String> authorityList = userDao.getActivityListByUsername(username, "");
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		for (String authority : authorityList) {
			authorities.add(new SimpleGrantedAuthority(authority));
		}
		User springUser = new User(username, password, enabled, accountNonExpired, credentialsNonExpired,
				accountNonLocked, authorities);
		return springUser;
	}
}
