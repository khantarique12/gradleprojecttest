package Oauth2WithJWT;

import org.eclipse.jetty.server.Authentication;
import ch.qos.logback.core.model.Model;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.SecurityContext;


@Path("/dashboard")
public class DashboardController {

	JwtGeneratorValidator jwtgenval;

	public String name;
	
	ValidationHelper validationHelper = new ValidationHelper();

	@GET
	public String displayDashboard(Authentication request, Model model) {
		if (validationHelper.validateReturn(request)) {
			SecurityContext securityContext = SecurityContextHolder.getContext();
			if (securityContext.getAuthentication().getPrincipal() instanceof DefaultOAuth2User) {
				DefaultOAuth2User user = (DefaultOAuth2User) securityContext.getAuthentication().getPrincipal();
				model.addAttribute("userDetails",
						user.getAttribute("name") != null ? user.getAttribute("name") : user.getAttribute("login"));
//				model.addAttribute("userDetails",
//						user.getAttribute("email") != null ? user.getAttribute("email") : user.getAttribute("login"));
				name = user.getAttribute("name");
			}
				return "dashboard";
		} else {
			return "redirect:/login";
		}
		
	}
}
