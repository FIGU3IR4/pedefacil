package com.pedefacil.pedefacil.dto;

import com.pedefacil.pedefacil.model.PedidoStatus;

import java.time.LocalDateTime;
import java.util.List;

public record PedidoResponse(
        Long id,
        Integer mesaNumero,
        String nomeCliente,
        LocalDateTime dataHora,
        Long cardapioId,
        List<ItemPedidoResponse> itens,
        PedidoStatus status
) {}
