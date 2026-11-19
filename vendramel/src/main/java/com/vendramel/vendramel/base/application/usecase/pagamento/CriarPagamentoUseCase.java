package com.vendramel.vendramel.base.application.usecase.pagamento;

import java.math.BigDecimal;

import com.vendramel.vendramel.base.domain.Services.PagamentoService;
import com.vendramel.vendramel.base.domain.Services.PedidoService;
import com.vendramel.vendramel.base.domain.entidades.Pagamento;
import com.vendramel.vendramel.base.domain.entidades.Pedido;

public class CriarPagamentoUseCase {

    private final PedidoService pedidoService;
    private final PagamentoService pagamentoService;
    private final CalcularDescontoUseCase calcularDescontoUseCase;

    public CriarPagamentoUseCase(
        PedidoService pedidoService,
        PagamentoService pagamentoService,
        CalcularDescontoUseCase calcularDescontoUseCase
    ) {
        this.pedidoService = pedidoService;
        this.pagamentoService = pagamentoService;
        this.calcularDescontoUseCase = calcularDescontoUseCase;
    }

    public Pagamento executar(
        int pedidoId,
        String tipo,
        int parcelas
    ) {

        Pedido pedido = pedidoService.buscarPorId(pedidoId);

        if (pedido == null) {
            throw new RuntimeException("Pedido inexistente.");
        }

        if (pedido.getStatus() == null ||
            pedido.getStatus().equalsIgnoreCase("concluido")) {
            throw new RuntimeException(
                "Não é possível pagar um pedido concluído."
            );
        }

        BigDecimal valorFinal = calcularDescontoUseCase.executar(
            pedido.getValorTotal(),
            tipo,
            parcelas
        );

        return pagamentoService.pagar(
            pedido,
            tipo,
            parcelas,
            valorFinal
        );
    }
}