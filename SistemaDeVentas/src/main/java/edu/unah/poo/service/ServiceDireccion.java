package edu.unah.poo.service;

import java.util.ArrayList;
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
		Direccion tmpdireccion = new Direccion(idDireccion, tipo, direccion, 1, cliente);
		this.repositoryDireccion.save(tmpdireccion);
	}
	
	public List<Direccion> obtenerDirecciones(){
		List<Direccion> direccionesActivas = new ArrayList<Direccion>();
		for(Direccion direccion: this.repositoryDireccion.findAll()) {
			if(direccion.getActivo()==1) {
				direccionesActivas.add(direccion);
			}
		}
		return direccionesActivas;
	}
	
	public Direccion buscarDireccion(int id){
		return this.repositoryDireccion.findById(id);
	}
	
	public Direccion eliminarDireccion(int id) {
		//return this.repositoryDireccion.deleteById(id);	
		Direccion direccion = this.buscarDireccion(id);
		direccion.setActivo(0);
		this.repositoryDireccion.save(direccion);
		return direccion;
	}
	
	
	public List<Direccion> direccionesDeCliente(Cliente cliente){
		return cliente.getDirecciones();
	}
	
	public boolean exist(int idDireccion) {
		return this.repositoryDireccion.existsById(idDireccion);
	}
	
}
