package in.cdac.portal.ePramaanPlugIn;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;

import in.cdac.epramaan.sp.dsig.SamlEncryption;
import io.jsonwebtoken.io.IOException;

@RestController
public class OTVController {

	private static final long serialVersionUID = 1L;
	@Autowired
	Environment env;
//	 -----------------prod-------------------------------------------
//	public  String aes="86196f2e-4e3d-486e-9f2d-14fe6b8e130a" ;
	
//	 -----------------local-------------------------------------------
	 public  String aes="804c467d-f5cd-4219-8168-571681cfa42b";
	 
	 
	public String salt;

	@PostMapping("/loadverification.do")
	public ModelAndView oTv(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("Inside OneTimeVerification doPost()");
		// get the ssotoken info from request parameter from epramaan
		String encrptedSsoToken = request.getParameter("ssoToken");
		System.out.println("**********************************1");
		System.out.println("The encrypted Token is:" + encrptedSsoToken);
		// Get the context

		ServletContext context = request.getSession().getServletContext();

		HttpSession session = request.getSession(false);
		String SSO_Id = executeOneTimeVerficationRequest(session, request, encrptedSsoToken);

		request.setAttribute("SSO_Id", SSO_Id);
//		 String parm = request.getParameter("j_captcha_response");
//	        String captchaKey = (String)session.getAttribute(CaptchaServlet.CAPTCHA_KEY) ;
//
//	        if(parm.equalsIgnoreCase(captchaKey)){
////	true
//	//CODE HERE
//	}
//	else{
//	//faLSE
//	//ERROR HERE 
//	}
		return new ModelAndView("onetimepushback");
	}

	public String executeOneTimeVerficationRequest(HttpSession session, HttpServletRequest request,
			String encrptedSsoToken) {
		System.out.println("Inside executeOneTimeVerficationRequest() in connector");
		try {
			ServletContext context = request.getSession().getServletContext();

			SamlEncryption samlEncryption = new SamlEncryption();
			String decryptedJsonSSOToken = samlEncryption.decrypt(encrptedSsoToken, aes, env.getProperty("salt"));
			System.out.println("Decrypted SSO Token " + decryptedJsonSSOToken);

			ObjectMapper objectMapper = new ObjectMapper();

			FlatToken ssoToken = objectMapper.readValue(decryptedJsonSSOToken, FlatToken.class);

			String sessionIndex = ssoToken.getSession_id().toString();
			System.out.println("End of executeOneTimeVerficationRequest() in connector");
			return ssoToken.getSso_id().toString();

		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
			e.printStackTrace();
			return null;
		}

	}

}
