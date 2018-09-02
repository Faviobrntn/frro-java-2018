package modelos;

import javax.print.attribute.DateTimeSyntax;

public class Notas {
	private DateTimeSyntax fecha;
	private int nota;
	private String observaciones;
	
	public DateTimeSyntax getFecha() {
		return fecha;
	}
	public void setFecha(DateTimeSyntax fecha) {
		this.fecha = fecha;
	}
	public int getNota() {
		return nota;
	}
	public void setNota(int nota) {
		this.nota = nota;
	}
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	

}
