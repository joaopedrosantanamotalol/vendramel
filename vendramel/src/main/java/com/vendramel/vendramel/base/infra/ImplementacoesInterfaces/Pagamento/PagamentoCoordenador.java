    package com.vendramel.vendramel.base.infra.ImplementacoesInterfaces.Pagamento;

    import java.math.BigDecimal;

    import org.springframework.stereotype.Service;
    import org.springframework.transaction.annotation.Transactional;

    import com.vendramel.vendramel.base.domain.Services.PagamentoService;
    import com.vendramel.vendramel.base.domain.Services.PedidoService;
    import com.vendramel.vendramel.base.domain.Services.LivroService;
    import com.vendramel.vendramel.base.domain.Services.CarrinhoService;

    import com.vendramel.vendramel.base.domain.entidades.Pagamento;
    import com.vendramel.vendramel.base.domain.interfaces.PagamentoRepository;

    import com.vendramel.vendramel.base.infra.mapper.Pedido.PedidoMapper;
    import com.vendramel.vendramel.base.infra.repository.Pedido.PedidoJpaRepository;
    import com.vendramel.vendramel.base.domain.interfaces.ProcessarPagamentoInterface;

    @Service
    public class PagamentoCoordenador implements ProcessarPagamentoInterface {

        private final PagamentoService pagamentoService;
        private final PagamentoRepository pagamentoRepository;
        private final PedidoService pedidoService;
        private final LivroService livroService;
        private final CarrinhoService carrinhoService;
        private final PedidoJpaRepository pedidoJpaRepository;

        public PagamentoCoordenador(
                PagamentoService pagamentoService,
                PagamentoRepository pagamentoRepository,
                PedidoService pedidoService,
                LivroService livroService,
                CarrinhoService carrinhoService,
                PedidoJpaRepository pedidoJpaRepository) {

            this.pagamentoService = pagamentoService;
            this.pagamentoRepository = pagamentoRepository;
            this.pedidoService = pedidoService;
            this.livroService = livroService;
            this.carrinhoService = carrinhoService;
            this.pedidoJpaRepository = pedidoJpaRepository;
        }

        @Override   
        @Transactional
        public Pagamento executar(
                int pedidoId,
                String tipo,
                int parcelas,
                BigDecimal valorFinal) {

            // 1. Busca o pedido com bloqueio
            var pedidoPersistencia = pedidoJpaRepository
                    .buscarPorIdComBloqueio(pedidoId)
                    .orElseThrow(() ->
                            new IllegalArgumentException("Pedido não encontrado."));

            // 2. Verifica o status
            if (!"PENDENTE".equalsIgnoreCase(pedidoPersistencia.getStatus())) {
                throw new IllegalStateException(
                        "Este pedido não está disponível para pagamento.");
            }

            // 3. Converte o pedido para domínio
            var pedido = PedidoMapper.paraDominio(pedidoPersistencia);

            // 4. Executa a estratégia de pagamento
            Pagamento pagamento = pagamentoService.pagar(
                    pedido,
                    tipo,
                    parcelas,
                    valorFinal
            );

            // 5. Verifica a aprovação
            if (!"APROVADO".equalsIgnoreCase(pagamento.getStatus())) {
                throw new IllegalStateException("Pagamento não aprovado.");
            }

            // 6. Persiste o pagamento
            Pagamento pagamentoSalvo = pagamentoRepository.salvar(
                    pagamento,
                    pedidoId
            );

            // 7. Atualiza o estoque
            livroService.atualizarEstoque(pedido.getItens());

            // 8. Finaliza o pedido
            pedidoService.finalizarPedido(pedidoId);

            // 9. Localiza o carrinho e exclui seus itens
            int clienteId = pedidoPersistencia.getCliente().getId();

            var carrinho = carrinhoService.visualizarCarrinho(clienteId);

            carrinhoService.destruirItens(carrinho.getId());

            return pagamentoSalvo;
        }
    }