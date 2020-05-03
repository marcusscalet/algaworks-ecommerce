package com.marcusscalet.ecommerce.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
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

	@OneToOne(optional = false)
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;

    private String xml;

    @Column(name = "data_emissao")
    private Date dataEmissao;
}
