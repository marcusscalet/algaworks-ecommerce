package com.marcusscalet.ecommerce.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@Entity
@Table(name = "nota_fiscal")
public class NotaFiscal {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Id
    private Integer id;

    @Column(name = "pedido")
    private Integer pedidoId;

    private String xml;

    @Column(name = "data_emissao")
    private Date dataEmissao;
}
