package br.edu.unicesumar.atividade.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.unicesumar.atividade.domain.ItemPedido;

public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Long>{

}
