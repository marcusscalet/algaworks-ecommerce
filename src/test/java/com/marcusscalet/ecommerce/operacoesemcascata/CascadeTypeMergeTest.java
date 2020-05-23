package com.marcusscalet.ecommerce.operacoesemcascata;

import com.marcusscalet.ecommerce.entitymanager.EntityManagerTest;
import com.marcusscalet.ecommerce.model.*;
import org.junit.Assert;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;

public class CascadeTypeMergeTest extends EntityManagerTest {

    //@Test
    public void atualizarProdutoComCategoria(){
        Produto produto = new Produto();
        produto.setDataCriacao(LocalDateTime.now());
        produto.setPreco(BigDecimal.TEN);
        produto.setNome("Kindle 3");
        produto.setDescricao("Agora com iluminação embutida ajustável.");

        Categoria categoria = new Categoria();
        categoria.setNome("Tablets");
        categoria.setId(2);

        produto.setCategorias(Arrays.asList(categoria));

        entityManager.getTransaction().begin();
        entityManager.merge(produto);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Categoria categoriaVerificacao = entityManager.find(Categoria.class, categoria.getId());
        Assert.assertEquals("Tablets", categoriaVerificacao.getNome());
    }

    //@Test
    public void atualizarItemPedidoComPedido(){
        Cliente cliente = entityManager.find(Cliente.class,1);
        Produto produto = entityManager.find(Produto.class, 1);

        Pedido pedido = new Pedido();
        pedido.setCliente(cliente);
        pedido.setId(1);
        pedido.setStatus(StatusPedido.PAGO);

        ItemPedido itemPedido = new ItemPedido();
        itemPedido.setId(new ItemPedidoId());
        itemPedido.getId().setPedidoId(pedido.getId());
        itemPedido.getId().setProdutoId(produto.getId());
        itemPedido.setProduto(produto);
        itemPedido.setPedido(pedido);
        itemPedido.setQuantidade(4);
        itemPedido.setPrecoProduto(produto.getPreco());

        pedido.setItens(Arrays.asList(itemPedido));

        entityManager.getTransaction().begin();
        entityManager.merge(itemPedido);
        entityManager.getTransaction().commit();

        entityManager.clear();

        ItemPedido itemPedidoVerificacao = entityManager.find(ItemPedido.class, itemPedido.getId());
        Assert.assertTrue(StatusPedido.PAGO.equals(itemPedidoVerificacao.getPedido().getStatus()));
    }

    //@Test
    public void atualizarPedidoComItens(){
        Cliente cliente = entityManager.find(Cliente.class,1);
        Produto produto = entityManager.find(Produto.class, 1);

        Pedido pedido = new Pedido();
        pedido.setCliente(cliente);
        pedido.setId(1);
        pedido.setStatus(StatusPedido.AGUARDANDO    );

        ItemPedido itemPedido = new ItemPedido();
        itemPedido.setId(new ItemPedidoId());
        itemPedido.getId().setPedidoId(pedido.getId());
        itemPedido.getId().setProdutoId(produto.getId());
        itemPedido.setProduto(produto);
        itemPedido.setQuantidade(4);
        itemPedido.setPedido(pedido);
        itemPedido.setPrecoProduto(produto.getPreco());

        pedido.setItens(Arrays.asList(itemPedido)); //CascadeType.MERGE

        entityManager.getTransaction().begin();
        entityManager.merge(pedido);
        entityManager.getTransaction().commit();

        entityManager.clear();

        ItemPedido itemPedidoVerificacao = entityManager.find(ItemPedido.class, itemPedido.getId());
        Assert.assertTrue(itemPedidoVerificacao.getQuantidade().equals(4));
    }
}
