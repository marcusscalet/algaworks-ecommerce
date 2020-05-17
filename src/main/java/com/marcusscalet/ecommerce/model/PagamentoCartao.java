package com.marcusscalet.ecommerce.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "pagamento_cartao")
public class PagamentoCartao extends Pagamento{

    @Column(name = "numero_cartao")
    private String numeroCartao;
}
