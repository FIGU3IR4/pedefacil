package com.pedefacil.pedefacil.controller;
import com.pedefacil.pedefacil.dto.HistoricoPedidoResponse;
import com.pedefacil.pedefacil.service.HistoricoPedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/historico")
public class HistoricoPedidoController {

    @Autowired
    private HistoricoPedidoService service;

    @GetMapping("/{restauranteId}")
    public List<HistoricoPedidoResponse> buscarHistoricoPorPeriodo(
            @PathVariable Long restauranteId,
            @RequestParam("inicio") String inicio,
            @RequestParam("fim") String fim
    ) {
        LocalDateTime dataInicio = LocalDateTime.parse(inicio);
        LocalDateTime dataFim = LocalDateTime.parse(fim);

        return service.buscarPorPeriodo(restauranteId, dataInicio, dataFim);
    }
}
