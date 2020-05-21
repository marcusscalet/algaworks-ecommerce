package com.marcusscalet.ecommerce.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "nota_fiscal")
public class NotaFiscal extends EntidadeBaseInteger{

    @MapsId
	@OneToOne(optional = false)
    @JoinColumn(name = "pedido_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_nota_fisal_pedido"))
    private Pedido pedido;

    @Lob
    @Column(nullable = false)
    private byte[] xml;

    @Column(name = "data_emissao", nullable = false)
    private Date dataEmissao;
}
