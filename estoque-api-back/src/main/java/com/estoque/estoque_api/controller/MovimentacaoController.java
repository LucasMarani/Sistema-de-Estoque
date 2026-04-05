package com.estoque.estoque_api.controller;

import com.estoque.estoque_api.dto.MovimentacaoDTO;
import com.estoque.estoque_api.dto.MovimentacaoResponseDTO;
import com.estoque.estoque_api.model.Movimentacao;
import com.estoque.estoque_api.service.MovimentacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/movimentacoes")
@CrossOrigin(origins = "*")
public class MovimentacaoController {

    @Autowired
    private MovimentacaoService movimentacaoService;

    @GetMapping
    public List<MovimentacaoResponseDTO> listarTodas() {
        List<Movimentacao> movimentacoes = movimentacaoService.listarTodas();
        return movimentacoes.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private MovimentacaoResponseDTO convertToDTO(Movimentacao mov) {
        MovimentacaoResponseDTO dto = new MovimentacaoResponseDTO();
        dto.setId(mov.getId());
        dto.setProduto_id(mov.getProduto().getId());
        dto.setProduto_nome(mov.getProduto().getNome());
        dto.setTipo(mov.getTipo());
        dto.setQuantidade(mov.getQuantidade());
        dto.setData(mov.getData());
        return dto;
    }

    @PostMapping
    public Movimentacao registrar(@RequestBody MovimentacaoDTO dto) {
        return movimentacaoService.registrar(dto);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        movimentacaoService.deletar(id);
    }
}