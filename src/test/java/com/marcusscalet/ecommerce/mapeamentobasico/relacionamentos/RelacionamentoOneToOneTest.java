package com.marcusscalet.ecommerce.mapeamentobasico.relacionamentos;

import com.marcusscalet.ecommerce.mapeamentobasico.EntityManagerTest;
import com.marcusscalet.ecommerce.model.NotaFiscal;
import com.marcusscalet.ecommerce.model.PagamentoCartao;
import com.marcusscalet.ecommerce.model.Pedido;
import com.marcusscalet.ecommerce.model.StatusPagamento;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;

public class RelacionamentoOneToOneTest extends EntityManagerTest {

	@Test
	public void verificarRelacionamento() {

		Pedido pedido = entityManager.find(Pedido.class, 1);

		PagamentoCartao pagamentoCartao = new PagamentoCartao();
		pagamentoCartao.setNumero("1254");
		pagamentoCartao.setStatus(StatusPagamento.PROCESSANDO);
		pagamentoCartao.setPedido(pedido);

		entityManager.getTransaction().begin();
	entityManager.persist(pagamentoCartao);
		entityManager.getTransaction().commit();

		entityManager.clear();

		Pedido pedidoVerificacao = entityManager.find(Pedido.class, pedido.getId());
		Assert.assertNotNull(pedidoVerificacao.getPagamento());
	}

	@Test
	public void verificarRelacionamento2() {

		Pedido pedido = entityManager.find(Pedido.class, 1);

		NotaFiscal nf = new NotaFiscal();
		nf.setXml("Teste");
		nf.setPedido(pedido);
		nf.setDataEmissao(new Date());

		entityManager.getTransaction().begin();
		entityManager.persist(nf);
		entityManager.getTransaction().commit();

		entityManager.clear();

		Pedido pedidoVerificacao = entityManager.find(Pedido.class, pedido.getId());
		Assert.assertNotNull(pedidoVerificacao.getNotaFiscal());
	}


}
