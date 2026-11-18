package com.vendramel.vendramel.base.domain.interfaces;

import java.math.BigDecimal;

import com.vendramel.vendramel.base.domain.entidades.Pagamento;
import com.vendramel.vendramel.base.domain.entidades.Pedido;

public interface PagamentoInterface {
    Pagamento pagar(Pedido pedido, BigDecimal valorFinal);
}
