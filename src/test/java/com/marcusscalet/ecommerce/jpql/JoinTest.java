package com.marcusscalet.ecommerce.jpql;

import java.util.List;

import javax.persistence.TypedQuery;

import org.junit.Assert;
import org.junit.Test;

import com.marcusscalet.ecommerce.entitymanager.EntityManagerTest;
import com.marcusscalet.ecommerce.model.Pedido;

public class JoinTest extends EntityManagerTest {

    @Test
    public void joinFetch(){
    	
        //String jpqlSemFetch = "select p from Pedido p ";
    	
        //fazendo uso do join fetch desta forma, fazemos tudo em uma consulta e
    	//contornamos o problema do n+1
        String jpql = "select p from Pedido p " +
                " left join fetch p.pagamento " +
                " join fetch p.cliente " +
                " left join fetch p.notaFiscal ";
        

        TypedQuery<Pedido> typedQuery = entityManager.createQuery(jpql, Pedido.class);

        List<Pedido> lista = typedQuery.getResultList();
        Assert.assertFalse(lista.isEmpty());
    }
    @Test
    public void leftJoin(){
//        Left Join = todos registros da tabela ao lado esquerdo que possui correspondência com lado direito,
//        mas também todos os registros que estão do lado esquerdo que não possuo correspondência com o lado direito

        String jpql = "select p from Pedido p left outer join p.pagamento pag on pag.status = 'PROCESSANDO'";

        TypedQuery<Pedido> typedQuery = entityManager.createQuery(jpql, Pedido.class);

        List<Pedido> lista = typedQuery.getResultList();
        Assert.assertFalse(lista.isEmpty());
    }

    @Test
    public void exemploDeJoin(){
//        correspondência nas duas pontas
        String jpql = "select p from Pedido p inner join p.pagamento pag";

        TypedQuery<Pedido> typedQuery = entityManager.createQuery(jpql, Pedido.class);

        List<Pedido> lista = typedQuery.getResultList();
        Assert.assertFalse(lista.isEmpty());
    }

    @Test
    public void joinComProjecao(){
        String jpql = "select p, i from Pedido p inner join p.itens i";
//      String jpql2 = "select p from Pedido p inner join p.itens i i join i.produto. prod where pro.id = 1";
//      String jpql3 = "select p from Pedido p inner join p.itens i where i.precoProduto > 10";

        TypedQuery<Object[]> typedQuery = entityManager.createQuery(jpql, Object[].class);

        List<Object[]> lista = typedQuery.getResultList();
        Assert.assertFalse(lista.isEmpty());
    }
}
