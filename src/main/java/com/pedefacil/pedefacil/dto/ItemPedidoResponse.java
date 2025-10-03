package com.pedefacil.pedefacil.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemPedidoResponse {
    private Long id;
    private String nomePrato;
    private Integer quantidade;
    private Double precoUnitario;
}
