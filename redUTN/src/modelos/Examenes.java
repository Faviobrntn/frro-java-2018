package modelos;

import java.util.TimeZone;
import java.util.Date;

public class Examenes {
	private int id;
	private int comision_id;
	private int materia_id;
	private Date fecha;
	private TimeZone hora;
	private String descripcion;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getComision_id() {
		return comision_id;
	}
	public void setComision_id(int comision_id) {
		this.comision_id = comision_id;
	}
	public int getMateria_id() {
		return materia_id;
	}
	public void setMateria_id(int materia_id) {
		this.materia_id = materia_id;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public TimeZone getHora() {
		return hora;
	}
	public void setHora(TimeZone hora) {
		this.hora = TimeZone.getTimeZone("SouthAmerica/Buenos_Aires");
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	
	
	 
	 

	

}
