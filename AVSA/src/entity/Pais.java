package entity;

import java.io.Serializable;

public class Pais implements Serializable{
	private static final long serialVersionUID = 1L;
	private int id;
	private String nombre;
	private Moneda moneda;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Moneda getMoneda() {
		return moneda;
	}
	public void setMoneda(Moneda moneda) {
		this.moneda = moneda;
	}
	
	
	@Override
	public String toString(){
		return this.getNombre();
	}
	
	@Override
	public boolean equals(Object o){
		return (o instanceof Pais && ((Pais)o).getId()==this.getId());
	}
	
	@Override
	public int hashCode(){
		return ((Integer)this.getId()).hashCode();
	}
}
