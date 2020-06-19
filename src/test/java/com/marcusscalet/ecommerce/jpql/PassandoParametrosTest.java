package com.marcusscalet.ecommerce.jpql;

import com.marcusscalet.ecommerce.entitymanager.EntityManagerTest;
import com.marcusscalet.ecommerce.model.NotaFiscal;
import com.marcusscalet.ecommerce.model.Pedido;
import com.marcusscalet.ecommerce.model.StatusPagamento;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;
import java.util.Date;
import java.util.List;

public class PassandoParametrosTest extends EntityManagerTest {

	@Test
	public void passarParametroDate() {
		String jpql = "Select nf from NotaFiscal nf where nf.dataEmissao <= ?1";
		
		TypedQuery<NotaFiscal> typedQuery = entityManager.createQuery(jpql, NotaFiscal.class);
		
		typedQuery.setParameter(1, new Date(), TemporalType.TIMESTAMP);
		
		List<NotaFiscal> lista = typedQuery.getResultList();
		Assert.assertTrue(lista.size() == 1);
	}

	@Test
	public void passarParametro() {
		String jpql = "Select p from Pedido p"
				+ " join p.pagamento pag"
				+ " where p.id = :pedidoId"
				+ " and pag.status = ?1";

		TypedQuery<Pedido> typedQuery = entityManager.createQuery(jpql, Pedido.class);

		typedQuery.setParameter("pedidoId", 2);
		typedQuery.setParameter(1, StatusPagamento.PROCESSANDO);

		List<Pedido> lista = typedQuery.getResultList();
		Assert.assertTrue(lista.size() == 1);
	}
}
