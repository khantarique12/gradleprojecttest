package Oauth2WithJWT;

import org.eclipse.jetty.server.Authentication;
import org.eclipse.jetty.util.annotation.ManagedAttribute;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("/login")
public class LoginController {

	
	JwtGeneratorValidator jwtgenval;

	ValidationHelper validationHelper = new ValidationHelper();

	@ManagedAttribute("user")
	public UserLoginDTO userLoginDTO() {
		return new UserLoginDTO();
	}

	@GET
	public String login(Authentication request) {
		if (validationHelper.validateReturn(request)) {
			return "redirect:/dashboard";
		} else {
			return "login";
		}
	}
	@GET
	@Path("/logout")
	public String logout(Authentication request) {
		validationHelper.clearData(request);
		return "login";
	}

}