package com.vendramel.vendramel.base.presentation.Pedido;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.vendramel.vendramel.base.domain.Services.PedidoService;
import com.vendramel.vendramel.base.domain.entidades.Pedido;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PostMapping
    public ResponseEntity<Pedido> efetuarPedido(
            @RequestParam int idCliente,
            @RequestParam int idEndereco) {

        Pedido pedido = pedidoService.efetuarPedido(
                idCliente, idEndereco);

        return ResponseEntity.status(HttpStatus.CREATED).body(pedido);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> buscarPorId(
            @PathVariable int id) {

        return ResponseEntity.ok(
                pedidoService.buscarPorId(id));
    }

    @PatchMapping("/{id}/finalizar")
    public ResponseEntity<Pedido> finalizarPedido(
            @PathVariable int id) {

        return ResponseEntity.ok(
                pedidoService.finalizarPedido(id));
    }

    @PatchMapping("/{id}/cancelar")
    public ResponseEntity<Pedido> cancelarPedido(
            @PathVariable int id) {

        return ResponseEntity.ok(
                pedidoService.cancelarPedido(id));
    }
}

