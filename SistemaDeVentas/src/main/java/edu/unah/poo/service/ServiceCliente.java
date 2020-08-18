package edu.unah.poo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.unah.poo.model.Cliente;
import edu.unah.poo.model.Direccion;
import edu.unah.poo.repository.RepositoryCliente;
import edu.unah.poo.repository.RepositoryDireccion;

@Service
public class ServiceCliente {

	@Autowired
	RepositoryCliente repositoryCliente;
	
	@Autowired
	RepositoryDireccion repositoryDireccion;
	
	public void crearSoloCliente(Cliente cliente) { 
		this.repositoryCliente.save(cliente);
	}
	
	public void crearCliente(int idCliente, String nombre, String email, String telefono,
							 double credito, int idDireccion, String tipo, String direccion) {
		Cliente cliente = new Cliente(idCliente, nombre, email, telefono, credito, 1);
		this.repositoryCliente.save(cliente);
		Direccion tmpDireccion = new Direccion(idDireccion, tipo, direccion, 1, cliente);
		this.repositoryDireccion.save(tmpDireccion);
		
	}
	
	public Cliente buscarCliente(int id) {
		return this.repositoryCliente.findById(id);
	} 
	
	public boolean exist(int idCliente) {
		return this.repositoryCliente.existsById(idCliente);
	}
	
	public List<Cliente> obtenerClientes(){
		List<Cliente> clientesActivos = new ArrayList<Cliente>();
		for(Cliente cliente: this.repositoryCliente.findAll()) {
			if(cliente.getActivo()==1) {
				clientesActivos.add(cliente);
			}
		}
		return clientesActivos;
	} 
	
	
	public Cliente eliminarCliente(int id) {
		//return this.repositoryCliente.deleteById(id);
		Cliente cliente = this.buscarCliente(id);
		cliente.setActivo(0);
		for(Direccion direccion: cliente.getDirecciones()) {
			direccion.setActivo(0);
			this.repositoryDireccion.save(direccion);
		}
		this.repositoryCliente.save(cliente);
		return cliente;
	}
}
