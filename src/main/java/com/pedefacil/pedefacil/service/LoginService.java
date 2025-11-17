package com.pedefacil.pedefacil.service;

import com.pedefacil.pedefacil.model.Restaurante;
import com.pedefacil.pedefacil.repository.RestauranteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final RestauranteRepository restauranteRepository;

    public Restaurante verificarLogin(String email, String senha) {
        Optional<Restaurante> restauranteOpt = restauranteRepository.findByEmail(email);

        if (restauranteOpt.isPresent()) {
            Restaurante restaurante = restauranteOpt.get();
            if (restaurante.getSenha().equals(senha)) {
                return restaurante; // login correto
            }
        }

        // login incorreto
        throw new RuntimeException("Email ou senha incorretos");
    }
}
