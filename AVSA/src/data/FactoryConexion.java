package data;

import java.sql.*;

import util.AppDataException;


public class FactoryConexion {
	private String driver="com.mysql.jdbc.Driver";
	private String host="localhost";
	private String port="3306";
	private String user="root";
	private String pass="";
	private String db="aversiahorra";
	private String type="mysql";
	
	private static FactoryConexion instancia;
	
	public FactoryConexion() throws ClassNotFoundException {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			throw e;
		}
	}
	
	public static FactoryConexion getInstancia() throws ClassNotFoundException{
		if (FactoryConexion.instancia == null){		
			FactoryConexion.instancia=new FactoryConexion();
		}
		return FactoryConexion.instancia;
		
}
	

	private Connection conn;
	private int cantConn=0;
	public Connection getConn() throws SQLException,AppDataException{
		try {
			if(conn==null || conn.isClosed()){	
				conn = DriverManager.getConnection(
			        "jdbc:"+type+"://" + host + ":" + port+"/"+db+"?user="+user+"&password="+pass+"&useSSL=false");
			}
		} catch (SQLException e) {
			throw new AppDataException(e, "Error al conectar a la base de datos");
		}
		cantConn++;
		return conn;
	}
	
	public void releaseConn() throws SQLException{
		try {
			cantConn--;
			if(cantConn==0){
				conn.close();
			}
		} catch (SQLException e) {
			throw e;
		}
}
}
