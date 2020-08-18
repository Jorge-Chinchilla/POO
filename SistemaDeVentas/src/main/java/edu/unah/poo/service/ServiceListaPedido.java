package edu.unah.poo.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.unah.poo.model.IdListaPedido;
import edu.unah.poo.model.ListaPedido;
import edu.unah.poo.repository.RepositoryListaPedido;
import edu.unah.poo.repository.RepositoryProducto;

@Service
public class ServiceListaPedido {

	@Autowired
	RepositoryListaPedido repositoryListaPedido;
	
	@Autowired
	RepositoryProducto repositoryProducto;
	
	public void crearListaPedido(ListaPedido listaPedido) {
		this.repositoryListaPedido.save(listaPedido);
	}
	
	public ListaPedido buscarListaPedido(IdListaPedido idListaPedido) {
		return this.repositoryListaPedido.getOne(idListaPedido); //quiza ocupe otro metodo
	}
	
	public List<ListaPedido> obtenerListaPedidos(){
		return this.repositoryListaPedido.findAll();
	}

	
}
