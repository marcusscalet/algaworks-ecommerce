package com.marcusscalet.ecommerce.mapeamentoavancado;

import com.marcusscalet.ecommerce.entitymanager.EntityManagerTest;
import com.marcusscalet.ecommerce.model.Cliente;
import org.junit.Assert;
import org.junit.Test;

public class PropriedadesTransientTest extends EntityManagerTest {

    @Test
    public void validarPrimeiroNome(){
        Cliente cliente = entityManager.find(Cliente.class,1);

        Assert.assertEquals("John", cliente.getPrimeiroNome());
    }
}
