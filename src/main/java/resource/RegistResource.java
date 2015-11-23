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

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Path("regist")
public class RegistResource {

	@Consumes("application/json")
	@Produces("application/json")
    @POST
    public Response regist(String registParams) throws JsonParseException, JsonMappingException, IOException {
		System.out.println("getparam==>" + registParams);
				
		ObjectMapper mapper 	= new ObjectMapper();
		
		RegistResponseBean bean = mapper.readValue(registParams, RegistResponseBean.class);
		System.out.println(bean.getName());
		System.out.println(bean.getNo());
		
		bean.setName("書き換えテスト");

		
		// JavaBeansオブジェクトをJSON文字列へ変換
		String jsonStr = mapper.writeValueAsString(bean);
		
		return Response.ok().entity(jsonStr).build();
		
    }

}