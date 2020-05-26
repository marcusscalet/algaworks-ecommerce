package com.marcusscalet.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoDTO {

    private Integer id;

    private String nome;

//    public ProdutoDTO(Integer id, String nome){
//        this.id = id;
//        this.nome = nome;
//    }
}