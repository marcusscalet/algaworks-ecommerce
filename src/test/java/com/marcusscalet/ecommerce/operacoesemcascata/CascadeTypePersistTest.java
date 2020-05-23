package com.marcusscalet.ecommerce.operacoesemcascata;

import com.marcusscalet.ecommerce.entitymanager.EntityManagerTest;
import com.marcusscalet.ecommerce.model.*;
import org.junit.Assert;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;

public class CascadeTypePersistTest extends EntityManagerTest {

//    @Test
    public void persistirPedidoComItens(){
        Cliente cliente = entityManager.find(Cliente.class,1);
        Produto produto = entityManager.find(Produto.class, 1);

        Pedido pedido = new Pedido();
        pedido.setCliente(cliente);
        pedido.setTotal(BigDecimal.TEN);
        pedido.setDataCriacao(LocalDateTime.now());
        pedido.setStatus(StatusPedido.PAGO);

        ItemPedido itemPedido = new ItemPedido();
        itemPedido.setId(new ItemPedidoId());
        itemPedido.setProduto(produto);
        itemPedido.setQuantidade(1);
        itemPedido.setPedido(pedido);
        itemPedido.setPrecoProduto(produto.getPreco());

        pedido.setItens(Arrays.asList(itemPedido)); //CascadeType.PERSIST

        entityManager.getTransaction().begin();
        entityManager.persist(pedido);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Pedido pedidoVerificacao = entityManager.find(Pedido.class, pedido.getId());

        Assert.assertNotNull(pedidoVerificacao);
        Assert.assertFalse(pedidoVerificacao.getItens().isEmpty());
    }

    //@Test
    public void persistirItemPedidoComPedido(){
        Cliente cliente = entityManager.find(Cliente.class,1);
        Produto produto = entityManager.find(Produto.class, 1);

        Pedido pedido = new Pedido();
        pedido.setCliente(cliente);
        pedido.setTotal(BigDecimal.ONE);
        pedido.setDataCriacao(LocalDateTime.now());
        pedido.setStatus(StatusPedido.AGUARDANDO);

        ItemPedido itemPedido = new ItemPedido();
        itemPedido.setId(new ItemPedidoId());
        itemPedido.setProduto(produto);
        itemPedido.setQuantidade(1);
        itemPedido.setPedido(pedido); // Não é necessário CascadeType.PERSISTE pois possui @MapsId
        itemPedido.setPrecoProduto(produto.getPreco());

        entityManager.getTransaction().begin();
        entityManager.persist(itemPedido);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Pedido pedidoVerificacao = entityManager.find(Pedido.class, pedido.getId());

        Assert.assertNotNull(pedidoVerificacao);
    }

//    @Test
    public void persistirPedidoComCliente(){
        Cliente cliente = new Cliente();
        cliente.setNome("Donald");
        cliente.setCpf("888");
        cliente.setSexo(SexoCliente.MASCULINO);
        cliente.setDataNascimento(LocalDate.of(1978,2,3));

        Pedido pedido = new Pedido();
        pedido.setCliente(cliente); //CascadeType.PERSIST
        pedido.setTotal(BigDecimal.ZERO);
        pedido.setDataCriacao(LocalDateTime.now());
        pedido.setStatus(StatusPedido.AGUARDANDO);

        entityManager.getTransaction().begin();
        entityManager.persist(pedido);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Cliente clienteVerificacao = entityManager.find(Cliente.class, cliente.getId());

        Assert.assertNotNull(cliente);
    }

        //@Test
        public void persistirProdutoComCategoria(){

        Categoria categoria = new Categoria();
        categoria.setNome("Cama, Mesa e Banho");

        Produto produto = new Produto();
        produto.setNome("Edredom casal Santista");
        produto.setDescricao("O mais confortável");
        produto.setPreco(BigDecimal.ONE);
        produto.setDataCriacao(LocalDateTime.now());
        produto.setDataUltimaAtualizacao(LocalDateTime.now());
        produto.setCategorias(Arrays.asList(categoria));  //CascadeType.PERSIST

        entityManager.getTransaction().begin();
        entityManager.persist(produto);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Produto produtoVerificacao = entityManager.find(Produto.class, produto.getId());
        Categoria categoriaVerificacao = entityManager.find(Categoria.class, categoria.getId());

        Assert.assertNotNull(produtoVerificacao);
        Assert.assertNotNull(categoriaVerificacao);
    }


}
