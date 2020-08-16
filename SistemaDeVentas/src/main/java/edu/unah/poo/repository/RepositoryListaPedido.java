package edu.unah.poo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.unah.poo.model.IdListaPedido;
import edu.unah.poo.model.ListaPedido;

public interface RepositoryListaPedido extends JpaRepository<ListaPedido, IdListaPedido>{
	
}
