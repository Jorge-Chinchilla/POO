package edu.unah.poo.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="producto")
public class Producto {

	@Id
	private int idProducto;
	private String nombre;
	private String unidad;
	private double precio;
	private String descripcion;
	private int cantidad;
	
	//relacion con proveedor
	@ManyToOne
	@JoinColumn(name="idProveedor")
	private Proveedor proveedor;
	
	public Producto() {}

	public Producto(int idProducto, String nombre, String unidad, double precio, String descripcion, int cantidad, Proveedor proveedor) {
		super();
		this.idProducto = idProducto;
		this.nombre = nombre;
		this.unidad = unidad;
		this.precio = precio;
		this.descripcion = descripcion;
		this.cantidad = cantidad;
		this.proveedor = proveedor;
	}

	public int getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getUnidad() {
		return unidad;
	}

	public void setUnidad(String unidad) {
		this.unidad = unidad;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public Proveedor getProveedor() {
		return proveedor;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}
	
	
	
}
