package com.vendramel.vendramel.base.domain.Services;

import java.math.BigDecimal;

import com.vendramel.vendramel.base.domain.entidades.Pagamento;
import com.vendramel.vendramel.base.domain.entidades.Pedido;
import com.vendramel.vendramel.base.domain.interfaces.AplicarDescontoInterface;
import com.vendramel.vendramel.base.domain.interfaces.PagamentoInterface;

public class PagamentoService {
    
    private final AplicarDescontoInterface aplicardescontointer;
    private final PagamentoInterface pagamentoiter;

    public PagamentoService(AplicarDescontoInterface aplicardescontointer, PagamentoInterface pagamentoiter) {
        this.aplicardescontointer = aplicardescontointer;
        this.pagamentoiter = pagamentoiter;
    }

    public Pagamento pagarviaPix(Pedido pedido){

       BigDecimal valortotal = pedido.getValorTotal();

       BigDecimal valorComDesconto = aplicardescontointer.calcularDesconto(valortotal, "PIX", 1);
       
       pedido.setValorTotal(valorComDesconto);

        try {
            return pagamentoiter.pagarviaPix(pedido);
        } finally {
            pedido.setValorTotal(valortotal);
    }

    }

    public Pagamento pagarviaCartao(Pedido pedido,int parcelas, String numeroCartao){

    BigDecimal valorOriginal = pedido.getValorTotal();

    BigDecimal valorComDesconto =
            aplicardescontointer.calcularDesconto(
                    valorOriginal,
                    "CARTAO",
                    parcelas
            );

    pedido.setValorTotal(valorComDesconto);

    try {
        return pagamentoiter.pagarviaCartao(
                pedido,
                parcelas,
                numeroCartao
        );
    } finally {
        pedido.setValorTotal(valorOriginal);
    }

    }



}
