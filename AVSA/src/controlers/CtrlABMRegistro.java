package controlers;

import java.util.ArrayList;
import java.util.Map;

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
	
	
	public ArrayList<Registro> reporte(Usuario user, Registro filtro)throws Exception{
		return dataRegistro.reporte(user, filtro);
	}
	
	public Map<String, Float> reporteAnual(Usuario user, Registro filtro)throws Exception{
		return dataRegistro.anual(user, filtro);
	}
	
	
	public String generarLista(Registro r) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(r.getFechaHora().toLocaleString()+";");
		stringBuilder.append(r.getCuenta().getNombre()+";");
		stringBuilder.append(r.getCategoria().getNombre()+";");
		stringBuilder.append(r.getImporte()+";");
		stringBuilder.append(r.getTipo()+";");
		stringBuilder.append(r.getEstado()+";");
		//stringBuilder.append("\n");
		return stringBuilder.toString();
	}
	
	public String generarJson(Registro r) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("{");
		stringBuilder.append("\"id\":"+r.getId()+",");
		stringBuilder.append("\"start\":\""+r.getFechaHora()+"\",");
		stringBuilder.append("\"end\":\""+r.getFechaHora()+"\",");
		if(r.getTipo().equals("Ingresos")) {
			stringBuilder.append("\"color\":\"#abe6ab\",");
		}
		if(r.getTipo().equals("Gastos")) {
			stringBuilder.append("\"color\":\"#eaafaf\",");
		}
		stringBuilder.append("\"importe\":\""+r.getImporte()+"\",");
		stringBuilder.append("\"title\":\""+r.getTipo()+"\"");
		stringBuilder.append("}");
		return stringBuilder.toString();
	}
		

}