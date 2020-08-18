package edu.unah.poo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import edu.unah.poo.model.Proveedor;
import edu.unah.poo.repository.RepositoryProveedor;

@Service
public class ServiceProveedor {

	@Autowired
	RepositoryProveedor repositoryProveedor;
	
	public void crearProveedor(Proveedor proveedor) {
		this.repositoryProveedor.save(proveedor);
	}
	
	public Proveedor buscarProveedor(int id) {
		return this.repositoryProveedor.findById(id);
	}
	
	public List<Proveedor> obtenerProveedores(){
		List<Proveedor> proveedoresActivos = new ArrayList<Proveedor>();
		for(Proveedor proveedor: this.repositoryProveedor.findAll()) {
			if(proveedor.getActivo()==1) {
				proveedoresActivos.add(proveedor);
			}
		}
		return proveedoresActivos;
	}
	
	public Proveedor eliminarProveedor(int id) {
		//return repositoryProveedor.deleteById(id);
		Proveedor proveedor = this.buscarProveedor(id);
		proveedor.setActivo(0);
		this.repositoryProveedor.save(proveedor);
		return proveedor;
	}
	
}
