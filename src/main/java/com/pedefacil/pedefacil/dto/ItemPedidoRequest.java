package com.pedefacil.pedefacil.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemPedidoRequest {
    private Long pratoId;
    private Integer quantidade;
    private Double precoUnitario;
}
