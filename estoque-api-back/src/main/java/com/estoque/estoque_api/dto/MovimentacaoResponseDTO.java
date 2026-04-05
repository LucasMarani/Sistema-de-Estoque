package com.estoque.estoque_api.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class MovimentacaoResponseDTO {
    private Long id;
    private Long produto_id;
    private String produto_nome;
    private String tipo;
    private Integer quantidade;
    private LocalDateTime data;
    private BigDecimal precoUnitario;     // ← Novo (para o relatório)
    private BigDecimal valorTotal;        // ← Novo (preco * quantidade)
}