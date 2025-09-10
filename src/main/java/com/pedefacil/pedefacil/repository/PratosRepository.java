package com.pedefacil.pedefacil.repository;

import com.pedefacil.pedefacil.model.Pratos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PratosRepository extends JpaRepository<Pratos, Long> {
    List<Pratos> findByCardapioId(Long cardapioId);
}

