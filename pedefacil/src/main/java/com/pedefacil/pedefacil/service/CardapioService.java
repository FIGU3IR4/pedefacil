package com.pedefacil.pedefacil.service;

import com.pedefacil.pedefacil.dto.CardapioResponse;
import com.pedefacil.pedefacil.model.Cardapio;
import com.pedefacil.pedefacil.repository.CardapioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardapioService {
    @Autowired
    private CardapioRepository repository;

    public List<CardapioResponse> findAll() {
        return repository.findAll().stream().map(cardapio -> new CardapioResponse(cardapio.getId(), cardapio.getNome(), cardapio.getRestaurante().getId())).toList();
    }

    public CardapioResponse findById(Long id) {
        Cardapio cardapio = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Cardapio Não encontrado com esse Id"));
        return new CardapioResponse(cardapio.getId(), cardapio.getNome(), cardapio.getRestaurante().getId());
    }

    public Cardapio save(Cardapio cardapio) {
        return repository.save(cardapio);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public List<CardapioResponse> findByRestauranteId(Long restauranteId) {
        return repository.findAll().stream()
                .filter(c -> c.getRestaurante().getId().equals(restauranteId))
                .map(c -> new CardapioResponse(c.getId(), c.getNome(), c.getRestaurante().getId()))
                .toList();
    }





    ;
}
