package com.vendramel.vendramel.base.application.usecase.pagamento;

import java.math.BigDecimal;

import com.vendramel.vendramel.base.domain.interfaces.AplicarDescontoInterface;

public class CalcularDescontoUseCase {

    private final AplicarDescontoInterface aplicarDesconto;

    public CalcularDescontoUseCase(
        AplicarDescontoInterface aplicarDesconto
    ) {
        this.aplicarDesconto = aplicarDesconto;
    }

    public BigDecimal executar(
        BigDecimal valorOriginal,
        String tipo,
        int parcelas
    ) {

        return aplicarDesconto.calcularDesconto(
            valorOriginal,
            tipo,
            parcelas
        );
    }
}