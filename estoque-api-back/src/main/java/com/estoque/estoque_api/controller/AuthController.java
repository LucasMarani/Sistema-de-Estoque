package com.estoque.estoque_api.controller;

import com.estoque.estoque_api.config.JwtUtil;
import com.estoque.estoque_api.dto.LoginRequest;
import com.estoque.estoque_api.dto.LoginResponse;
import com.estoque.estoque_api.model.Usuario;
import com.estoque.estoque_api.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    
    @Autowired
    private JwtUtil jwtUtil;
    
    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        Usuario usuario = usuarioRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        
        if (!passwordEncoder.matches(request.getPassword(), usuario.getPassword())) {
            throw new RuntimeException("Senha inválida");
        }
        
        String token = jwtUtil.generateToken(usuario.getUsername(), usuario.getPapel());
        
        return new LoginResponse(
            token,
            new LoginResponse.UsuarioDTO(usuario.getId(), usuario.getNome(), usuario.getPapel())
        );
    }
}