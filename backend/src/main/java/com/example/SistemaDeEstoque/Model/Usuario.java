package com.example.SistemaDeEstoque.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Usuario não pode ser vazio")
    private String usuario;

    @NotBlank(message = "Senha não pode ser vazia")
    private String senha;

    @NotBlank(message = "Nível de acesso deve ser informado")
    private String nivelAcesso;

    public Usuario() {}

    public Usuario(String usuario, String senha, String nivelAcesso) {
        this.usuario = usuario;
        this.senha = senha;
        this.nivelAcesso = nivelAcesso;
    }

    // getters e setters
}
