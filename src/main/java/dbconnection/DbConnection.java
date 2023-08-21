package dbconnection;

import java.sql.*;

public class DbConnection {

	
	public static Connection getGameDb()
	{
		Connection conn=null;
		try {
			
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn=DriverManager.getConnection("jdbc:mysql://5.189.169.12:3306/httmtn?autoReconnect=true&serverTimezone=UTC","root","gloadmin123");
			System.out.println("DB GameDbconnect Connection "+conn);
		} catch (Exception e) {
			
			e.printStackTrace();
			
			}
		return conn;
	}
	
	public static Connection getUserCheckDbConn() {
		Connection conn=null;
		try {
			
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn=DriverManager.getConnection("jdbc:mysql://91.205.172.123:3306/gameomania?autoReconnect=true&serverTimezone=UTC&enabledTLSProtocols=TLSv1.2","root","gloadmin123");
//			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/gameomania?autoReconnect=true&serverTimezone=UTC&enabledTLSProtocols=TLSv1.2","root","gloadmin123");
			System.out.println("DB UserCheckConnect Connection "+conn);
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			}
		return conn;
	}
}
