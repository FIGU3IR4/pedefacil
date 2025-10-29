package com.pedefacil.pedefacil.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
public class RelatorioVendas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dataHoraFinalizacao;
    private Double valorTotal;
    private Long restauranteId;
    private Long pedidoId;
    private Long cardapioId;

    @Column(length = 500)
    private String observacao;

}
