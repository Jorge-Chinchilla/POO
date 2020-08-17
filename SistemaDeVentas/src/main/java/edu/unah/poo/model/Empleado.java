package edu.unah.poo.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="empleado")
public class Empleado {
	
	@Id
	private int idEmpleado;
	private String nombre;
	private String telefono;
	private String direccion;
	private String usuario;
	private String contrasenia;
	private String rol;
	private int activo;
	
	public Empleado() {}

	public Empleado(int idEmpleado, String nombre, String telefono, String direccion, String usuario, String contrasenia,
			String rol, int activo) {
		super();
		this.idEmpleado = idEmpleado;
		this.nombre = nombre;
		this.telefono = telefono;
		this.direccion = direccion;
		this.usuario = usuario;
		this.contrasenia = contrasenia;
		this.rol = rol;
		this.activo = activo;
	}

	public int getIdEmpleado() {
		return idEmpleado;
	}

	public void setIdEmpleado(int idEmpleado) {
		this.idEmpleado = idEmpleado;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContraseña(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	public int getActivo() {
		return activo;
	}

	public void setActivo(int activo) {
		this.activo = activo;
	}


	
	

}
