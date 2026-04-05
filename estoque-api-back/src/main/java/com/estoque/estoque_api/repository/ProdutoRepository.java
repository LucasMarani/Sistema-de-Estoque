package com.estoque.estoque_api.repository;

import com.estoque.estoque_api.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    List<Produto> findByNomeContainingIgnoreCase(String nome);
    List<Produto> findByCategoriaContainingIgnoreCase(String categoria);
    
    @Query("SELECT p FROM Produto p WHERE p.quantidade <= :limite")
    List<Produto> findProdutosEstoqueBaixo(@Param("limite") Integer limite);
}