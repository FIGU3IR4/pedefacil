package com.pedefacil.pedefacil.controller;

import com.pedefacil.pedefacil.dto.CardapioResponse;
import com.pedefacil.pedefacil.dto.PratosResponse;
import com.pedefacil.pedefacil.service.CardapioService;
import com.pedefacil.pedefacil.service.PratosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/pratos")
public class PratosController {
    @Autowired
    private PratosService service;

    @GetMapping
    public List<PratosResponse>getAll(){
       return service.findAll();
}
    @GetMapping ("restaurante/cardapio/{id}")
    public List<PratosResponse> getPratosByCardapio(@PathVariable Long id){
        return service.findByCardapioId(id);
    }
}
