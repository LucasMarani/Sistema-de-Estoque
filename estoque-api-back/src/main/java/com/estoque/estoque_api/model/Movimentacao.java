package com.estoque.estoque_api.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "movimentacoes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Movimentacao {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "produto_id", nullable = false)
    private Produto produto;
    
    @Column(nullable = false)
    private String tipo; // ENTRADA ou SAIDA
    
    @Column(nullable = false)
    private Integer quantidade;
    
    @CreationTimestamp
    private LocalDateTime data;
    
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
}