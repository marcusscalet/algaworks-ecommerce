package com.marcusscalet.ecommerce.mapeamentobasico.relacionamentos;

import com.marcusscalet.ecommerce.mapeamentobasico.EntityManagerTest;
import com.marcusscalet.ecommerce.model.*;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class RelacionamentoManyToOneTest extends EntityManagerTest {

	@Test
	public void verificarRelacionamento() {

		Cliente cliente = entityManager.find(Cliente.class, 1);

		Pedido pedido = new Pedido();
		pedido.setStatus(StatusPedido.AGUARDANDO);
		pedido.setDataCriacao(LocalDateTime.now());
		pedido.setTotal(BigDecimal.TEN);

		pedido.setCliente(cliente);

		entityManager.getTransaction().begin();
		entityManager.persist(pedido);
		entityManager.getTransaction().commit();

		entityManager.clear();

		Pedido pedidoVerificacao = entityManager.find(Pedido.class, pedido.getId());

		Assert.assertNotNull(pedidoVerificacao.getCliente());
	}

	@Test
	public void verificarRelacionamentoItemPedido() {

		entityManager.getTransaction().begin();

		Cliente cliente = entityManager.find(Cliente.class, 1);
		Produto produto = entityManager.find(Produto.class, 1);

		System.out.println(produto);

		Pedido pedido = new Pedido();
		pedido.setStatus(StatusPedido.AGUARDANDO);
		pedido.setDataCriacao(LocalDateTime.now());
		pedido.setTotal(BigDecimal.TEN);
		pedido.setCliente(cliente);

		entityManager.persist(pedido);

		entityManager.flush();

		ItemPedido itemPedido = new ItemPedido();
//		itemPedido.setPedidoId(pedido.getId()); IdClass
//		itemPedido.setProdutoId(produto.getId()); IdClass
		itemPedido.setId(new ItemPedidoId(pedido.getId(), produto.getId()));
		itemPedido.setProduto(produto);
		itemPedido.setQuantidade(1);
		itemPedido.setPrecoProduto(BigDecimal.TEN);
		itemPedido.setPedido(pedido);

		entityManager.persist(itemPedido);

		entityManager.getTransaction().commit();

		entityManager.clear();

		ItemPedido itemPedidoVerificacao = entityManager.find(ItemPedido.class, new ItemPedidoId(1,1));

		Assert.assertNotNull(itemPedidoVerificacao.getPedido());
		Assert.assertNotNull(itemPedidoVerificacao.getProduto());
	}
}
