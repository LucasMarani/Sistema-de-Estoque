package com.estoque.estoque_api.service.impl;

import com.estoque.estoque_api.dto.MovimentacaoDTO;
import com.estoque.estoque_api.model.Movimentacao;
import com.estoque.estoque_api.model.Produto;
import com.estoque.estoque_api.repository.MovimentacaoRepository;
import com.estoque.estoque_api.repository.ProdutoRepository;
import com.estoque.estoque_api.service.MovimentacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MovimentacaoServiceImpl implements MovimentacaoService {

    @Autowired
    private MovimentacaoRepository movimentacaoRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Override
    public List<Movimentacao> listarTodas() {
        return movimentacaoRepository.findAllByOrderByDataDesc();
    }

    @Override
    @Transactional
    public Movimentacao registrar(MovimentacaoDTO dto) {
        Produto produto = produtoRepository.findById(dto.getProduto_id())
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        if (dto.getTipo().equalsIgnoreCase("saida") && produto.getQuantidade() < dto.getQuantidade()) {
            throw new RuntimeException("Estoque insuficiente");
        }

        Movimentacao movimentacao = new Movimentacao();
        movimentacao.setProduto(produto);
        movimentacao.setTipo(dto.getTipo());
        movimentacao.setQuantidade(dto.getQuantidade());

        // Atualiza estoque
        if (dto.getTipo().equalsIgnoreCase("entrada")) {
            produto.setQuantidade(produto.getQuantidade() + dto.getQuantidade());
        } else {
            produto.setQuantidade(produto.getQuantidade() - dto.getQuantidade());
        }

        produtoRepository.save(produto);
        return movimentacaoRepository.save(movimentacao);
    }

    @Override
    public void deletar(Long id) {
        movimentacaoRepository.deleteById(id);
    }
}