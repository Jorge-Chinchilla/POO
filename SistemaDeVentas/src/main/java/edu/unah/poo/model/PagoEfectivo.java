package edu.unah.poo.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="pagoEfectivo")
public class PagoEfectivo {
	
	@Id
	private int idEfectivo;
	private double montoPagado;
	private double vuelto;
	
	public PagoEfectivo() {}

	public PagoEfectivo(int idEfectivo, double montoPagado, double vuelto) {
		super();
		this.idEfectivo = idEfectivo;
		this.montoPagado = montoPagado;
		this.vuelto = vuelto;
	}

	public int getIdEfectivo() {
		return idEfectivo;
	}

	public void setIdEfectivo(int idEfectivo) {
		this.idEfectivo = idEfectivo;
	}

	public double getMontoPagado() {
		return montoPagado;
	}

	public void setMontoPagado(double montoPagado) {
		this.montoPagado = montoPagado;
	}

	public double getVuelto() {
		return vuelto;
	}

	public void setVuelto(double vuelto) {
		this.vuelto = vuelto;
	}
	
	
	
}
