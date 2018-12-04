package entity;
import java.sql.Timestamp;

public class Registro {
	private int id;
	private Usuario usuario;
	private Cuenta cuenta;
	private Categoria categoria;
	private String tipo;
	private Timestamp fecha_hora;
	private float importe;
	private String estado;
	private String lugar;
	private String notas;
	private Timestamp creado;
	private Timestamp modificado;
	public static String[] tipos = {
			  "Ingresos", 
			  "Gastos"
			};
	public static String[] estados = {
			  "Conciliado", 
			  "Procesado",
			  "Pendiente"
			};
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public Cuenta getCuenta() {
		return cuenta;
	}
	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}
	
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public Timestamp getFechaHora() {
		return fecha_hora;
	}
	public void setFechaHora(Timestamp fecha_hora) {
		this.fecha_hora = fecha_hora;
	}
	
	public float getImporte() {
		return importe;
	}
	public void setImporte(float importe) {
		this.importe = importe;
	}
	
	public String getNotas() {
		return notas;
	}
	public void setNotas(String notas) {
		this.notas = notas;
	}
	
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	public String getLugar() {
		return lugar;
	}
	public void setLugar(String lugar) {
		this.lugar = lugar;
	}
	
	public Timestamp getCreado() {
		return creado;
	}
	public void setCreado(Timestamp creado) {
		this.creado = creado;
	}
	
	public Timestamp getModificado() {
		return modificado;
	}
	public void setModificado(Timestamp modificado) {
		this.modificado = modificado;
	}
	
	
	

}

