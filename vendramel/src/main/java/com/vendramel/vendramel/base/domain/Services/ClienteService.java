package com.vendramel.vendramel.base.domain.Services;

import com.vendramel.vendramel.base.domain.entidades.Cliente;
import com.vendramel.vendramel.base.domain.interfaces.ClienteInterface;

public class ClienteService {
    
    private final ClienteInterface inter;

    public ClienteService(ClienteInterface inter) {
        this.inter = inter;
    }

    public Cliente autenticar(String email,String senha){
        return inter.autenticar(email, senha);
    }
    
    public Cliente cadastrar(Cliente cliente){
        return inter.cadastrar(cliente);
    }

    public Cliente atualizar(int id, Cliente cliente){
        return inter.atualizar(id, cliente);
    }

    public Cliente buscarPorId(int id){
        return inter.buscarPorId(id);
    }

}
