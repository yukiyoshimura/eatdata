package connector;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
public class MySQLConnector {
    public Connection getConnection() throws SQLException, ClassNotFoundException {
        Connection con = null;
            // MySQLに接続
				try {
					Class.forName("com.mysql.jdbc.Driver");
					
					System.out.println("Connetcting.....");
		            con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/eatdata", "root", "");
		            System.out.println("MySQLに接続できました。");
				} catch (SQLException e) {
					System.out.println("Connector Error MySQL接続に失敗しました。");
					e.printStackTrace();
					throw new SQLException();
				}
	            return con;

    }
    
    public void closeConn(Connection con) throws SQLException{
    	con.close();
 
    }
    
}
    
