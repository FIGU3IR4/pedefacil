package com.pedefacil.pedefacil.controller;

import com.pedefacil.pedefacil.dto.PedidoRequest;
import com.pedefacil.pedefacil.dto.PedidoResponse;
import com.pedefacil.pedefacil.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class PedidoController {

    private final PedidoService pedidoService;

    @GetMapping
    public ResponseEntity<List<PedidoResponse>> getAllPedidos() {
        return ResponseEntity.ok(pedidoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoResponse> getPedidoById(@PathVariable Long id) {
        return ResponseEntity.ok(pedidoService.findById(id));
    }

    @PostMapping
    public ResponseEntity<PedidoResponse> createPedido(@RequestBody PedidoRequest request) {
        PedidoResponse novoPedido = pedidoService.createPedido(request);
        return ResponseEntity.ok(novoPedido);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePedido(@PathVariable Long id) {
        pedidoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/mesa/{mesaNumero}")
    public ResponseEntity<List<PedidoResponse>> getPedidosByMesa(@PathVariable Integer mesaNumero) {
        return ResponseEntity.ok(pedidoService.findByMesaNumero(mesaNumero));
    }
}
