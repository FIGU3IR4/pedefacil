package com.pedefacil.pedefacil.service;

import com.pedefacil.pedefacil.model.Pedido;
import com.pedefacil.pedefacil.repository.PedidoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PedidoReportServiceTest {

    @Mock
    private PedidoRepository pedidoRepository;

    @InjectMocks
    private PedidoReportService reportService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveGerarRelatorioComPedidosNoIntervalo() {
        // ARRANGE
        LocalDate dataInicial = LocalDate.of(2025, 1, 1);
        LocalDate dataFinal = LocalDate.of(2025, 1, 31);

        LocalDateTime inicio = dataInicial.atStartOfDay();
        LocalDateTime fim = dataFinal.atTime(23, 59, 59);

        Pedido pedido1 = new Pedido();
        pedido1.setId(1L);
        pedido1.setDataHora(LocalDateTime.of(2025, 1, 10, 14, 0));

        Pedido pedido2 = new Pedido();
        pedido2.setId(2L);
        pedido2.setDataHora(LocalDateTime.of(2025, 1, 20, 18, 0));

        when(pedidoRepository.findByDataHoraBetween(inicio, fim))
                .thenReturn(List.of(pedido1, pedido2));

        // ACT
        List<Pedido> resultado = reportService.gerarRelatorio(inicio, fim);

        // ASSERT
        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        assertEquals(1L, resultado.get(0).getId());
        assertEquals(2L, resultado.get(1).getId());

        verify(pedidoRepository, times(1)).findByDataHoraBetween(inicio, fim);
    }

    @Test
    void deveRetornarListaVaziaQuandoNaoHouverPedidos() {
        // ARRANGE
        LocalDate dataInicial = LocalDate.of(2025, 2, 1);
        LocalDate dataFinal = LocalDate.of(2025, 2, 28);

        LocalDateTime inicio = dataInicial.atStartOfDay();
        LocalDateTime fim = dataFinal.atTime(23, 59, 59);

        when(pedidoRepository.findByDataHoraBetween(inicio, fim))
                .thenReturn(List.of());

        // ACT
        List<Pedido> resultado = reportService.gerarRelatorio(inicio, fim);

        // ASSERT
        assertNotNull(resultado);
        assertTrue(resultado.isEmpty());

        verify(pedidoRepository, times(1)).findByDataHoraBetween(inicio, fim);
    }
}
