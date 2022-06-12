package br.edu.unicesumar.atividade.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.edu.unicesumar.atividade.domain.Cliente;
import br.edu.unicesumar.atividade.repository.ClienteRepository;
import lombok.Setter;
	

@Service
@Setter
public class ClienteService {
    
    @Autowired
    private ClienteRepository clienteRepository;

    public Page<Cliente> buscarTodos(Pageable pageable) {
        return this.clienteRepository.findAll(pageable);
    }

    public Optional<Cliente> buscarPorId(Long id) {
        return this.clienteRepository.findById(id);
    }

    public Cliente salvar(Cliente novoCliente) {
        return this.clienteRepository.save(novoCliente);
    }

    public Optional<Cliente> atualizar(Cliente clienteExistente) {
        if(clienteRepository.existsById(clienteExistente.getId())) {
        	Cliente clienteAtualizado = this.clienteRepository.save(clienteExistente);
            return Optional.of(clienteAtualizado);
        }
        return Optional.empty();
    }

    public void excluirPorId(Long id) {
        this.clienteRepository.deleteById(id);
    }

    public void excluirTodos() {
        this.clienteRepository.deleteAll();
    }

    public boolean existePorId(Long id) {
        return this.clienteRepository.existsById(id);
    }
}

