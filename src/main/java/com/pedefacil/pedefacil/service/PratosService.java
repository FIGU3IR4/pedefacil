package com.pedefacil.pedefacil.service;

import com.pedefacil.pedefacil.dto.CardapioResponse;
import com.pedefacil.pedefacil.dto.PratosResponse;
import com.pedefacil.pedefacil.model.Cardapio;
import com.pedefacil.pedefacil.model.Pratos;
import com.pedefacil.pedefacil.repository.PratosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import  java.util.List;


@Service
public class PratosService {
    @Autowired
    private PratosRepository repository;

    public List<PratosResponse> findAll(){
        return  repository.findAll().stream().map(pratos-> new PratosResponse(pratos.getId(), pratos.getNome(), pratos.getDescricao(), pratos.getPreco(), pratos.getCardapio().getId())).toList();
    }
    public List<PratosResponse> findByCardapioId(Long cardapioId) {
        return repository.findAll().stream()
                .filter(c -> c.getCardapio().getId().equals(cardapioId))
                .map(c -> new PratosResponse(c.getId(), c.getNome(), c.getDescricao(), c.getPreco(), c.getCardapio().getId()))
                .toList();
    }

}
