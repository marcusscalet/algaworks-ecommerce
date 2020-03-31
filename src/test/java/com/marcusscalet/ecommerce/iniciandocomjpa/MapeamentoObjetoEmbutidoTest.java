package com.marcusscalet.ecommerce.iniciandocomjpa;

import com.marcusscalet.ecommerce.EntityManagerTest;
import com.marcusscalet.ecommerce.model.EnderecoEntregaPedido;
import com.marcusscalet.ecommerce.model.Pedido;
import com.marcusscalet.ecommerce.model.StatusPedido;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class MapeamentoObjetoEmbutidoTest extends EntityManagerTest {

    @Test
    public void test(){
        EnderecoEntregaPedido end = new EnderecoEntregaPedido();
        end.setBairro("Liberdade");
        end.setCep("13301100");
        end.setCidade("Itu");
        end.setComplemento("Casa frente");
        end.setLogradouro("Ru Rio Amazonas");
        end.setNumero("351");

        Pedido pedido = new Pedido();
        pedido.setId(1);
        pedido.setEnderecoEntregaPedido(end);
        pedido.setStatus(StatusPedido.AGUARDANDO);
        pedido.setTotal(new BigDecimal(5000));

        entityManager.getTransaction().begin();
        entityManager.persist(pedido);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Assert.assertNotNull(pedido.getId());
    }
}
