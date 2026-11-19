package com.vendramel.vendramel.base.infra.ImplementacoesInterfaces.Pagamento;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.vendramel.vendramel.base.domain.entidades.Pagamento;
import com.vendramel.vendramel.base.domain.entidades.Pedido;
import com.vendramel.vendramel.base.domain.interfaces.PagamentoInterface;

@Component("CARTAO")
public class PagamentoCartao implements PagamentoInterface {

    @Override
    public Pagamento pagar(Pedido pedido, BigDecimal valorFinal) {

        Pagamento pagamento = new Pagamento();

        pagamento.setFormaPagamento("CARTÃO");
        pagamento.setValor(valorFinal);
        pagamento.setStatus("AUTORIZADO");
        pagamento.setDataPagamento(LocalDateTime.now());

        return pagamento;
    }
}