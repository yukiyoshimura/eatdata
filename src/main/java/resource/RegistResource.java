package resource;

import java.io.IOException;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import responseBean.RegistResponseBean;
import responseBean.TRespondentBean;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Path("regist")
public class RegistResource {

	@Consumes("application/json")
	@Produces("application/json")
    @POST
    public Response regist() throws JsonParseException, JsonMappingException, IOException {
    	System.out.println("星respondent");
//    	System.out.println(callback + "callback");

    	TRespondentBean bean = new TRespondentBean();
    	ObjectMapper mapper 	= new ObjectMapper();
		
//		RespondentBean bean = mapper.readValue(respondentParams, RespondentBean.class);

		
		
		// JavaBeansオブジェクトをJSON文字列へ変換
		String jsonStr = mapper.writeValueAsString(bean);
		
		return Response.ok()
				.header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
				.entity(jsonStr).build();
		
    }

}