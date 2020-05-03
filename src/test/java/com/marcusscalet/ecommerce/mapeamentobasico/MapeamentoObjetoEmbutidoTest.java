package com.marcusscalet.ecommerce.mapeamentobasico;

import com.marcusscalet.ecommerce.model.Cliente;
import com.marcusscalet.ecommerce.model.EnderecoEntregaPedido;
import com.marcusscalet.ecommerce.model.Pedido;
import com.marcusscalet.ecommerce.model.StatusPedido;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class MapeamentoObjetoEmbutidoTest extends EntityManagerTest {

    @Test
    public void test(){

        Cliente cliente = entityManager.find(Cliente.class, 1);
        EnderecoEntregaPedido end = new EnderecoEntregaPedido();
        end.setBairro("Liberdade");
        end.setCep("13301100");
        end.setCidade("Itu");
        end.setComplemento("Casa frente");
        end.setLogradouro("Ru Rio Amazonas");
        end.setNumero("351");

        Pedido pedido = new Pedido();
        pedido.setEnderecoEntregaPedido(end);
        pedido.setStatus(StatusPedido.AGUARDANDO);
        pedido.setTotal(new BigDecimal(5000));
        pedido.setDataPedido(LocalDateTime.now());
        pedido.setCliente(cliente);

        entityManager.getTransaction().begin();
        entityManager.persist(pedido);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Pedido pedidoVerificacao = entityManager.find(Pedido.class,pedido.getId());
        Assert.assertNotNull(pedidoVerificacao);
        Assert.assertNotNull(pedidoVerificacao.getEnderecoEntregaPedido());
        Assert.assertNotNull(pedidoVerificacao.getEnderecoEntregaPedido().getCep());
    }
}
