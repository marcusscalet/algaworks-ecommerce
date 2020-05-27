package jpql;

import com.marcusscalet.ecommerce.entitymanager.EntityManagerTest;
import com.marcusscalet.ecommerce.model.Pedido;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.TypedQuery;
import java.util.List;

public class JoinTest extends EntityManagerTest {

    @Test
    public void leftJoin(){
//        Left Join = todos registros da tabela ao lado esquerdo que possui correspondência com lado direito,
//        mas também todos os registros que estão do lado esquerdo que não possuo correspondência com o lado direito

        String jpql = "select p from Pedido p left outer join p.pagamento pag on pag.status = 'PROCESSANDO'";

//        String jpql2 = "select p from Pedido p left outer join p.pagamento pag where pag.status = 'PROCESSANDO'";

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
