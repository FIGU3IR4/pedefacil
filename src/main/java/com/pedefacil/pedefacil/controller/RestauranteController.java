package com.pedefacil.pedefacil.controller;

import com.pedefacil.pedefacil.dto.RestauranteResponse;
import com.pedefacil.pedefacil.service.RestauranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {
    @Autowired
    private RestauranteService service;

    @GetMapping
    public List<RestauranteResponse> getAll() {
        return service.findAll();
    }
    @GetMapping ("{id}")
    public  RestauranteResponse getById(@PathVariable Long id){
        return service.findById(id);
    }


}




