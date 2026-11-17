package com.vendramel.vendramel.base.domain.interfaces;

import com.vendramel.vendramel.base.domain.entidades.Pagamento;
import com.vendramel.vendramel.base.domain.entidades.Pedido;

public interface PagamentoInterface {
    Pagamento pagarviaPix(Pedido pedido);
    Pagamento pagarviaCartao(Pedido pedido,int parcelas, String numeroCartao);
}
