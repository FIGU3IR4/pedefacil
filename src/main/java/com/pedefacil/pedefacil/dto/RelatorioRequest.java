package com.pedefacil.pedefacil.dto;

import java.time.LocalDate;

public record RelatorioRequest(
        LocalDate dataInicio,
        LocalDate dataFim
) {}
