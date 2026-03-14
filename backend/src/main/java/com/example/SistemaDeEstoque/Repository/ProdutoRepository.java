package com.example.SistemaDeEstoque.Repository;

import com.example.SistemaDeEstoque.Model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
