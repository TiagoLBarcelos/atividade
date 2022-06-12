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

import br.edu.unicesumar.atividade.domain.Prestador;
import br.edu.unicesumar.atividade.service.PrestadorService;
import lombok.SneakyThrows;

@RestController
@RequestMapping("/prestador")
public class PrestadorController {

	@Autowired
    private PrestadorService prestadorService;

    @GetMapping("/{id}")
    public ResponseEntity<Prestador> buscarPorId(@PathVariable(name = "id") Long id) {
        Optional<Prestador> prestadorOptional = this.prestadorService.buscarPorId(id);

        if (prestadorOptional.isPresent()) {
            return ResponseEntity.ok(prestadorOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping
    public ResponseEntity<Page<Prestador>> buscarTodosPaginado(Pageable pageable) {
    	return ResponseEntity.ok(this.prestadorService.buscarTodos(pageable));
    }

    @PostMapping
    @SneakyThrows
    public ResponseEntity<Prestador> criarNovoPrestador(@Valid @RequestBody Prestador prestador) {
    	Prestador prestadorCriado = this.prestadorService.salvar(prestador);
        return ResponseEntity.created(new URI("/prestador/"+prestadorCriado.getId())).body(prestadorCriado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Prestador> criarNovoPrestador(@PathVariable(name = "id") Long id,
            @RequestBody Prestador prestador) {

        if (!id.equals(prestador.getId())) {
            return ResponseEntity.badRequest().build();
        }

        Optional<Prestador> prestadorAtualizadoOptional = this.prestadorService.atualizar(prestador);

        if (prestadorAtualizadoOptional.isPresent()) {
            return ResponseEntity.ok(prestadorAtualizadoOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @DeleteMapping
    public ResponseEntity<Void> excluirTodos() {
        this.prestadorService.excluirTodos();
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirPorId(@PathVariable(name = "id") Long id) {
        if (this.prestadorService.existePorId(id)) {
            this.prestadorService.excluirPorId(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }	
}
