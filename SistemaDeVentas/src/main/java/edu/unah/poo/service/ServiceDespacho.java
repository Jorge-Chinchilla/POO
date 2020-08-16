package edu.unah.poo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.unah.poo.model.Despacho;
import edu.unah.poo.repository.RepositoryDespacho;

@Service
public class ServiceDespacho {

	@Autowired
	RepositoryDespacho repositoryDespacho;
	
	public void crearDespacho(Despacho despacho) {
		this.repositoryDespacho.save(despacho);
	}
	
	public List<Despacho> obtenerDespachos(){
		return this.repositoryDespacho.findAll();
	}
	
	public Despacho buscarDespacho(int id) {
		return this.repositoryDespacho.findById(id);
	}
	
	public Despacho eliminarDespacho(int id) {
		return this.repositoryDespacho.deleteById(id);
	}
	
}
