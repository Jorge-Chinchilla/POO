package edu.unah.poo.model;

import java.io.Serializable;

import javax.persistence.Embeddable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Embeddable
public class IdListaPedido implements Serializable{

	private int idPedido;
	private int idProducto;
	
	public IdListaPedido() {}

	public IdListaPedido(int idPedido, int idProducto) {
		super();
		this.idPedido = idPedido;
		this.idProducto = idProducto;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof IdListaPedido) {
			IdListaPedido tmpid = (IdListaPedido)obj;
			if(this.idPedido == tmpid.getIdPedido()
				&& this.idProducto == tmpid.getIdProducto()) {
				return true;
			}else 
				return false;
		}else return false;
		
	}
	
	public int hashCode() {
		return (int) this.idPedido+this.idProducto;
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
	
	
	
}
