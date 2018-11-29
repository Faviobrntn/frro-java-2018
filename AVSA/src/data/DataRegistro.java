package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entity.Categoria;
import entity.Cuenta;
import entity.Registro;
import entity.Usuario;
import util.AppDataException;

public class DataRegistro {

	public ArrayList<Registro> getAll(Usuario u) throws Exception{
		
		//Statement stmt=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		ArrayList<Registro> registros= new ArrayList<Registro>();
		try{
			stmt=FactoryConexion.getInstancia().getConn().prepareStatement(
					"SELECT * FROM registros r INNER JOIN cuentas c ON c.id = r.id_cuenta INNER JOIN categorias cat ON cat.id = r.id_categoria WHERE r.id_usuario = ?"
			);
			stmt.setInt(1, u.getId());
			
			rs=stmt.executeQuery();			
			
			if(rs!=null){
				while(rs.next()){
					Registro r = new Registro();
					r.setCuenta(new Cuenta());
					r.setCategoria(new Categoria());
					r.setId(rs.getInt("id"));
					r.setTipo(rs.getString("tipo"));
					r.setFechaHora(rs.getTimestamp("fecha_hora"));
					r.setImporte(rs.getFloat("importe"));
					r.setEstado(rs.getString("estado"));
					r.setLugar(rs.getString("lugar"));
					r.setNotas(rs.getString("notas"));
					r.setCreado(rs.getTimestamp("creado"));
					r.setModificado(rs.getTimestamp("modificado"));
					
					
					r.getCuenta().setId(rs.getInt("c.id"));
					r.getCuenta().setNombre(rs.getString("c.nombre"));
					
					r.getCategoria().setId(rs.getInt("cat.id"));
					r.getCategoria().setNombre(rs.getString("cat.nombre"));
					
					registros.add(r);
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
		
		return registros;
	}
	
	
	public Registro getById(Registro registro) throws Exception{
		
		PreparedStatement stmt=null;
		ResultSet rs=null;
		Registro r = null;
		try{
			stmt=FactoryConexion.getInstancia().getConn().prepareStatement(
					"SELECT * FROM registros r INNER JOIN cuentas c ON c.id = r.id_cuenta INNER JOIN categorias cat ON cat.id = r.id_categoria WHERE r.id=?"
			);
			stmt.setInt(1, registro.getId());
			rs=stmt.executeQuery();
			
			if(rs!=null && rs.next()){
				r = new Registro();
				r.setCuenta(new Cuenta());
				r.setCategoria(new Categoria());
				r.setId(rs.getInt("id"));
				r.setTipo(rs.getString("tipo"));
				r.setFechaHora(rs.getTimestamp("fecha_hora"));
				r.setImporte(rs.getFloat("importe"));
				r.setEstado(rs.getString("estado"));
				r.setLugar(rs.getString("lugar"));
				r.setNotas(rs.getString("notas"));
				r.setCreado(rs.getTimestamp("creado"));
				r.setModificado(rs.getTimestamp("modificado"));
				
				
				r.getCuenta().setId(rs.getInt("c.id"));
				r.getCuenta().setNombre(rs.getString("c.nombre"));
				
				r.getCategoria().setId(rs.getInt("cat.id"));
				r.getCategoria().setNombre(rs.getString("cat.nombre"));
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
		return r;
	}
	
	
	public void add(Registro r) throws Exception{
		PreparedStatement stmt=null;
		ResultSet keyResultSet=null;
		try {
			stmt=FactoryConexion.getInstancia().getConn().prepareStatement(
				"INSERT INTO Registros(id_usuario, id_cuenta, id_categoria, tipo, fecha_hora, importe, estado, lugar, notas, creado, modificado) values (?,?,?,?,?,?,?,?,?,?,?)",
				PreparedStatement.RETURN_GENERATED_KEYS
			);
			stmt.setInt(1, r.getUsuario().getId());
			stmt.setInt(2, r.getCuenta().getId());
			stmt.setInt(3, r.getCategoria().getId());
			stmt.setString(4, r.getTipo());
			stmt.setTimestamp(5, r.getFechaHora());
			stmt.setFloat(6, r.getImporte());
			stmt.setString(7, r.getEstado());
			stmt.setString(8, r.getLugar());
			stmt.setString(9, r.getNotas());
			stmt.setTimestamp(10, r.getCreado());
			stmt.setTimestamp(11, r.getModificado());

			
			stmt.executeUpdate();
			keyResultSet = stmt.getGeneratedKeys();
			
			if(keyResultSet!=null && keyResultSet.next()){
				r.setId(keyResultSet.getInt(1));
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
	
	
	
	public void delete(Registro registro) throws Exception{
		PreparedStatement stmt=null;
		try {
			stmt=FactoryConexion.getInstancia().getConn().prepareStatement(
					"DELETE FROM registros WHERE id=?"
				);
			stmt.setInt(1, registro.getId());
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
	
	
	
	public void update(Registro r) throws Exception{
		PreparedStatement stmt=null;
		try {
			stmt=FactoryConexion.getInstancia().getConn().prepareStatement(
				"UPDATE Registros SET id_cuenta=?, id_categoria=?, tipo=?, fecha_hora=?, importe=?, estado=?, lugar=?, notas=?, modificado=? WHERE id=?"
			);
			stmt.setInt(1, r.getCuenta().getId());
			stmt.setInt(2, r.getCategoria().getId());
			stmt.setString(3, r.getTipo());
			stmt.setTimestamp(4, r.getFechaHora());
			stmt.setFloat(5, r.getImporte());
			stmt.setString(6, r.getEstado());
			stmt.setString(7, r.getLugar());
			stmt.setString(8, r.getNotas());
			stmt.setTimestamp(9, r.getModificado());
			stmt.setInt(10, r.getId());
			
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
