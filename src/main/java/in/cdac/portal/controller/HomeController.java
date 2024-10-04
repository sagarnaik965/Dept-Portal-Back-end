package in.cdac.portal.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import cn.apiclub.captcha.Captcha;
import in.cdac.portal.ePramaanPlugIn.EpramaanOIDCConnector;
import in.cdac.portal.entities.SSOSession;
import in.cdac.portal.entities.UserSession;
import in.cdac.portal.modal.Constant;
import in.cdac.portal.services.DashboardService;
import in.cdac.portal.services.UserService;

@CrossOrigin(origins = { "*" })
@Controller
@RequestMapping("/")
@SessionAttributes({ "captchaValue" })
public class HomeController {

	@Autowired
	UserService userService;

	@Autowired
	DashboardService dashService;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	@Qualifier("test_ds")
	DataSource ds1;

	@Autowired
	@Qualifier("preproduction_ds")
	DataSource ds2;

	@Autowired
	@Qualifier("production_ds")
	DataSource ds3;

	@Autowired
	Environment env;

	@Autowired
	ServletContext context;

	@Autowired
	RedirectView redirect;
	@Autowired
	HttpServletRequest req;
	@Autowired
	HttpServletResponse resp;

	@PostMapping(path = "/isSessNull")
	@JsonManagedReference
	public ResponseEntity<String> isSessNull(@RequestBody String username) {

		return userService.isSessNull(username);

	}

	@RequestMapping(value = { "/", "home" }, method = RequestMethod.GET)
	public RedirectView getHome(HttpSession session) {

		return userService.getHome(session);

	}

	@RequestMapping(value = "/login")
	public RedirectView getLogin(@RequestParam(required = false) String authfailed, String logout, String denied,
			String captchainvalid, String disabled) {

		return userService.getLogin(authfailed, logout, denied, captchainvalid, disabled);

	}

	@RequestMapping(value = "captcha.png", method = RequestMethod.GET)
	public void captcha(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
		Captcha captcha = new Captcha.Builder(152, 47).addText().addBackground().addNoise().gimp().build();
		model.addAttribute(Constant.CAPTCHA_NAME, captcha.getAnswer());
		BufferedImage image = captcha.getImage();
		ServletOutputStream servletOutputStream = response.getOutputStream();
		ImageIO.write(image, "PNG", servletOutputStream);
	}

	@PostMapping(path = "/printUS")
	public ModelAndView printUS(@RequestBody String req) {
//		System.out.println("post2 get " + req);
		return new ModelAndView("post2");
	}
	@GetMapping(path = "/Logout")
	public ModelAndView redirect()
	{
		return new ModelAndView("login");
	}

	@GetMapping(path = "/Logout/{user}")
	public ModelAndView postSSO(@PathVariable("user") String user) throws ServletException, IOException {
		System.out.println("Inside doPost method of Logout Servlet " + user);
		HttpSession session1 = req.getSession(false);
		try {
			SSOSession u = UserSession.currSession.get(user);
		} catch (NullPointerException e) {
			return new ModelAndView("login");
		}

		String logoutRequestId = UUID.randomUUID().toString();

		String clientId = env.getProperty("ePrammanService");
	String sessionId = "";
		try {
			sessionId = UserSession.currSession.get(user).getSessId();
		} catch (NullPointerException e) {
			return new ModelAndView("login");
		}
		String iss = "ePramaan";
		String sub = UserSession.currSession.get(user).getSub();
		UserSession.currSession.remove(user);
		SecurityContextHolder.clearContext();
		String redirectUrl = env.getProperty("forlogin");

		String inputValue = "" + clientId + sessionId + iss + logoutRequestId + sub + redirectUrl;

		String hmac = EpramaanOIDCConnector.hashHMACHex(logoutRequestId, inputValue);
		String customParameter = "";
		// RestTemplate restTemplate = new RestTemplate();
		// HttpHeaders headers = new HttpHeaders();

		String url = env.getProperty("path.logout");
		String data = "{\"clientId\":\"" + env.getProperty("ePrammanService") + "\",\"sessionId\":\"" + sessionId
				+ "\",\"hmac\":\"" + hmac + "\",\"iss\":\"ePramaan\",\"logoutRequestId\":\"" + logoutRequestId
				+ "\",\"sub\":\"" + sub + "\",\"redirectUrl\":\"" + redirectUrl + "\",\"customParameter\":\""
				+ customParameter + "\"}";

		req.setAttribute("redirectionURL", url);
		req.setAttribute("data", data);
		RequestDispatcher dispatcher;

		return new ModelAndView("post2");
	}

}
