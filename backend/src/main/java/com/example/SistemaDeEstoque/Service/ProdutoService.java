package com.example.SistemaDeEstoque.Service;

import com.example.SistemaDeEstoque.Model.Produto;
import com.example.SistemaDeEstoque.Repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    private final ProdutoRepository repository;

    public ProdutoService(ProdutoRepository repository) {
        this.repository = repository;
    }

    public List<Produto> listar() {
        return repository.findAll();
    }

    public Produto salvar(Produto produto) {
        return repository.save(produto);
    }

    public void deletar(Long id){
        repository.deleteById(id);
    }
}
