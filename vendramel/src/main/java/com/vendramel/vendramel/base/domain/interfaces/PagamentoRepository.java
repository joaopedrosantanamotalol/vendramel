package com.vendramel.vendramel.base.domain.interfaces;

import com.vendramel.vendramel.base.domain.entidades.Pagamento;

public interface PagamentoRepository {

    Pagamento salvar(Pagamento pagamento, int pedidoId);

}