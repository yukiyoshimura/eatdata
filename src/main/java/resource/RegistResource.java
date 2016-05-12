package resource;

import java.io.IOException;
import java.sql.SQLException;
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
import responseBean.WRespondentBean;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mysql.jdbc.Connection;

import connector.MySQLConnector;
import dao.TRespondentDao;
import dao.WRespondentDao;

@Path("regist")
public class RegistResource {

	@Consumes("application/json")
	@Produces("application/json")
    @POST
    public Response regist(WRespondentBean request) throws JsonParseException, JsonMappingException, IOException, ClassNotFoundException, SQLException {
    	System.out.println("regist respondent");
    	
    	System.out.println(request.getEmployeeId());
       	MySQLConnector conn = new MySQLConnector();
       	Connection connection = null;
       	
    	WRespondentDao wDao = new WRespondentDao();
    	TRespondentDao tDao = new TRespondentDao();

    	
    	try {
    		connection = conn.getConnection();	
        	String resultIns = wDao.insertWRespondent(connection, request);
        	String resultUpd = tDao.updateTRespondent(connection, request);
        	
		} catch (SQLException e) {
			System.out.println("SQLError");
			e.printStackTrace();
		} finally {
			//connection close
			if (connection != null){
				conn.closeConn(connection);			
			}
		}
    	

    	
    	TRespondentBean bean = new TRespondentBean();
    	ObjectMapper mapper 	= new ObjectMapper();
		
//		RespondentBean bean = mapper.readValue(respondentParams, RespondentBean.class);

		
		
		// JavaBeansオブジェクトをJSON文字列へ変換
		String jsonStr = mapper.writeValueAsString(bean);
		
		return Response.ok()
				.header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "POST")
				.entity(jsonStr).build();
		
    }

}