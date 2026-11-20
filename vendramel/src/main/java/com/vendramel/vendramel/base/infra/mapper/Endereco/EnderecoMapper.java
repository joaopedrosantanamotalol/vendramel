
package com.vendramel.vendramel.base.infra.mapper.Endereco;

public class EnderecoMapper {

    public static com.vendramel.vendramel.base.domain.entidades.Endereco paraDominio(
            com.vendramel.vendramel.base.infra.entidadesFrameWork.Endereco endereco) {

        if (endereco == null) {
            return null;
        }

        var dominio = new com.vendramel.vendramel.base.domain.entidades.Endereco();

        dominio.setId(endereco.getId());
        dominio.setLogradouro(endereco.getLogradouro());
        dominio.setNumero(endereco.getNumero());
        dominio.setBairro(endereco.getBairro());
        dominio.setCidade(endereco.getCidade());
        dominio.setUf(endereco.getUf());
        dominio.setCep(endereco.getCep());

        return dominio;
    }

    public static com.vendramel.vendramel.base.infra.entidadesFrameWork.Endereco paraEntidade(
            com.vendramel.vendramel.base.domain.entidades.Endereco endereco) {

        if (endereco == null) {
            return null;
        }

        var entidade = new com.vendramel.vendramel.base.infra.entidadesFrameWork.Endereco();

        entidade.setLogradouro(endereco.getLogradouro());
        entidade.setNumero(endereco.getNumero());
        entidade.setBairro(endereco.getBairro());
        entidade.setCidade(endereco.getCidade());
        entidade.setUf(endereco.getUf());
        entidade.setCep(endereco.getCep());

        return entidade;
    }
}
