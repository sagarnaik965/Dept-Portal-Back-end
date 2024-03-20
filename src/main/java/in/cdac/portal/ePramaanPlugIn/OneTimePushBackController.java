package in.cdac.portal.ePramaanPlugIn;

import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.Calendar;
import java.util.UUID;

import javax.crypto.NoSuchPaddingException;
import javax.net.ssl.SSLContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.TrustStrategy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import in.cdac.dbswitch.CustomUserDetailsService;
import in.cdac.epramaan.sp.dsig.SamlEncryption;
import in.cdac.portal.dao.UserDaoImpl;
import io.jsonwebtoken.io.IOException;

@Controller
public class OneTimePushBackController {
	private static final long serialVersionUID = 1L;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	Environment env;

	@Autowired
	public CustomUserDetailsService cust;
//	private final static Logger logger = Logger.getLogger(OneTimePushBackController.class);
	 private final  static Logger logger = LogManager.getLogger( CustomUserDetailsService.class );

	@PostMapping("/onetimepushback")
	protected ModelAndView doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("Inside OneTimePushBack");

		// read user name and login from SP login page
		String username = request.getParameter("username");
		String password = request.getParameter("userpass");
		String SSO_Id = request.getParameter("SSO_Id");
		// Get the context
		ServletContext context = request.getSession().getServletContext();

		// take username and pwd from db and verify username and password.

		System.out.println("username is " + username);
		System.out.println("password is " + password);

		String userActualPassWord = cust.matchPass(username);
		System.out.println("actual pass "+userActualPassWord);
		boolean statusVerify = passwordEncoder.matches(password, userActualPassWord);
		System.out.println(statusVerify + " status Verify ");
		if (statusVerify == false) {
			return new ModelAndView("post");
		}
		System.out.println(statusVerify + " status Verify ");
		// System.out.println(passwordEncoder.encode("welcome@123"));
		System.out.println("SSO_Id  " + SSO_Id);
		System.out.println("context  " + context);

		String sslProtocolVersion = "TLSv1.3";

		boolean status = executeOneTimePushBack(request.getSession(false), username, UUID.fromString(SSO_Id),
				sslProtocolVersion);

		System.out.println("executeOneTimePushBack() status: " + status);

		RequestDispatcher dispatcher = null;
		if (status == true) {
			request.setAttribute("msg", "One time verification Successful");
//            dispatcher = request.getRequestDispatcher("otvResponse.jsp");
		} else {
			request.setAttribute("msg", "One time verification Failed");
//            dispatcher = request.getRequestDispatcher("otvResponse.jsp");
		}
//        dispatcher.forward(request, response);

		return new ModelAndView("otvResponse");
//      }else{
//          //handle username password credentials mismatch
//          System.out.println("You cannot enter username other than U001 and U001pwd, for now");
//      }
	}

	public boolean executeOneTimePushBack(HttpSession session, String username, UUID SSO_Id,
			String sslProtocolVersion) {
		System.out.println("Inside executeOneTimePushBack() in connector");

		try {
			System.out.println("One Time Push back");

			String serviceId = env.getProperty("ePrammanService");
			// String ePramaanUrl =
			// "http://epstg.meripehchaan.gov.in/rest/epramaan/enrol/response";
			String ePramaanUrl = "https://epramaan.meripehchaan.gov.in/rest/epramaan/enrol/response";
//          SSOSessionManager sessionManager = (SSOSessionManager) context.getAttribute("SSO_MANAGE");
//          String sessionIndex = sessionManager.getKeyByValue(session);
//          System.out.println("sessionIndex from sessionManager: "+sessionIndex);
//          System.out.println("Session Object in OneTimePushBack: "+session);
//          System.out.println("Session ID in OneTimePushBack: "+sessionManager.toString());

			EnrolSPServiceResponse responseObject = new EnrolSPServiceResponse(SSO_Id, username, true,
					Calendar.getInstance().getTimeInMillis(), Integer.parseInt(serviceId));
			ObjectMapper objectMapper = new ObjectMapper();
			String requestJSON = objectMapper.writeValueAsString(responseObject);
			System.out.println("Request JSOn : " + requestJSON);
			SamlEncryption samlEncryption;
			samlEncryption = new SamlEncryption();
			String encryptedEnrolResp = samlEncryption
					.encrypt(requestJSON, env.getProperty("aes"),env.getProperty("salt")).trim();
			EnrolSPServiceResponseWrapper enrolSPServiceResponseWrapper = new EnrolSPServiceResponseWrapper(
					encryptedEnrolResp, Integer.parseInt(serviceId));

			URL url = new URL(ePramaanUrl);
			System.out.println("Posting to url" + url.toString());
			
			
			TrustStrategy acceptingTrustStrategy = (X509Certificate[] chain, String authType) -> true;
			HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();

			try {		
			
			SSLContext sslContext;
				sslContext = org.apache.http.ssl.SSLContexts.custom().loadTrustMaterial(null, acceptingTrustStrategy)
						.build();

				SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext);
				CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(csf).build();
				requestFactory.setHttpClient(httpClient);
			} catch (Exception e) {
				// TODO: handle exception
				
				
			}			
				RestTemplate restTemplate = new RestTemplate(requestFactory);

//			RestTemplate restTemplate = new RestTemplate();
			String resp = restTemplate.postForObject(ePramaanUrl, enrolSPServiceResponseWrapper, String.class);

			if (resp.equalsIgnoreCase("success")) {
				session.setAttribute("epramaanId", username);
				return true;
			}

		} catch (JsonProcessingException je) {
			je.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
