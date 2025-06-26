package it.project.repository;

import java.util.Optional;

import org.springframework.data.repository.ListCrudRepository;

import it.project.entity.ContoBancario;

public interface ContoBancarioRepository extends ListCrudRepository<ContoBancario, Integer> {

	 Optional<ContoBancario> findByNumeroConto(String numeroConto);
	    boolean existsByNumeroConto(String numeroConto);
}
