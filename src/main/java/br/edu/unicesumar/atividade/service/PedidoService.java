package br.edu.unicesumar.atividade.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.edu.unicesumar.atividade.domain.Pedido;
import br.edu.unicesumar.atividade.repository.PedidoRepository;
import lombok.Setter;

@Service
@Setter
public class PedidoService {
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	public Page<Pedido> buscarTodos(Pageable pageable) {
		return this.pedidoRepository.findAll(pageable);
	}
	
	public Optional<Pedido> buscarPorId(Long id){
		return this.pedidoRepository.findById(id);
	}
	
	public Pedido salvar(Pedido novoPedido) {
		return this.pedidoRepository.save(novoPedido);
	}
	
	public boolean existePorId(Long id) {
		return this.pedidoRepository.existsById(id);
	}
}
