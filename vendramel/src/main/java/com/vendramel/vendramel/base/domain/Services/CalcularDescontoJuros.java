package com.vendramel.vendramel.base.domain.Services;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.vendramel.vendramel.base.domain.interfaces.AplicarDescontoInterface;

public class CalcularDescontoJuros implements AplicarDescontoInterface {

    private static final BigDecimal DESCONTO_CARTAO =
        new BigDecimal("0.03");

    private static final BigDecimal DESCONTO_PIX =
        new BigDecimal("0.08");

    private final BigDecimal taxaJurosMensal;

    public CalcularDescontoJuros(BigDecimal taxaJurosMensal) {
        this.taxaJurosMensal = taxaJurosMensal;
    }

    @Override
    public BigDecimal calcularDesconto(
        BigDecimal valorOriginal,
        String tipo,
        int parcelas
    ) {

        if (valorOriginal == null || valorOriginal.signum() <= 0) {
            throw new IllegalArgumentException("Valor inválido.");
        }

        if (parcelas < 1) {
            throw new IllegalArgumentException("Parcelas inválidas.");
        }

        BigDecimal valorFinal;

        switch (tipo.toUpperCase()) {

            case "CARTÃO":

                if (parcelas == 1) {
                    valorFinal = valorOriginal.multiply(
                        BigDecimal.ONE.subtract(DESCONTO_CARTAO)
                    );

                } else if (parcelas <= 3) {
                    valorFinal = valorOriginal;

                } else {
                    valorFinal = valorOriginal.multiply(
                        BigDecimal.ONE.add(taxaJurosMensal).pow(parcelas)
                    );
                }

                break;

            case "PIX":

                if (parcelas != 1) {
                    throw new IllegalArgumentException(
                        "PIX somente à vista."
                    );
                }

                valorFinal = valorOriginal.multiply(
                    BigDecimal.ONE.subtract(DESCONTO_PIX)
                );

                break;

            default:
                throw new IllegalArgumentException(
                    "Forma de pagamento inválida."
                );
        }

        return valorFinal.setScale(2, RoundingMode.HALF_UP);
    }
}