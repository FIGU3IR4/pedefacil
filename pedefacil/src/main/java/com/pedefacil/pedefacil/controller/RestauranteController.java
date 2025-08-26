package com.pedefacil.pedefacil.controller;

import com.pedefacil.pedefacil.dto.CardapioResponse;
import com.pedefacil.pedefacil.dto.RestauranteResponse;
import com.pedefacil.pedefacil.service.CardapioService;
import com.pedefacil.pedefacil.service.RestauranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://127.0.0.1:5500")

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {
    @Autowired
    private RestauranteService service;

    @GetMapping
    public List<RestauranteResponse>getAll(){
        return  service.findAll();
    }
}

