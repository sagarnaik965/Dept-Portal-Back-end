package in.cdac.portal.ePramaanPlugIn;

import java.io.FileInputStream;
import java.net.URI;
import java.security.Key;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.interfaces.RSAPublicKey;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.net.ssl.SSLContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.nimbusds.jose.JWEObject;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.AESDecrypter;
import com.nimbusds.jose.crypto.RSASSAVerifier;
import com.nimbusds.jwt.SignedJWT;
import com.nimbusds.oauth2.sdk.AuthorizationCode;
import com.nimbusds.oauth2.sdk.AuthorizationCodeGrant;
import com.nimbusds.oauth2.sdk.Scope;
import com.nimbusds.oauth2.sdk.TokenRequest;
import com.nimbusds.oauth2.sdk.id.ClientID;
import com.nimbusds.oauth2.sdk.pkce.CodeVerifier;
import com.nimbusds.openid.connect.sdk.AuthenticationRequest;
import com.nimbusds.openid.connect.sdk.Nonce;
import com.nimbusds.openid.connect.sdk.OIDCScopeValue;

import in.cdac.portal.entities.SSOSession;
import in.cdac.portal.entities.UserSession;
import io.jsonwebtoken.io.IOException;

@RestController
public class EpramaanController {

	@Autowired
	Environment env;
	public static CodeVerifier codeVerifier = new CodeVerifier();
	public static Nonce nonceValue = new Nonce();
	RequestDispatcher dispatcher;
	boolean status = false;
 public static String certificatePath="";

	private static final String ALGO = "AES";
	private static final byte[] keyValue = new byte[] { 'A', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
			'n', 'o', 'p' };

