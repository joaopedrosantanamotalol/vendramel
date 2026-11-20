package com.vendramel.vendramel.base.domain.Services;

import java.math.BigDecimal;
import java.util.Map;

import com.vendramel.vendramel.base.domain.entidades.Pagamento;
import com.vendramel.vendramel.base.domain.entidades.Pedido;
import com.vendramel.vendramel.base.domain.interfaces.PagamentoInterface;

public class PagamentoService {

    private final Map<String, PagamentoInterface> pagamentos;

    public PagamentoService(Map<String, PagamentoInterface> pagamentos) {
        this.pagamentos = pagamentos;
    }

    public Pagamento pagar(
            Pedido pedido,
            String tipo,
            int parcelas,
            BigDecimal valorFinal) {

        if (tipo == null || tipo.isBlank()) {
            throw new IllegalArgumentException("Informe a forma de pagamento.");
        }

        PagamentoInterface estrategia = pagamentos.get(tipo.toUpperCase());

        if (estrategia == null) {
            throw new IllegalArgumentException("Forma de pagamento inválida.");
        }

        Pagamento pagamento = estrategia.pagar(pedido, valorFinal);

        pagamento.setParcelas(parcelas);

        if ("CONFIRMADO".equalsIgnoreCase(pagamento.getStatus())
                || "AUTORIZADO".equalsIgnoreCase(pagamento.getStatus())) {
            pagamento.setStatus("APROVADO");
        }

        return pagamento;
    }
}