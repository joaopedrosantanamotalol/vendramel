
package com.vendramel.vendramel.base.infra.ImplementacoesInterfaces.ItemCarrinho;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.vendramel.vendramel.base.domain.entidades.Carrinho;
import com.vendramel.vendramel.base.domain.interfaces.ItemCarrinhoInterface;
import com.vendramel.vendramel.base.infra.entidadesFrameWork.Cliente;
import com.vendramel.vendramel.base.infra.entidadesFrameWork.ItemCarrinho;
import com.vendramel.vendramel.base.infra.entidadesFrameWork.Livro;
import com.vendramel.vendramel.base.infra.mapper.Carrinho.CarrinhoMapper;
import com.vendramel.vendramel.base.infra.repository.Carrinho.CarrinhoJpaRepository;
import com.vendramel.vendramel.base.infra.repository.Cliente.ClienteJpaRepository;
import com.vendramel.vendramel.base.infra.repository.ItemCarrinho.ItemCarrinhoJpaRepository;
import com.vendramel.vendramel.base.infra.repository.Livro.LivroJpaRepository;

@Repository
public class ItemCarrinhoImp implements ItemCarrinhoInterface {

    private final CarrinhoJpaRepository carrinhoRepository;
    private final ClienteJpaRepository clienteRepository;
    private final LivroJpaRepository livroRepository;
    private final ItemCarrinhoJpaRepository itemRepository;

    public ItemCarrinhoImp(
            CarrinhoJpaRepository carrinhoRepository,
            ClienteJpaRepository clienteRepository,
            LivroJpaRepository livroRepository,
            ItemCarrinhoJpaRepository itemRepository) {

        this.carrinhoRepository = carrinhoRepository;
        this.clienteRepository = clienteRepository;
        this.livroRepository = livroRepository;
        this.itemRepository = itemRepository;
    }

    @Override
    @Transactional
    public Carrinho adicionarItem(int idCliente, int idLivro, int quantidade) {

        if (quantidade <= 0) {
            throw new IllegalArgumentException(
                "A quantidade deve ser maior que zero."
            );
        }

        Cliente cliente = clienteRepository.findById(idCliente)
                .orElseThrow(() ->
                    new IllegalArgumentException("Cliente não encontrado."));

        Livro livro = livroRepository.findById(idLivro)
                .orElseThrow(() ->
                    new IllegalArgumentException("Livro não encontrado."));

        com.vendramel.vendramel.base.infra.entidadesFrameWork.Carrinho carrinho =
                carrinhoRepository.findByCliente_Id(idCliente)
                .orElseGet(() -> {
                    var novo = new com.vendramel.vendramel.base.infra.entidadesFrameWork.Carrinho();
                    novo.setCliente(cliente);
                    novo.setDataCriacao(LocalDateTime.now());
                    novo.setItens(new ArrayList<>());
                    return carrinhoRepository.save(novo);
                });

        if (carrinho.getItens() == null) {
            carrinho.setItens(new ArrayList<>());
        }

        ItemCarrinho item = carrinho.getItens().stream()
                .filter(i -> i.getLivro().getId() == idLivro)
                .findFirst()
                .orElse(null);

        if (item != null) {
            item.setQuantidade(item.getQuantidade() + quantidade);
        } else {
            item = new ItemCarrinho();
            item.setCarrinho(carrinho);
            item.setLivro(livro);
            item.setQuantidade(quantidade);

            carrinho.getItens().add(item);
        }

        itemRepository.save(item);

        return CarrinhoMapper.paraDominio(carrinho);
    }
}
