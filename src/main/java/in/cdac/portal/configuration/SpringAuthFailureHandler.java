package in.cdac.portal.configuration;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import org.apache.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

public class SpringAuthFailureHandler extends SimpleUrlAuthenticationFailureHandler {

//	private final static Logger logger = Logger.getLogger(SpringAuthFailureHandler.class);
	 private final  static Logger logger = LogManager.getLogger( SpringAuthFailureHandler.class );

	private String defaultFailureUrl;

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {

		logger.info("onAuthenticationFailure");
		String invalidCaptcha = (String) request.getAttribute("invalidcaptcha");

		if (invalidCaptcha == null) {
			invalidCaptcha = "";
		}
		invalidCaptcha = invalidCaptcha.trim();
		if (invalidCaptcha.equals("invalidcaptcha")) {
			logger.info("Invalid Captcha attribute received");
			request.removeAttribute("invalidcaptcha");
			getRedirectStrategy().sendRedirect(request, response, "/login?captchainvalid=captchainvalid");
		} else if (exception.getClass().isAssignableFrom(DisabledException.class)) {
			logger.info("in disabledException");
			getRedirectStrategy().sendRedirect(request, response, "/login?disabled=disabled");
		} else {
			logger.info("Invalid password attribute received");
			getRedirectStrategy().sendRedirect(request, response, defaultFailureUrl);
		}

	}

	public String getDefaultFailureUrl() {
		return defaultFailureUrl;
	}

	@Override
	public void setDefaultFailureUrl(String defaultFailureUrl) {
		this.defaultFailureUrl = defaultFailureUrl;
	}

}
