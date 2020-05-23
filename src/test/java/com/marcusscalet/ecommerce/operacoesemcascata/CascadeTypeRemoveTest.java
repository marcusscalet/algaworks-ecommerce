package com.marcusscalet.ecommerce.operacoesemcascata;

import com.marcusscalet.ecommerce.entitymanager.EntityManagerTest;
import com.marcusscalet.ecommerce.model.ItemPedido;
import com.marcusscalet.ecommerce.model.ItemPedidoId;
import com.marcusscalet.ecommerce.model.Pedido;
import com.marcusscalet.ecommerce.model.Produto;
import org.junit.Assert;

public class CascadeTypeRemoveTest extends EntityManagerTest {

    //@Test
    public void removerItensOrfaos(){
        Pedido pedido = entityManager.find(Pedido.class, 1);

        Assert.assertFalse((pedido.getItens().isEmpty()));

        entityManager.getTransaction().begin();
        pedido.getItens().clear();
        entityManager.getTransaction().commit();

        entityManager.clear();

        Pedido pedidoVerificacao = entityManager.find(Pedido.class, pedido.getId());

        Assert.assertTrue(pedidoVerificacao.getItens().isEmpty());
    }

    //@Test
    public void removerPedidoEItens(){
        Pedido pedido = entityManager.find(Pedido.class, 1);

        entityManager.getTransaction().begin();
        entityManager.remove(pedido);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Pedido pedidoVerificacao = entityManager.find(Pedido.class, 1);
        Assert.assertNull(pedidoVerificacao);
    }

    //@Test
    public  void removerItemPedidoEPedido(){
        ItemPedido itemPedido = entityManager.find(ItemPedido.class, new ItemPedidoId(1,1));

        entityManager.getTransaction().begin();
        entityManager.remove(itemPedido);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Pedido pedidoVerificacao = entityManager.find(Pedido.class, itemPedido.getPedido().getId());
        Assert.assertNull(pedidoVerificacao);
    }

    //@Test
    public  void removerRelacaoProdutoCategoria(){
        Produto produto = entityManager.find(Produto.class, 1);

        Assert.assertFalse(produto.getCategorias().isEmpty());

        entityManager.getTransaction().begin();
        produto.getCategorias().clear();
        entityManager.getTransaction().commit();

        entityManager.clear();

        Produto produtoVerificacao = entityManager.find(Produto.class, produto.getId());
        Assert.assertTrue(produtoVerificacao.getCategorias().isEmpty());
    }
}
