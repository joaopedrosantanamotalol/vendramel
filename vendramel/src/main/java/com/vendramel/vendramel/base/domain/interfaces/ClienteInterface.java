package com.vendramel.vendramel.base.domain.interfaces;

import com.vendramel.vendramel.base.domain.entidades.Cliente;

public interface ClienteInterface {

    Cliente autenticar(String email,String senha);
    Cliente cadastrar(Cliente cliente);
    Cliente atualizar(int id, Cliente cliente);
    Cliente buscarPorId(int id);
}
