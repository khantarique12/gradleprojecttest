package gradle.project.jettyExample;

import java.io.IOException;

import org.eclipse.jetty.http.HttpStatus;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ExampleServlet extends HttpServlet {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		resp.setStatus(HttpStatus.OK_200);
		resp.getWriter().println("Hello!, EmbeddedJetty");
	}
}