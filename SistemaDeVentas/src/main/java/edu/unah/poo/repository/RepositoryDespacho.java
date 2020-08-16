package edu.unah.poo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.unah.poo.model.Despacho;

public interface RepositoryDespacho extends JpaRepository<Despacho, Integer>{
	public Despacho findById(int id);
	public Despacho deleteById(int id);
}
