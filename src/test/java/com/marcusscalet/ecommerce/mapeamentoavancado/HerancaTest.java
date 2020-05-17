package com.marcusscalet.ecommerce.mapeamentoavancado;

import com.marcusscalet.ecommerce.entitymanager.EntityManagerTest;
import com.marcusscalet.ecommerce.model.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class HerancaTest extends EntityManagerTest {

    @Test
    public void salvarCliente(){
        Cliente cliente = new Cliente();
        cliente.setNome("Fernanda Moraes");

        entityManager.getTransaction().begin();
        entityManager.persist(cliente);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Cliente clienteVerificacao = entityManager.find(Cliente.class, cliente.getId());
        Assert.assertNotNull(clienteVerificacao.getId());
    }

    @Test
    public void buscarPagamento(){
        List<Pagamento> pagamentos = entityManager.
                createQuery("select p from Pagamento p")
                .getResultList();

        Assert.assertFalse(pagamentos.isEmpty());
    }

    @Test
    public void incluirPagamentoPedido(){
        Pedido pedido = entityManager.find(Pedido.class,1);

        PagamentoCartao pagamentoCartao = new PagamentoCartao();
        pagamentoCartao.setNumeroCartao("123");
        pagamentoCartao.setPedido(pedido);
        pagamentoCartao.setStatus(StatusPagamento.PROCESSANDO);

        entityManager.getTransaction().begin();
        entityManager.persist(pagamentoCartao);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Pedido pedidoVerificacao = entityManager.find(Pedido.class, pedido.getId());
        Assert.assertNotNull(pedidoVerificacao.getPagamento());
    }
}
