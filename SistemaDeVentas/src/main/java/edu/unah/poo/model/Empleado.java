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
	
	public Empleado() {}

	public Empleado(int idEmpleado, String nombre, String telefono, String direccion) {
		super();
		this.idEmpleado = idEmpleado;
		this.nombre = nombre;
		this.telefono = telefono;
		this.direccion = direccion;
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
	
	

}
