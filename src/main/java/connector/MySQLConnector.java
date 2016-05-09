package connector;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.mysql.jdbc.Connection;
public class MySQLConnector {
    public Connection getConnection() throws SQLException, ClassNotFoundException {
        Connection con = null;
        
		//propertiesファイル読み込み
		ResourceBundle bundle = ResourceBundle.getBundle("dbinfo");
		
		String host = bundle.getString("host");
		String port = bundle.getString("port");
		String user = bundle.getString("user");
		String dbname = bundle.getString("dbname");
		String password = bundle.getString("password");
		String url = "jdbc:mysql://" + host + ":" + port + "/" + dbname;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			System.out.println("Connetcting....." + url);
	        // MySQLに接続
            con = (Connection) DriverManager.getConnection(url, user, password);
///            con = (Connection) DriverManager.getConnection("jdbc:mysql://ja-cdbr-azure-east-a.cloudapp.net:3306/eatdata", "b3d674e06c3e5b", "bb28b0c7");
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
    
