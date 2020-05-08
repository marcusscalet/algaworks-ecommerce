package com.marcusscalet.ecommerce.service;

import com.marcusscalet.ecommerce.model.Pedido;

public class NotaFiscalService {

    public void gerar(Pedido pedido){
        System.out.println("Gerando nota para o pedido " + pedido.getId() + ".");
    }
}
