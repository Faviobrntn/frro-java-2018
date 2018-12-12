package entity;

import java.sql.Timestamp;

public class Cuenta {
	private int id;
	private Usuario usuario;
	private String nombre;
	private float valorInicial;
	private float saldo;
	private Moneda moneda;
	private String color;
	private String tipo;
	private String descripcion;
	private Timestamp creado;
	public static String[] tipos = {
			  "General", 
			  "Efectivo", 
			  "Tarjeta de credito", 
			  "Caja de ahorro",
			  "Seguro",
			  "Inversion",
			  "Prestamo",
			  "Hipoteca"
			};
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public float getSaldo() {
		return saldo;
	}
	public void setSaldo(float saldo) {
		this.saldo = saldo;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public float getValorInicial() {
		return valorInicial;
	}
	public void setValorInicial(float valorInicial) {
		this.valorInicial = valorInicial;
	}
	public Moneda getMoneda() {
		return moneda;
	}
	public void setMoneda(Moneda moneda) {
		this.moneda = moneda;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Timestamp getCreado() {
		return creado;
	}
	public void setCreado(Timestamp creado) {
		this.creado = creado;
	}

}
