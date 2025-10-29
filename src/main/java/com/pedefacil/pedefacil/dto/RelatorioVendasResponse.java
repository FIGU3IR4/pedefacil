package com.pedefacil.pedefacil.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RelatorioVendasResponse {
    private String periodo;
    private Long restauranteId;
    private Long quantidadePedidos;
    private Double valorTotal;
    private Map<String, Long> itensMaisVendidos;
}
