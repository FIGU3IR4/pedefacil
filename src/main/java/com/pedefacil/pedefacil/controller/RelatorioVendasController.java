package com.pedefacil.pedefacil.controller;
import com.pedefacil.pedefacil.dto.RelatorioVendasResponse;
import com.pedefacil.pedefacil.service.RelatorioVendasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/relatorios")
public class RelatorioVendasController {

    @Autowired
    private RelatorioVendasService service;

    @GetMapping("/{restauranteId}")
    public RelatorioVendasResponse gerarRelatorio(
            @PathVariable Long restauranteId,
            @RequestParam("periodo") String periodo
    ) {
        return service.gerarRelatorio(restauranteId, periodo);
    }
}
