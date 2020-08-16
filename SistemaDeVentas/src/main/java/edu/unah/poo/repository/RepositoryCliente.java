package edu.unah.poo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.unah.poo.model.Cliente;


public interface RepositoryCliente extends JpaRepository<Cliente, Integer>{
	public Cliente findById(int id);
}