	@RequestMapping(value = "/Demo", method = RequestMethod.GET)
	public ModelAndView pagePost(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			ModelMap map) throws ServletException, IOException, java.io.IOException {
		//System.out.println("into the DEMO  ...");
		certificatePath=env.getProperty("pathOfePramaanCertificatePath");
		String redirectionURL = null;
		String[] scope = { "OpenId" };
		// OIDCRequest
		// ServiceId

	//	System.out.println("codeVerifier: " + codeVerifier.getValue());
		// Nonce nonce = new Nonce();
		//	System.out.println("Nonce: " + nonceValue.getValue());

		EpramaanOIDCConnector eoidc = new EpramaanOIDCConnector();
		// request.getSession().setAttribute("nonceValue", nonce.getValue());
//		request.getSession().setAttribute("codeVerifier", codeVerifier.getValue());
//		System.out.println("Nonce Value: "+ nonce);
		AuthenticationRequest grantRequest = null;

		try {
//				grantRequest = eoidc.createOIDCAuthGrantRequest("100000913", scope,"https://epstg.meripehchaan.gov.in/OIDCClientOtv/UDemo","https://epstg.meripehchaan.gov.in/openid/jwt/processJwtAuthGrantRequest.do",codeVerifier,nonceValue);
			grantRequest = eoidc.createOIDCAuthGrantRequest(env.getProperty("ePrammanService"), scope,
					env.getProperty("ePramaanSecondControl"),
					"https://epramaan.meripehchaan.gov.in/openid/jwt/processJwtAuthGrantRequest.do", codeVerifier,
					nonceValue);

		} catch (Exception e) {
			e.printStackTrace();
		}
		//////
//			RestTemplate rt = new RestTemplate();
//			//String [] arr= {appcode,first,last};
//			String auCode=rt.postForObject(grantRequest.toQueryString(),null,String.class);
//			System.out.println("authCODE "+auCode);
		////////

		request.getSession().setAttribute("codeVerifier", codeVerifier);
		//	System.out.println("apiHmac from Demo class: " + eoidc.apiHmac);
		redirectionURL = grantRequest.toURI().toString() + "&apiHmac=" + eoidc.apiHmac;
		//	System.out.println("redirectionURL: " + redirectionURL);
		request.setAttribute("redirectionURL", redirectionURL);
		return new ModelAndView("post");

	}
//
//	@GetMapping("/NDemo")
//	public String doGetDemo(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException, java.io.IOException {
//
//		//	System.out.println("into the DEMO  ...");
//		String redirectionURL = null;
//		String[] scope = { "OpenId" };
//		// OIDCRequest
//		// ServiceId
//
//		System.out.println("codeVerifier: " + codeVerifier.getValue());
//		// Nonce nonce = new Nonce();
//		System.out.println("Nonce: " + nonceValue.getValue());
//
//		EpramaanOIDCConnector eoidc = new EpramaanOIDCConnector();
//		// request.getSession().setAttribute("nonceValue", nonce.getValue());
////		request.getSession().setAttribute("codeVerifier", codeVerifier.getValue());
////		System.out.println("Nonce Value: "+ nonce);
//		AuthenticationRequest grantRequest = null;
//
//		try {
////				grantRequest = eoidc.createOIDCAuthGrantRequest("100000913", scope,"https://epstg.meripehchaan.gov.in/OIDCClientOtv/UDemo","https://epstg.meripehchaan.gov.in/openid/jwt/processJwtAuthGrantRequest.do",codeVerifier,nonceValue);
//			grantRequest = eoidc.createOIDCAuthGrantRequest(env.getProperty("ePrammanService"), scope,
//					env.getProperty("ePramaanSecondControl"),
//					"https://epramaan.meripehchaan.gov.in/openid/jwt/processJwtAuthGrantRequest.do", codeVerifier,
//					nonceValue);
//
//		} catch (JOSEException e) {
//			e.printStackTrace();
//		}
//		//////
////			RestTemplate rt = new RestTemplate();
////			//String [] arr= {appcode,first,last};
////			String auCode=rt.postForObject(grantRequest.toQueryString(),null,String.class);
////			System.out.println("authCODE "+auCode);
//		////////
//
//		request.getSession().setAttribute("codeVerifier", codeVerifier);
//		System.out.println("apiHmac from Demo class: " + eoidc.apiHmac);
//		redirectionURL = grantRequest.toURI().toString() + "&apiHmac=" + eoidc.apiHmac;
//		System.out.println("redirectionURL: " + redirectionURL);
//		request.setAttribute("redirectionURL", redirectionURL);
////		
////		  dispatcher = request.getRequestDispatcher("post.jsp");
////		  dispatcher.forward(request, response);
//
//		return "post";
//
//	}

