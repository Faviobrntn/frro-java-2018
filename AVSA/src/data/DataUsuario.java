package data;
import java.util.ArrayList;


import org.apache.logging.log4j.Level;

import java.sql.*;

import entity.*;
import util.AppDataException;


public class DataUsuario {	
	public ArrayList<Usuario> getAll() throws Exception{
	
		Statement stmt=null;
		ResultSet rs=null;
		ArrayList<Usuario> usu= new ArrayList<Usuario>();
		try {
			stmt = FactoryConexion.getInstancia()
					.getConn().createStatement();
			rs = stmt.executeQuery("select * from usuarios u inner join paises p on u.id_pais=p.id");
			if(rs!=null){
				while(rs.next()){
					Usuario p=new Usuario();
					p.setPais(new Pais());
					p.setId(rs.getInt("id"));
					p.setUsuario(rs.getString("usuario"));
					p.setPassword(rs.getString("password"));
					p.setNombre(rs.getString("nombre"));
					p.setApellido(rs.getString("apellido"));
					p.setEmail(rs.getString("email"));
					
					p.getPais().setId(rs.getInt("id_pais"));
					p.getPais().setNombre(rs.getString("nombre"));
					
					usu.add(p);
				}
			}
		} catch (SQLException e) {
			AppDataException ade=new AppDataException(e, "Error al recuperar listado de Usuarios.\n"+e.getSQLState()+":"+e.getMessage(), Level.WARN);
			throw ade;
		} catch (AppDataException ade){
			throw ade;
		}
		

		try {
			if(rs!=null) rs.close();
			if(stmt!=null) stmt.close();
			FactoryConexion.getInstancia().releaseConn();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return usu;
		
	}
	
	public Usuario getByUsuario(Usuario usu) throws Exception{
		Usuario u=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt=FactoryConexion.getInstancia().getConn().prepareStatement(
					"select u.id, nombre, apellido, id_pais, p.nombre from usuarios u inner join paises p on u.id_pais=p.id where dni=?");
			stmt.setString(1, usu.getUsuario());
			rs=stmt.executeQuery();
			if(rs!=null && rs.next()){
					u=new Usuario();
					u.setPais(new Pais());
					u.setId(rs.getInt("id"));
					u.setNombre(rs.getString("nombre"));
					u.setApellido(rs.getString("apellido"));
					u.getPais().setId(rs.getInt("id_pais"));
					u.getPais().setNombre(rs.getString("nombre"));
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
		return u;
	}
	
	public void add(Usuario u) throws Exception{
		PreparedStatement stmt=null;
		ResultSet keyResultSet=null;
		try {
			stmt=FactoryConexion.getInstancia().getConn()
					.prepareStatement(
					"insert into usuarios(nombre, apellido, id_pais) values (?,?,?)",
					PreparedStatement.RETURN_GENERATED_KEYS
					);
			stmt.setString(1, u.getNombre());
			stmt.setString(2, u.getApellido());
			stmt.setInt(3, u.getPais().getId());
			stmt.executeUpdate();
			keyResultSet=stmt.getGeneratedKeys();
			if(keyResultSet!=null && keyResultSet.next()){
				u.setId(keyResultSet.getInt(1));
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
	
	public Usuario getLogedUser(Usuario usu) throws Exception{
		Usuario u=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt=FactoryConexion.getInstancia().getConn().prepareStatement(
					"select u.id, u.nombre, apellido, id_pais, p.nombre from usuarios u inner join paises on p.id=u.id_pais where user=? and pass=?");
			stmt.setString(1, usu.getUsuario());
			stmt.setString(2, usu.getPassword());
			rs=stmt.executeQuery();
			if(rs!=null && rs.next()){
					u=new Usuario();
					u.setPais(new Pais());
					u.setId(rs.getInt("id"));
					u.setNombre(rs.getString("nombre"));
					u.setApellido(rs.getString("apellido"));
					u.getPais().setId(rs.getInt("id_pais"));
					u.getPais().setNombre(rs.getString("nombre"));
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

		return u;
	}

	public void remove(Usuario u) throws Exception{
		PreparedStatement stmt=null;
		try {
			stmt=FactoryConexion.getInstancia().getConn()
				.prepareStatement(
					"delete from usuarios where id=?"
				);
			stmt.setInt(1, u.getId());
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

	public void update(Usuario u) throws Exception{
		PreparedStatement stmt=null;
		try {
			stmt=FactoryConexion.getInstancia().getConn()
					.prepareStatement(
					"update usuario set nombre=?, apellido=?, id_categoria=? where id=?"
					);
			stmt.setString(1, u.getNombre());
			stmt.setString(2, u.getApellido());
			stmt.setInt(3, u.getPais().getId());
			stmt.setInt(4, u.getId());
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

	public Usuario getByNombreApellido(Usuario usu) throws Exception{
		Usuario u=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt=FactoryConexion.getInstancia().getConn().prepareStatement(
					"select u.id, u.nombre, apellido, id_pais, p.nombre from usuarios u inner join paises p on u.id_pais=p.id where nombre=? and apellido=?");
			stmt.setString(1, usu.getNombre());
			stmt.setString(2, usu.getApellido());
			rs=stmt.executeQuery();
			if(rs!=null && rs.next()){
					u=new Usuario();
					u.setPais(new Pais());
					u.setId(rs.getInt("id"));
					u.setNombre(rs.getString("nombre"));
					u.setApellido(rs.getString("apellido"));
					u.getPais().setId(rs.getInt("id_pais"));
					u.getPais().setNombre(rs.getString("nombre"));
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

		return u;
	}

}