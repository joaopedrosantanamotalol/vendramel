package com.vendramel.vendramel.base.infra.Interfaces;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import com.vendramel.vendramel.base.domain.entidades.Pagamento;
import com.vendramel.vendramel.base.domain.entidades.Pedido;
import com.vendramel.vendramel.base.domain.interfaces.PagamentoInterface;

@Component("CARTÃO")  
public class PagamentoCartao implements PagamentoInterface {

@Override  
public Pagamento pagar(Pedido pedido, BigDecimal valorfinal){

    // colocar lógica de pagamento

    Pagamento pagamento = new Pagamento();
    return pagamento;


}

}
