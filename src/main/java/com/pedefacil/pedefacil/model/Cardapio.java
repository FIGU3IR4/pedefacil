package com.pedefacil.pedefacil.model;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.engine.internal.Cascade;

import java.util.List;

@Entity
@Data
public class Cardapio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private  String nome;

    @ManyToOne
    @JoinColumn(name = "restaurante_id", nullable =false)
    private Restaurante restaurante;

    @OneToMany(mappedBy = "cardapio", cascade = CascadeType.ALL)
    private List<Pratos> prato;

    @OneToMany(mappedBy = "cardapio", cascade = CascadeType.ALL)
    private  List<Pedido> pedido;


}
