package br.edu.unicesumar.atividade.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.unicesumar.atividade.domain.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Long>{
	
	
}
