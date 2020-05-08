package com.marcusscalet.ecommerce.mapeamentoavancado;

import com.marcusscalet.ecommerce.mapeamentobasico.EntityManagerTest;
import com.marcusscalet.ecommerce.model.Produto;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class DetalhesColumnTest extends EntityManagerTest {

    @Test
    public void impedirInsercaoDaColunaAtualizacao(){
        Produto produto = new Produto();
        produto.setNome("Teclado para Smartphone");
        produto.setDescricao("O mais confortável");
        produto.setPreco(BigDecimal.ONE);
        produto.setDataCriacao(LocalDateTime.now());
        produto.setDataUltimaAtualizacao(LocalDateTime.now());

        entityManager.getTransaction().begin();
        entityManager.persist(produto);
        entityManager.getTransaction().commit();

        entityManager.clear();

        Produto produtoVerificacao = entityManager.find(Produto.class, produto.getId());
        Assert.assertNotNull(produtoVerificacao.getDataCriacao());
        Assert.assertNull(produtoVerificacao.getDataUltimaAtualizacao());
    }

    @Test
    public void impedirAtualizacaoColunaInsercao(){
        entityManager.getTransaction().begin();

        Produto produto = entityManager.find(Produto.class, 1);

        produto.setPreco(BigDecimal.ONE);
        produto.setDataCriacao(LocalDateTime.now());
        produto.setDataUltimaAtualizacao(LocalDateTime.now());

        entityManager.getTransaction().commit();

        entityManager.clear();

        Produto produtoVerificacao = entityManager.find(Produto.class, produto.getId());

        Assert.assertNotEquals(produto.getDataCriacao().truncatedTo(ChronoUnit.SECONDS),
                produtoVerificacao.getDataCriacao().truncatedTo(ChronoUnit.SECONDS));

        Assert.assertEquals(produto.getDataUltimaAtualizacao().truncatedTo(ChronoUnit.SECONDS),
                produtoVerificacao.getDataUltimaAtualizacao().truncatedTo(ChronoUnit.SECONDS));
    }
}
