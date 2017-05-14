package dao;

import java.sql.SQLException;
import responseBean.WRespondentBean;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class WRespondentDao {

public String insertWRespondent(Connection con, WRespondentBean request) throws SQLException{
        
        System.out.println("Start WRespondentDao");
        String sql = 
                " insert into    " 
                 + "   wk_respondent"
                 + " ("
                 + "   employee_id,"
                 + "   respondent_status,"
                 + "   comment,"
                 + "   create_date,"
                 + "   update_date"
                 + " )"
                 + " values"
                 + " ("
                 + "   ?,"
                 + "   ?,"
                 + "   ?,"
                 + "   now(),"
                 + "   now()"
                 + " )"
                 ;
        
        PreparedStatement pstmt = (PreparedStatement) con.prepareStatement(sql);
        pstmt.setString(1,request.getEmployeeId());
        pstmt.setString(2,request.getRespondentStatus());
        pstmt.setString(3,request.getComment());
        
        try {
            int cnt = pstmt.executeUpdate();
            System.out.println("executeSQL" + sql);
            System.out.println("取得結果 INSERT-> " +  cnt);
            
        } catch (SQLException e) {
            System.out.println("SQLエラー");
            e.printStackTrace();
        }
        
        return "OK";
    }
    
}
