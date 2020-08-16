package edu.unah.poo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.unah.poo.model.Cliente;
import edu.unah.poo.model.Pedido;
import edu.unah.poo.repository.RepositoryPedido;

@Service
public class ServicePedido {

	@Autowired
	RepositoryPedido repositoryPedido;
	
	public void crearPedido(Pedido pedido) {
		this.repositoryPedido.save(pedido);
	}
	
	public Pedido buscarPedido(int id) {
		return this.repositoryPedido.findById(id);
	}
	
	public List<Pedido> obtenerPedidos(){
		return this.repositoryPedido.findAll();
	}
	
	public List<Pedido> pedidosDeCliente(Cliente cliente){
		return cliente.getPedidos();
	}
	
	
}
