package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import entity.Categoria;
import util.AppDataException;

public class DataCategoria {
	
	
	public ArrayList<Categoria> getAll() throws Exception{
		
		Statement stmt=null;
		ResultSet rs=null;
		ArrayList<Categoria> categorias= new ArrayList<Categoria>();
		try{
			stmt = FactoryConexion.getInstancia().getConn().createStatement();
			
			rs = stmt.executeQuery("SELECT * FROM categorias");
			
			if(rs!=null){
				while(rs.next()){
					Categoria c = new Categoria();
					c.setId(rs.getInt("id"));
					c.setNombre(rs.getString("nombre"));
					c.setDescripcion(rs.getString("descripcion"));
					categorias.add(c);
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
			throw e;
		}
		
		return categorias;
	}
	
	
	public Categoria getById(Categoria categoria) throws Exception{
		
		PreparedStatement stmt=null;
		ResultSet rs=null;
		Categoria c = null;
		try{
			stmt=FactoryConexion.getInstancia().getConn().prepareStatement(
					"SELECT * FROM categorias WHERE id=?");
			stmt.setInt(1, categoria.getId());
			
			rs=stmt.executeQuery();
			
			if(rs!=null && rs.next()){
				c = new Categoria();
				c.setId(rs.getInt("id"));
				c.setNombre(rs.getString("nombre"));
				c.setDescripcion(rs.getString("descripcion"));
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
		return c;
	}
	
	
	public void add(Categoria p) throws Exception{
		PreparedStatement stmt=null;
		ResultSet keyResultSet=null;
		try {
			stmt=FactoryConexion.getInstancia().getConn()
					.prepareStatement(
						"INSERT INTO categorias(nombre, descripcion) values (?,?)",
						PreparedStatement.RETURN_GENERATED_KEYS
					);
			stmt.setString(1, p.getNombre());
			stmt.setString(2, p.getDescripcion());
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
	
	
	
	public void delete(Categoria categoria) throws Exception{
		PreparedStatement stmt=null;
		try {
			stmt=FactoryConexion.getInstancia().getConn()
				.prepareStatement(
					"DELETE FROM categorias WHERE id=?"
				);
			stmt.setInt(1, categoria.getId());
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
	
	
	
	public void update(Categoria categoria) throws Exception{
		PreparedStatement stmt=null;
		try {
			stmt=FactoryConexion.getInstancia().getConn()
					.prepareStatement(
						"UPDATE categorias SET nombre=?, descripcion=? WHERE id=?"
					);

			stmt.setString(1, categoria.getNombre());
			stmt.setString(2, categoria.getDescripcion());
			stmt.setInt(3, categoria.getId());
			
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
