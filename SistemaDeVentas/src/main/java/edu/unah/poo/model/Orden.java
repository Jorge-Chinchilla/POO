package edu.unah.poo.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="orden")
public class Orden {

	@Id
	private int idOrden;
	private LocalDate fechaCreacion;
	private LocalDate fechaEntrega;
	
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idEmpleado")
    @JsonBackReference
    private Empleado empleado;
    
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idPago")
    @JsonBackReference
    private Pago pago;
    
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idPedido")
    @JsonBackReference

    private Pedido pedido;
    
    public Orden() {}

	public Orden(int idOrden, LocalDate fechaCreacion, LocalDate fechaEntrega, Empleado empleado, Pago pago,
			Pedido pedido) {
		super();
		this.idOrden = idOrden;
		this.fechaCreacion = fechaCreacion;
		this.fechaEntrega = fechaEntrega;
		this.empleado = empleado;
		this.pago = pago;
		this.pedido = pedido;
	}

	public LocalDate getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(LocalDate fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public LocalDate getFechaEntrega() {
		return fechaEntrega;
	}

	public void setFechaEntrega(LocalDate fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}

	public Empleado getEmpleado() {
		return empleado;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}

	public Pago getPago() {
		return pago;
	}

	public void setPago(Pago pago) {
		this.pago = pago;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public int getIdOrden() {
		return idOrden;
	}

	public void setIdOrden(int idOrden) {
		this.idOrden = idOrden;
	}
    
    
	
}
