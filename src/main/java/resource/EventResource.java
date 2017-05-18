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

import responseBean.MEventBean;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mysql.jdbc.Connection;

import connector.MySQLConnector;
import dao.MEventDao;

@Path("event")
public class EventResource extends AbstractResource<MEventBean> {

    @GET
    @Consumes("application/json")
    @Produces("application/json")
    public Response geMEvent(@QueryParam("callback") String callback) throws JsonParseException, JsonMappingException, IOException, ClassNotFoundException, SQLException {
    	logger.info("start geMEvent");
        System.out.println(callback + "callback");
        
        List<MEventBean> beans = new ArrayList<MEventBean>();
        
        // resouce set to beans
        beans = setResource(beans);
       
        logger.debug("0番目！" + beans.get(0).getEventId());
        logger.debug("1番目！" + beans.get(1).getEventName());
        
        // JavaBeansオブジェクトをJSON文字列へ変換
        ObjectMapper mapper = new ObjectMapper();
        String jsonStr = mapper.writeValueAsString(beans);
        logger.debug(jsonStr);
        logger.info("end geMEvent");
        return Response.ok().entity(callback +  "(" + jsonStr + ")"  ).build();
    }
    
    @Override
    public List<MEventBean> setResource(List<MEventBean> beans) {

         MySQLConnector conn = new MySQLConnector();
         MEventDao mevent = new MEventDao();
         Connection connection = null;
         try {               	
             connection = conn.getConnection();
             beans = mevent.getMEventDao(connection);
         } catch (SQLException | ClassNotFoundException e) {
             System.out.println("SQLError");
             e.printStackTrace();
         } finally {
             //connection close
             if (connection != null){
                 try {
                     conn.closeConn(connection);
                 } catch (SQLException e) {
                     e.printStackTrace();
                 }            
             }
         }
         return beans;
     }
}