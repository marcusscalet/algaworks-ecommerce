package com.marcusscalet.ecommerce.conhecendoentitymanager;

import com.marcusscalet.ecommerce.entitymanager.EntityManagerTest;
import com.marcusscalet.ecommerce.model.Produto;
import org.junit.Test;

import java.math.BigDecimal;

public class ContextoDePersistencia extends EntityManagerTest {

    @Test
    public void usarContextoDePersistencia(){

        entityManager.getTransaction().begin();

        /* a partir da chamada do find a instância já está contextualizada no conceito de persistência */
        Produto produto = entityManager.find(Produto.class, 1);
        produto.setPreco(new BigDecimal(10));

        Produto produto2 = new Produto();
        produto2.setNome("Caneca para café");
        produto2.setPreco(new BigDecimal(12));
        produto2.setDescricao("Boa caneca para café");
        /* apenas na chamada do método persist a instancia fica contextualizada */
        entityManager.persist(produto2);

        Produto produto3 = new Produto();
        produto3.setNome("Caneca para chá");
        produto3.setDescricao("Boa caneca para chá");
        produto3.setPreco(new BigDecimal(8));
        /* apenas quando merge dá o retorno que produto3 passa a estar contextualizado */
        produto3 = entityManager.merge(produto3);

        /* uso de flush aqui para limpar a memória e forçar o update de produto3 */
        entityManager.flush();

        produto3.setDescricao("Alterar descrição");
        entityManager.getTransaction().commit();
    }
}
