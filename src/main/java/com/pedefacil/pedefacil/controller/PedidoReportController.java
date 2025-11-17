package com.pedefacil.pedefacil.controller;

import com.pedefacil.pedefacil.model.Pedido;
import com.pedefacil.pedefacil.service.PedidoReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/relatorios")
@RequiredArgsConstructor
public class PedidoReportController {

    private final PedidoReportService reportService;

    @GetMapping("/pedidos")
    public ResponseEntity<List<Pedido>> gerarRelatorio(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate inicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fim) {

        // Converte LocalDate para LocalDateTime no início e fim do dia
        LocalDateTime dataInicio = inicio.atStartOfDay();
        LocalDateTime dataFim = fim.atTime(23, 59, 59);

        List<Pedido> pedidos = reportService.gerarRelatorio(dataInicio, dataFim);
        return ResponseEntity.ok(pedidos);
    }
}
