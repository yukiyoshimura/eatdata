package connector;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
public class MySQLConnector {
    public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        Connection con = null;
        try {
            // MySQLに接続
            con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "");
            System.out.println("MySQLに接続できました。");
            
            Statement stm = (Statement) con.createStatement();
            String sql = "select * from test";
            ResultSet rs = stm.executeQuery(sql);

            while(rs.next()){
              //  int id = rs.getInt("id");
                String name = rs.getString("user");
                System.out.println("取得結果 -> " +  name);
            }
            
            
            
        } catch (SQLException e) {
            System.out.println("MySQLに接続できませんでした。");
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    System.out.println("MySQLのクローズに失敗しました。");
                }
            }
        }
    }
}