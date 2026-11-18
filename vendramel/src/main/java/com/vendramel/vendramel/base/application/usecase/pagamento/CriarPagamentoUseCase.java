package com.vendramel.vendramel.base.application.usecase.pagamento;

import com.vendramel.vendramel.base.domain.Services.PagamentoService;
import com.vendramel.vendramel.base.domain.Services.PedidoService;
import com.vendramel.vendramel.base.domain.entidades.Pagamento;
import com.vendramel.vendramel.base.domain.entidades.Pedido;

public class CriarPagamentoUseCase {
    
    private final PedidoService service;
    private final PagamentoService pagamentoService;

    public CriarPagamentoUseCase(PagamentoService pagamentoService,PedidoService service) {
        this.pagamentoService = pagamentoService;
        this.service = service;
    }

     public Pagamento executar(
        int pedidoId,
        String tipo,
        int parcelas
    ) {

        Pedido ped = service.buscarPorId(pedidoId);

        if(ped == null){
            throw new RuntimeException("não existe pedido com esse id!");
        }

        if(ped.getStatus() == null || ped.getStatus().equals("concluido")){
            throw new RuntimeException(  "Não é possível realizar pagamento de um pedido concluído ou inesistente!");
        }

        return pagamentoService.pagar(
            ped,
            tipo,
            parcelas
        );
    }

}
