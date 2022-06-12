package br.edu.unicesumar.atividade.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.unicesumar.atividade.domain.Prestador;

public interface PrestadorRepository extends JpaRepository<Prestador, Long>{
	
	Page<Prestador> findByNomeIgnoreCaseContaining(String nome, Pageable pageable);

}
