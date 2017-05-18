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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import responseBean.TRespondentBean;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mysql.jdbc.Connection;

import connector.MySQLConnector;
import dao.TRespondentDao;

@Path("respondent")
public class RespondentResource extends AbstractResource<TRespondentBean> {

    @GET
    @Consumes("application/json")
    @Produces("application/json")
    public Response getRespondent(@QueryParam("callback") String callback) throws JsonParseException, JsonMappingException, IOException, ClassNotFoundException, SQLException {
           logger.info("start getRespondent");
           System.out.println(callback + "callback");
           
           List<TRespondentBean> beans = new ArrayList<TRespondentBean>();
           
           // resouce set to beans
           beans = setResource(beans);
          
           // JavaBeansオブジェクトをJSON文字列へ変換
           ObjectMapper mapper = new ObjectMapper();
           String jsonStr = mapper.writeValueAsString(beans);
           logger.debug("jsonStr" + jsonStr);
           logger.info("end getRespondent");
           return Response.ok().entity(callback +  "(" + jsonStr + ")"  ).build();
       }
       
       @Override
       public List<TRespondentBean> setResource(List<TRespondentBean> beans) {
           System.out.println("setResouce:Start");
            MySQLConnector conn = new MySQLConnector();
            TRespondentDao trespondent = new TRespondentDao();
            Connection connection = null;
            try {                   
                connection = conn.getConnection();
                beans = trespondent.getTRespondent(connection);
                System.out.println("beans.size()" + beans.size());
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
            System.out.println("setResource:finish");
            return beans;
        }
    }