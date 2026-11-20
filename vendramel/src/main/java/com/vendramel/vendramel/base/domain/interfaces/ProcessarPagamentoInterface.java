package com.vendramel.vendramel.base.domain.interfaces;

import java.math.BigDecimal;

import com.vendramel.vendramel.base.domain.entidades.Pagamento;

public interface ProcessarPagamentoInterface {

    Pagamento executar(
            int pedidoId,
            String tipo,
            int parcelas,
            BigDecimal valorFinal
    );
}