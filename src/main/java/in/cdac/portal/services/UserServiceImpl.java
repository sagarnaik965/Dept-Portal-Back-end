package in.cdac.portal.services;

import java.security.Key;
import java.util.List;
import java.util.UUID;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import in.cdac.portal.dao.ReadDao;
import in.cdac.portal.dao.UserDao;
import in.cdac.portal.ePramaanPlugIn.EpramaanOIDCConnector;
import in.cdac.portal.entities.UserSession;
import in.cdac.portal.modal.AllowedOpr;
import in.cdac.portal.modal.AppDetail;
import in.cdac.portal.modal.AppList;
import in.cdac.portal.modal.AppLk;
import in.cdac.portal.modal.DeptList;
import in.cdac.portal.modal.PortalConstant;


@CrossOrigin(origins = { "*"})
@Service
public class UserServiceImpl implements UserService {

	public static String username;
	@Autowired
	UserDao userDao;

	@Autowired
	ReadDao rdao;

	@Autowired
	PasswordEncoder encoder;

	@Autowired(required = true)
	BillingServices billSer;

	@Autowired
	Environment env;

	@Autowired
	RedirectView redirect;

	@Autowired
	DashboardService dashServ;
	@Autowired
	HttpServletRequest req;
	@Autowired
	HttpServletResponse resp;
	
	public static HttpSession sess;


	@Override
	public DeptList getDeptcodeFromUsername(String username) {

		return userDao.getDeptcodeFromUsername(username);
	}

	@Override
	public List<AppList> getAppListR(String deptcode) {
	
		return userDao.getAppListR(deptcode);
	}

	@Override
	public AppDetail getAppDetailR(String appcode) {

		return userDao.getAppDetailR(appcode);
	}

	@Override
	public  List<AppLk> getAppLkR(String appcode) {

		return userDao.getAppLkR(appcode);
	}

	@Override
	public AllowedOpr getOprR(String appcode) {

		return userDao.getOprR(appcode);
	}

	@Override
	public RedirectView getLogin(@RequestParam(required = false) String authfailed, String logout, String denied,
			String captchainvalid, String disabled) {
redirect.setUrl(env.getProperty("ePramaanControl"));
return	redirect;
	}

	@Override
	public ResponseEntity<String> isSessNull(String username) {
		
		try {
			if (UserSession.currSession.containsKey(username)) {
				return null;
			} else {
				return new ResponseEntity<String>("FSESSION" , HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			return new ResponseEntity<String>("FSESSION" , HttpStatus.BAD_REQUEST);		
		}	
	}
	
	@Override
	public List<AppList> getAppListForReportsR(String username){
		DeptList dept =  userDao.getDeptcodeFromUsername(username);
		List<AppList> appList =  userDao.getAppListIgnite(username);
		return userDao.getAppListR(dept.getDept_code());
		
	}

	String getPrincipal() {
		String userName = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			userName = ((UserDetails) principal).getUsername();
		} else {
			userName = principal.toString();
		}
		return userName;
	}

	@Override
	public RedirectView getHome(HttpSession session) {

		RedirectView newr = new RedirectView();
		if (UserSession.currSession.containsKey(username)) {
			newr.setUrl(env.getProperty("ePramaanControl"));
		return newr;
		}
//System.out.println("in home");
		redirect.setUrl(env.getProperty("ePramaanControl"));
return	redirect;
	}

	@Override
	public RedirectView getusernames(HttpSession session) {
		String inp = "";
		String username = UserServiceImpl.username;
		try {
			inp = encrypt(username);
		} catch (Exception e) {
			e.printStackTrace();
		}
		inp = inp.replaceAll("/", "slashinurl");
		RedirectView newr = new RedirectView();
		if (UserSession.currSession.containsKey(username)) {
			newr.setUrl(env.getProperty("TEMPLINK")+ inp + "");
			return newr;
		}

		redirect.setUrl(env.getProperty("loginforlogin"));
		return redirect;
	}

	@Override
	public ResponseEntity<String> loggingOut(String username) {

		try {
		
		
		} catch (Exception e) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}

		redirect.setUrl(env.getProperty("login"));
		return new ResponseEntity<String>(HttpStatus.OK);
	}

	@Override
	public RedirectView getHomeSaveSession(HttpSession session) {
		return null;
	}

	private static final String ALGO = "AES";
	private static final byte[] keyValue = new byte[] { 'A', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
			'n', 'o', 'p' };

	public static String encrypt(String Data) throws Exception {
		Key key = generateKey();
		Cipher c = Cipher.getInstance(ALGO);
		c.init(Cipher.ENCRYPT_MODE, key);
		byte[] encVal = c.doFinal(Data.getBytes());
		String encryptedValue = Base64.encodeBase64String(encVal);
		return encryptedValue;
	}

	private static Key generateKey() throws Exception {
		Key key = new SecretKeySpec(keyValue, ALGO);
		return key;
	}

	@Override
	public RedirectView getusername(HttpSession session) {
		return null;
	}
	@Override
	public ModelAndView logoutSSO(String sessionId,String sub)
	{
		System.out.println("Inside doPost method of Logout Servlet");

		
		String logoutRequestId = UUID.randomUUID().toString();
		String clientId = env.getProperty("ePrammanService");
		String iss = "ePramaan";

		String redirectUrl =  env.getProperty("forlogin");
		
		String inputValue = ""+clientId+sessionId+iss+logoutRequestId+sub+redirectUrl;
		
		String hmac = EpramaanOIDCConnector.hashHMACHex(logoutRequestId, inputValue);
		String customParameter = "";
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		
		String url = "https://epramaan.meripehchaan.gov.in/openid/jwt/processOIDCSLORequest.do";
		String data = "{\"clientId\":\""+env.getProperty("ePrammanService")+"\",\"sessionId\":\""+sessionId+"\",\"hmac\":\""+hmac+"\",\"iss\":\"ePramaan\",\"logoutRequestId\":\""+logoutRequestId+"\",\"sub\":\""+sub+"\",\"redirectUrl\":\""+redirectUrl+"\",\"customParameter\":\""+customParameter+"\"}";

//		System.out.println("json string of data: "+ data);

		
		req.setAttribute("redirectionURL", url);
		req.setAttribute("data", data);
		RequestDispatcher dispatcher;
		
		return new ModelAndView("post2");
	}

	@Override
	public Long applkexpiryalert(String appcode) {
	
		return userDao.applkexpiryalert(appcode);
	}
}