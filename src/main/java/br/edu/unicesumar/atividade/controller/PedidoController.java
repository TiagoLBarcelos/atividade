package br.edu.unicesumar.atividade.controller;


import java.net.URI;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.unicesumar.atividade.domain.Pedido;
import br.edu.unicesumar.atividade.service.PedidoService;
import lombok.SneakyThrows;

@RestController
@RequestMapping("/pedido")
public class PedidoController {

	@Autowired
	private PedidoService pedidoService;
	
	@GetMapping("/{id}")
    public ResponseEntity<Pedido> buscarPorId(@PathVariable(name = "id") Long id) {
        Optional<Pedido> pedidoOptional = this.pedidoService.buscarPorId(id);

        if (pedidoOptional.isPresent()) {
            return ResponseEntity.ok(pedidoOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping
    public ResponseEntity<Page<Pedido>> buscarTodosPaginado(Pageable pageable) {
    	return ResponseEntity.ok(this.pedidoService.buscarTodos(pageable));
    }

    @PostMapping
    @SneakyThrows
    public ResponseEntity<Pedido> criarNovoPedido(@Valid @RequestBody Pedido pedido) {
    	Pedido pedidoCriado = this.pedidoService.salvar(pedido);
        return ResponseEntity.created(new URI("/pedido/"+pedidoCriado.getId())).body(pedidoCriado);
    }
	
}
