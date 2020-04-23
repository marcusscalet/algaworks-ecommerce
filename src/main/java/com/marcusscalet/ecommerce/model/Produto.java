package com.marcusscalet.ecommerce.model;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "produto")
public class Produto {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Id
    private Integer id;

    private String nome;

    private String descricao;

    private BigDecimal preco;
    
    @ManyToMany
    @JoinTable(
    		name="produto_categoria",
    		joinColumns = @JoinColumn(name = "produto_id"),
    		inverseJoinColumns = @JoinColumn(name = "categoria_id"))
    private List<Categoria> categorias;
}
