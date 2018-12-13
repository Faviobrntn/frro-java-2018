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
			rs = stmt.executeQuery("SELECT * FROM usuarios u INNER JOIN paises p on u.id_pais=p.id");
			if(rs!=null){
				while(rs.next()){
					Usuario p=new Usuario();
					p.setPais(new Pais());
					p.setId(rs.getInt("id"));
					p.setUsuario(rs.getString("usuario"));
					p.setNombre(rs.getString("nombre"));
					p.setApellido(rs.getString("apellido"));
					p.setEmail(rs.getString("email"));
					p.setRol(rs.getString("rol"));
					
					p.getPais().setId(rs.getInt("id_pais"));
					p.getPais().setNombre(rs.getString("p.nombre"));
					
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
			
			throw e;
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
			Usuario user = this.getByEmail(u);
			
			if(user != null) throw new AppDataException("El E-mail ya se encuentra registrado. Por favor, intente con uno diferente.");
			stmt=FactoryConexion.getInstancia().getConn()
					.prepareStatement(
					"INSERT INTO usuarios(nombre, apellido, email, password, id_pais, rol) values (?,?,?,?,?,?)",
					PreparedStatement.RETURN_GENERATED_KEYS
					);
			stmt.setString(1, u.getNombre());
			stmt.setString(2, u.getApellido());
			stmt.setString(3, u.getEmail());
			stmt.setString(4, u.getPassword());
			stmt.setInt(5, u.getPais().getId());
			stmt.setString(6, u.getRol());
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
			throw e;
		}
	}
	
	
	
	public Usuario getById(Usuario user) throws Exception{
		
		PreparedStatement stmt=null;
		ResultSet rs=null;
		Usuario p = null;
		try{
			stmt=FactoryConexion.getInstancia().getConn().prepareStatement(
					"SELECT * FROM usuarios u INNER JOIN paises p ON p.id = u.id_pais WHERE u.id=?");
			stmt.setInt(1, user.getId());
			
			rs=stmt.executeQuery();
			
			if(rs!=null && rs.next()){
				p = new Usuario();
				p.setPais(new Pais());
				p.setId(rs.getInt("u.id"));
				p.setNombre(rs.getString("u.nombre"));
				p.setApellido(rs.getString("u.apellido"));
				p.setEmail(rs.getString("u.email"));
				p.setRol(rs.getString("u.rol"));
				
				p.getPais().setId(rs.getInt("p.id"));
				p.getPais().setNombre(rs.getString("p.nombre"));
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


	
	public Usuario getLogedUser(Usuario usu) throws Exception{
		Usuario u=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt=FactoryConexion.getInstancia().getConn().prepareStatement(
					"SELECT u.id, u.nombre, apellido, email, rol, id_pais, p.nombre "
					+ "FROM usuarios u INNER JOIN paises p ON p.id = u.id_pais WHERE u.email=? AND u.password=?");
			stmt.setString(1, usu.getEmail());
			stmt.setString(2, usu.getPassword());
			rs=stmt.executeQuery();
			if(rs!=null && rs.next()){
				u=new Usuario();
				u.setPais(new Pais());
				u.setId(rs.getInt("id"));
				u.setNombre(rs.getString("nombre"));
				u.setApellido(rs.getString("apellido"));
				u.setEmail(rs.getString("email"));
				u.setRol(rs.getString("rol"));
				u.getPais().setId(rs.getInt("id_pais"));
				u.getPais().setNombre(rs.getString("p.nombre"));
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
					"DELETE FROM usuarios where id=?"
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
			throw e;
		}
		
	}

	public void update(Usuario u) throws Exception{
		PreparedStatement stmt=null;
		try {
			stmt=FactoryConexion.getInstancia().getConn()
					.prepareStatement(
					"UPDATE usuarios SET nombre=?, apellido=?, email=?, rol=?, id_pais=? WHERE id=?"
					);
			stmt.setString(1, u.getNombre());
			stmt.setString(2, u.getApellido());
			stmt.setString(3, u.getEmail());
			stmt.setString(4, u.getRol());
			stmt.setInt(5, u.getPais().getId());
			stmt.setInt(6, u.getId());
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

	public Usuario getByNombreApellido(Usuario usu) throws Exception{
		Usuario u=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt=FactoryConexion.getInstancia().getConn().prepareStatement(
					"SELECT u.id, u.nombre, apellido, id_pais, p.nombre FROM usuarios u INNER JOIN paises p on u.id_pais=p.id WHERE nombre=? and apellido=?");
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
	
	
	public Usuario getByEmail(Usuario usu) throws Exception{
		Usuario u=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt=FactoryConexion.getInstancia().getConn().prepareStatement(
					"SELECT u.id, u.nombre, u.apellido, u.email, u.id_pais, p.nombre FROM usuarios u INNER JOIN paises p on u.id_pais=p.id WHERE email=?");
			stmt.setString(1, usu.getEmail());
			rs=stmt.executeQuery();
			
			if(rs!=null && rs.next()){
					u=new Usuario();
					u.setPais(new Pais());
					u.setId(rs.getInt("id"));
					u.setNombre(rs.getString("nombre"));
					u.setApellido(rs.getString("apellido"));
					u.setEmail(rs.getString("email"));
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