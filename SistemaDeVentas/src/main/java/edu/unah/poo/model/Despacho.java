package edu.unah.poo.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="despacho")
public class Despacho {

	@Id
	private int idEstado;
	private boolean estado;
	
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idPedido")
    @JsonBackReference
    private Pedido pedido;
    
    public Despacho() {}

    /// NOTA //
    //El idEstado por los momentos sera el mismo que idDespacho
    
	public Despacho(Pedido pedido, boolean estado) {
		super();
		this.idEstado = pedido.getIdPedido();
		this.estado = estado;
		this.pedido = pedido;
	}

	public int getIdEstado() {
		return idEstado;
	}

	public void setIdEstado(int idEstado) {
		this.idEstado = idEstado;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
    
    
	
	
}
