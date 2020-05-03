package com.marcusscalet.ecommerce.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@Entity
@Table(name = "item_pedido")
public class ItemPedido {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	@Id
	private Integer id;

	@ManyToOne(optional = false)
	@JoinColumn(name = "pedido_id")
	private Pedido pedido;

	@ManyToOne(optional = false)
	@JoinColumn(name = "produto_id")
	private Produto produto;

	@Column(name = "preco_produto")
	private BigDecimal precoProduto;

	private Integer quantidade;

}
