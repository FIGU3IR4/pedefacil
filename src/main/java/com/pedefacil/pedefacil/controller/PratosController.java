package com.pedefacil.pedefacil.controller;

import com.pedefacil.pedefacil.dto.PratosResponse;
import com.pedefacil.pedefacil.service.PratosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pratos")
public class PratosController {

    @Autowired
    private PratosService service;


    @GetMapping
    public List<PratosResponse> getAll() {
        return service.findAll();
    }


    @GetMapping("/cardapio/{cardapioId}")
    public List<PratosResponse> getPratosByCardapio(@PathVariable Long cardapioId) {
        return service.findByCardapioId(cardapioId);
    }
}
