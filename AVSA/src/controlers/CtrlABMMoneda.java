package controlers;

import java.util.ArrayList;

import data.DataMoneda;
import entity.Moneda;

public class CtrlABMMoneda {
	
	private DataMoneda dataMoney;
	
	public CtrlABMMoneda(){
		dataMoney = new DataMoneda();
	}
	
	public void add(Moneda u) throws Exception{
		dataMoney.add(u);
	}
	
	public void delete(Moneda u)throws Exception{
		dataMoney.delete(u);
	}
	
	public void update(Moneda u)throws Exception{
		dataMoney.update(u);
	}
	
	public Moneda getById(Moneda u) throws Exception{
		return dataMoney.getById(u);
	}
	
	public ArrayList<Moneda> getAll()throws Exception{
		return dataMoney.getAll();
	}
	
}
