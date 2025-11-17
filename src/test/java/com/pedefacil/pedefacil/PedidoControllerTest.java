package com.pedefacil.pedefacil.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pedefacil.pedefacil.dto.ItemPedidoRequest;
import com.pedefacil.pedefacil.dto.PedidoRequest;
import com.pedefacil.pedefacil.dto.PedidoResponse;
import com.pedefacil.pedefacil.dto.ItemPedidoResponse;
import com.pedefacil.pedefacil.model.PedidoStatus;
import com.pedefacil.pedefacil.service.PedidoService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PedidoController.class)
public class PedidoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PedidoService pedidoService;

    @Test
    void deveCriarPedidoEndpoint() throws Exception {

        // --- RESPOSTA MOCKADA DO SERVICE ---
        PedidoResponse response = new PedidoResponse(
                1L,
                5,
                "Victor",
                LocalDateTime.now(),
                1L,
                List.of(
                        new ItemPedidoResponse(
                                1L,
                                "Prato Teste",
                                2,
                                30.0
                        )
                ),
                PedidoStatus.ACEITO // <-- IMPORTANTE
        );

        Mockito.when(pedidoService.createPedido(any())).thenReturn(response);

        // --- REQUEST ENVIADO PARA O CONTROLLER ---
        PedidoRequest request = new PedidoRequest();
        request.setMesaNumero(5);
        request.setNomeCliente("Victor");
        request.setCardapioId(1L);

        ItemPedidoRequest item = new ItemPedidoRequest();
        item.setPratoId(1L);
        item.setQuantidade(2);
        item.setPrecoUnitario(30.0);

        request.setItens(List.of(item));

        ObjectMapper mapper = new ObjectMapper();

        // --- EXECUTANDO A REQUISIÇÃO ---
        mockMvc.perform(post("/pedidos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nomeCliente").value("Victor"))
                .andExpect(jsonPath("$.mesaNumero").value(5))
                .andExpect(jsonPath("$.status").value("ACEITO"));
    }
}
