package br.edu.unicesumar.atividade.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.unicesumar.atividade.domain.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long>{

	Page<Pedido> findByIdIgnoreCaseContaining(Long id, Pageable pageable);
}
