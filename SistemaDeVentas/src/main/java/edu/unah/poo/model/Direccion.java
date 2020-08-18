package edu.unah.poo.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "direccion")
public class Direccion {
	
	@Id
	private int idDireccion;
	private String tipo;
	private String direccion;
	private int activo;
	
	@ManyToOne
	@JoinColumn(name="idCliente")
	@JsonBackReference
	private Cliente cliente;
	
	@OneToMany(mappedBy="cliente", fetch=FetchType.EAGER)
	private List<Pedido> pedidos;
	
	public Direccion() {}

	public Direccion(int idDireccion, String tipo, String direccion, int activo, Cliente cliente) {
		super();
		this.idDireccion = idDireccion;
		this.tipo = tipo;
		this.direccion = direccion;
		this.activo= activo;
		this.cliente = cliente;
	}

	public int getIdDireccion() {
		return idDireccion;
	}

	public void setIdDireccion(int idDireccion) {
		this.idDireccion = idDireccion;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public int getActivo() {
		return activo;
	}

	public void setActivo(int activo) {
		this.activo = activo;
	}
	
	
}
