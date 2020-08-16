package edu.unah.poo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.unah.poo.model.PagoEfectivo;
import edu.unah.poo.repository.RepositoryPagoEfectivo;

@Service	
public class ServicePagoEfectivo {

	@Autowired
	RepositoryPagoEfectivo repositoryPagoEfectivo;
	
	public void crearPagoEfectivo(PagoEfectivo pagoEfectivo) {
		this.repositoryPagoEfectivo.save(pagoEfectivo);
	}	
	
	public PagoEfectivo buscarPagoEfectivo(int id) {
		return this.repositoryPagoEfectivo.findById(id);
	}
	
	public List<PagoEfectivo> obtenerPagoEfectivo(){
		return this.repositoryPagoEfectivo.findAll();
	}
	
	public PagoEfectivo eliminarPagoEfectivo(int id) {
		return this.repositoryPagoEfectivo.deleteById(id);
	}
	
}
