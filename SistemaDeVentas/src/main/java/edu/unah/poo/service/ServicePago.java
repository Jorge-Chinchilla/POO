package edu.unah.poo.service;

import java.util.List;

import edu.unah.poo.model.PagoEfectivo;
import edu.unah.poo.model.PagoTarjeta;
import edu.unah.poo.repository.RepositoryPagoEfectivo;
import edu.unah.poo.repository.RepositoryPagoTarjeta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.unah.poo.model.Pago;
import edu.unah.poo.repository.RepositoryPago;

@Service
public class ServicePago {

	@Autowired
	RepositoryPago repositoryPago;

	@Autowired
	RepositoryPagoEfectivo repositoryPagoEfectivo;

	@Autowired
	RepositoryPagoTarjeta repositoryPagoTarjeta;
	
	public void crearPago(Pago pago) {
		this.repositoryPago.save(pago);
	}

	public void crearPagoMaster(int idPago, int metodoPago, double MontoPagado, String numeroT,
								String expiracionT, String tipoT, String claveT){
		//MetodoPago = 1 -> Efectivo
		//MetodoPago = 2 -> Tarjeta
		if(metodoPago == 1){
			this.crearPagoEfectivo(MontoPagado);
		}else{
			this.crearPagoTarjeta(numeroT,expiracionT,tipoT,claveT);
		}

	}

	public void crearPagoEfectivo(double MontoPago) {
		PagoEfectivo pagoEfectivo = new PagoEfectivo(3, MontoPago, 0);
		this.repositoryPagoEfectivo.save(pagoEfectivo);

		//Calcular PagoPedido -- No listo
		int PagoPedido = 2000;
		int valorEnvio = 55;

		Pago pago = new Pago(1,PagoPedido,valorEnvio);
		pago.setPagoEfectivo(pagoEfectivo);
		pago.setPagoTarjeta(null);
		this.repositoryPago.save(pago);
	}

	public void crearPagoTarjeta(String numeroT, String expiracionT, String tipoT, String claveT){
		PagoTarjeta pagoTarjeta = new PagoTarjeta( 1, numeroT, expiracionT, tipoT, claveT);
		this.repositoryPagoTarjeta.save(pagoTarjeta);

		int PagoPedido = 2000;
		int ValorEnvio = 55;
		Pago pago = new Pago(1,PagoPedido, ValorEnvio);
		pago.setPagoTarjeta(pagoTarjeta);
		pago.setPagoEfectivo(null);

		this.repositoryPago.save(pago);

	}

	public Pago buscarPago(int id) {
		return this.repositoryPago.findById(id);
	}

	public List<Pago> obtenerTodosPagos(){
		return this.repositoryPago.findAll();
	}
	
	public Pago eliminarPago(int id) {
		return this.repositoryPago.deleteById(id);
	}
	
	
}
