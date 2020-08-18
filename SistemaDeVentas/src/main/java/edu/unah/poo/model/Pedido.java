package edu.unah.poo.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "pedido")
public class Pedido {
	
	@Id
	private int idPedido;
	private LocalDate fechaVenta;

	@ManyToOne
	@JoinColumn(name="idCliente")
	@JsonBackReference
	private Cliente cliente;
	
	@ManyToOne
	@JoinColumn(name="idDireccion")
	@JsonBackReference
	private Direccion direccion;
    
	public Pedido() {}

	public Pedido(int idPedido, LocalDate fechaVenta, Cliente cliente, Direccion direccion) {
		super();
		this.idPedido = idPedido;
		this.fechaVenta = fechaVenta;
		this.cliente = cliente;
		this.direccion = direccion;
	}

	public int getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(int idPedido) {
		this.idPedido = idPedido;
	}

	public LocalDate getFechaVenta() {
		return fechaVenta;
	}

	public void setFechaVenta(LocalDate fechaVenta) {
		this.fechaVenta = fechaVenta;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Direccion getDireccion() {
		return direccion;
	}

	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}
	
}
