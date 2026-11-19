package com.vendramel.vendramel.base.infra.ImplementacoesInterfaces.Carrinho;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.vendramel.vendramel.base.domain.interfaces.CarrinhoInterface;
import com.vendramel.vendramel.base.domain.entidades.Carrinho;
import com.vendramel.vendramel.base.infra.repository.Carrinho.CarrinhoJpaRepository;
import com.vendramel.vendramel.base.infra.repository.ItemCarrinho.ItemCarrinhoJpaRepository;
import com.vendramel.vendramel.base.infra.mapper.Carrinho.CarrinhoMapper;


@Repository
@Transactional
public class CarrinhoImp implements CarrinhoInterface {

    private final CarrinhoJpaRepository carrinhoRepository;
    private final ItemCarrinhoJpaRepository itemCarrinhoRepository;

    public CarrinhoImp(
            CarrinhoJpaRepository carrinhoRepository,
            ItemCarrinhoJpaRepository itemCarrinhoRepository) {

        this.carrinhoRepository = carrinhoRepository;
        this.itemCarrinhoRepository = itemCarrinhoRepository;
    }

    @Override
    public Carrinho visualizarCarrinho(int idCliente) {

        var carrinho = carrinhoRepository.findByCliente_Id(idCliente)
                .orElseThrow(() ->
                    new IllegalArgumentException("Carrinho não encontrado."));

        carrinho.setItens(
            itemCarrinhoRepository.findAllByCarrinho_Id(carrinho.getId())
        );

        return CarrinhoMapper.paraDominio(carrinho);
    }

    @Override
    public Carrinho alterarQuantidade(int idItem, int quantidade) {

        if (quantidade <= 0) {
            throw new IllegalArgumentException(
                "A quantidade deve ser maior que zero."
            );
        }

        var item = itemCarrinhoRepository.findById(idItem)
                .orElseThrow(() ->
                    new IllegalArgumentException("Item não encontrado."));

        item.setQuantidade(quantidade);

        itemCarrinhoRepository.save(item);

        return visualizarCarrinho(item.getCarrinho().getCliente().getId());
    }

    @Override
    public void removerItem(int idItem) {

        var item = itemCarrinhoRepository.findById(idItem)
                .orElseThrow(() ->
                    new IllegalArgumentException("Item não encontrado."));

        itemCarrinhoRepository.delete(item);
    }

    @Override
    public void destruirItens(int idCarrinho) {

        var itens = itemCarrinhoRepository.findAllByCarrinho_Id(idCarrinho);

        itemCarrinhoRepository.deleteAll(itens);
    }
    
}
