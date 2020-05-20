package com.marcusscalet.ecommerce.conhecendoentitymanager;

import com.marcusscalet.ecommerce.entitymanager.EntityManagerTest;
import com.marcusscalet.ecommerce.model.Cliente;
import com.marcusscalet.ecommerce.model.Pedido;
import com.marcusscalet.ecommerce.model.Produto;
import com.marcusscalet.ecommerce.model.StatusPedido;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ListenersTest extends EntityManagerTest {

    @Test
    public void carregarEntidades(){
        Produto produto = entityManager.find(Produto.class, 1);
        Pedido pedido = entityManager.find(Pedido.class, 1);
    }
    @Test
    public void acionarCallBack(){
        Cliente cliente = entityManager.find(Cliente.class,1);

        Pedido pedido = new Pedido();

        pedido.setCliente(cliente);
        pedido.setStatus(StatusPedido.AGUARDANDO);
        pedido.setTotal(BigDecimal.TEN);
        pedido.setDataCriacao(LocalDateTime.now());

        entityManager.getTransaction().begin();

        entityManager.persist(pedido);
        entityManager.flush();

        pedido.setStatus(StatusPedido.PAGO);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Pedido pedidoVerificacao = entityManager.find(Pedido.class, pedido.getId());

        Assert.assertNotNull(pedidoVerificacao.getDataCriacao());
        Assert.assertNotNull(pedidoVerificacao.getDataUltimaAtualizacao());
    }
}
