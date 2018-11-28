package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entity.Cuenta;
import entity.Moneda;
import entity.Usuario;
import util.AppDataException;

public class DataCuenta {

	public ArrayList<Cuenta> getAll(Usuario u) throws Exception{
		
		//Statement stmt=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		ArrayList<Cuenta> cuentas= new ArrayList<Cuenta>();
		try{
			stmt=FactoryConexion.getInstancia().getConn().prepareStatement(
					"SELECT * FROM cuentas c INNER JOIN monedas m ON m.id = c.id_moneda WHERE c.id_usuario = ?");
			stmt.setInt(1, u.getId());
			
			rs=stmt.executeQuery();			
			
			if(rs!=null){
				while(rs.next()){
					Cuenta c = new Cuenta();
					c.setMoneda(new Moneda());
					c.setId(rs.getInt("id"));
					c.setNombre(rs.getString("nombre"));
					c.setValorInicial(rs.getFloat("valor_inicial"));
					c.setColor(rs.getString("color"));
					c.setTipo(rs.getString("tipo"));
					c.setDescripcion(rs.getString("descripcion"));
					c.setCreado(rs.getTimestamp("creado"));
					
					c.getMoneda().setId(rs.getInt("m.id"));
					c.getMoneda().setNombre(rs.getString("m.nombre"));
					
					cuentas.add(c);
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
		
		return cuentas;
	}
	
	
	public Cuenta getById(Cuenta cuenta) throws Exception{
		
		PreparedStatement stmt=null;
		ResultSet rs=null;
		Cuenta c = null;
		try{
			stmt=FactoryConexion.getInstancia().getConn().prepareStatement(
					"SELECT * FROM cuentas c INNER JOIN monedas m ON m.id = c.id_moneda WHERE c.id=?");
			stmt.setInt(1, cuenta.getId());
			
			rs=stmt.executeQuery();
			
			if(rs!=null && rs.next()){
				c = new Cuenta();
				c.setMoneda(new Moneda());
				c.setId(rs.getInt("c.id"));
				c.setNombre(rs.getString("c.nombre"));
				c.setValorInicial(rs.getFloat("valor_inicial"));
				c.setColor(rs.getString("color"));
				c.setTipo(rs.getString("tipo"));
				c.setDescripcion(rs.getString("descripcion"));
				c.setCreado(rs.getTimestamp("creado"));
				
				c.getMoneda().setId(rs.getInt("m.id"));
				c.getMoneda().setNombre(rs.getString("m.nombre"));
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
	
	
	public void add(Cuenta p) throws Exception{
		PreparedStatement stmt=null;
		ResultSet keyResultSet=null;
		try {
			stmt=FactoryConexion.getInstancia().getConn()
					.prepareStatement(
						"INSERT INTO cuentas(id_usuario, nombre, valor_inicial, id_moneda, color, tipo, descripcion, creado) values (?,?,?,?,?,?,?,?)",
						PreparedStatement.RETURN_GENERATED_KEYS
					);
			stmt.setInt(1, p.getUsuario().getId());
			stmt.setString(2, p.getNombre());
			stmt.setFloat(3, p.getValorInicial());
			stmt.setInt(4, p.getMoneda().getId());
			stmt.setString(5, p.getColor());
			stmt.setString(6, p.getTipo());
			stmt.setString(7, p.getDescripcion());
			stmt.setTimestamp(8, p.getCreado());

			
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
	
	
	
	public void delete(Cuenta cuenta) throws Exception{
		PreparedStatement stmt=null;
		try {
			stmt=FactoryConexion.getInstancia().getConn()
				.prepareStatement(
					"DELETE FROM cuentas WHERE id=?"
				);
			stmt.setInt(1, cuenta.getId());
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
	
	
	
	public void update(Cuenta cuenta) throws Exception{
		PreparedStatement stmt=null;
		try {
			stmt=FactoryConexion.getInstancia().getConn()
					.prepareStatement(
						"UPDATE cuentas SET nombre=?, valor_inicial=?, id_moneda=?, color=?, tipo=?, descripcion=? WHERE id=?"
					);

			stmt.setString(1, cuenta.getNombre());
			stmt.setFloat(2, cuenta.getValorInicial());
			stmt.setInt(3, cuenta.getMoneda().getId());
			stmt.setString(4, cuenta.getColor());
			stmt.setString(5, cuenta.getTipo());
			stmt.setString(6, cuenta.getDescripcion());
			stmt.setInt(7, cuenta.getId());
			
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
