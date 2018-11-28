package entity;

import java.io.Serializable;

public class Usuario implements Serializable{
	private static final long serialVersionUID = 1L;
	private int id;
	private String usuario;
	private String password;
	private String nombre;
	private String apellido;
	private String email;
	private String rol;
	private Pais pais;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRol() {
		return rol;
	}
	public void setRol(String rol) {
		this.rol = rol;
	}
	
	public Pais getPais() {
		return pais;
	}
	public void setPais(Pais pais) {
		this.pais = pais;
	}

	public Usuario (String nombre, String apellido, String email){
		this.setNombre(nombre);
		this.setApellido(apellido);
		this.setEmail(email);
	}
	
	public Usuario(){}
	
	@Override
	public boolean equals(Object p){
		return (p instanceof Usuario) &&
				(((Usuario)p).getId()==this.getId());
					

}

}
