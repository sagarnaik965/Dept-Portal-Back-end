package in.cdac.portal.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import in.cdac.dbswitch.CustomAuthenticationToken;

public class CaptchaUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private final String CAPTCHA_CHALLENGE_FIELD = "captchaValue";
	private final String CAPTCHA_RESPONSE_FIELD = "captcha";
    public static final String SPRING_SECURITY_FORM_TENANT_NAME_KEY = "tenant";

//	private final static Logger logger = Logger.getLogger(CaptchaUsernamePasswordAuthenticationFilter.class);
    private final  static Logger logger = LogManager.getLogger( CaptchaUsernamePasswordAuthenticationFilter.class );
	private boolean postOnly = true;

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {

		if (postOnly && !request.getMethod().equals("POST")) {
			throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
		}

		String username = obtainUsername(request);
		String password = obtainPassword(request);
		String responseCaptcha = obtainCaptchca(request);
		String tenant=obtainTenanat(request);
		if (username == null) {
			username = "";
		}

		if (password == null) {
			password = "";
		}

		if (responseCaptcha == null) {
			responseCaptcha = "";
		}
		if(tenant==null) {
			tenant="";
		}
		
		username = username.trim();

		responseCaptcha = responseCaptcha.trim();
		HttpSession session = request.getSession();
		String captchaValue = (String) session.getAttribute(CAPTCHA_CHALLENGE_FIELD);

		logger.info(responseCaptcha + " " + captchaValue + "------Captcha Values ");

		/*
		 * #TODO UNCOMMENT THIS SECTION FOR CAPTCHA
		 * 
		 */
		if (!responseCaptcha.equals(captchaValue)) {
			request.setAttribute("invalidcaptcha", "invalidcaptcha");
			throw new BadCredentialsException("Invalid Captcha");
		}

		CustomAuthenticationToken authRequest = new CustomAuthenticationToken(username, password, tenant);
    	setDetails(request, authRequest);
        return this.getAuthenticationManager().authenticate(authRequest);
	}

	private String obtainCaptchca(HttpServletRequest request) {
		return request.getParameter(CAPTCHA_RESPONSE_FIELD);
	}

	private String obtainTenanat(HttpServletRequest request) {
		return request.getParameter(SPRING_SECURITY_FORM_TENANT_NAME_KEY);
	}
}
