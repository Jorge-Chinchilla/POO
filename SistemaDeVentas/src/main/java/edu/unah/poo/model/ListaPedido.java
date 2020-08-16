package edu.unah.poo.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name="listaPedido")
@IdClass(IdListaPedido.class)
public class ListaPedido implements Serializable{
	
	@Id
	private int idPedido;
	@Id
	private int idProducto;
	private int cantidad;
	private double precio;
	
	//precio almacenara el precio actual de la unidad del producto
	//cantidad almacenara la cantidad de productos de ese determinado producto en un solo pedido
	
	@ManyToOne
	@JoinColumn(name="idPedido", referencedColumnName="idPedido", insertable = false, updatable = false)
	@JsonBackReference
	private Pedido pedido; //crear lista de productos en pedido si es necesario ??

	@ManyToOne
	@JoinColumn(name="idProducto", referencedColumnName="idProducto", insertable = false, updatable = false)
	@JsonBackReference
	private Producto producto;

	public ListaPedido() {}
	
	public ListaPedido(int idPedido, int idProducto, int cantidad, double precio, Pedido pedido, Producto producto) {
		super();
		this.idPedido = idPedido;
		this.idProducto = idProducto;
		this.cantidad = cantidad;
		this.precio = precio;
		this.pedido = pedido;
		this.producto = producto;
	}

	public int getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(int idPedido) {
		this.idPedido = idPedido;
	}

	public int getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(int precio) {
		this.precio = precio;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	
	
	
}
