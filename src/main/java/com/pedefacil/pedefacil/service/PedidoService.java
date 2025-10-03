package com.pedefacil.pedefacil.service;

import com.pedefacil.pedefacil.dto.ItemPedidoResponse;
import com.pedefacil.pedefacil.dto.PedidoResponse;
import com.pedefacil.pedefacil.model.Pedido;
import com.pedefacil.pedefacil.repository.PedidoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PedidoService {

    @Autowired
    private PedidoRepository repository;

    public List<PedidoResponse> findAll() {
        return repository.findAll().stream().map(this::mapToResponse).toList();
    }

    public PedidoResponse findById(Long id) {
        Pedido pedido = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Pedido não encontrado com esse Id"));
        return mapToResponse(pedido);
    }

    public Pedido save(Pedido pedido) {
        return repository.save(pedido);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public List<PedidoResponse> findByMesaNumero(Integer mesaNumero) {
        return repository.findAll().stream()
                .filter(p -> p.getMesaNumero().equals(mesaNumero))
                .map(this::mapToResponse)
                .toList();
    }

    private PedidoResponse mapToResponse(Pedido pedido) {
        return new PedidoResponse(
                pedido.getId(),
                pedido.getMesaNumero(),
                pedido.getNomeCliente(),
                pedido.getDataHora(),
                pedido.getCardapio().getId(),
                pedido.getItens().stream()
                        .map(i -> new ItemPedidoResponse(
                                i.getId(),
                                i.getPrato().getNome(),    // nome do prato
                                i.getQuantidade(),
                                i.getPrecoUnitario()       // preço unitário
                        ))
                        .toList()
        );
    }
}
