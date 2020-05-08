package com.marcusscalet.ecommerce.listener;

import com.marcusscalet.ecommerce.model.Pedido;
import com.marcusscalet.ecommerce.service.NotaFiscalService;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

public class GerarNotaFiscalListener {

    private NotaFiscalService notaFiscalService = new NotaFiscalService();

    @PrePersist
    @PreUpdate
    public void gerar(Pedido pedido){
        if (pedido.isPago() && pedido.getNotaFiscal() == null) {
            notaFiscalService.gerar(pedido);
        }
    }
}
