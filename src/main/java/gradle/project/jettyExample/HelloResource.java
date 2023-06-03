package gradle.project.jettyExample;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

import com.google.protobuf.DescriptorProtos.GeneratedCodeInfo.Annotation;

import clojure.asm.Type;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.ext.MessageBodyWriter;
import jakarta.ws.rs.ext.Provider;

@Path("/hello")
public class HelloResource {

	public static class Greeting {
		private String message;

		public Greeting() {
		}

		public Greeting(String message) {
			this.message = message;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}
	}

	@GET
	@Path("/{param}")
	@Produces(MediaType.APPLICATION_JSON)
	public Greeting hello(@PathParam("param") String name) {
		return new Greeting("Hello " + name);
	}

	@Provider
	@Produces(MediaType.TEXT_PLAIN)
	public static abstract class GreetingMessageBodyWriter implements MessageBodyWriter<Greeting> {

		public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
			return type == Greeting.class;
		}

		public void writeTo(Greeting greeting, Class<?> type, Type genericType, Annotation[] annotations,
				MediaType mediaType, MultivaluedMap<String, Object> httpHeaders, OutputStream out)
				throws IOException, WebApplicationException {
			out.write(greeting.getMessage().getBytes(StandardCharsets.UTF_8));
			out.flush();
		}
	}

	@GET
	@Path("/{param}.txt")
	@Produces(MediaType.TEXT_PLAIN)
	public Greeting helloText(@PathParam("param") String name) {
		return new Greeting("Hello " + name);
	}

}