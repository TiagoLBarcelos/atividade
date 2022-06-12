package br.edu.unicesumar.atividade.controller;

import java.net.URI;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.unicesumar.atividade.domain.Endereco;
import br.edu.unicesumar.atividade.service.EnderecoService;
import lombok.SneakyThrows;

@RestController
@RequestMapping("/endereço")
public class EnderecoController {

	@Autowired
    private EnderecoService enderecoService;

    @GetMapping("/{id}")
    public ResponseEntity<Endereco> buscarPorId(@PathVariable(name = "id") Long id) {
        Optional<Endereco> enderecoOptional = this.enderecoService.buscarPorId(id);

        if (enderecoOptional.isPresent()) {
            return ResponseEntity.ok(enderecoOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PostMapping
    @SneakyThrows
    public ResponseEntity<Endereco> criarNovoEndereco(@Valid @RequestBody Endereco endereco) {
    	Endereco enderecoCriado = this.enderecoService.salvar(endereco);
        return ResponseEntity.created(new URI("/endereco/"+enderecoCriado.getId())).body(enderecoCriado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Endereco> criarNovoEndereco(@PathVariable(name = "id") Long id,
            @RequestBody Endereco endereco) {

        if (!id.equals(endereco.getId())) {
            return ResponseEntity.badRequest().build();
        }

        Optional<Endereco> enderecoAtualizadoOptional = this.enderecoService.atualizar(endereco);

        if (enderecoAtualizadoOptional.isPresent()) {
            return ResponseEntity.ok(enderecoAtualizadoOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @DeleteMapping
    public ResponseEntity<Void> excluirTodos() {
        this.enderecoService.excluirTodos();
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirPorId(@PathVariable(name = "id") Long id) {
        if (this.enderecoService.existePorId(id)) {
            this.enderecoService.excluirPorId(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }	
}
