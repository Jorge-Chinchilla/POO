package edu.unah.poo.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="pago")
public class Pago {

	@Id
	private int idPago;
	private double valorPedido;
	private double valorEnvio;
	
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idEfectivo")
    @JsonBackReference
    private PagoEfectivo pagoEfectivo;
	
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idTarjeta")
    @JsonBackReference
    private PagoTarjeta pagoTarjeta;

    public Pago(){}

	public Pago(int idPago, double valorPedido, double valorEnvio) {
		super();
		this.idPago = idPago;
		this.valorPedido = valorPedido;
		this.valorEnvio = valorEnvio;
	}

	public int getIdPago() {
		return idPago;
	}

	public void setIdPago(int idPago) {
		this.idPago = idPago;
	}

	public double getValorPedido() {
		return valorPedido;
	}

	public void setValorPedido(double valorPedido) {
		this.valorPedido = valorPedido;
	}

	public double getValorEnvio() {
		return valorEnvio;
	}

	public void setValorEnvio(double valorEnvio) {
		this.valorEnvio = valorEnvio;
	}

	public PagoEfectivo getPagoEfectivo() {
		return pagoEfectivo;
	}

	public void setPagoEfectivo(PagoEfectivo pagoEfectivo) {
		this.pagoEfectivo = pagoEfectivo;
	}

	public PagoTarjeta getPagoTarjeta() {
		return pagoTarjeta;
	}

	public void setPagoTarjeta(PagoTarjeta pagoTarjeta) {
		this.pagoTarjeta = pagoTarjeta;
	}


    
    
    
}
