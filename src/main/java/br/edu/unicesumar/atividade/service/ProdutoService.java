package br.edu.unicesumar.atividade.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.edu.unicesumar.atividade.domain.Produto;
import br.edu.unicesumar.atividade.repository.ProdutoRepository;
import lombok.Setter;

@Service
@Setter
public class ProdutoService {

	@Autowired
    private ProdutoRepository produtoRepository;

    public Page<Produto> buscarTodos(Pageable pageable) {
        return this.produtoRepository.findAll(pageable);
    }

    public Optional<Produto> buscarPorId(Long id) {
        return this.produtoRepository.findById(id);
    }

    public Produto salvar(Produto novoProduto) {
        return this.produtoRepository.save(novoProduto);
    }

    public Optional<Produto> atualizar(Produto produtoExistente) {
        if(produtoRepository.existsById(produtoExistente.getId())) {
        	Produto produtoAtualizado = this.produtoRepository.save(produtoExistente);
            return Optional.of(produtoAtualizado);
        }
        return Optional.empty();
    }

    public void excluirPorId(Long id) {
        this.produtoRepository.deleteById(id);
    }

    public void excluirTodos() {
        this.produtoRepository.deleteAll();
    }

    public boolean existePorId(Long id) {
        return this.produtoRepository.existsById(id);
    }	
}
