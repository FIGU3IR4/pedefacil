package com.pedefacil.pedefacil.repository;

import com.pedefacil.pedefacil.model.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface RestauranteRepository extends JpaRepository<Restaurante, Long> {
    Optional<Restaurante> findByEmail(String email); // <--- importante
}
