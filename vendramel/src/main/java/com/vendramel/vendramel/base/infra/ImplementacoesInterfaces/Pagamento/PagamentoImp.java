package com.vendramel.vendramel.base.infra.ImplementacoesInterfaces.Pagamento;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.vendramel.vendramel.base.domain.entidades.Pagamento;
import com.vendramel.vendramel.base.domain.interfaces.PagamentoRepository;
import com.vendramel.vendramel.base.infra.mapper.Pagamento.PagamentoMapper;
import com.vendramel.vendramel.base.infra.repository.Pagamento.PagamentoJpaRepository;
import com.vendramel.vendramel.base.infra.repository.Pedido.PedidoJpaRepository;

@Repository
public class PagamentoImp implements PagamentoRepository {

    private final PagamentoJpaRepository pagamentoRepository;
    private final PedidoJpaRepository pedidoRepository;

    public PagamentoImp(
            PagamentoJpaRepository pagamentoRepository,
            PedidoJpaRepository pedidoRepository) {

        this.pagamentoRepository = pagamentoRepository;
        this.pedidoRepository = pedidoRepository;
    }

    @Override
    @Transactional
    public Pagamento salvar(Pagamento pagamento, int pedidoId) {

        var pedido = pedidoRepository.findById(pedidoId)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Pedido não encontrado."));

        var pagamentoPersistencia =
                PagamentoMapper.paraPersistencia(pagamento, pedido);

        var pagamentoSalvo =
                pagamentoRepository.save(pagamentoPersistencia);

        return PagamentoMapper.paraDominio(pagamentoSalvo);
    }
}