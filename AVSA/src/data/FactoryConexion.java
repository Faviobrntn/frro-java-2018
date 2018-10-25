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
	
	public FactoryConexion() {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static FactoryConexion getInstancia(){
		if (FactoryConexion.instancia == null){		
			FactoryConexion.instancia=new FactoryConexion();
		}
		return FactoryConexion.instancia;
		
}
	
/*	public Connection getConn(){
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
	}*/
	
	private Connection conn;
	private int cantConn=0;
	public Connection getConn() throws SQLException,AppDataException{
		try {
			if(conn==null || conn.isClosed()){	
				conn = DriverManager.getConnection(
			        "jdbc:mysql://"+host+":"+port+"/"+db+"?user="+user+"&password="+pass);
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
