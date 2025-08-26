package com.pedefacil.pedefacil.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestauranteResponse {
    private  Long id;
    private  String email;
    private  String nome;
    private  String senha;
}
