package com.pedefacil.pedefacil.service;
import com.pedefacil.pedefacil.dto.RelatorioVendasResponse;
import com.pedefacil.pedefacil.model.HistoricoPedido;
import com.pedefacil.pedefacil.repository.HistoricoPedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RelatorioVendasService {

    @Autowired
    private HistoricoPedidoRepository repository;

    public RelatorioVendasResponse gerarRelatorio(Long restauranteId, String periodo) {

        LocalDateTime inicio;
        LocalDateTime fim = LocalDateTime.now();

        switch (periodo.toLowerCase()) {
            case "diario" -> inicio = LocalDate.now().atStartOfDay();
            case "semanal" -> inicio = LocalDate.now().minusDays(7).atStartOfDay();
            case "mensal" -> inicio = LocalDate.now().with(TemporalAdjusters.firstDayOfMonth()).atStartOfDay();
            default -> throw new IllegalArgumentException("Período inválido. Use: diario, semanal ou mensal.");
        }

        List<HistoricoPedido> pedidos = repository.findByRestauranteIdAndDataHoraFinalizacaoBetween(
                restauranteId, inicio, fim
        );

        if (pedidos.isEmpty()) {
            return new RelatorioVendasResponse(periodo, restauranteId, 0L, 0.0, Map.of());
        }

        Long qtdPedidos = (long) pedidos.size();
        Double valorTotal = pedidos.stream().mapToDouble(HistoricoPedido::getValorTotal).sum();

        // Aqui simulamos itens mais vendidos (você pode integrar com tabela de ItensPedido)
        Map<String, Long> itensMaisVendidos = Map.of("Pizza", 10L, "Lasanha", 5L);

        return new RelatorioVendasResponse(periodo, restauranteId, qtdPedidos, valorTotal, itensMaisVendidos);
    }
}
