package com.marcusscalet.ecommerce.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@Entity
@Table(name = "estoque")
public class Estoque {

    @EqualsAndHashCode.Include
    @Id
    private Integer Id;


    @Column(name = "produto_id")
    private Integer produtoId;

    private Integer quantidade;
}