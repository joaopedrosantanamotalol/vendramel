package com.vendramel.vendramel.base.presentation.Carrinho;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.vendramel.vendramel.base.application.dto.quantidade.AlterarQuantidadeRequest;
import com.vendramel.vendramel.base.domain.Services.CarrinhoService;
import com.vendramel.vendramel.base.domain.entidades.Carrinho;

@RestController
@RequestMapping("/carrinhos")
public class CarrinhoController {

    private final CarrinhoService carrinhoService;

    public CarrinhoController(CarrinhoService carrinhoService) {
        this.carrinhoService = carrinhoService;
    }

    @GetMapping("/{idCliente}")
    public ResponseEntity<Carrinho> visualizarCarrinho(
            @PathVariable int idCliente) {

        return ResponseEntity.ok(
                carrinhoService.visualizarCarrinho(idCliente)
        );
    }

    @GetMapping("/{idCliente}/itens")
    public ResponseEntity<Carrinho> visualizarItem(
            @PathVariable int idCliente) {

        return ResponseEntity.ok(
                carrinhoService.vizualizarItem(idCliente)
        );
    }

    @PatchMapping("/itens/{idItem}/quantidade")
    public ResponseEntity<Carrinho> alterarQuantidade(
            @PathVariable int idItem,
            @RequestBody AlterarQuantidadeRequest request) {

        return ResponseEntity.ok(
            carrinhoService.alterarQuantidade(
                idItem,
                request.quantidade()
            )
        );
    }

    @DeleteMapping("/itens/{idItem}")
    public ResponseEntity<Void> removerItem(
            @PathVariable int idItem) {

        carrinhoService.removerItem(idItem);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{idCarrinho}/itens/todos")
    public ResponseEntity<Void> destruirItens(
            @PathVariable int idCarrinho) {

        carrinhoService.destruirItens(idCarrinho);

        return ResponseEntity.noContent().build();
    }
}
