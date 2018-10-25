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
		dataCateg.delete(u);
	}
	
	public void update(Categoria u)throws Exception{
		dataCateg.update(u);
	}
	
	public Categoria getById(Categoria u) throws Exception{
		return dataCateg.getById(u);
	}
	
	public ArrayList<Categoria> getAll()throws Exception{
		return dataCateg.getAll();
	}
	

}