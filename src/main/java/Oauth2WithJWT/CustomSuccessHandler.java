package Oauth2WithJWT;

//import jakarta.ws.rs.GET;
//import jakarta.ws.rs.Path;
//import jakarta.ws.rs.Produces;
//import jakarta.ws.rs.core.MediaType;
//
//@Path("hello")
//public class CustomSuccessHandler {
//	@GET
//	@Produces(MediaType.APPLICATION_JSON)
//	public String name() {
//		return "Hello";
//	}
//}
import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.DefaultRedirectStrategy;



public abstract class CustomSuccessHandler implements Authentication{

	
	JwtGeneratorValidator jwtgenval;
	
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		String redirectUrl = null;
		if(authentication.getPrincipal() instanceof DefaultOAuth2User) {
		DefaultOAuth2User  userDetails = (DefaultOAuth2User ) authentication.getPrincipal();
         String username = userDetails.getAttribute("email") !=null?userDetails.getAttribute("email"):userDetails.getAttribute("login")+"@gmail.com" ;
         if(username != null) {
        	 String token = jwtgenval.generateToken(username); 
             Date expiryDate = new Date(System.currentTimeMillis() + TimeUnit.DAYS.toMillis(7));
             Token tokenDetails = new Token(token, expiryDate);

        	 ((OAuth2AuthenticationToken) authentication).setDetails(tokenDetails);
        	 redirectUrl = "/dashboard";
         }
		}  
		new DefaultRedirectStrategy().sendRedirect(request, response, redirectUrl);
	}
}
