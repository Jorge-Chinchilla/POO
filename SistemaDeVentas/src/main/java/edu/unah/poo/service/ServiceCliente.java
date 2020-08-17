package edu.unah.poo.service;

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
	
	public void crearSoloCliente(int idCliente, String nombre, String email, String telefono,
			 double credito) { 
		Cliente cliente = new Cliente(idCliente, nombre, email, telefono, credito);
		this.repositoryCliente.save(cliente);
	}
	
	public void crearCliente(int idCliente, String nombre, String email, String telefono,
							 double credito, int idDireccion, String tipo, String direccion) {
		Cliente cliente = new Cliente(idCliente, nombre, email, telefono, credito);
		this.repositoryCliente.save(cliente);
		Direccion tmpDireccion = new Direccion(idDireccion, tipo, direccion, cliente);
		this.repositoryDireccion.save(tmpDireccion);
		
	}
	
	public Cliente buscarCliente(int id) {
		return this.repositoryCliente.findById(id);
	} 
	
	public boolean exist(int idCliente) {
		return this.repositoryCliente.existsById(idCliente);
	}
	
	public List<Cliente> obtenerClientes(){
		return this.repositoryCliente.findAll();
	} 
	
	public Cliente eliminarCliente(int id) {
		return this.repositoryCliente.deleteById(id);
	}
}
