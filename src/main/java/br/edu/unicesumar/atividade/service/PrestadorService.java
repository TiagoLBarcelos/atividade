package br.edu.unicesumar.atividade.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.edu.unicesumar.atividade.domain.Prestador;
import br.edu.unicesumar.atividade.repository.PrestadorRepository;
import lombok.Setter;

@Service
@Setter
public class PrestadorService {
	@Autowired
    private PrestadorRepository prestadorRepository;

    public Page<Prestador> buscarTodos(Pageable pageable) {
        return this.prestadorRepository.findAll(pageable);
    }

    public Optional<Prestador> buscarPorId(Long id) {
        return this.prestadorRepository.findById(id);
    }

    public Prestador salvar(Prestador novoPedido) {
        return this.prestadorRepository.save(novoPedido);
    }

    public Optional<Prestador> atualizar(Prestador pedidoExistente) {
        if(prestadorRepository.existsById(pedidoExistente.getId())) {
        	Prestador pedidoAtualizado = this.prestadorRepository.save(pedidoExistente);
            return Optional.of(pedidoAtualizado);
        }
        return Optional.empty();
    }

    public void excluirPorId(Long id) {
        this.prestadorRepository.deleteById(id);
    }

    public void excluirTodos() {
        this.prestadorRepository.deleteAll();
    }

    public boolean existePorId(Long id) {
        return this.prestadorRepository.existsById(id);
    }
}
