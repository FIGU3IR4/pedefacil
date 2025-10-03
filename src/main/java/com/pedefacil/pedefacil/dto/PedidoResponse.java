package com.pedefacil.pedefacil.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PedidoResponse {
    private Long id;
    private Integer mesaNumero;
    private String nomeCliente;
    private LocalDateTime dataHora;
    private Long cardapioId;
    private List<ItemPedidoResponse> itens;
}
