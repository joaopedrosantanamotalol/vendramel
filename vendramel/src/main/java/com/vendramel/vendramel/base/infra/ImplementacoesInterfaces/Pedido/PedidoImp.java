
package com.vendramel.vendramel.base.infra.ImplementacoesInterfaces.Pedido;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.vendramel.vendramel.base.domain.Services.FreteService;
import com.vendramel.vendramel.base.domain.interfaces.PedidoInterface;
import com.vendramel.vendramel.base.infra.entidadesFrameWork.Carrinho;
import com.vendramel.vendramel.base.infra.entidadesFrameWork.Cliente;
import com.vendramel.vendramel.base.infra.entidadesFrameWork.Endereco;
import com.vendramel.vendramel.base.infra.entidadesFrameWork.ItemCarrinho;
import com.vendramel.vendramel.base.infra.entidadesFrameWork.ItemPedido;
import com.vendramel.vendramel.base.infra.entidadesFrameWork.Pedido;
import com.vendramel.vendramel.base.infra.mapper.Pedido.PedidoMapper;
import com.vendramel.vendramel.base.infra.repository.Carrinho.CarrinhoJpaRepository;
import com.vendramel.vendramel.base.infra.repository.Cliente.ClienteJpaRepository;
import com.vendramel.vendramel.base.infra.repository.Endereco.EnderecoJpaRepository;
import com.vendramel.vendramel.base.infra.repository.ItemCarrinho.ItemCarrinhoJpaRepository;
import com.vendramel.vendramel.base.infra.repository.Pedido.PedidoJpaRepository;

import com.vendramel.vendramel.base.infra.mapper.Endereco.EnderecoMapper;
import com.vendramel.vendramel.base.infra.mapper.ItemCarrinho.ItemCarrinhoMapper;

@Repository
@Transactional
public class PedidoImp implements PedidoInterface {

    private final PedidoJpaRepository pedidoRepository;
    private final ClienteJpaRepository clienteRepository;
    private final EnderecoJpaRepository enderecoRepository;
    private final CarrinhoJpaRepository carrinhoRepository;
    private final ItemCarrinhoJpaRepository itemCarrinhoRepository;
    private final FreteService freteService;
    

    public PedidoImp(
            PedidoJpaRepository pedidoRepository,
            ClienteJpaRepository clienteRepository,
            EnderecoJpaRepository enderecoRepository,
            CarrinhoJpaRepository carrinhoRepository,
            ItemCarrinhoJpaRepository itemCarrinhoRepository,
            FreteService freteService) {

        this.pedidoRepository = pedidoRepository;
        this.clienteRepository = clienteRepository;
        this.enderecoRepository = enderecoRepository;
        this.carrinhoRepository = carrinhoRepository;
        this.itemCarrinhoRepository = itemCarrinhoRepository;
        this.freteService = freteService;
    }

    @Override
    public com.vendramel.vendramel.base.domain.entidades.Pedido
            efetuarPedido(int idCliente, int idEndereco) {

        Cliente cliente = clienteRepository.findById(idCliente)
                .orElseThrow(() ->
                        new IllegalArgumentException("Cliente não encontrado."));

        Endereco endereco = enderecoRepository.findById(idEndereco)
                .orElseThrow(() ->
                        new IllegalArgumentException("Endereço não encontrado."));

        Carrinho carrinho = carrinhoRepository.findByCliente_Id(idCliente)
                .orElseThrow(() ->
                        new IllegalArgumentException("Carrinho não encontrado."));

        List<ItemCarrinho> itensCarrinho =
                itemCarrinhoRepository.findAllByCarrinho_Id(carrinho.getId());

        if (itensCarrinho.isEmpty()) {
            throw new IllegalStateException("O carrinho está vazio.");
        }

        Pedido pedido = new Pedido();

        pedido.setCliente(cliente);
        pedido.setEndereco(endereco);
        pedido.setDataPedido(LocalDateTime.now());
        pedido.setStatus("PENDENTE");

        BigDecimal subtotal = BigDecimal.ZERO;

        var enderecoDominio = EnderecoMapper.paraDominio(endereco);

        var itensCarrinhoDominio = itensCarrinho.stream()
                .map(ItemCarrinhoMapper::paraDominio)
                .toList();

        BigDecimal frete = freteService.calcularFrete(
                enderecoDominio,
                itensCarrinhoDominio
        );

        BigDecimal desconto = BigDecimal.ZERO;

        List<ItemPedido> itensPedido = new ArrayList<>();

        for (ItemCarrinho itemCarrinho : itensCarrinho) {

            if (itemCarrinho.getQuantidade() <= 0) {
                throw new IllegalStateException(
                        "A quantidade do item deve ser maior que zero.");
            }

            BigDecimal precoUnitario =
                    itemCarrinho.getLivro().getPrecoVenda();

            BigDecimal valorItem = precoUnitario.multiply(
                    BigDecimal.valueOf(itemCarrinho.getQuantidade()));

            subtotal = subtotal.add(valorItem);

            ItemPedido itemPedido = new ItemPedido();

            itemPedido.setQuantidade(itemCarrinho.getQuantidade());
            itemPedido.setPrecoUnitario(precoUnitario);
            itemPedido.setLivro(itemCarrinho.getLivro());
            itemPedido.setPedido(pedido);

            itensPedido.add(itemPedido);
        }

        pedido.setSubtotal(subtotal);
        pedido.setValorFrete(frete);
        pedido.setDesconto(desconto);
        pedido.setValorTotal(subtotal.add(frete).subtract(desconto));
        pedido.setItens(itensPedido);

        Pedido pedidoSalvo = pedidoRepository.save(pedido);

        // Limpa os itens do carrinho após salvar o pedido.
        itemCarrinhoRepository.deleteAll(itensCarrinho);

        return PedidoMapper.paraDominio(pedidoSalvo);
    }

    @Override
    public com.vendramel.vendramel.base.domain.entidades.Pedido
            finalizarPedido(int idPedido) {

        Pedido pedido = pedidoRepository.findById(idPedido)
                .orElseThrow(() ->
                        new IllegalArgumentException("Pedido não encontrado."));

        if (!"PENDENTE".equals(pedido.getStatus())) {
            throw new IllegalStateException(
                    "Somente pedidos pendentes podem ser finalizados.");
        }

        pedido.setStatus("FINALIZADO");

        return PedidoMapper.paraDominio(
                pedidoRepository.save(pedido));
    }

    @Override
    public com.vendramel.vendramel.base.domain.entidades.Pedido
            cancelarPedido(int idPedido) {

        Pedido pedido = pedidoRepository.findById(idPedido)
                .orElseThrow(() ->
                        new IllegalArgumentException("Pedido não encontrado."));

        if (!"PENDENTE".equals(pedido.getStatus())) {
            throw new IllegalStateException(
                    "Somente pedidos pendentes podem ser cancelados.");
        }

        pedido.setStatus("CANCELADO");

        return PedidoMapper.paraDominio(
                pedidoRepository.save(pedido));
    }

    @Override
    public com.vendramel.vendramel.base.domain.entidades.Pedido
            buscarPorId(int id) {

        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("Pedido não encontrado."));

        return PedidoMapper.paraDominio(pedido);
    }
}
