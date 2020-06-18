package com.marcusscalet.ecommerce.iniciandocomjpa;

import org.junit.Assert;
import org.junit.Test;

import com.marcusscalet.ecommerce.entitymanager.EntityManagerTest;
import com.marcusscalet.ecommerce.model.Produto;

public class ConsultandoRegistrosTest extends EntityManagerTest {

	@Test
	public void buscarPorIdentificador() {
		Produto produto = entityManager.find(Produto.class, 1);

		System.out.println(">>>" + produto.getNome());

		Assert.assertNotNull(produto);
		Assert.assertEquals("Kindle", produto.getNome());
	}

	@Test
	public void atualizarReferencia() {
		Produto produto = entityManager.find(Produto.class, 1);
		produto.setNome("Microfone Samson");

		entityManager.refresh(produto);

		Assert.assertEquals("Kindle", produto.getNome());
	}
}
