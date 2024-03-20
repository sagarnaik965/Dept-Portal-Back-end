package in.cdac.dbswitch;

import java.util.ArrayList;
import java.util.List;

//import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.InvalidResultSetAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import in.cdac.portal.dao.UserDao;
import in.cdac.portal.entities.UserSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@Service
public class CustomUserDetailsServiceImpl implements CustomUserDetailsService {
	
//	private final static Logger logger = Logger.getLogger(CustomUserDetailsServiceImpl.class);
	 private final  static Logger logger = LogManager.getLogger( CustomUserDetailsServiceImpl.class );
	
	@Autowired
	UserDao dao;
	

	@Override
	public UserDetails loadUserByUsernameAndTenantname(String username, String tenantName)
			throws UsernameNotFoundException {
		in.cdac.portal.entities.User user=null;
		
		try {
			user = dao.loadUserByUsernameAndTenantname(username, tenantName).get(0);
			logger.info(String.format("user %s and tenant %s found",username,tenantName));
			UserSession.setCurrSession(username,null);
			
			if (user == null) {
				throw new UsernameNotFoundException("Username not found");
			}
		}catch(IndexOutOfBoundsException ix )
		{
			logger.info("user not found");
			throw new UsernameNotFoundException("Username not found");
		} catch (InvalidResultSetAccessException ex) {
			logger.info("Error : ", ex);
			throw new UsernameNotFoundException("database error ");
		} catch (DataAccessException dataex) {
			logger.info("Error : ", dataex);
			throw new UsernameNotFoundException("database error ");
		}
		return buildUserFromUserEntity(user);
		
	}
	
	
	
	
	
	
	
	private User buildUserFromUserEntity(in.cdac.portal.entities.User userEntity) {
		String username = userEntity.getUsername();
		String password = userEntity.getPasswd();
		String tenant=userEntity.getTenantname();
		boolean enabled = userEntity.isActive();
		boolean accountNonExpired = true;
		boolean credentialsNonExpired = true;
		boolean accountNonLocked = true;
		
		List<String> authorityList = dao.getActivityListByUsername(username,tenant);
		logger.info("activities list of user::"+authorityList.toString());
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

		for(String authority : authorityList)
		{
			authorities.add(new SimpleGrantedAuthority(authority));
		}
	
		CustomeUserDetails user=new CustomeUserDetails(username,password,enabled,accountNonExpired,credentialsNonExpired,accountNonLocked,authorities,tenant);
		return user;
	}
	

	public String matchPass(String username)
	{
		return dao.matachPass(username);
	}

}
