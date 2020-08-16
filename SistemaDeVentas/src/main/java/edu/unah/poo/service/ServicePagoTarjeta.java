package edu.unah.poo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.unah.poo.model.PagoTarjeta;
import edu.unah.poo.repository.RepositoryPagoTarjeta;

@Service
public class ServicePagoTarjeta {

	@Autowired
	RepositoryPagoTarjeta repositoryPagoTatjeta;
	
	public void crearPagoTarjeta(PagoTarjeta pagoTarjeta) {
		this.repositoryPagoTatjeta.save(pagoTarjeta);
	}
	
	public PagoTarjeta buscarpagoTarjeta(int id) {
		return this.repositoryPagoTatjeta.findById(id);
	}
	
	public List<PagoTarjeta> obtenerPagoTarjeta(){
		return this.repositoryPagoTatjeta.findAll();
	}
	
	public PagoTarjeta eliminarPagoTarjeta(int id) {
		return this.repositoryPagoTatjeta.deleteById(id);
	}
	
}
