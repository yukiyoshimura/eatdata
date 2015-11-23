package resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Path("hello")
public class HelloResource {

    @GET
    public String helloWorld() {
    	System.out.println("hello");
        return "Hello World!!";
    }

	@GET
	@Path("world")
	public String say(
		@QueryParam("name") String name) throws JsonProcessingException {
		
			return "Hello, " + name + "!";
	  }
}