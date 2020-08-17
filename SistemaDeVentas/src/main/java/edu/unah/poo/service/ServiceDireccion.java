package edu.unah.poo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.unah.poo.model.Cliente;
import edu.unah.poo.model.Direccion;
import edu.unah.poo.repository.RepositoryDireccion;

@Service
public class ServiceDireccion {

	@Autowired
	RepositoryDireccion repositoryDireccion;
	

	
	public void crearDireccion(int idDireccion, String tipo, String direccion, Cliente cliente) {
		Direccion tmpdireccion = new Direccion(idDireccion, tipo, direccion, cliente);
		this.repositoryDireccion.save(tmpdireccion);
	}
	
	public List<Direccion> obtenerDirecciones(){
		return this.repositoryDireccion.findAll();
	}
	
	public Direccion buscarDireccion(int id){
		return this.repositoryDireccion.findById(id);
	}
	
	public Direccion eliminarDireccion(int id) {
		return this.repositoryDireccion.deleteById(id);	
	}
	
	
	public List<Direccion> direccionesDeCliente(Cliente cliente){
		return cliente.getDirecciones();
	}
	
	public boolean exist(int idDireccion) {
		return this.repositoryDireccion.existsById(idDireccion);
	}
	
}
