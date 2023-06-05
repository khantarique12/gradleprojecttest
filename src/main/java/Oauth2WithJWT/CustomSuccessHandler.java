package Oauth2WithJWT;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;


@Component
public class CustomSuccessHandler implements AuthenticationSuccessHandler{

	@Autowired
	JwtGeneratorValidator jwtgenval;

	@Override
	public void onAuthenticationSuccess(jakarta.servlet.http.HttpServletRequest request,
			jakarta.servlet.http.HttpServletResponse response, Authentication authentication)
			throws IOException, jakarta.servlet.ServletException {
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
	
