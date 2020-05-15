package com.marcusscalet.ecommerce.mapeamentoavancado;

import com.marcusscalet.ecommerce.entitymanager.EntityManagerTest;
import com.marcusscalet.ecommerce.model.NotaFiscal;
import com.marcusscalet.ecommerce.model.Pedido;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.Date;

public class SalvandoArquivosTest extends EntityManagerTest {

    @Test
    public void salvarXmlNota() {
        Pedido pedido = entityManager.find(Pedido.class, 1);

        NotaFiscal notaFiscal = new NotaFiscal();
        notaFiscal.setPedido(pedido);
        notaFiscal.setDataEmissao(new Date());
        notaFiscal.setXml(carregatNotaFiscal());

        entityManager.getTransaction().begin();
        entityManager.persist(notaFiscal);
        entityManager.getTransaction().commit();

        NotaFiscal notaFiscalVerificacao = entityManager.find(NotaFiscal.class, notaFiscal.getId());
        Assert.assertNotNull(notaFiscalVerificacao.getXml());
        Assert.assertTrue(notaFiscalVerificacao.getXml().length > 0);

        /* salvando um arquivo nf.xml na pasta de usuário
        * e depois pegando o arquivo pra validar nas Assertions
        try {
            File file;
            OutputStream out = new FileOutputStream(Files.createFile(Paths.get(
                    System.getProperty("user.home") + "/nf.xml")).toFile());
            out.write(notaFiscalVerificacao.getXml());
        } catch (IOException e){
            throw new RuntimeException(e);
        } */
    }

    private static byte[] carregatNotaFiscal(){
        try {
            return SalvandoArquivosTest.class.getResourceAsStream(
                    "/nota-fiscal.xml").readAllBytes();
        } catch (IOException e){
            throw new RuntimeException(e);
        }
    }

}
