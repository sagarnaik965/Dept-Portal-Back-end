package in.cdac.portal.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.filter.OncePerRequestFilter;

public class CaptchaCaptureFilter extends OncePerRequestFilter {

	private final String CAPTCHA_CHALLENGE_FIELD = "captchaValue";
	private final String CAPTCHA_RESPONSE_FIELD = "captcha";
//	private final static Logger logger = Logger.getLogger(CaptchaCaptureFilter.class);
	 private final  static Logger logger = LogManager.getLogger( CaptchaCaptureFilter.class );

	private String responseCaptcha;

	private String captchaChallenge;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		responseCaptcha = obtainCaptchca(request);
		captchaChallenge = (String) request.getSession().getAttribute(CAPTCHA_CHALLENGE_FIELD);
		logger.info("--------------Captcha generated -----: " + responseCaptcha + "   " + captchaChallenge);
		chain.doFilter(request, response);
	}

	public String getResponseCaptcha() {
		return responseCaptcha;
	}

	public void setResponseCaptcha(String responseCaptcha) {
		this.responseCaptcha = responseCaptcha;
	}

	public String getCaptchaChallenge() {
		return captchaChallenge;
	}

	public void setCaptchaChallenge(String captchaChallenge) {
		this.captchaChallenge = captchaChallenge;
	}

	private String obtainCaptchca(HttpServletRequest request) {
		return request.getParameter(CAPTCHA_RESPONSE_FIELD);
	}

}
