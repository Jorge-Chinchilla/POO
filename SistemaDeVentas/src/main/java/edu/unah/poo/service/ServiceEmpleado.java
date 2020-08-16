package edu.unah.poo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.unah.poo.model.Empleado;
import edu.unah.poo.repository.RepositoryEmpleado;

@Service
public class ServiceEmpleado {

	@Autowired
	RepositoryEmpleado repositoryEmpleado;
	
	public void crearEmpleado(Empleado empleado) {
		this.repositoryEmpleado.save(empleado);
	}
	
	public Empleado buscarEmpleado(int id) {
		return this.repositoryEmpleado.findById(id);
	}
	
	public List<Empleado> obtenerEmpleados(){
		return this.repositoryEmpleado.findAll();
	}
	
	public Empleado eliminarEmpleado(int id) {
		return this.repositoryEmpleado.deleteById(id);
	}
	
}
