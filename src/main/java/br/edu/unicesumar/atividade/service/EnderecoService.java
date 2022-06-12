package br.edu.unicesumar.atividade.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.unicesumar.atividade.domain.Endereco;
import br.edu.unicesumar.atividade.repository.EnderecoRepository;
import lombok.Setter;

@Service
@Setter
public class EnderecoService {

	@Autowired
    private EnderecoRepository enderecoRepository;

    public Optional<Endereco> buscarPorId(Long id) {
        return this.enderecoRepository.findById(id);
    }

    public Endereco salvar(Endereco novoEndereco) {
        return this.enderecoRepository.save(novoEndereco);
    }

    public Optional<Endereco> atualizar(Endereco enderecoExistente) {
        if(enderecoRepository.existsById(enderecoExistente.getId())) {
        	Endereco enderecoAtualizado = this.enderecoRepository.save(enderecoExistente);
            return Optional.of(enderecoAtualizado);
        }
        return Optional.empty();
    }

    public void excluirPorId(Long id) {
        this.enderecoRepository.deleteById(id);
    }

    public void excluirTodos() {
        this.enderecoRepository.deleteAll();
    }

    public boolean existePorId(Long id) {
        return this.enderecoRepository.existsById(id);
    }	
}
