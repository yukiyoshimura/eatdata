package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import responseBean.TRespondentBean;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class TRespondentDao {

public List<TRespondentBean> getTRespondent(Connection con) throws SQLException{
    	
    	List<TRespondentBean> records = new ArrayList<TRespondentBean>();
    	
    	System.out.println("Start TRespondentDao");
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
        		+ "   res.update_date"
        		+ " from"
        		+ "   t_respondent res,"
        		+ "   m_employee emp"
        		+ " where"
        		+ "   res.employee_id = emp.employee_id"
        		;        
        ResultSet rs = stm.executeQuery(sql);

        try {
        	
        	System.out.println("executeSQL" + sql);
        	
			while(rs.next()){
				
				TRespondentBean bean = new TRespondentBean();
				HashMap<String, String> resStatusMap = new HashMap<String, String>();
				
			    bean.setRespondentId(rs.getString("respondent_id"));
				bean.setEmployeeId(rs.getString("employee_id"));
				bean.setEmployeeNm(rs.getString("employee_nm"));
				
				resStatusMap.put("code", rs.getString("status_code"));
				resStatusMap.put("text", rs.getString("status_text"));
				bean.setRespondentStatus(resStatusMap);
				
				bean.setComment(rs.getString("comment"));
				bean.setCreateDate(rs.getString("create_date"));
				bean.setUpdateDate(rs.getString("update_date"));
				
				records.add(bean);
			    System.out.println("取得結果 -> " +  bean.getRespondentId());
			}
			
			for (TRespondentBean record:records){
				
				System.out.println("check value" + record.getRespondentId());
				
				
			}
			
		} catch (SQLException e) {
			System.out.println("SQLエラー");
			e.printStackTrace();
		}
        
        return records;
    }
	
}
