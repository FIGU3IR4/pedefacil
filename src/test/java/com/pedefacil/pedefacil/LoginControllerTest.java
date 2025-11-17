package com.pedefacil.pedefacil.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pedefacil.pedefacil.model.Restaurante;
import com.pedefacil.pedefacil.service.LoginService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Map;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.jupiter.api.Assertions.assertEquals;
import com.jayway.jsonpath.JsonPath;

class LoginControllerTest {

    private MockMvc mockMvc;

    @Mock
    private LoginService loginService;

    @InjectMocks
    private LoginController loginController;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(loginController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void testLoginComSucesso() throws Exception {
        // Mock do restaurante
        Restaurante restaurante = new Restaurante();
        restaurante.setId(1L);
        restaurante.setEmail("teste@restaurante.com");
        restaurante.setSenha("12345");

        // Configurar comportamento do serviço
        when(loginService.verificarLogin("teste@restaurante.com", "12345"))
                .thenReturn(restaurante);

        // Dados do login
        Map<String, String> loginData = Map.of(
                "email", "teste@restaurante.com",
                "senha", "12345"
        );

        // Executar requisição POST
        String response = mockMvc.perform(post("/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginData)))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        // Verificar resposta
        String emailRetornado = JsonPath.read(response, "$.email");
        assertEquals("teste@restaurante.com", emailRetornado);
    }

    @Test
    void testLoginComFalha() throws Exception {
        // Configurar comportamento do serviço para falha
        when(loginService.verificarLogin("teste@restaurante.com", "senhaErrada"))
                .thenThrow(new RuntimeException("Email ou senha incorretos"));

        Map<String, String> loginData = Map.of(
                "email", "teste@restaurante.com",
                "senha", "senhaErrada"
        );

        String response = mockMvc.perform(post("/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginData)))
                .andExpect(status().isUnauthorized())
                .andReturn()
                .getResponse()
                .getContentAsString();

        // Verificar mensagem de erro
        String erro = JsonPath.read(response, "$.erro");
        assertEquals("Email ou senha incorretos", erro);
    }
}
