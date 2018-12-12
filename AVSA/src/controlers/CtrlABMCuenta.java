package controlers;

import java.util.ArrayList;

import data.DataCuenta;
import entity.Cuenta;
import entity.Usuario;

public class CtrlABMCuenta {
	
	private DataCuenta dataCuenta;
	
	public CtrlABMCuenta(){
		dataCuenta = new DataCuenta();
	}
	
	public void add(Cuenta u) throws Exception{
		dataCuenta.add(u);
	}
	
	public void delete(Cuenta u)throws Exception{
		dataCuenta.delete(u);
	}
	
	public void update(Cuenta u)throws Exception{
		dataCuenta.update(u);
	}
	
	public Cuenta getById(Cuenta u) throws Exception{
		return dataCuenta.getById(u);
	}
	
	public ArrayList<Cuenta> getAll(Usuario user)throws Exception{
		return dataCuenta.getAll(user);
	}
	
	public ArrayList<Cuenta> getCuentas(Usuario user)throws Exception{
		return dataCuenta.getCuentas(user);
	}
	
//	public Cuenta getSaldo(Cuenta c) throws Exception {
//		return dataCuenta.getSaldo(c);
//	}
	
	public float getSaldo(Cuenta c) throws Exception {
		return dataCuenta.getSaldo(c);
	}

}