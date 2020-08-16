package edu.unah.poo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.unah.poo.model.Pago;
import edu.unah.poo.repository.RepositoryPago;

@Service
public class ServicePago {

	@Autowired
	RepositoryPago repositoryPago;
	
	public void crearPago(Pago pago) {
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
