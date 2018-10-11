package data;

import java.sql.*;


public class FactoryConexion {
	private String driver="com.mysql.jdbc.Driver";
	private String host="localhost";
	private String port="3306";
	private String user="root";
	private String pass="";
	private String db="futnDB";
	private String type="mysql";
	
	public void FactoryConexion() {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public Connection getConn(){
		try {
			if(conn==null || conn.isClosed()){
				conn = DriverManager.getConnection(
						"jdbc:"+type+"://"+host+":"+port+"/"+
								db+"?user="+user+"&password="+pass);
				cantConn++;	
			}
		} catch (SQLException e) {
			new ApplicationException("Error connecting to the DB",e);

		}
		return conn;
	}
}