	@PostMapping("/UDemo")
	public ModelAndView doPostUD(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
System.out.println("in UDEMO entered ");
		//	System.out.println("***********Inside doPost method of UDemo*************");

		String authCode = request.getParameter("code");
		String state = request.getParameter("state");
		String error = request.getParameter("error");
		String errorDesc = request.getParameter("error_description");
		String errorUri = request.getParameter("error_uri");
		// String filepath =
		// "C:/Users/CDAC-HP73/Documents/JWTCertificate/SelfSignedCertificate/FromNIC/Final/privateKey.der";
		if (error != null) {
			request.setAttribute("JWSClaimset", error + " : " + errorDesc);
			RequestDispatcher dispatcher;
			// dispatcher = request.getRequestDispatcher("Claimset.jsp");
//			try {
//				//dispatcher.forward(request, response);
//			} catch (ServletException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (java.io.IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}

		} else {

			PublicKey publicKey = this.getPublicKey();

			//	System.out.println("publicKey=" + publicKey);

			//	System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&");
			//	System.out.println("authCode: " + authCode);
			//	System.out.println("state: " + state);

			CodeVerifier codeVerifier = this.codeVerifier;
			// CodeVerifier codeVerifier = (CodeVerifier)
			// request.getSession().getAttribute("codeVerifier");
			// System.out.println("codeVerifier in UDemo from session: "+ codeVerifier);
			TokenRequest tokenRequest = createOIDCTokenRequest(env.getProperty("ePramaanSecondControl"), authCode,
					env.getProperty("ePrammanService"),
					"https://epramaan.meripehchaan.gov.in/openid/jwt/processJwtTokenRequest.do", codeVerifier);
//		TokenRequest tokenRequest = createOIDCTokenRequest("http://localhost:8080/OIDCClient/UDemo", authCode, "100000902", "https://localhost:8080/openid/jwt/processJwtTokenRequest.do",codeVerifier);

			//	System.out.println("tokenRequest: " + tokenRequest);
			//	System.out.println(tokenRequest.toHTTPRequest().getQueryParameters());

			TrustStrategy acceptingTrustStrategy = (X509Certificate[] chain, String authType) -> true;

			SSLContext sslContext;
			try {
				sslContext = org.apache.http.ssl.SSLContexts.custom().loadTrustMaterial(null, acceptingTrustStrategy)
						.build();

				SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext);

				CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(csf).build();

				HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();

				requestFactory.setHttpClient(httpClient);
				RestTemplate restTemplate = new RestTemplate(requestFactory);
//			List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();        
//			//Add the Jackson Message converter
//			MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
//
//			// Note: here we are making this converter to process any kind of response, 
//			// not only application/*json, which is the default behaviour
////			converter.setSupportedMediaTypes(Collections.singletonList(MediaType.APPLICATION_JSON));        
//			messageConverters.add(converter);  
//			restTemplate.setMessageConverters(messageConverters);

//			HttpHeaders headers = new HttpHeaders();
//			headers.setContentType(MediaType.APPLICATION_JSON);
//			HttpEntity<String> entity = new HttpEntity<String>(headers);
				final String url = "https://epramaan.meripehchaan.gov.in/openid/jwt/processJwtTokenRequest.do";
				String authResp = restTemplate.postForObject(url, tokenRequest.toHTTPRequest().getQueryParameters(),
						String.class);
				//	System.out.println("authResp: " + authResp);
				HttpSession session = request.getSession(true);
				//	System.out.println((request == null) + "req");
				//	System.out.println((session == null) + "sess");
				session.setAttribute("authResponse", authResp);
				// decrypting the authResp
				//System.out.println("*****decrypting JWE using AES 256****************");
				String seed = env.getProperty("aes");
//86196f2e-4e3d-486e-9f2d-14fe6b8e130a
				//System.out.println("*************decrypting JWE using NonceValue****************");

				SecretKeySpec secretKeySpec = (SecretKeySpec) generateAES256Key(nonceValue.getValue());

				//System.out.println("-----------NonceValue in UDemo--------->" + nonceValue);

				JWEObject jweObject = JWEObject.parse(authResp);
				//System.out.println("jweObject after parse method: " + jweObject.toString());

				jweObject.decrypt(new AESDecrypter(secretKeySpec));
				//System.out.println("jweObject after decrypt method: " + jweObject.toString());

				//System.out.println("jweObject payload: " + jweObject.getPayload());
				//System.out.println("jweObject parsedString: " + jweObject.getParsedString());
				//System.out.println("jweObject serialize: " + jweObject.serialize());
				//System.out.println("jweObject parsedParts: " + jweObject.getParsedParts());

				SignedJWT signedJWT = jweObject.getPayload().toSignedJWT();

				//System.out.println("signedJWT: " + signedJWT);

				//	System.out.println("RSAPublickey retrieved. Proceeding with verification. ");

				// Verifying decrypted authResp
				JWSVerifier jwsVerifier = new RSASSAVerifier((RSAPublicKey) this.getPublicKey());

				// Check the signature
				boolean signatureVerified = signedJWT.verify(jwsVerifier);

				// Do something useful with the result of signature verification
				//System.out.println("JWS Signature is valid: " + signatureVerified);
				// Do something useful with the content
				//System.out.println("JWS Claim Set: " + signedJWT.getPayload().toJSONObject());

				//System.out.println("username : " + signedJWT.getPayload().toJSONObject().get("username"));
				request.setAttribute("JWSClaimset", signedJWT.getPayload().toJSONObject());
				request.setAttribute("tokenRequestParameters", tokenRequest.toHTTPRequest().getQueryParameters());
				request.setAttribute("tokenRequestUrl", url);
				RequestDispatcher dispatcher;

				try {
					String username = (String) signedJWT.getPayload().toJSONObject().get("service_user_id");
					SSOSession sess = new SSOSession();
					sess.setSessId((String) signedJWT.getPayload().toJSONObject().get("session_id"));
					sess.setSub((String) signedJWT.getPayload().toJSONObject().get("sub"));
					UserSession.setCurrSession(username, sess);
					String urlReact = "";

					String inp = "";

					try {
						inp = encrypt(username);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					inp = inp.replaceAll("/", "slashinurl");
					inp = inp.replaceAll("\\+", "plusinurl");

					RedirectView newr = new RedirectView();
					if (UserSession.currSession.containsKey(username)) {
						urlReact = env.getProperty("templink") + inp + "";
						System.out.println(urlReact);
						// newr.setUrl(PortalConstant.TEMPLINK );
						request.setAttribute("urlReact", urlReact);

					}

				} catch (Exception e) {
					// TODO Auto-generated catch block
					//	System.out.println("in EXCEPTION");
					e.printStackTrace();
					return new ModelAndView("login");
				}

//				dispatcher = request.getRequestDispatcher("Claimset.jsp");
//				try {
//					dispatcher.forward(request, response);
//				} catch (ServletException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				} catch (java.io.IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}

//		         signedJWT.getJWTClaimsSet();

				// AuthenticationResponse authResp= restTemplate.excha

			} catch (KeyManagementException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (KeyStoreException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

//
//		ResponseEntity<String> responseData= restTemplate.exchange(url,HttpMethod.GET,entity,String.class);

//		if(token == null) {
//			RequestDispatcher dispatcher;
//			dispatcher = request.getRequestDispatcher("errorPage.jsp");
//			dispatcher.forward(request, response);
//		}else {
//		Jwt parsedToken = UDemo.verifyToken(token, key);
//		request.setAttribute("token", parsedToken.getBody());

//		Jwt parsedToken = UDemo.verifyToken(token, publicKey);
//		String parsedToken = extractToken(token);
		// Gson gson = new Gson();
		/*
		 * JWTTokenData data = null; JwtClaims claims = null;
		 * 
		 * try { claims = JwtClaims.parse(parsedToken);
		 * if(claims.getIssuer().equals("ePramaan")) {
		 * 
		 * System.out.println("claims after parsing" + claims.toJson());
		 * System.out.println("claims tostring after parsing" + claims.toString());
		 * String dataAsString = claims.getClaimValueAsString("data");
		 * System.out.println("data claim in string format :" + dataAsString);
		 * 
		 * 
		 * data = gson.fromJson(dataAsString, JWTTokenData.class);
		 * System.out.println("******tokenDataObj = " + data); }else { HashMap<String,
		 * String> hmpData= (HashMap<String, String>) claims.getClaimValue("data");
		 * System.out.println(gson.toJson(hmpData)); data =
		 * gson.fromJson(gson.toJson(hmpData), JWTTokenData.class);
		 * 
		 * }
		 */
		/*
		 * System.out.println(parsedToken);
		 * 
		 * 
		 * JWTToken tokenObj = gson.fromJson(parsedToken, JWTToken.class);
		 * 
		 * // JWTTokenData data = gson.fromJson(tokenObj.getData(),JWTTokenData.class);
		 * request.setAttribute("token", token); request.setAttribute("parsedToken",
		 * parsedToken); //request.setAttribute("token", tokenObj);
		 * 
		 * request.setAttribute("iss", tokenObj.getIss()); request.setAttribute("aud",
		 * tokenObj.getAud()); request.setAttribute("exp", tokenObj.getExp());
		 * request.setAttribute("sub", tokenObj.getSub()); request.setAttribute("iat",
		 * tokenObj.getIat()); request.setAttribute("preferred_username",
		 * tokenObj.getPreferred_username()); request.setAttribute("given_name",
		 * tokenObj.getGiven_name()); request.setAttribute("birthdate",
		 * tokenObj.getBirthdate()); request.setAttribute("email", tokenObj.getEmail());
		 * request.setAttribute("phone_number", tokenObj.getPhone_number());
		 * request.setAttribute("jti", tokenObj.getJti());
		 * 
		 * RequestDispatcher dispatcher; dispatcher =
		 * request.getRequestDispatcher("JWTTokenJ.jsp"); dispatcher.forward(request,
		 * response);
		 * 
		 * }
		 */
		//System.out.println("CLAIM SET ");
		return new ModelAndView("help");

	}

	public Key generateAES256Key(String seed) {
		//	System.out.println(seed);
		MessageDigest sha256 = null;
		try {
			sha256 = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		byte[] passBytes = seed.getBytes();
		//System.out.println(passBytes.length);
		byte[] passHash = sha256.digest(passBytes);
		//System.out.println(passHash.length);
		SecretKeySpec secretKeySpec = new SecretKeySpec(passHash, "AES");

		return secretKeySpec;
	}

	public static PublicKey getPublicKey() {
		try {
			String filepath = certificatePath;

			CertificateFactory certFac = CertificateFactory.getInstance("X.509");

			//	System.out.println(filepath);
			FileInputStream fis = new FileInputStream(filepath);

			X509Certificate cer = (X509Certificate) certFac.generateCertificate(fis);
			PublicKey publicKey = cer.getPublicKey();
			return publicKey;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

// Use this method for NSSO client	
	private TokenRequest createOIDCTokenRequest(String callbackURL, String authCode, String serviceId,
			String endPointURL, CodeVerifier codeVerifier) {

// Construct the code grant from the code obtained from the authz
// endpoint
// and the original callback URI used at the authz endpoint
		AuthorizationCode code = new AuthorizationCode(authCode);
		URI callback = URI.create(callbackURL);
//AuthorizationGrant codeGrant = new AuthorizationCodeGrant(code,
//      callback);

		AuthorizationCodeGrant codeGrant = new AuthorizationCodeGrant(code, callback, codeVerifier);

// The credentials to authenticate the client at the token endpoint
		ClientID clientID = new ClientID(serviceId);

// The token endpoint
		URI tokenEndpoint = URI.create(endPointURL);

// Make the token request
//TokenRequest request = new TokenRequest(tokenEndpoint, clientID,
//      codeGrant);
		Scope scope = new Scope();
		scope.add(OIDCScopeValue.OPENID);

		Map<String, List<String>> map = new HashMap<>();

		List<String> arr = new ArrayList<>();

		arr.add("clientSecret");

		map.put("params", arr);

		TokenRequest request = new TokenRequest(tokenEndpoint, clientID, codeGrant, scope, null, null, map);

		return request;
	}

	@GetMapping("/page")
	public ModelAndView page() {
		//System.out.println("in PAGE ");
		return new ModelAndView("post");
	}

	public static String encrypt(String Data) throws Exception {
		Key key = generateKey();
		Cipher c = Cipher.getInstance(ALGO);
		c.init(Cipher.ENCRYPT_MODE, key);
		//System.out.println("data to encrypt " + Data);
		byte[] encVal = c.doFinal(Data.getBytes());
		String encryptedValue = Base64.encodeBase64String(encVal);
		return encryptedValue;
	}

	private static Key generateKey() throws Exception {
		Key key = new SecretKeySpec(keyValue, ALGO);
		return key;
	}
}
