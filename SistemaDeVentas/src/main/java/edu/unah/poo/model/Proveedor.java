package edu.unah.poo.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="proveedor")
public class Proveedor {

	@Id
	private int idProveedor;
	private String nombre;
	private String telefono;
	private String email;
	private String rtn;
	private int activo;
	
	@OneToMany(mappedBy="proveedor",fetch=FetchType.EAGER)
	private List<Producto> productosDelProveedor;
	
	
	public Proveedor() {}

	public Proveedor(int idProveedor, String nombre, String telefono, String email, String rtn, int activo) {
		super();
		this.idProveedor = idProveedor;
		this.nombre = nombre;
		this.telefono = telefono;
		this.email = email;
		this.rtn = rtn;
		this.activo = activo;
	}

	public int getIdProveedor() {
		return idProveedor;
	}

	public void setIdProveedor(int idProveedor) {
		this.idProveedor = idProveedor;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRtn() {
		return rtn;
	}

	public void setRtn(String rtn) {
		this.rtn = rtn;
	}

	public List<Producto> getProductosDelProveedor() {
		return productosDelProveedor;
	}

	public void setProductosDelProveedor(List<Producto> productosDelProveedor) {
		this.productosDelProveedor = productosDelProveedor;
	}

	public int getActivo() {
		return activo;
	}

	public void setActivo(int activo) {
		this.activo = activo;
	}
	
}
