package com.vendramel.vendramel.base.infra.config;

import java.math.BigDecimal;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.vendramel.vendramel.base.domain.Services.PagamentoService;
import com.vendramel.vendramel.base.domain.Services.CalcularDescontoJuros;
import com.vendramel.vendramel.base.domain.interfaces.AplicarDescontoInterface;
import com.vendramel.vendramel.base.domain.interfaces.PagamentoInterface;

@Configuration
public class PagamentoConfig {

    @Bean
    public AplicarDescontoInterface aplicarDesconto() {
        return new CalcularDescontoJuros(new BigDecimal("0.02"));
    }

    @Bean
    public PagamentoService pagamentoService(
        AplicarDescontoInterface aplicarDesconto,
        Map<String, PagamentoInterface> pagamentos
    ) {
        return new PagamentoService(aplicarDesconto, pagamentos);
    }
}