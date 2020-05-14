package com.marcusscalet.ecommerce.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "cliente")
public class Cliente {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Id
    private Integer id;

	private String nome;

	/* campos com @Transient são ignorados pelo JPA p/ criação e busca no BD */
	@Transient
    private String primeiroNome;

    @Enumerated(EnumType.STRING)
    private SexoCliente sexo;
    
    @OneToMany(mappedBy = "cliente")
    private List<Pedido> pedidos;

    @PostLoad
    public void configurarPrimeiroNome(){
        if (nome != null && !nome.isBlank()){
            int index = nome.indexOf(" ");
            if(index > -1){
                primeiroNome = nome.substring(0, index);
            }
        }
    }
}
