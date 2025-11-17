package com.pedefacil.pedefacil.controller;

import com.pedefacil.pedefacil.model.Pedido;
import com.pedefacil.pedefacil.service.RelatorioService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/relatorio")
@RequiredArgsConstructor
public class RelatorioController {

    private final RelatorioService relatorioService;

    @GetMapping("/pedidos")
    public ResponseEntity<List<Pedido>> gerarRelatorio(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFim) {

        List<Pedido> pedidos = relatorioService.gerarRelatorio(dataInicio, dataFim);
        return ResponseEntity.ok(pedidos);
    }
}
