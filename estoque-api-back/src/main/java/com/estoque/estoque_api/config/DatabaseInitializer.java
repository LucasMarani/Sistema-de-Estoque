package com.estoque.estoque_api.config;

import com.estoque.estoque_api.model.Produto;
import com.estoque.estoque_api.model.Usuario;
import com.estoque.estoque_api.repository.ProdutoRepository;
import com.estoque.estoque_api.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class DatabaseInitializer implements CommandLineRunner {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        // Inserir usuários se não existirem
        if (usuarioRepository.count() == 0) {
            Usuario admin = new Usuario();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setNome("Administrador");
            admin.setPapel("ADMIN");
            usuarioRepository.save(admin);

            Usuario funcionario = new Usuario();
            funcionario.setUsername("funcionario");
            funcionario.setPassword(passwordEncoder.encode("user123"));
            funcionario.setNome("Funcionário");
            funcionario.setPapel("FUNCIONARIO");
            usuarioRepository.save(funcionario);

            System.out.println("✅ Usuários criados com sucesso!");
        }

        // Inserir produtos se não existirem
        if (produtoRepository.count() == 0) {
            produtoRepository.save(new Produto(null, "Notebook Dell", "Informática", 10, new BigDecimal("3500.00")));
            produtoRepository.save(new Produto(null, "Mouse Logitech", "Informática", 5, new BigDecimal("89.90")));
            produtoRepository.save(new Produto(null, "Teclado Mecânico", "Informática", 3, new BigDecimal("250.00")));
            produtoRepository.save(new Produto(null, "Monitor LG", "Eletrônicos", 2, new BigDecimal("1200.00")));

            System.out.println("✅ Produtos criados com sucesso!");
        }
    }
}