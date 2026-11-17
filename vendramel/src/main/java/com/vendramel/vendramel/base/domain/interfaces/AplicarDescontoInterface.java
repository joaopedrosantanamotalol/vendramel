package com.vendramel.vendramel.base.domain.interfaces;

import java.math.BigDecimal;

public interface AplicarDescontoInterface {
    BigDecimal calcularDesconto(BigDecimal valor, String forma, int parcelas);
}
