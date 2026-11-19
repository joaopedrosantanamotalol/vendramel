
package com.vendramel.vendramel.base.infra.repository.Carrinho;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vendramel.vendramel.base.infra.entidadesFrameWork.Carrinho;

public interface CarrinhoJpaRepository extends JpaRepository<Carrinho, Integer> {

    Optional<Carrinho> findByCliente_Id(int clienteId);

}
