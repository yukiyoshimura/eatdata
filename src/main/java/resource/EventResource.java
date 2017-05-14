package resource;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import responseBean.TRespondentBean;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mysql.jdbc.Connection;

import connector.MySQLConnector;
import dao.TRespondentDao;

@Path("event")
public class EventResource {

    @Consumes("application/json")
    @Produces("application/json")
    @GET
    public   Response getRespondent(@QueryParam("callback") String callback) throws JsonParseException, JsonMappingException, IOException, ClassNotFoundException, SQLException {
        System.out.println(callback + "callback");
        
        List<TRespondentBean> beans = new ArrayList<TRespondentBean>();
        ObjectMapper mapper = new ObjectMapper();
        
        MySQLConnector conn = new MySQLConnector();
        TRespondentDao trespondent = new TRespondentDao();
        
        Connection connection = null;
        
        try {
            connection = conn.getConnection();    
            beans = trespondent.getTRespondent(connection);
        } catch (SQLException e) {
            System.out.println("SQLError");
            e.printStackTrace();
        } finally {
            //connection close
            if (connection != null){
                conn.closeConn(connection);            
            }
        }
        
        System.out.println("0番目！" + beans.get(0).getEmployeeId());
        System.out.println("1番目！" + beans.get(1).getEmployeeId());
        
        // JavaBeansオブジェクトをJSON文字列へ変換
        String jsonStr = mapper.writeValueAsString(beans);
        return Response.ok().entity(callback +  "(" + jsonStr + ")"  ).build();
        
    }
}