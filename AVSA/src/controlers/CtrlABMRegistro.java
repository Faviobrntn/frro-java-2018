package controlers;

import java.util.ArrayList;

import data.DataRegistro;
import entity.Registro;
import entity.Usuario;

public class CtrlABMRegistro {
	
	private DataRegistro dataRegistro;
	
	public CtrlABMRegistro(){
		dataRegistro = new DataRegistro();
	}
	
	public void add(Registro u) throws Exception{
		dataRegistro.add(u);
	}
	
	public void delete(Registro u)throws Exception{
		dataRegistro.delete(u);
	}
	
	public void update(Registro u)throws Exception{
		dataRegistro.update(u);
	}
	
	public Registro getById(Registro u) throws Exception{
		return dataRegistro.getById(u);
	}
	
	public ArrayList<Registro> getAll(Usuario user)throws Exception{
		return dataRegistro.getAll(user);
	}
	

}