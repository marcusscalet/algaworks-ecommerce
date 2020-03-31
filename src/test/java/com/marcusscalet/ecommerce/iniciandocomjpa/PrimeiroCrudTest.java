package com.marcusscalet.ecommerce.iniciandocomjpa;

import com.marcusscalet.ecommerce.EntityManagerTest;
import com.marcusscalet.ecommerce.model.Cliente;
import com.marcusscalet.ecommerce.model.Produto;
import org.junit.Assert;
import org.junit.Test;

public class PrimeiroCrudTest extends EntityManagerTest {

    @Test
    public void inserirRegistro() {

        Cliente c1 = new Cliente();

        c1.setId(6);
        c1.setNome("Manoel");

        entityManager.getTransaction().begin();
        entityManager.persist(c1);
        entityManager.getTransaction().commit();


        entityManager.clear();
        Assert.assertNotNull(c1);
    }

    @Test
    public void buscarPorId() {

        Cliente c1 = new Cliente();

        c1.setId(7);
        c1.setNome("João");

        entityManager.getTransaction().begin();

        Cliente clienteCadastrado = new Cliente();
        clienteCadastrado = entityManager.find(Cliente.class, 7);

        entityManager.clear();

//        Assert.assertTrue(clienteCadastrado.getId(), 7);
    }

    @Test
    public void alterarRegistro() {

        Cliente c1 = new Cliente();

        c1.setId(7);
        c1.setNome("João");

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

        Produto produto = entityManager.find(Produto.class, 1);

        entityManager.getTransaction().begin();
        entityManager.remove(produto);
        entityManager.getTransaction().commit();

        Produto produtoVerificacao = entityManager.find(Produto.class, 1);

       Assert.assertNull(produtoVerificacao);
    }
}
