package edu.unah.poo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.unah.poo.model.Empleado;

public interface RepositoryEmpleado extends JpaRepository<Empleado, Integer>{
	public Empleado findById(int id);
	public Empleado deleteById(int id);
}
