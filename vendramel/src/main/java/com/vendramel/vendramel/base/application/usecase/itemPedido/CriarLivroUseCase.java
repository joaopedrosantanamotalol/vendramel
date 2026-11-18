package com.vendramel.vendramel.base.application.usecase.itemPedido;

import com.vendramel.vendramel.base.domain.Services.ItemPedidoService;
import com.vendramel.vendramel.base.domain.entidades.ItemPedido;

public class CriarLivroUseCase {
    
    private final ItemPedidoService service;

    public CriarLivroUseCase(ItemPedidoService service) {
        this.service = service;
    }

    public ItemPedido executar(ItemPedido pedido){
        return service.criar(pedido);
    }

}
