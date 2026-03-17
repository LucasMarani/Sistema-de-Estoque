package com.example.SistemaDeEstoque.Repository;

import com.example.SistemaDeEstoque.Model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
