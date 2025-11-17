package com.pedefacil.pedefacil.controller;

import com.pedefacil.pedefacil.model.Pedido;
import com.pedefacil.pedefacil.service.PedidoReportService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PedidoReportController.class)
class PedidoReportControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PedidoReportService reportService;

    private Pedido pedido1;
    private Pedido pedido2;

    @BeforeEach
    void setup() {
        pedido1 = new Pedido();
        pedido1.setId(1L);
        pedido1.setDataHora(LocalDateTime.of(2025, 1, 10, 14, 0));

        pedido2 = new Pedido();
        pedido2.setId(2L);
        pedido2.setDataHora(LocalDateTime.of(2025, 1, 20, 18, 0));
    }

    @Test
    void deveRetornarPedidosDoRelatorio() throws Exception {
        LocalDate inicio = LocalDate.of(2025, 1, 1);
        LocalDate fim = LocalDate.of(2025, 1, 31);

        when(reportService.gerarRelatorio(any(LocalDateTime.class), any(LocalDateTime.class)))
                .thenReturn(List.of(pedido1, pedido2));

        mockMvc.perform(get("/relatorios/pedidos")
                        .param("inicio", inicio.toString())
                        .param("fim", fim.toString()))
                .andExpect(status().isOk());

        verify(reportService, times(1))
                .gerarRelatorio(inicio.atStartOfDay(), fim.atTime(23, 59, 59));
    }

    @Test
    void deveRetornarListaVaziaQuandoNaoHouverPedidos() throws Exception {
        LocalDate inicio = LocalDate.of(2025, 2, 1);
        LocalDate fim = LocalDate.of(2025, 2, 28);

        when(reportService.gerarRelatorio(any(LocalDateTime.class), any(LocalDateTime.class)))
                .thenReturn(List.of());

        mockMvc.perform(get("/relatorios/pedidos")
                        .param("inicio", inicio.toString())
                        .param("fim", fim.toString()))
                .andExpect(status().isOk());

        verify(reportService, times(1))
                .gerarRelatorio(inicio.atStartOfDay(), fim.atTime(23, 59, 59));
    }
}
