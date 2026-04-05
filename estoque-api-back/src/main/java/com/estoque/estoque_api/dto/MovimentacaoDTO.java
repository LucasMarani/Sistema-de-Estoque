package com.estoque.estoque_api.dto;

import lombok.Data;

@Data
public class MovimentacaoDTO {
    private Long produto_id;
    private String tipo;        // "entrada" ou "saida"
    private Integer quantidade;
}