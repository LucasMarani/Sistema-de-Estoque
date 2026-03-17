package com.example.SistemaDeEstoque.Service;

import com.example.SistemaDeEstoque.Model.Usuario;
import com.example.SistemaDeEstoque.Repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public List<Usuario> listar() {
        return repository.findAll();
    }

    public Usuario salvar(Usuario usuario) {
        return repository.save(usuario);
    }

    public void deletar(Long id){
        repository.deleteById(id);
    }
}
