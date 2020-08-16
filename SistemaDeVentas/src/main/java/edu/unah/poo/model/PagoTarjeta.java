package edu.unah.poo.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="pagoTarjeta")
public class PagoTarjeta {

	@Id
	private int idTarjeta;
	private String numero;
	private String expiracion;
	private String tipo;
	private String clave;
	
	public PagoTarjeta() {}

	public PagoTarjeta(int idTarjeta, String numero, String expiracion, String tipo, String clave) {
		super();
		this.idTarjeta = idTarjeta;
		this.numero = numero;
		this.expiracion = expiracion;
		this.tipo = tipo;
		this.clave = clave;
	}

	public int getIdTarjeta() {
		return idTarjeta;
	}

	public void setIdTarjeta(int idTarjeta) {
		this.idTarjeta = idTarjeta;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getExpiracion() {
		return expiracion;
	}

	public void setExpiracion(String expiracion) {
		this.expiracion = expiracion;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}


	
	
}
