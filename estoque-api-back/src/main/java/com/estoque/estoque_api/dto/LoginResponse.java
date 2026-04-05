package com.estoque.estoque_api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponse {
    private String token;
    private UsuarioDTO usuario;
    
    @Data
    @AllArgsConstructor
    public static class UsuarioDTO {
        private Long id;
        private String nome;
        private String papel;
    }
}