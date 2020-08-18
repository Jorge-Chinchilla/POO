package edu.unah.poo.service;

import java.util.ArrayList;
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
		List<Empleado> empleadosActivos = new ArrayList<Empleado>();
		for(Empleado empleado: this.repositoryEmpleado.findAll()) {
			if(empleado.getActivo()==1) {
				empleadosActivos.add(empleado);
			}
		}
		return empleadosActivos;
	}
	
	public Empleado eliminarEmpleado(int id) {
		//return this.repositoryEmpleado.deleteById(id);
		Empleado empleado = this.buscarEmpleado(id);
		empleado.setActivo(0);
		this.crearEmpleado(empleado);
		return empleado;
	}
	
	public boolean exist(int idEmpleado) {
		return this.repositoryEmpleado.existsById(idEmpleado);
	}
	
}
