package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import responseBean.MEventBean;
import responseBean.WRespondentBean;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

public class MEventDao {

    public List<MEventBean> getMEventDao(Connection con) throws SQLException{
            System.out.println("Start MEventDao");
            List<MEventBean> records = new ArrayList<MEventBean>();
            Statement stm = null;
            try {
                stm = (Statement) con.createStatement();
            } catch (SQLException e) {
                System.out.println("create statement error");
                e.printStackTrace();
            }
            
            String sql = 
                    "   select "
                       + "   event_id,"
                       + "   event_name,"
                       + "   hold_start_date,"
                       + "   hold_end_date,"
                       + "   publish_start_date,"
                       + "   publish_end_date,"
                       + "   input_end_date,"
                       + "   message,"
                       + "   valid_flg,"
                       + "   create_date,"
                       + "   update_date"
                       + " from"
                       + "   m_event"
                       + " where"
                       + "   publish_end_date <= data_format(now(),'%Y%m%d')"
                       + " and"
                       + "   publish_start_date >= data_format(now(),'%Y%m%d')"
                       ;
            ResultSet rs = stm.executeQuery(sql);
    
            try {
                
                System.out.println("executeSQL" + sql);
                
                while (rs.next()) {
                    
                    MEventBean bean = new MEventBean();
                    bean.setEventId(rs.getString("event_id"));
                    bean.setEventName(rs.getString("event_name"));
                    bean.setHoldStartDate(rs.getString("hold_start_date"));
                    bean.setEventName(rs.getString("event_name"));
                    bean.setHoldStartDate(rs.getString("hold_start_date"));
                    bean.setHoldEndDate(rs.getString("hold_end_date"));
                    bean.setMessage(rs.getString("message"));
                    bean.setInputEndDate(rs.getString("input_end_date"));
                    bean.setValidFlag(rs.getString("valid_flg"));
                    bean.setCreateDate(rs.getString("create_date"));
                    bean.setUpdateDate(rs.getString("update_date"));                
                    
                    records.add(bean);
                }
                
            } catch (SQLException e) {
                System.out.println("SQLエラー");
                e.printStackTrace();
            }
            
            return records;
        }
    
    public String updateTRespondent(Connection con, WRespondentBean request) throws SQLException{
        System.out.println("Start TRespondentDao");
        
        String sql = 
                " update  " 
                 + "   t_respondent res,"
                 + "   m_employee emp"
                 + "  set"
                 + "   res.respondent_status = ?,"
                 + "   res.comment = ?,"
                 + "   res.update_date = now()"
                 + " where"
                 + "   res.employee_id = ? "
                 + " and "
                 + "   res.employee_id = emp.employee_id "
                 + " and "
                 + "   emp.valid_flg = '1' "
                 ;
        
        PreparedStatement pstmt = (PreparedStatement) con.prepareStatement(sql);
        pstmt.setString(1,request.getRespondentStatus());
        pstmt.setString(2,request.getComment());
        pstmt.setString(3,request.getEmployeeId());
    
        try {
            int cnt = pstmt.executeUpdate();
            System.out.println("executeSQL" + sql);
            System.out.println("取得結果 UPDATE-> " +  cnt);
        } catch (SQLException e) {
            System.out.println("SQLエラー");
            e.printStackTrace();
        }
        
        return "OK";
    }
    
}
