package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import responseBean.TRespondentBean;
import responseBean.WRespondentBean;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

public class TRespondentDao {

    public List<TRespondentBean> getTRespondent(Connection con) throws SQLException{
           System.out.println("Start TRespondentDao");
            List<TRespondentBean> records = new ArrayList<TRespondentBean>();

            Statement stm = null;
            try {
                stm = (Statement) con.createStatement();
            } catch (SQLException e) {
                System.out.println("create statement error");
                e.printStackTrace();
            }
            
            String sql = 
                    "   select "
                    + "   res.respondent_id,"
                    + "   res.employee_id,"
                    + "   emp.employee_nm,"
                    + "   res.respondent_status as status_code,"
                    + "   (select"
                    + "      text "
                    + "   from"
                    + "      m_translate"
                    + "   where "
                    + "      key_id = 'RESPOND_ST'"
                    + "   and"
                    + "     code = res.respondent_status) as status_text, "
                    + "   res.comment,"
                    + "   res.create_date," 
                    + "   res.update_date,"
                    + "   (select count(*) as cnt from t_respondent where respondent_status = '1' and valid_flg = '1') as sumAttend,"
                    + "   (select count(*) as cnt from t_respondent where respondent_status = '2' and valid_flg = '1') as sumAbsent,"
                    + "   (select count(*) as cnt from t_respondent where respondent_status = '3' and valid_flg = '1') as sumUndecided"
                    + " from"
                    + "   t_respondent res,"
                    + "   m_employee emp"
                    + " where"
                    + "   res.employee_id = emp.employee_id"
                    + " and"  
                    + "   emp.valid_flg = '1'"
                    ;        
            ResultSet rs = stm.executeQuery(sql);
    
            try {
                
                System.out.println("executeSQL" + sql);
                
                while (rs.next()) {
                    TRespondentBean bean = new TRespondentBean();
                    HashMap<String, String> resStatusMap = new HashMap<String, String>();
                    
                    bean.setRespondentId(rs.getString("respondent_id"));
                    bean.setEmployeeId(rs.getString("employee_id"));
                    bean.setEmployeeNm(rs.getString("employee_nm"));
                    bean.setRespondentStatus(resStatusMap);
                    bean.setComment(rs.getString("comment"));
                    bean.setCreateDate(rs.getString("create_date"));
                    bean.setUpdateDate(rs.getString("update_date"));
                    bean.setSumAttend(rs.getString("sumAttend"));
                    bean.setSumAbsent(rs.getString("sumAbsent"));
                    bean.setsumUndecided(rs.getString("sumUndecided"));
                    resStatusMap.put("code", rs.getString("status_code"));
                    resStatusMap.put("text", rs.getString("status_text"));
                    
                    records.add(bean);
                    System.out.println("取得結果 -> " +  bean.getRespondentId());
                }
                
                for (TRespondentBean record:records) {
                    System.out.println("check value" + record.getRespondentId());
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
