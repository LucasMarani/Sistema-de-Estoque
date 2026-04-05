package com.estoque.estoque_api.controller;

import com.estoque.estoque_api.model.Produto;
import com.estoque.estoque_api.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/produtos")
@CrossOrigin(origins = "*")
public class ProdutoController {
    
    @Autowired
    private ProdutoRepository produtoRepository;
    
    @GetMapping
    public List<Produto> listar() {
        return produtoRepository.findAll();
    }
    
    @GetMapping("/{id}")
    public Produto buscarPorId(@PathVariable Long id) {
        return produtoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado"));
    }
    
    @GetMapping("/buscar")
    public List<Produto> buscarPorNome(@RequestParam String q) {
        return produtoRepository.findByNomeContainingIgnoreCase(q);
    }
    
    @GetMapping("/estoque-baixo")
    public List<Produto> estoqueBaixo() {
        return produtoRepository.findProdutosEstoqueBaixo(5);
    }
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Produto criar(@RequestBody Produto produto) {
        return produtoRepository.save(produto);
    }
    
    @PutMapping("/{id}")
    public Produto atualizar(@PathVariable Long id, @RequestBody Produto produtoAtualizado) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        
        produto.setNome(produtoAtualizado.getNome());
        produto.setCategoria(produtoAtualizado.getCategoria());
        produto.setQuantidade(produtoAtualizado.getQuantidade());
        produto.setPreco(produtoAtualizado.getPreco());
        
        return produtoRepository.save(produto);
    }
    
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        produtoRepository.deleteById(id);
    }
}