package edu.unah.poo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.unah.poo.model.Producto;

public interface RepositoryProducto extends JpaRepository<Producto, Integer>{
	public Producto findById(int id);
	public Producto deleteById(int id);
}
