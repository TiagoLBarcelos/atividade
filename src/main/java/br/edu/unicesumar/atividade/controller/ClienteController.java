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

import br.edu.unicesumar.atividade.domain.Cliente;
import br.edu.unicesumar.atividade.service.ClienteService;
import lombok.SneakyThrows;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

	@Autowired
    private ClienteService clienteService;

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscarPorId(@PathVariable(name = "id") Long id) {
        Optional<Cliente> clienteOptional = this.clienteService.buscarPorId(id);

        if (clienteOptional.isPresent()) {
            return ResponseEntity.ok(clienteOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping
    public ResponseEntity<Page<Cliente>> buscarTodosPaginado(Pageable pageable) {
    	return ResponseEntity.ok(this.clienteService.buscarTodos(pageable));
    }

    @PostMapping
    @SneakyThrows
    public ResponseEntity<Cliente> criarNovoCliente(@Valid @RequestBody Cliente cliente) {
    	Cliente clienteCriado = this.clienteService.salvar(cliente);
        return ResponseEntity.created(new URI("/cliente/"+clienteCriado.getId())).body(clienteCriado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> criarNovoCliente(@PathVariable(name = "id") Long id,
            @RequestBody Cliente cliente) {

        if (!id.equals(cliente.getId())) {
            return ResponseEntity.badRequest().build();
        }

        Optional<Cliente> clienteAtualizadoOptional = this.clienteService.atualizar(cliente);

        if (clienteAtualizadoOptional.isPresent()) {
            return ResponseEntity.ok(clienteAtualizadoOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @DeleteMapping
    public ResponseEntity<Void> excluirTodos() {
        this.clienteService.excluirTodos();
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirPorId(@PathVariable(name = "id") Long id) {
        if (this.clienteService.existePorId(id)) {
            this.clienteService.excluirPorId(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }	
}
