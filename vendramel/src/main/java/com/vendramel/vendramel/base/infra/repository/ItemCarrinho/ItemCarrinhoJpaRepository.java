
package com.vendramel.vendramel.base.infra.repository.ItemCarrinho;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.vendramel.vendramel.base.infra.entidadesFrameWork.ItemCarrinho;

public interface ItemCarrinhoJpaRepository extends JpaRepository<ItemCarrinho, Integer> {

    List<ItemCarrinho> findAllByCarrinho_Id(int carrinhoId);

    void deleteAllByCarrinho_Id(int carrinhoId);

    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Query("DELETE FROM ItemCarrinho i WHERE i.carrinho.id = :carrinhoId")
    void excluirPorCarrinhoId(@Param("carrinhoId") int carrinhoId);
}
