package com.pedefacil.pedefacil.service;

import com.pedefacil.pedefacil.dto.*;
import com.pedefacil.pedefacil.model.*;
import com.pedefacil.pedefacil.repository.PedidoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PedidoService {

    @Autowired
    private PedidoRepository repository;

    public List<PedidoResponse> findAll() {
        return repository.findAll().stream()
                .map(this::mapToResponse)
                .toList();
    }

    public PedidoResponse findById(Long id) {
        Pedido pedido = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Pedido não encontrado com esse Id"));
        return mapToResponse(pedido);
    }

    public PedidoResponse createPedido(PedidoRequest request) {
        Pedido pedido = new Pedido();
        pedido.setMesaNumero(request.getMesaNumero());
        pedido.setNomeCliente(request.getNomeCliente());
        pedido.setDataHora(LocalDateTime.now());
        pedido.setStatus(PedidoStatus.PREPARANDO); // Define um status inicial

        // associar o cardápio
        Cardapio cardapio = new Cardapio();
        cardapio.setId(request.getCardapioId());
        pedido.setCardapio(cardapio);

        // mapear itens
        List<ItemPedido> itens = request.getItens().stream().map(i -> {
            ItemPedido item = new ItemPedido();
            Pratos prato = new Pratos();
            prato.setId(i.getPratoId());
            item.setPrato(prato);
            item.setQuantidade(i.getQuantidade());
            item.setPrecoUnitario(i.getPrecoUnitario());
            item.setPedido(pedido);
            return item;
        }).toList();

        pedido.setItens(itens);

        Pedido salvo = repository.save(pedido);
        return mapToResponse(salvo);
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
                                i.getPrato().getNome(),
                                i.getQuantidade(),
                                i.getPrecoUnitario()
                        ))
                        .toList(),
                pedido.getStatus() // Incluído o status corretamente
        );
    }
}
