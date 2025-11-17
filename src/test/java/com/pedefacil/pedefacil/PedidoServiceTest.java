package com.pedefacil.pedefacil.service;

import com.pedefacil.pedefacil.dto.ItemPedidoRequest;
import com.pedefacil.pedefacil.dto.PedidoRequest;
import com.pedefacil.pedefacil.dto.PedidoResponse;
import com.pedefacil.pedefacil.model.*;
import com.pedefacil.pedefacil.repository.PedidoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PedidoServiceTest {

    @Mock
    private PedidoRepository pedidoRepository;

    @InjectMocks
    private PedidoService pedidoService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveCriarPedidoComStatusAceito() {

        // ------- REQUEST -------
        PedidoRequest request = new PedidoRequest();
        request.setMesaNumero(12);
        request.setNomeCliente("Victor");
        request.setCardapioId(5L);

        ItemPedidoRequest item = new ItemPedidoRequest();
        item.setPratoId(10L);
        item.setQuantidade(2);
        item.setPrecoUnitario(25.0);

        request.setItens(List.of(item));

        // ------- MOCK DO SAVE -------
        when(pedidoRepository.save(any())).thenAnswer(inv -> {
            Pedido p = inv.getArgument(0);
            p.setId(1L);
            return p;
        });

        // ------- EXECUÇÃO -------
        PedidoResponse response = pedidoService.createPedido(request);

        // ------- VERIFICAÇÕES -------
        assertNotNull(response);
        assertEquals(12, response.mesaNumero());
        assertEquals("Victor", response.nomeCliente());
        assertEquals(5L, response.cardapioId());
        assertEquals(1, response.itens().size());
        assertEquals(PedidoStatus.PREPARANDO, response.status());
    }

    @Test
    void deveRetornarPedidoPorId() {

        Pedido pedido = new Pedido();
        pedido.setId(1L);
        pedido.setMesaNumero(7);
        pedido.setNomeCliente("Cliente Teste");
        pedido.setDataHora(java.time.LocalDateTime.now());
        pedido.setStatus(PedidoStatus.PRONTO);

        Cardapio cardapio = new Cardapio();
        cardapio.setId(10L);
        pedido.setCardapio(cardapio);

        pedido.setItens(List.of());

        when(pedidoRepository.findById(1L)).thenReturn(Optional.of(pedido));

        PedidoResponse response = pedidoService.findById(1L);

        assertEquals(1L, response.id());
        assertEquals(7, response.mesaNumero());
        assertEquals("Cliente Teste", response.nomeCliente());
        assertEquals(10L, response.cardapioId());
        assertEquals(PedidoStatus.PRONTO, response.status());
    }

    @Test
    void deveLancarErroQuandoPedidoNaoExiste() {
        when(pedidoRepository.findById(99L))
                .thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> {
            pedidoService.findById(99L);
        });
    }

    @Test
    void deveListarTodosPedidos() {

        Pedido pedido = new Pedido();
        pedido.setId(1L);
        pedido.setMesaNumero(3);
        pedido.setNomeCliente("Victor");
        pedido.setDataHora(java.time.LocalDateTime.now());
        pedido.setStatus(PedidoStatus.PREPARANDO);

        Cardapio cardapio = new Cardapio();
        cardapio.setId(4L);
        pedido.setCardapio(cardapio);

        pedido.setItens(List.of());

        when(pedidoRepository.findAll()).thenReturn(List.of(pedido));

        List<PedidoResponse> lista = pedidoService.findAll();

        assertEquals(1, lista.size());
        assertEquals(3, lista.get(0).mesaNumero());
        assertEquals("Victor", lista.get(0).nomeCliente());
        assertEquals(4L, lista.get(0).cardapioId());
        assertEquals(PedidoStatus.PREPARANDO, lista.get(0).status());
    }

    static class RelatorioServiceTest {

        @Mock
        private PedidoRepository pedidoRepository;

        @InjectMocks
        private RelatorioService relatorioService;

        @BeforeEach
        void setup() {
            MockitoAnnotations.openMocks(this);
        }

        @Test
        void deveGerarRelatorioComPedidosNoIntervalo() {
            // ARRANGE
            LocalDate dataInicio = LocalDate.of(2025, 1, 1);
            LocalDate dataFim = LocalDate.of(2025, 1, 31);

            LocalDateTime inicio = dataInicio.atStartOfDay();
            LocalDateTime fim = dataFim.atTime(23, 59, 59);

            Pedido pedido1 = new Pedido();
            pedido1.setId(1L);
            pedido1.setDataHora(LocalDateTime.of(2025, 1, 10, 14, 0));

            Pedido pedido2 = new Pedido();
            pedido2.setId(2L);
            pedido2.setDataHora(LocalDateTime.of(2025, 1, 20, 18, 0));

            // MOCK: quando o método do repository for chamado, retorna a lista de pedidos
            when(pedidoRepository.findByDataHoraBetween(inicio, fim))
                    .thenReturn(List.of(pedido1, pedido2));

            // ACT: chama o serviço
            List<Pedido> resultado = relatorioService.gerarRelatorio(dataInicio, dataFim);

            // ASSERT
            assertNotNull(resultado);
            assertEquals(2, resultado.size());
            assertEquals(1L, resultado.get(0).getId());
            assertEquals(2L, resultado.get(1).getId());

            // Verifica se o repository foi chamado exatamente uma vez
            verify(pedidoRepository, times(1)).findByDataHoraBetween(inicio, fim);
        }

        @Test
        void deveRetornarListaVaziaQuandoNaoHouverPedidos() {
            // ARRANGE
            LocalDate dataInicio = LocalDate.of(2025, 2, 1);
            LocalDate dataFim = LocalDate.of(2025, 2, 28);

            LocalDateTime inicio = dataInicio.atStartOfDay();
            LocalDateTime fim = dataFim.atTime(23, 59, 59);

            when(pedidoRepository.findByDataHoraBetween(inicio, fim))
                    .thenReturn(List.of());

            // ACT
            List<Pedido> resultado = relatorioService.gerarRelatorio(dataInicio, dataFim);

            // ASSERT
            assertNotNull(resultado);
            assertTrue(resultado.isEmpty());

            verify(pedidoRepository, times(1)).findByDataHoraBetween(inicio, fim);
        }
    }
}
