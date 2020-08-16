package edu.unah.poo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.unah.poo.model.PagoTarjeta;

public interface RepositoryPagoTarjeta extends JpaRepository<PagoTarjeta, Integer>{
	public PagoTarjeta findById(int id);
	public PagoTarjeta deleteById(int id);
}
