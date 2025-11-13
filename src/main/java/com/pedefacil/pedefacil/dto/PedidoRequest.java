package com.pedefacil.pedefacil.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PedidoRequest {
    private Integer mesaNumero;
    private String nomeCliente;
    private Long cardapioId;
    private List<ItemPedidoRequest> itens;
}
