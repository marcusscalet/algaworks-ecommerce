package com.marcusscalet.ecommerce.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "pagamento_boleto")
public class PagamentoBoleto extends Pagamento {

    @Column(name = "codigo_barras")
    private String codigoBarras;
}
