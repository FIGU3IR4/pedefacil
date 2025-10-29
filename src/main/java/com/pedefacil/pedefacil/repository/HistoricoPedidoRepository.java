package com.pedefacil.pedefacil.repository;
import com.pedefacil.pedefacil.model.HistoricoPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface HistoricoPedidoRepository extends JpaRepository<HistoricoPedido, Long> {

    List<HistoricoPedido> findByRestauranteIdAndDataHoraFinalizacaoBetween(
            Long restauranteId,
            LocalDateTime inicio,
            LocalDateTime fim
    );
}
