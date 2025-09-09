package com.pedefacil.pedefacil.controller;

import com.pedefacil.pedefacil.dto.CardapioResponse;
import com.pedefacil.pedefacil.model.Cardapio;
import com.pedefacil.pedefacil.service.CardapioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cardapio")
public class CardapioController {
    @Autowired
    private CardapioService service;

    @GetMapping
    public List<CardapioResponse>getAll(){
        return service.findAll();

    }
    @GetMapping ("/{id}")
    public CardapioResponse getById(@PathVariable Long id) {
        return service.findById(id);
    }
    @GetMapping("/restaurante/{id}")
    public List<CardapioResponse> getCardapioByRestaurante(@PathVariable Long id) {
        return service.findByRestauranteId(id);
    }


    @PostMapping
    public Cardapio create(@RequestBody Cardapio cardapio){
        return  service.save(cardapio);
    }
    @DeleteMapping ("/{id}")
    public  void delete(@PathVariable Long id){
        service.delete(id);
    }

}
