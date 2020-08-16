package edu.unah.poo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.unah.poo.model.Orden;
import edu.unah.poo.repository.RepositoryOrden;

@Service
public class ServiceOrden {

	@Autowired
	RepositoryOrden repositoryOrden;
	
	public void crearOrden(Orden orden) {
		this.repositoryOrden.save(orden);
	}

	public List<Orden> obtenerOrdenes(){
		return this.repositoryOrden.findAll();
	}
	
}
