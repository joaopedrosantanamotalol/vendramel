
package com.vendramel.vendramel.base.infra.ImplementacoesInterfaces.Endereco;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.vendramel.vendramel.base.domain.entidades.Endereco;
import com.vendramel.vendramel.base.domain.interfaces.EnderecoInterface;
import com.vendramel.vendramel.base.infra.mapper.Endereco.EnderecoMapper;
import com.vendramel.vendramel.base.infra.repository.Endereco.EnderecoJpaRepository;

@Repository
@Transactional
public class EnderecoImp implements EnderecoInterface {

    private final EnderecoJpaRepository enderecoRepository;

    public EnderecoImp(EnderecoJpaRepository enderecoRepository) {
        this.enderecoRepository = enderecoRepository;
    }

    @Override
    public Endereco buscarPorId(int id) {

        var endereco = enderecoRepository.findById(id)
                .orElseThrow(() ->
                    new IllegalArgumentException("Endereço não encontrado."));

        return EnderecoMapper.paraDominio(endereco);
    }

    @Override
    public Endereco cadastrar(Endereco endereco) {

        var entidade = EnderecoMapper.paraEntidade(endereco);

        var salvo = enderecoRepository.save(entidade);

        return EnderecoMapper.paraDominio(salvo);
    }
}
