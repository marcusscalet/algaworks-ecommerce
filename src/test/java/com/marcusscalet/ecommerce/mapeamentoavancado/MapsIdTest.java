package com.marcusscalet.ecommerce.mapeamentoavancado;

import com.marcusscalet.ecommerce.entitymanager.EntityManagerTest;
import com.marcusscalet.ecommerce.model.*;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

public class MapsIdTest extends EntityManagerTest {

    @Test
    public void inserirPagamento() {
        Pedido pedido = entityManager.find(Pedido.class, 1);

        NotaFiscal notaFiscal = new NotaFiscal();
        notaFiscal.setPedido(pedido);
        notaFiscal.setDataEmissao(new Date());
//        notaFiscal.setXml("<xml>");

        entityManager.getTransaction().begin();
        entityManager.persist(notaFiscal);
        entityManager.getTransaction().commit();

        entityManager.clear();

        NotaFiscal notaFiscalVerificacao = entityManager.find(NotaFiscal.class, notaFiscal.getId());
        Assert.assertNotNull(notaFiscalVerificacao);
        Assert.assertEquals(pedido.getId(), notaFiscalVerificacao.getId());
    }

    @Test
    public void inserItemPedido(){
        Cliente cliente = entityManager.find(Cliente.class, 1);
        Produto produto = entityManager.find(Produto.class, 1);

        Pedido pedido = new Pedido();
        pedido.setTotal(BigDecimal.TEN);
        pedido.setStatus(StatusPedido.PAGO);
        pedido.setDataCriacao(LocalDateTime.now());
        pedido.setCliente(cliente);

        ItemPedido itemPedido = new ItemPedido();
        itemPedido.setId(new ItemPedidoId());
        itemPedido.setQuantidade(2);
        itemPedido.setPedido(pedido);
        itemPedido.setProduto(produto);
        itemPedido.setPrecoProduto(BigDecimal.TEN);

        entityManager.getTransaction().begin();
        entityManager.persist(pedido);
        entityManager.persist(itemPedido);
        entityManager.getTransaction().commit();

        entityManager.clear();

        ItemPedido itemPedidoVerificacao = entityManager.find(ItemPedido.class, new ItemPedidoId(pedido.getId(),produto.getId()));
        Assert.assertNotNull(itemPedidoVerificacao);
    }
}
