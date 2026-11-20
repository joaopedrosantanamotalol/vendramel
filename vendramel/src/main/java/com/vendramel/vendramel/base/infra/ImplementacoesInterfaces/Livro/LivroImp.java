
package com.vendramel.vendramel.base.infra.ImplementacoesInterfaces.Livro;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.vendramel.vendramel.base.domain.entidades.ItemPedido;
import com.vendramel.vendramel.base.domain.entidades.Livro;
import com.vendramel.vendramel.base.domain.interfaces.LivroInterface;
import com.vendramel.vendramel.base.infra.mapper.Livro.LivroMapper;
import com.vendramel.vendramel.base.infra.repository.Livro.LivroJpaRepository;

@Repository
@Transactional
public class LivroImp implements LivroInterface {

    private final LivroJpaRepository livroRepository;

    public LivroImp(LivroJpaRepository livroRepository) {
        this.livroRepository = livroRepository;
    }

    @Override
    public BigDecimal calcularPreco(Livro livro) {
        return livro.getPrecoVenda();
    }

    @Override
    public void atualizarEstoque(List<ItemPedido> itens) {

        itens.forEach(item -> {

            var livro = livroRepository.buscarPorIdComBloqueio(item.getLivro().getId())
                    .orElseThrow(() ->
                            new IllegalArgumentException(
                                    "Livro não encontrado."));

            int quantidadeAtual = livro.getQuantidadeEstoque();
            int quantidadeComprada = item.getQuantidade();

            if (quantidadeComprada <= 0) {
                throw new IllegalArgumentException(
                        "Quantidade do item deve ser maior que zero.");
            }

            if (quantidadeAtual < quantidadeComprada) {
                throw new IllegalStateException(
                        "Estoque insuficiente para o livro: "
                                + livro.getTitulo());
            }

            livro.setQuantidadeEstoque(
                    quantidadeAtual - quantidadeComprada);

            livroRepository.save(livro);
        });
    }

    @Override
    public Livro buscarPorid(int id) {

        var livro = livroRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Livro não encontrado."));

        return LivroMapper.paraDominio(livro);
    }

    @Override
    public Livro cadastrar(Livro livro) {

        var persistencia = LivroMapper.paraPersistencia(livro);

        var salvo = livroRepository.save(persistencia);

        return LivroMapper.paraDominio(salvo);
    }
}
