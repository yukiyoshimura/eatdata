package resource;

import java.io.IOException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import responseBean.RegistResponseBean;
import responseBean.RespondentBean;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Path("respondent")
public class RespondentResource {

	@Consumes("application/json")
	@Produces("application/json")
    @GET
    public   Response getRespondent(@QueryParam("callback") String callback) throws JsonParseException, JsonMappingException, IOException {
    	System.out.println("regist");
    	System.out.println(callback + "callback");
    	
    	RespondentBean bean = new RespondentBean();
    	ObjectMapper mapper 	= new ObjectMapper();
		
//		RespondentBean bean = mapper.readValue(respondentParams, RespondentBean.class);
		bean.setName("regist test");
		bean.setNo("1");
		
		
		// JavaBeansオブジェクトをJSON文字列へ変換
		String jsonStr = mapper.writeValueAsString(bean);
		
		return Response.ok().entity(callback +  "(" + jsonStr + ")"  ).build();
    	
    	
    }

	@GET
	@Path("world")
	public String say(
		@QueryParam("name") String name) throws JsonProcessingException {
		
			return "Hello, " + name + "!";
	  }
}