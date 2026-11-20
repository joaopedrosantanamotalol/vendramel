package com.vendramel.vendramel.base.application.usecase.pagamento;

import java.math.BigDecimal;

import com.vendramel.vendramel.base.domain.Services.PedidoService;
import com.vendramel.vendramel.base.domain.entidades.Pagamento;
import com.vendramel.vendramel.base.domain.entidades.Pedido;
import com.vendramel.vendramel.base.domain.interfaces.ProcessarPagamentoInterface;

public class CriarPagamentoUseCase {

    private final PedidoService pedidoService;
    private final ProcessarPagamentoInterface processarPagamento;
    private final CalcularDescontoUseCase calcularDescontoUseCase;

    public CriarPagamentoUseCase(
            PedidoService pedidoService,
            ProcessarPagamentoInterface processarPagamento,
            CalcularDescontoUseCase calcularDescontoUseCase) {

        this.pedidoService = pedidoService;
        this.processarPagamento = processarPagamento;
        this.calcularDescontoUseCase = calcularDescontoUseCase;
    }

    public Pagamento executar(
            int pedidoId,
            String tipo,
            int parcelas) {

        Pedido pedido = pedidoService.buscarPorId(pedidoId);

        if (pedido == null) {
            throw new RuntimeException("Pedido inexistente.");
        }

        if (!"PENDENTE".equalsIgnoreCase(pedido.getStatus())) {
            throw new IllegalStateException(
                    "Somente pedidos pendentes podem receber pagamento.");
        }

        BigDecimal valorFinal = calcularDescontoUseCase.executar(
                pedido.getValorTotal(),
                tipo,
                parcelas
        );

        return processarPagamento.executar(
                pedidoId,
                tipo,
                parcelas,
                valorFinal
        );
    }
}