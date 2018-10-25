package controlers;

import java.util.ArrayList;

import data.DataCategoria;
import entity.Categoria;

public class CtrlABMCategoria {
	
	private DataCategoria dataCateg;
	
	public CtrlABMCategoria(){
		dataCateg = new DataCategoria();
	}
	
	public void add(Categoria u) throws Exception{
		dataCateg.add(u);
	}
	
	public void delete(Categoria u)throws Exception{
		
	}
	
	public void update(Categoria u)throws Exception{
		
	}
	
	public Categoria getCategoria(Categoria u) throws Exception{
		return new Categoria();
	}
	
	public Categoria getByCategoria(String categoria)throws Exception{
		return new Categoria();
	}
	
	public Categoria getByNombre(Categoria u) throws Exception{
		return new Categoria();	
	}
	
	public ArrayList<Categoria> getAll()throws Exception{
		return dataCateg.getAll();
	}
	

}