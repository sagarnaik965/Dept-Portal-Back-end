package in.cdac.portal.ePramaanPlugIn;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.UUID;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import com.nimbusds.oauth2.sdk.ResponseType;
import com.nimbusds.oauth2.sdk.Scope;
import com.nimbusds.oauth2.sdk.id.ClientID;
import com.nimbusds.oauth2.sdk.id.State;
import com.nimbusds.oauth2.sdk.pkce.CodeChallenge;
import com.nimbusds.oauth2.sdk.pkce.CodeChallengeMethod;
import com.nimbusds.oauth2.sdk.pkce.CodeVerifier;
import com.nimbusds.openid.connect.sdk.AuthenticationRequest;
import com.nimbusds.openid.connect.sdk.Nonce;
import com.nimbusds.openid.connect.sdk.OIDCScopeValue;


public class EpramaanOIDCConnector {
//	 @Resource	 
//	public Environment env;
	
	@Autowired
	public Environment env;
	
//	 -----------------prod-------------------------------------------
//	public  String aes="86196f2e-4e3d-486e-9f2d-14fe6b8e130a" ;
	
//	 -----------------local-------------------------------------------
	 public  String aes="804c467d-f5cd-4219-8168-571681cfa42b";
	
	
	
	
//	804c467d-f5cd-4219-8168-571681cfa42b
	public  String salt;
	//public static String aesKey = env.getProperty("aes");
	//public static String salt = env.getProperty("salt");
	AuthenticationRequest request;
	String apiHmac;

	
public static String hashHMACHex(String hMACKey, String inputValue) {
        
        System.out.println("InputValue: "+inputValue);
        System.out.println("HMAC Key: "+hMACKey);
        
        
            byte[] keyByte = hMACKey.getBytes(StandardCharsets.US_ASCII);
            byte[] messageBytes = inputValue.getBytes(StandardCharsets.US_ASCII);
            
            Mac sha256_HMAC = null;
			try {
				sha256_HMAC = Mac.getInstance("HmacSHA256");
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            SecretKeySpec secret_key = new SecretKeySpec(keyByte, "HmacSHA256");
            try {
				sha256_HMAC.init(secret_key);
			} catch (InvalidKeyException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            
            System.out.println("Secret key generated is "+secret_key);
            return Base64.getUrlEncoder().encodeToString(sha256_HMAC.doFinal(messageBytes));
            
         

    }
public AuthenticationRequest createOIDCAuthGrantRequest(String serviceId,
        String[] requestedScopes, String callbackURL, String endpointUrl, CodeVerifier codeVerifier, Nonce nonce ) throws Exception {
	
	Scope scope = new Scope();
	scope.add(OIDCScopeValue.OPENID);
	
    // Build the request
//    AuthorizationRequest request = new AuthorizationRequest.Builder(
//    		
//            new ResponseType(ResponseType.Value.CODE),
//            new ClientID(serviceId))
//                    // TODO: This needs to be expanded for utilising during
//                    // consent management and token customization
//                    .scope(scope)
//                    .state(new State(UUID.randomUUID().toString()))
//                    .redirectionURI(URI.create(callbackURL))
//                    // .customParameter("resource",
//                    // "https://example.com/resource")
//                    .endpointURI(URI.create(endpointUrl))
//                    .codeChallenge(codeVerifier, CodeChallengeMethod.S256)
//                    .build();
	
//	List<ACR> list = new ArrayList<>();
//	list.add(0,"password");
	
	//System.out.println(list);
	
	ResponseType responseType = new ResponseType("code");
	
			request = new AuthenticationRequest.Builder(
			URI.create(endpointUrl),
            new ClientID(serviceId))
            // TODO: This needs to be expanded for utilising during
            // consent management and token customization
            .scope(scope)
            .state(new State(UUID.randomUUID().toString()))
            .redirectionURI(URI.create(callbackURL))
            .endpointURI(URI.create(endpointUrl))
            .codeChallenge(codeVerifier, CodeChallengeMethod.S256)
			.nonce(nonce)
			.responseType(responseType)
			.build();
		
			State stateID = request.getState();
			URI redirectionURI = request.getRedirectionURI();
			scope = request.getScope();
			CodeChallenge codeChallenge = request.getCodeChallenge();
			String inputValue = ""+serviceId+aes+stateID+nonce+redirectionURI+scope+codeChallenge;
			
			apiHmac = hashHMACHex(aes, inputValue);
			
	
	
	
    /*
     * // Getting a custom parameter String resource =
     * request.getCustomParameter("resource");
     * 
     * // Getting a map of all custom parameters Map<String,String>
     * customParams = request.getCustomParameters();
     */
    System.out.println("request: "+request);
    System.out.println("apiHmac: "+apiHmac);
    return request;
    
   
}




}
