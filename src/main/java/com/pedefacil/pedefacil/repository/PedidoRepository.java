package com.pedefacil.pedefacil.repository;

import com.pedefacil.pedefacil.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    // Método para buscar pedidos entre duas datas
    List<Pedido> findByDataHoraBetween(LocalDateTime inicio, LocalDateTime fim);
}
