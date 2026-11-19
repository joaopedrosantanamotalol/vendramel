package com.vendramel.vendramel.base.infra.repository.ItemCarrinho;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vendramel.vendramel.base.infra.entidadesFrameWork.ItemCarrinho;

public interface ItemCarrinhoJpaRepository extends JpaRepository<ItemCarrinho, Integer> {

    List<ItemCarrinho> findAllByCarrinho_Id(int carrinhoId);

}
