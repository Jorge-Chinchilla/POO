package edu.unah.poo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.unah.poo.model.Orden;

public interface RepositoryOrden  extends JpaRepository<Orden, Integer>{

}
