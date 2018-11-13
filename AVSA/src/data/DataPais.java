package data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.PreparedStatement;

import entity.Moneda;
import entity.Pais;
import util.AppDataException;

public class DataPais {
	
	public ArrayList<Pais> getAll() throws Exception{
			
			Statement stmt=null;
			ResultSet rs=null;
			ArrayList<Pais> paises= new ArrayList<Pais>();
			try{
				stmt = FactoryConexion.getInstancia()
						.getConn().createStatement();
				rs = stmt.executeQuery("SELECT * FROM paises p INNER JOIN monedas m on p.id_moneda = m.id");
				if(rs!=null){
					while(rs.next()){
						Pais p=new Pais();
						p.setMoneda(new Moneda());
						p.setId(rs.getInt("p.id"));
						p.setNombre(rs.getString("p.nombre"));
						
						p.getMoneda().setId(rs.getInt("m.id"));
						p.getMoneda().setNombre(rs.getString("m.nombre"));
						
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

	public Pais getById(Pais pais) throws Exception{
		
		PreparedStatement stmt=null;
		ResultSet rs=null;
		Pais p = null;
		try{
			stmt=FactoryConexion.getInstancia().getConn().prepareStatement(
					"SELECT * FROM paises p INNER JOIN monedas m on p.id_moneda = m.id WHERE p.id=?");
			stmt.setInt(1, pais.getId());
			
			rs=stmt.executeQuery();
			
			if(rs!=null && rs.next()){
				p = new Pais();
				p.setMoneda(new Moneda());
				p.setId(rs.getInt("p.id"));
				p.setNombre(rs.getString("p.nombre"));
				
				p.getMoneda().setId(rs.getInt("m.id"));
				p.getMoneda().setNombre(rs.getString("m.nombre"));
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
	
	
	public void add(Pais p) throws Exception{
		PreparedStatement stmt=null;
		ResultSet keyResultSet=null;
		try {
			stmt=FactoryConexion.getInstancia().getConn()
					.prepareStatement(
						"INSERT INTO paises(nombre, id_moneda) values (?, ?)",
						PreparedStatement.RETURN_GENERATED_KEYS
					);
			stmt.setString(1, p.getNombre());
			stmt.setInt(2, p.getMoneda().getId());
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
			e.printStackTrace();
		}
	}
	
	
	
	public void delete(Pais pais) throws Exception{
		PreparedStatement stmt=null;
		try {
			stmt=FactoryConexion.getInstancia().getConn()
				.prepareStatement(
					"DELETE FROM paises WHERE id=?"
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
			e.printStackTrace();
		}
		
	}
	
	
	
	public void update(Pais pais) throws Exception{
		PreparedStatement stmt=null;
		try {
			stmt=FactoryConexion.getInstancia().getConn()
					.prepareStatement(
						"UPDATE paises SET nombre=?, id_moneda=? WHERE id=?"
					);

			stmt.setString(1, pais.getNombre());
			stmt.setInt(2, pais.getMoneda().getId());
			stmt.setInt(3, pais.getId());
			
			stmt.executeUpdate();
			
		} catch (SQLException | AppDataException e) {
			throw e;
		}
		try {
			if(stmt!=null)stmt.close();
			FactoryConexion.getInstancia().releaseConn();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
