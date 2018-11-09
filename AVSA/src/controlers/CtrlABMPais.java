package controlers;

import java.util.ArrayList;

import data.DataPais;
import entity.Pais;

public class CtrlABMPais {
	
	private DataPais dataP;
	
	public CtrlABMPais(){
		dataP = new DataPais();
	}
	
	public void add(Pais u) throws Exception{
		dataP.add(u);
	}
	
	public void delete(Pais u)throws Exception{
		dataP.delete(u);
	}
	
	public void update(Pais u)throws Exception{
		dataP.update(u);
	}
	
	public Pais getById(Pais u) throws Exception{
		return dataP.getById(u);
	}
	
	public ArrayList<Pais> getAll()throws Exception{
		return dataP.getAll();
	}
	

}
