package model;
import java.util.Date;

import enums.Rol;

public class Usuario extends Persona{

	private int idUsuario;
	private String nombreUsuario;
	private String password;
	private Rol rol;
	
	
	
	public int ingresarCodigoSucursalDestinoMigrado() {
		return 0;
	}
	
	
	public int getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getNombreUsuario() {
		return nombreUsuario;
	}
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Rol getRol() {
		return rol;
	}
	public void setRol(Rol rol) {
		this.rol = rol;
	}
	@Override
	public String toString() {
		return this.idUsuario + ". " + this.getNombre() + " (" + this.nombreUsuario + ")";
	}
	public Usuario(int idUsuario, String nombreUsuario, String email, String password, String nombre, Rol rol, int dni, String domicilio, Date fechaNacimiento) {
		super();
		this.idUsuario = idUsuario;
		this.nombreUsuario = nombreUsuario;
		this.setEmail(email);
		this.password = password;
		this.rol = rol;
		this.setDni(dni);
		this.setDomicilio(domicilio);
		this.setFechaNacimiento(fechaNacimiento);
		this.setNombre(nombre);

	}

	
    
}