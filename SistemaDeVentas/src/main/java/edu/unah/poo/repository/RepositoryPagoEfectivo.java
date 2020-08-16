package edu.unah.poo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.unah.poo.model.PagoEfectivo;

public interface RepositoryPagoEfectivo extends JpaRepository<PagoEfectivo, Integer>{
	public PagoEfectivo findById(int id);
	public PagoEfectivo deleteById(int id);
}
