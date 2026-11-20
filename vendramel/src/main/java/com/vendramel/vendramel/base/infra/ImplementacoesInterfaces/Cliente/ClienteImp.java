
package com.vendramel.vendramel.base.infra.ImplementacoesInterfaces.Cliente;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.vendramel.vendramel.base.domain.entidades.Cliente;
import com.vendramel.vendramel.base.domain.interfaces.ClienteInterface;
import com.vendramel.vendramel.base.infra.mapper.Endereco.EnderecoMapper;
import com.vendramel.vendramel.base.infra.repository.Cliente.ClienteJpaRepository;
import com.vendramel.vendramel.base.infra.repository.Endereco.EnderecoJpaRepository;

@Repository
@Transactional
public class ClienteImp implements ClienteInterface {

    private final ClienteJpaRepository clienteRepository;
    private final EnderecoJpaRepository enderecoRepository;

    public ClienteImp(
            ClienteJpaRepository clienteRepository,
            EnderecoJpaRepository enderecoRepository) {

        this.clienteRepository = clienteRepository;
        this.enderecoRepository = enderecoRepository;
    }

    @Override
    public Cliente autenticar(String email, String senha) {

        var cliente = clienteRepository.findByEmailIgnoreCase(email)
                .orElseThrow(() ->
                        new IllegalArgumentException("Cliente não encontrado."));

        if (!cliente.getSenha().equals(senha)) {
            throw new IllegalArgumentException("Senha incorreta.");
        }

        return paraDominio(cliente);
    }

    @Override
    public Cliente cadastrar(Cliente cliente) {

        var endereco = enderecoRepository.findById(
                cliente.getEndereco().getId()
        ).orElseThrow(() ->
                new IllegalArgumentException("Endereço não encontrado."));

        var entidade = new com.vendramel.vendramel.base.infra.entidadesFrameWork.Cliente();

        entidade.setNomeCompleto(cliente.getNomeCompleto());
        entidade.setCpf(cliente.getCpf());
        entidade.setDataNascimento(cliente.getDataNascimento());
        entidade.setEmail(cliente.getEmail());
        entidade.setTelefone(cliente.getTelefone());
        entidade.setSenha(cliente.getSenha());
        entidade.setEndereco(endereco);

        var salvo = clienteRepository.save(entidade);

        return paraDominio(salvo);
    }

    @Override
    public Cliente atualizar(int id, Cliente cliente) {

        var entidade = clienteRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("Cliente não encontrado."));

        var endereco = enderecoRepository.findById(
                cliente.getEndereco().getId()
        ).orElseThrow(() ->
                new IllegalArgumentException("Endereço não encontrado."));

        entidade.setNomeCompleto(cliente.getNomeCompleto());
        entidade.setCpf(cliente.getCpf());
        entidade.setDataNascimento(cliente.getDataNascimento());
        entidade.setEmail(cliente.getEmail());
        entidade.setTelefone(cliente.getTelefone());
        entidade.setEndereco(endereco);

        if (cliente.getSenha() != null && !cliente.getSenha().isBlank()) {
            entidade.setSenha(cliente.getSenha());
        }

        var atualizado = clienteRepository.save(entidade);

        return paraDominio(atualizado);
    }

    @Override
    @Transactional(readOnly = true)
    public Cliente buscarPorId(int id) {

        var cliente = clienteRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("Cliente não encontrado."));

        return paraDominio(cliente);
    }

    private Cliente paraDominio(
            com.vendramel.vendramel.base.infra.entidadesFrameWork.Cliente entidade) {

        Cliente dominio = new Cliente();

        dominio.setId(entidade.getId());
        dominio.setNomeCompleto(entidade.getNomeCompleto());
        dominio.setCpf(entidade.getCpf());
        dominio.setDataNascimento(entidade.getDataNascimento());
        dominio.setEmail(entidade.getEmail());
        dominio.setTelefone(entidade.getTelefone());
        dominio.setSenha(entidade.getSenha());
        dominio.setEndereco(EnderecoMapper.paraDominio(entidade.getEndereco()));

        return dominio;
    }
}
