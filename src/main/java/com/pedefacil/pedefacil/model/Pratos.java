package com.pedefacil.pedefacil.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Pratos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private  String descricao;
    private Double preco;

    @ManyToOne
    @JoinColumn(name = "cardapio_id", nullable = false)
    private Cardapio cardapio;

}
