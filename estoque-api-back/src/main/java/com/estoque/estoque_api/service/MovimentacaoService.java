package com.estoque.estoque_api.service;

import com.estoque.estoque_api.dto.MovimentacaoDTO;
import com.estoque.estoque_api.model.Movimentacao;

import java.util.List;

public interface MovimentacaoService {

    List<Movimentacao> listarTodas();

    Movimentacao registrar(MovimentacaoDTO dto);

    void deletar(Long id);
}