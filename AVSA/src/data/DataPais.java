package data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import entity.Pais;

public class DataPais {
	
	public ArrayList<Pais> getAll() throws Exception{
			
			Statement stmt=null;
			ResultSet rs=null;
			ArrayList<Pais> paises= new ArrayList<Pais>();
			try{
				stmt = FactoryConexion.getInstancia()
						.getConn().createStatement();
				rs = stmt.executeQuery("select * from paises");
				if(rs!=null){
					while(rs.next()){
						Pais p=new Pais();
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

}
