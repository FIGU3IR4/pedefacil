package com.pedefacil.pedefacil.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PratosResponse {
    private Long id;

    private String nome;
    private  String descricao;
    private Double preco;
    private  long cardapioId;

}
