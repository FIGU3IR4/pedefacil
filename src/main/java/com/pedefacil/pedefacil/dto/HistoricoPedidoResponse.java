package com.pedefacil.pedefacil.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HistoricoPedidoResponse {
    private Long id;
    private Long restauranteId;
    private Long pedidoId;
    private Double valorTotal;
    private LocalDateTime dataHoraFinalizacao;
    private String observacao;
}
