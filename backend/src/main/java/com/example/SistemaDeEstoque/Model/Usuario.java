package com.example.SistemaDeEstoque.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome não pode ser vazio")
    private String nome;

    @NotBlank(message = "Usuario não pode ser vazio")
    private String usuario;

    @NotBlank(message = "Senha não pode ser vazia")
    private String senha;

    @NotBlank(message = "Cargo deve ser informado")
    private String cargo;

    @Email
    @NotBlank(message = "Email não pode ser vazio")
    private Email email;

    public Usuario() {}

    public Usuario(String nome, String usuario, String senha, String cargo, Email email) {
        this.nome = nome;
        this.usuario = usuario;
        this.senha = senha;
        this.cargo = cargo;
        this.email = email;
    }

    // getters e setters
}
