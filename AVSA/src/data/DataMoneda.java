package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import entity.Moneda;
import util.AppDataException;

public class DataMoneda {
	
	public ArrayList<Moneda> getAll() throws Exception{
		
		Statement stmt=null;
		ResultSet rs=null;
		ArrayList<Moneda> paises= new ArrayList<Moneda>();
		try{
			stmt = FactoryConexion.getInstancia()
					.getConn().createStatement();
			rs = stmt.executeQuery("SELECT * FROM monedas");
			if(rs!=null){
				while(rs.next()){
					Moneda p=new Moneda();
					p.setId(rs.getInt("id"));
					p.setNombre(rs.getString("nombre"));
					paises.add(p);
				}
			}
		} catch (Exception e){
			throw e;
		}
		
		try {
			if(rs!=null) rs.close();
			if(stmt!=null) stmt.close();
			FactoryConexion.getInstancia().releaseConn();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return paises;
	}
	
	public Moneda getById(Moneda pais) throws Exception{
		
		PreparedStatement stmt=null;
		ResultSet rs=null;
		Moneda p = null;
		try{
			stmt=FactoryConexion.getInstancia().getConn().prepareStatement(
					"SELECT * FROM monedas WHERE id=?");
			stmt.setInt(1, pais.getId());
			
			rs=stmt.executeQuery();
			
			if(rs!=null && rs.next()){
				p = new Moneda();
				p.setId(rs.getInt("id"));
				p.setNombre(rs.getString("nombre"));
			}
			
		} catch (Exception e) {
			throw e;
		} finally{
			try {
				if(rs!=null)rs.close();
				if(stmt!=null)stmt.close();
				FactoryConexion.getInstancia().releaseConn();
			} catch (SQLException e) {
				throw e;
			}
		}
		return p;
	}
	
	
	public void add(Moneda p) throws Exception{
		PreparedStatement stmt=null;
		ResultSet keyResultSet=null;
		try {
			stmt=FactoryConexion.getInstancia().getConn()
					.prepareStatement(
						"INSERT INTO monedas(nombre) values (?)",
						PreparedStatement.RETURN_GENERATED_KEYS
					);
			stmt.setString(1, p.getNombre());
			stmt.executeUpdate();
			keyResultSet = stmt.getGeneratedKeys();
			
			if(keyResultSet!=null && keyResultSet.next()){
				p.setId(keyResultSet.getInt(1));
			}
	
		} catch (SQLException | AppDataException e) {
			throw e;
		}
		
		try {
			if(keyResultSet!=null)keyResultSet.close();
			if(stmt!=null)stmt.close();
			FactoryConexion.getInstancia().releaseConn();
		} catch (SQLException e) {
			throw e;
		}
	}
	
	
	
	public void delete(Moneda pais) throws Exception{
		PreparedStatement stmt=null;
		try {
			stmt=FactoryConexion.getInstancia().getConn()
				.prepareStatement(
					"DELETE FROM monedas WHERE id=?"
				);
			stmt.setInt(1, pais.getId());
			stmt.executeUpdate();
		} catch (SQLException | AppDataException e) {
			throw e;
		}
		try {
			if(stmt!=null)stmt.close();
			FactoryConexion.getInstancia().releaseConn();
		} catch (SQLException e) {
			throw e;
		}
		
	}
	
	
	
	public void update(Moneda pais) throws Exception{
		PreparedStatement stmt=null;
		try {
			stmt=FactoryConexion.getInstancia().getConn()
					.prepareStatement(
						"UPDATE monedas SET nombre=? WHERE id=?"
					);
	
			stmt.setString(1, pais.getNombre());
			stmt.setInt(2, pais.getId());
			
			stmt.executeUpdate();
			
		} catch (SQLException | AppDataException e) {
			throw e;
		}
		try {
			if(stmt!=null)stmt.close();
			FactoryConexion.getInstancia().releaseConn();
		} catch (SQLException e) {
			throw e;
		}
		
	}
}
