package com.pedefacil.pedefacil.service;

import com.pedefacil.pedefacil.model.Pedido;
import com.pedefacil.pedefacil.repository.PedidoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PedidoReportService {

    private final PedidoRepository pedidoRepository;

    /**
     * Gera um relatório de pedidos no intervalo de datas fornecido.
     *
     * @param inicio Data e hora inicial
     * @param fim    Data e hora final
     * @return Lista de pedidos no intervalo
     */
    public List<Pedido> gerarRelatorio(LocalDateTime inicio, LocalDateTime fim) {
        return pedidoRepository.findByDataHoraBetween(inicio, fim);
    }
}
