package com.pedefacil.pedefacil.service;

import com.pedefacil.pedefacil.model.Pedido;
import com.pedefacil.pedefacil.repository.PedidoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RelatorioService {

    private final PedidoRepository pedidoRepository;

    // Busca pedidos dentro de um intervalo de datas
    public List<Pedido> gerarRelatorio(LocalDate dataInicio, LocalDate dataFim) {
        LocalDateTime inicio = dataInicio.atStartOfDay();
        LocalDateTime fim = dataFim.atTime(23, 59, 59);
        return pedidoRepository.findByDataHoraBetween(inicio, fim);
    }

    // Calcula o valor total de um pedido
    public double calcularValorTotal(Pedido pedido) {
        return pedido.getItens().stream()
                .mapToDouble(item -> item.getQuantidade() * item.getPrecoUnitario())
                .sum();
    }

    // Calcula o valor total de vários pedidos
    public double calcularValorTotal(List<Pedido> pedidos) {
        return pedidos.stream()
                .mapToDouble(this::calcularValorTotal)
                .sum();
    }
}
