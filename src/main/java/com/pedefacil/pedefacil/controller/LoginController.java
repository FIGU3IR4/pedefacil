package com.pedefacil.pedefacil.controller;

import com.pedefacil.pedefacil.model.Restaurante;
import com.pedefacil.pedefacil.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @PostMapping
    public ResponseEntity<?> login(@RequestBody Map<String, String> body) {
        try {
            String email = body.get("email");
            String senha = body.get("senha");

            Restaurante restaurante = loginService.verificarLogin(email, senha);

            restaurante.setSenha(null); // não retornar a senha
            return ResponseEntity.ok(restaurante);

        } catch (RuntimeException e) {
            return ResponseEntity.status(401).body(Map.of("erro", e.getMessage()));
        }
    }
}
