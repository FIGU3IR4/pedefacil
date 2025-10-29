package com.pedefacil.pedefacil.service;

import com.pedefacil.pedefacil.dto.HistoricoPedidoResponse;
import com.pedefacil.pedefacil.model.HistoricoPedido;
import com.pedefacil.pedefacil.repository.HistoricoPedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class HistoricoPedidoService {

    @Autowired
    private HistoricoPedidoRepository repository;

    public List<HistoricoPedidoResponse> buscarPorPeriodo(Long restauranteId, LocalDateTime inicio, LocalDateTime fim) {
        return repository.findByRestauranteIdAndDataHoraFinalizacaoBetween(restauranteId, inicio, fim)
                .stream()
                .map(h -> new HistoricoPedidoResponse(
                        h.getId(),
                        h.getRestauranteId(),
                        h.getPedidoId(),
                        h.getValorTotal(),
                        h.getDataHoraFinalizacao(),
                        h.getObservacao()
                ))
                .toList();
    }

    public HistoricoPedido salvar(HistoricoPedido historico) {
        return repository.save(historico);
    }
}
