package com.pedefacil.pedefacil.service;

import com.pedefacil.pedefacil.model.Restaurante;
import com.pedefacil.pedefacil.repository.RestauranteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LoginServiceTest {

    @Mock
    private RestauranteRepository restauranteRepository;

    @InjectMocks
    private LoginService loginService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testarLoginCorreto() {
        // Dados de exemplo
        String email = "teste@restaurante.com";
        String senha = "123456";

        Restaurante restaurante = new Restaurante();
        restaurante.setEmail(email);
        restaurante.setSenha(senha);

        // Configurando o mock
        when(restauranteRepository.findByEmail(email)).thenReturn(Optional.of(restaurante));

        // Chamando o método
        Restaurante resultado = loginService.verificarLogin(email, senha);

        // Verificando
        assertNotNull(resultado);
        assertEquals(email, resultado.getEmail());
        verify(restauranteRepository, times(1)).findByEmail(email);
    }

    @Test
    void testarLoginComEmailIncorreto() {
        String email = "inexistente@restaurante.com";
        String senha = "123456";

        when(restauranteRepository.findByEmail(email)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            loginService.verificarLogin(email, senha);
        });

        assertEquals("Email ou senha incorretos", exception.getMessage());
        verify(restauranteRepository, times(1)).findByEmail(email);
    }

    @Test
    void testarLoginComSenhaIncorreta() {
        String email = "teste@restaurante.com";
        String senhaCorreta = "123456";
        String senhaErrada = "000000";

        Restaurante restaurante = new Restaurante();
        restaurante.setEmail(email);
        restaurante.setSenha(senhaCorreta);

        when(restauranteRepository.findByEmail(email)).thenReturn(Optional.of(restaurante));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            loginService.verificarLogin(email, senhaErrada);
        });

        assertEquals("Email ou senha incorretos", exception.getMessage());
        verify(restauranteRepository, times(1)).findByEmail(email);
    }
}
