package it.project.repository;

import org.springframework.data.repository.ListCrudRepository;

import it.project.entity.Cliente;

public interface ClienteRepository extends ListCrudRepository<Cliente,Integer> {

	Cliente findByNomeCompleto(String nomeCompleto);
}
