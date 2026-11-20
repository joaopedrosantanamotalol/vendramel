
package com.vendramel.vendramel.base.infra.repository.Pagamento;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vendramel.vendramel.base.infra.entidadesFrameWork.Pagamento;

public interface PagamentoJpaRepository
        extends JpaRepository<Pagamento, Integer> {

    List<Pagamento> findAllByPedido_Id(int pedidoId);
}
