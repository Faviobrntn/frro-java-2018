package modelos;
import modelos.Carreras;

public class Comisiones {
	private int id;
	private int carrera_id;
	private int numero;
	private int a�o;
	private String descripcion;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCarrera_id() {
		return carrera_id;
	}
	public void setCarrera_id(int carrera_id) {
		this.carrera_id = Carreras.getId();
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public int getA�o() {
		return a�o;
	}
	public void setA�o(int a�o) {
		this.a�o = a�o;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}
