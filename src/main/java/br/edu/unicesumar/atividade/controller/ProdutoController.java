package br.edu.unicesumar.atividade.controller;

import java.net.URI;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.unicesumar.atividade.domain.Produto;
import br.edu.unicesumar.atividade.service.ProdutoService;
import lombok.SneakyThrows;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

	@Autowired
    private ProdutoService produtoService;

    @GetMapping("/{id}")
    public ResponseEntity<Produto> buscarPorId(@PathVariable(name = "id") Long id) {
        Optional<Produto> produtoOptional = this.produtoService.buscarPorId(id);

        if (produtoOptional.isPresent()) {
            return ResponseEntity.ok(produtoOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping
    public ResponseEntity<Page<Produto>> buscarTodosPaginado(Pageable pageable) {
    	return ResponseEntity.ok(this.produtoService.buscarTodos(pageable));
    }

    @PostMapping
    @SneakyThrows
    public ResponseEntity<Produto> criarNovoProduto(@Valid @RequestBody Produto produto) {
    	Produto produtoCriado = this.produtoService.salvar(produto);
        return ResponseEntity.created(new URI("/produto/"+produtoCriado.getId())).body(produtoCriado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produto> criarNovoProduto(@PathVariable(name = "id") Long id,
            @RequestBody Produto produto) {

        if (!id.equals(produto.getId())) {
            return ResponseEntity.badRequest().build();
        }

        Optional<Produto> produtoAtualizadoOptional = this.produtoService.atualizar(produto);

        if (produtoAtualizadoOptional.isPresent()) {
            return ResponseEntity.ok(produtoAtualizadoOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @DeleteMapping
    public ResponseEntity<Void> excluirTodos() {
        this.produtoService.excluirTodos();
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirPorId(@PathVariable(name = "id") Long id) {
        if (this.produtoService.existePorId(id)) {
            this.produtoService.excluirPorId(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }	
}
