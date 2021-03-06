package com.marcusscalet.ecommerce.mapeamentoavancado;

import com.marcusscalet.ecommerce.entitymanager.EntityManagerTest;
import com.marcusscalet.ecommerce.model.Atributo;
import com.marcusscalet.ecommerce.model.Cliente;
import com.marcusscalet.ecommerce.model.Produto;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

public class ElementCollectionTest extends EntityManagerTest {

    @Test
    public void aplicarTags(){
        entityManager.getTransaction().begin();
        Produto produto = entityManager.find(Produto.class, 1);

        produto.setTags(Arrays.asList("Ebook", "Reader"));

        entityManager.getTransaction().commit();

        entityManager.clear();

        Produto produtoVerificacao = entityManager.find(Produto.class, produto.getId());
        Assert.assertFalse(produtoVerificacao.getTags().isEmpty());
    }
    @Test
    public void aplicarAtributos(){
        entityManager.getTransaction().begin();

        Produto produto = entityManager.find(Produto.class, 1);
        produto.setAtributos(Arrays.asList(new Atributo("tela","320x480")));

        entityManager.getTransaction().commit();
        entityManager.clear();

        Produto produtoVerificacao = entityManager.find(Produto.class, produto.getId());

        Assert.assertFalse(produtoVerificacao.getAtributos().isEmpty());
    }

    @Test
    public void aplicarContatos(){
        entityManager.getTransaction().begin();

        Cliente cliente = entityManager.find(Cliente.class,1);
        cliente.setContatos(Collections.singletonMap("email", "john.doe@gmail.com"));
//        cliente.setNome("Fernanda Moraes");
//        cliente.setSexo(SexoCliente.FEMININO);
//        cliente.setCpf("585.956.415-88");

        entityManager.getTransaction().commit();

        entityManager.clear();

        Cliente clienteVerificacao = entityManager.find(Cliente.class, cliente.getId());

        Assert.assertEquals(
                "john.doe@gmail.com", clienteVerificacao.getContatos().get("email"));
    }
}
