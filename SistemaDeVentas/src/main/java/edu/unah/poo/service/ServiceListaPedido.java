package edu.unah.poo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.unah.poo.model.IdListaPedido;
import edu.unah.poo.model.ListaPedido;
import edu.unah.poo.model.Producto;
import edu.unah.poo.repository.RepositoryListaPedido;

@Service
public class ServiceListaPedido {

	@Autowired
	RepositoryListaPedido repositoryListaPedido;
	
	public void crearListaPedido(ListaPedido listaPedido) {
		this.repositoryListaPedido.save(listaPedido);
	}
	
	public ListaPedido buscarListaPedido(IdListaPedido idListaPedido) {
		return this.repositoryListaPedido.getOne(idListaPedido); //quiza ocupe otro metodo
	}
	
	public List<ListaPedido> obtenerListaPedidos(){
		return this.repositoryListaPedido.findAll();
	}
	
	public List<Producto> obtenerProductosDePedido(int idPedido){
		//necesitamos retornar todos los elementos en la lista de listapedido 
		//que pertenecen al id del producto
		return null;
		
	}
	
}
