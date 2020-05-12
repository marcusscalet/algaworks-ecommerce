package com.marcusscalet.ecommerce.conhecendoentitymanager;

import com.marcusscalet.ecommerce.entitymanager.EntityManagerTest;
import com.marcusscalet.ecommerce.model.Produto;
import org.junit.Test;

public class CachePrimeiroNivelTest extends EntityManagerTest {

    @Test
    public void verificarCache(){
        Produto prod = entityManager.find(Produto.class, 1);
        System.out.println(prod.getNome());

        System.out.println("-----------------");

        /* exemplo mostrando que quando buscamos novamente o produto
         o EntityManager buscou direto da memória
         ao invés de fazer uma segunda consulta no banco */

        Produto prodResgatado = entityManager.find(Produto.class, prod.getId());
        System.out.println(prodResgatado.getNome());
    }

}
