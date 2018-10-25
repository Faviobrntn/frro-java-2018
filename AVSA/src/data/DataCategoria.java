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
			e.printStackTrace();
		}
		
		return categorias;
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
			e.printStackTrace();
		}
	}
}
