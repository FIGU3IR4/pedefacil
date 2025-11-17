package com.pedefacil.pedefacil.dto;

import java.time.LocalDate;
import java.util.List;

public record RelatorioResponse(
        LocalDate dataInicio,
        LocalDate dataFim,
        int quantidadePedidos,
        double valorTotal,
        List<PedidoResponse> pedidos
) {}
