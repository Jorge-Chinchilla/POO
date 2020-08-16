package edu.unah.poo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.unah.poo.model.Pago;

public interface RepositoryPago extends JpaRepository<Pago, Integer>{
	public Pago findById(int id);
	public Pago deleteById(int id);
}
