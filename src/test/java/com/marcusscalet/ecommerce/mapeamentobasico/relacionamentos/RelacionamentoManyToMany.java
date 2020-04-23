package com.marcusscalet.ecommerce.mapeamentobasico.relacionamentos;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

import com.marcusscalet.ecommerce.mapeamentobasico.EntityManagerTest;
import com.marcusscalet.ecommerce.model.Categoria;
import com.marcusscalet.ecommerce.model.Produto;

public class RelacionamentoManyToMany extends EntityManagerTest {

	@Test
	public void verificarRelacionamento() {
		Produto produto = entityManager.find(Produto.class, 1);
		Categoria categoria = entityManager.find(Categoria.class, 1);

		entityManager.getTransaction().begin();

		/* categoria.setProdutos(Arrays.asList(produto)); */

		/* atributo owner da relação */
		produto.setCategorias(Arrays.asList(categoria));

		entityManager.getTransaction().commit();

		entityManager.clear();

		Categoria categoriaVerificacao = entityManager.find(Categoria.class, categoria.getId());
		Assert.assertFalse(categoriaVerificacao.getProdutos().isEmpty());
	}
}
