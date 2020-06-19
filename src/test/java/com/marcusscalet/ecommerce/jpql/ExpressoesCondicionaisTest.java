package com.marcusscalet.ecommerce.jpql;

import com.marcusscalet.ecommerce.entitymanager.EntityManagerTest;
import com.marcusscalet.ecommerce.model.Pedido;
import com.marcusscalet.ecommerce.model.Produto;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.TypedQuery;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class ExpressoesCondicionaisTest extends EntityManagerTest {

	@Test
	public void usarExpressaoDiferente() {

		String jpql = "select p from Produto p" +
				" where p.preco <> 100";

		TypedQuery<Produto> typedQuery = entityManager.createQuery(jpql, Produto.class);

		List<Produto> lista = typedQuery.getResultList();
		Assert.assertFalse(lista.isEmpty());
	}

	@Test
	public void usarExpressaoBetween() {

		String jpql = "select p from Pedido p" +
				" where p.dataCriacao between :dataInicial and :dataFinal";

		TypedQuery<Pedido> typedQuery = entityManager.createQuery(jpql, Pedido.class);
		typedQuery.setParameter("dataInicial", LocalDateTime.now().minusDays(2));
		typedQuery.setParameter("dataFinal", LocalDateTime.now());

		List<Pedido> lista = typedQuery.getResultList();
		Assert.assertFalse(lista.isEmpty());
	}

	@Test
	public void usarExpressaoDatas() {
		
		//todos os pedidos ultimos 2 dias
		String jpql = "select p from Pedido p"
				+ " where p.dataCriacao >= :dataInicial";
		
		TypedQuery<Pedido> typedQuery = entityManager.createQuery(jpql, Pedido.class);
		typedQuery.setParameter("dataInicial", LocalDateTime.now().minusDays(2));
		
		List<Pedido> lista = typedQuery.getResultList();
		Assert.assertFalse(lista.isEmpty());
	}
	
	@Test
	public void usarExpressaoMaiorMenor() {
		
		String jpql = "select p from Produto p where p.preco >= :precoInicial and p.preco <= :precoFinal";
		
		TypedQuery<Produto> typedQuery = entityManager.createQuery(jpql, Produto.class);
		typedQuery.setParameter("precoInicial", new BigDecimal(499));
		typedQuery.setParameter("precoFinal", new BigDecimal(1500));
		
		List<Produto> lista = typedQuery.getResultList();
		Assert.assertFalse(lista.isEmpty());
	}
	
	@Test
	public void usarExpressaoIsNull() {
		
		String jpql = "select p from Produto p where p.foto is null";
		
		TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql, Object[].class);
		
		List<Object[]> lista = typedQuery.getResultList();
		Assert.assertFalse(lista.isEmpty());
	}
	
	@Test
	public void usarExpressaoIsEmpty() {
		
		String jpql = "select p from Produto p where p.categorias is empty";
		
		TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql, Object[].class);
		
		List<Object[]> lista = typedQuery.getResultList();
		Assert.assertFalse(lista.isEmpty());
	}
	
	@Test
	public void usarExpressaoCondicionalLike() {
		/*
		String jpql = "select c from Cliente c where c.nome like :nome";
		sem o uso do % o like seria interpretado como o símbolo =
		typedQuery.setParameter("nome", "John%");


		buscar por sobrenome
		String jpql2 = "select c from Cliente c where c.nome like concat('%', :nome)";
		
		
		buscar por tudo que contiver o que especificar, por ex: a
		String jpql = "select c from Cliente c where c.nome like concat('%', :nome, '%')";
		typedQuery.setParameter("nome", "a");
		*/		
		
		String jpql = "select c from Cliente c where c.nome like concat(:nome, '%')";
		
		TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql, Object[].class);
		typedQuery.setParameter("nome", "John");
		
		List<Object[]> lista = typedQuery.getResultList();
		Assert.assertFalse(lista.isEmpty());
	}

}
