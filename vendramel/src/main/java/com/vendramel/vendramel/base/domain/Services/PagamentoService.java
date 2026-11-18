package com.vendramel.vendramel.base.domain.Services;

import java.math.BigDecimal;
import java.util.Map;

import com.vendramel.vendramel.base.domain.entidades.Pagamento;
import com.vendramel.vendramel.base.domain.entidades.Pedido;
import com.vendramel.vendramel.base.domain.interfaces.AplicarDescontoInterface;
import com.vendramel.vendramel.base.domain.interfaces.PagamentoInterface;


public class PagamentoService {

    private final AplicarDescontoInterface aplicarDesconto;
    private final Map<String, PagamentoInterface> pagamentos;

    public PagamentoService(
        AplicarDescontoInterface aplicarDesconto,
        Map<String, PagamentoInterface> pagamentos
    ) {
        this.aplicarDesconto = aplicarDesconto;
        this.pagamentos = pagamentos;
    }

    public Pagamento pagar(
        Pedido pedido,
        String tipo,
        int parcelas
    ) { 

        PagamentoInterface estrategia = pagamentos.get(tipo);

        if (estrategia == null) {
            throw new IllegalArgumentException(
                "Forma de pagamento inválida"
            );
        }

        BigDecimal valorOriginal = pedido.getValorTotal();
        BigDecimal valorFinal = aplicarDesconto.calcularDesconto(valorOriginal, tipo, parcelas);

            return estrategia.pagar(pedido, valorFinal);
    }
}
