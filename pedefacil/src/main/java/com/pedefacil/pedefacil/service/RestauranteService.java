package com.pedefacil.pedefacil.service;


import com.pedefacil.pedefacil.dto.RestauranteResponse;
import com.pedefacil.pedefacil.model.Restaurante;
import lombok.RequiredArgsConstructor;
import com.pedefacil.pedefacil.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestauranteService {
    @Autowired
    private RestauranteRepository repository;

    public  List<RestauranteResponse> findAll(){
        return  repository.findAll().stream().map(restaurante -> new RestauranteResponse(restaurante.getId(), restaurante.getNome(), restaurante.getEmail(), restaurante.getSenha())).toList();

    }

}
