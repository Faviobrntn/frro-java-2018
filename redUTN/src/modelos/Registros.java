package modelos;
import javax.print.attribute.DateTimeSyntax;;
public class Registros {
	private int id;
	private String tipo;
	private float importe;
	private String notas;
	private DateTimeSyntax fecha_hora;
	private String estado;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
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
	public DateTimeSyntax getFecha_hora() {
		return fecha_hora;
	}
	public void setFecha_hora(DateTimeSyntax fecha_hora) {
		this.fecha_hora = fecha_hora;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}

}

