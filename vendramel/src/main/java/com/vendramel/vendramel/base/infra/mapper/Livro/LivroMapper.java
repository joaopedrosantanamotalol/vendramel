
package com.vendramel.vendramel.base.infra.mapper.Livro;

import com.vendramel.vendramel.base.infra.entidadesFrameWork.Livro;

public class LivroMapper {

    public static com.vendramel.vendramel.base.domain.entidades.Livro paraDominio(
            Livro livro) {

        if (livro == null) {
            return null;
        }

        var dominio = new com.vendramel.vendramel.base.domain.entidades.Livro();

        dominio.setId(livro.getId());
        dominio.setTitulo(livro.getTitulo());
        dominio.setFormato(livro.getFormato());
        dominio.setPrecoVenda(livro.getPrecoVenda());
        dominio.setQuantidadeEstoque(livro.getQuantidadeEstoque());
        dominio.setStatus(livro.getStatus());

        return dominio;
    }

    public static Livro
            paraPersistencia(
            com.vendramel.vendramel.base.domain.entidades.Livro livro) {

        if (livro == null) {
            return null;
        }

        var persistencia =
                new Livro();

        persistencia.setId(livro.getId());
        persistencia.setTitulo(livro.getTitulo());
        persistencia.setFormato(livro.getFormato());
        persistencia.setPrecoVenda(livro.getPrecoVenda());
        persistencia.setQuantidadeEstoque(livro.getQuantidadeEstoque());
        persistencia.setStatus(livro.getStatus());

        return persistencia;
    }
}
