package util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

//DB연결할 때 쓰여지는 클래스
public class DBManager {
	
	public static Connection getConnection() {
		//Connection 객체를 얻는 메소드
		Connection con = null;

		try {
			Context initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:/comp/env");
			DataSource ds = (DataSource) envContext.lookup("jdbc/myoracle");
			con = ds.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return con;
	}
	//insert, update, delete작업을 수행한 후 리소스 해제를 위한 메소드
	public static void close(Connection con, Statement stmt) {

		try {
			stmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	// select을 수행한 후 리소스 해제를 위한 메소드
	public static void close(Connection con, Statement stmt, ResultSet rs) {

		try {
			rs.close();
			stmt.close();
			con.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
