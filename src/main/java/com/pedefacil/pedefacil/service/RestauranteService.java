package com.pedefacil.pedefacil.service;


import com.pedefacil.pedefacil.dto.RestauranteResponse;
import com.pedefacil.pedefacil.model.Restaurante;
import com.pedefacil.pedefacil.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestauranteService {
    @Autowired
    private RestauranteRepository repository;

    public  List<RestauranteResponse> findAll(){
        return  repository.findAll().stream().map(restaurante -> new RestauranteResponse(restaurante.getId(), restaurante.getEmail(), restaurante.getNome(), restaurante.getEndereco(), restaurante.getNumero())).toList();

    }
    public RestauranteResponse findById(Long id){
        Restaurante restaurante=repository.findById(id).orElseThrow(()-> new IllegalArgumentException("Cardapio Não encontrado com esse Id"));

        return  new RestauranteResponse(restaurante.getId(), restaurante.getEmail(), restaurante.getNome(), restaurante.getEndereco(), restaurante.getNumero());
    }
   

}
