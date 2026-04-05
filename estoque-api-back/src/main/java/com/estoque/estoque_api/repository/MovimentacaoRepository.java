package com.estoque.estoque_api.repository;

import com.estoque.estoque_api.model.Movimentacao;
import com.estoque.estoque_api.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDateTime;
import java.util.List;

public interface MovimentacaoRepository extends JpaRepository<Movimentacao, Long> {
    List<Movimentacao> findByProdutoOrderByDataDesc(Produto produto);
    List<Movimentacao> findAllByOrderByDataDesc();
    
    @Query("SELECT m FROM Movimentacao m WHERE m.data BETWEEN :inicio AND :fim ORDER BY m.data DESC")
    List<Movimentacao> findByPeriodo(@Param("inicio") LocalDateTime inicio, @Param("fim") LocalDateTime fim);
}