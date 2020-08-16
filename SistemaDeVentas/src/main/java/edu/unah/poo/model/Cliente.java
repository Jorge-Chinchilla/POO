package edu.unah.poo.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name= "cliente" )
public class Cliente {

	@Id
	private int idCliente;
	private String nombre;
	private String email;
	private String telefono;
	private double credito;
	
	@OneToMany(mappedBy="cliente", fetch=FetchType.EAGER)
	private List<Direccion> direcciones;
	
	@OneToMany(mappedBy="cliente", fetch=FetchType.EAGER)
	private List<Pedido> pedidos;
	
	public Cliente() {}

	public Cliente(int idCliente, String nombre, String email, String telefono, double credito) {
		super();
		this.idCliente = idCliente;
		this.nombre = nombre;
		this.email = email;
		this.telefono = telefono;
		this.credito = credito;
	}

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public double getCredito() {
		return credito;
	}

	public void setCredito(double credito) {
		this.credito = credito;
	}

	public List<Direccion> getDirecciones() {
		return direcciones;
	}

	public void setDirecciones(List<Direccion> direcciones) {
		this.direcciones = direcciones;
	}

	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}
	
}
