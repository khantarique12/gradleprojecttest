package gradle.project.jettyserver;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;

@Path("calculator")
public class Calculator {
	// http://localhost:7070/api/calculator/squareroot?input=16

	@GET
	@Path("squareroot")
	@Produces(MediaType.APPLICATION_JSON)
	public Result squareRoot(@QueryParam("input") double input) {
		Result result = new Result("Square Root");
		result.setInput(input);
		result.setOutput(Math.sqrt(result.getInput()));
		return result;
	}

	// http://localhost:7070/api/calculator/square?input=16
	@GET
	@Path("square")
	@Produces(MediaType.APPLICATION_JSON)
	public Result square(@QueryParam("input") double input) {
		Result result = new Result("Square");
		result.setInput(input);
		result.setOutput(result.getInput() * result.getInput());
		return result;
	}

	static class Result {
		double input;
		double output;
		String action;

		public Result() {
		}

		public Result(String action) {
			this.action = action;
		}

		public String getAction() {
			return action;
		}

		public void setAction(String action) {
			this.action = action;
		}

		public double getInput() {
			return input;
		}

		public void setInput(double input) {
			this.input = input;
		}

		public double getOutput() {
			return output;
		}

		public void setOutput(double output) {
			this.output = output;
		}
	}
}