package com.example.SistemaDeEstoque.Controller;

import com.example.SistemaDeEstoque.Model.Produto;
import com.example.SistemaDeEstoque.Model.Usuario;
import com.example.SistemaDeEstoque.Service.ProdutoService;
import com.example.SistemaDeEstoque.Service.UsuarioService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @GetMapping
    public List<Usuario> listar() {
        return service.listar();
    }

    @PostMapping
    public Usuario salvar(@RequestBody Usuario usuario) {
        return service.salvar(usuario);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }
}
