package com.marcusscalet.ecommerce.mapeamentobasico.relacionamentos;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.junit.Assert;
import org.junit.Test;

import com.marcusscalet.ecommerce.mapeamentobasico.EntityManagerTest;
import com.marcusscalet.ecommerce.model.Cliente;
import com.marcusscalet.ecommerce.model.ItemPedido;
import com.marcusscalet.ecommerce.model.Pedido;
import com.marcusscalet.ecommerce.model.Produto;
import com.marcusscalet.ecommerce.model.StatusPedido;

public class RelacionamentoManyToOneTest extends EntityManagerTest {

	@Test
	public void verificarRelacionamento() {

		Cliente cliente = entityManager.find(Cliente.class, 1);

		Pedido pedido = new Pedido();
		pedido.setStatus(StatusPedido.AGUARDANDO);
		pedido.setDataPedido(LocalDateTime.now());
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

		Cliente cliente = entityManager.find(Cliente.class, 1);
		Produto produto = entityManager.find(Produto.class, 1);

		System.out.println(produto);

		Pedido pedido = new Pedido();
		pedido.setStatus(StatusPedido.AGUARDANDO);
		pedido.setDataPedido(LocalDateTime.now());
		pedido.setTotal(BigDecimal.TEN);
		pedido.setCliente(cliente);

		ItemPedido itemPedido = new ItemPedido();
		itemPedido.setProduto(produto);
		itemPedido.setQuantidade(1);
		itemPedido.setPrecoProduto(BigDecimal.TEN);
		itemPedido.setPedido(pedido);

		entityManager.getTransaction().begin();
		entityManager.persist(pedido);
		entityManager.persist(itemPedido);
		entityManager.getTransaction().commit();

		entityManager.clear();

		ItemPedido itemPedidoVerificacao = entityManager.find(ItemPedido.class, itemPedido.getId());

		Assert.assertNotNull(itemPedidoVerificacao.getPedido());
		Assert.assertNotNull(itemPedidoVerificacao.getProduto());
	}
}
