package com.pedefacil.pedefacil.repository;

import com.pedefacil.pedefacil.model.Pratos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PratosRepository extends JpaRepository<Pratos, Long> {
}
