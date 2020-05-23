package com.marcusscalet.ecommerce.operacoesemcascata;

import com.marcusscalet.ecommerce.entitymanager.EntityManagerTest;
import com.marcusscalet.ecommerce.model.ItemPedido;
import com.marcusscalet.ecommerce.model.ItemPedidoId;
import com.marcusscalet.ecommerce.model.Pedido;
import org.junit.Assert;

public class CascadeTypeRemoveTest extends EntityManagerTest {

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
}
