package com.marcusscalet.ecommerce.mapeamentobasico.iniciandocomjpa;

import org.junit.Assert;
import org.junit.Test;

import com.marcusscalet.ecommerce.mapeamentobasico.EntityManagerTest;
import com.marcusscalet.ecommerce.model.Cliente;
import com.marcusscalet.ecommerce.model.Produto;

public class PrimeiroCrudTest extends EntityManagerTest {

    @Test
    public void inserirRegistro() {

        Cliente c1 = new Cliente();
        c1.setNome("Manoel");

        entityManager.getTransaction().begin();
        entityManager.persist(c1);
        entityManager.getTransaction().commit();

        entityManager.clear();
        Assert.assertNotNull(c1);
    }

    @Test
    public void buscarPorId() {

        Produto produto = entityManager.find(Produto.class, 1);

        Assert.assertNotNull(produto);
        Assert.assertEquals("Kindle", produto.getNome());
    }

    @Test
    public void alterarRegistro() {

        Cliente c1 = new Cliente();
        c1.setNome("Jo√o");

        entityManager.getTransaction().begin();
        entityManager.persist(c1);
        entityManager.getTransaction().commit();

        c1.setNome("Zeus");
        entityManager.merge(c1);

        entityManager.clear();
        Assert.assertEquals(c1.getNome(), "Zeus");

    }

    @Test
    public void removerRegistro() {

        Cliente c1 = entityManager.find(Cliente.class, 2);

        entityManager.getTransaction().begin();
        entityManager.remove(c1);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Cliente clienteVerificacao = entityManager.find(Cliente.class, c1.getId());

        Assert.assertNull(clienteVerificacao);
    }
}
