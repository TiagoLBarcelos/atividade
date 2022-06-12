package br.edu.unicesumar.atividade.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.unicesumar.atividade.domain.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{

}
