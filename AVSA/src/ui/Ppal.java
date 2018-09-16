package ui;

import java.sql.*;

import data.FactoryConexion;

public class Ppal {
	
	public static void main(String[] args) {
		FactoryConexion fc = new FactoryConexion();
		Connection conn = fc.getConn();
		
		Statement stmt=null;
		ResultSet rs=null;
		
		
		try {
			stmt=conn.createStatement();
			rs=stmt.executeQuery("SELECT * FROM persona");
			System.out.println();
			System.out.println("query persona");
			
			if (rs!=null) {
				while(rs.next()) {
					String salida="Persona "+rs.getInt("id")+", se llama "+rs.getString("nombre")+" "+rs.getString("apellido");
					System.out.println(salida);
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try { 
				//Cierra las conexiones
				if(rs!=null) rs.close();
				if(stmt!=null) stmt.close();
				if(conn!=null && !!conn.isClosed()) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
}
