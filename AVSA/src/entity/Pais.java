package entity;

public class Pais {
	private int id;
	private String nombre;
	
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
