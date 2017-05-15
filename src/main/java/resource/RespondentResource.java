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

@Path("respondent")
public class RespondentResource extends AbstractResource<TRespondentBean> {

    @Consumes("application/json")
    @Produces("application/json")
    @GET
    public Response getRespondent(@QueryParam("callback") String callback) throws JsonParseException, JsonMappingException, IOException, ClassNotFoundException, SQLException {
    	   System.out.println(callback + "callback");
           
           List<TRespondentBean> beans = new ArrayList<TRespondentBean>();
           
           // resouce set to beans
           setResource(beans);
          
           System.out.println("0番目！" + beans.get(0).getEmployeeId());
           System.out.println("1番目！" + beans.get(1).getEmployeeId());
           
           // JavaBeansオブジェクトをJSON文字列へ変換
           ObjectMapper mapper = new ObjectMapper();
           String jsonStr = mapper.writeValueAsString(beans);
           return Response.ok().entity(callback +  "(" + jsonStr + ")"  ).build();
       }
       
       @Override
       public List<TRespondentBean> setResource(List<TRespondentBean> setList) {
            MySQLConnector conn = new MySQLConnector();
            TRespondentDao trespondent = new TRespondentDao();
            Connection connection = null;
            try {               	
                connection = conn.getConnection();
                setList = trespondent.getTRespondent(connection);
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
            return setList;
        }
    }