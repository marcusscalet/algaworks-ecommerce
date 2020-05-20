package com.marcusscalet.ecommerce.model;

import com.marcusscalet.ecommerce.listener.GenericoListener;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@EntityListeners({GenericoListener.class})
@Entity
@Table(name = "produto", uniqueConstraints = { @UniqueConstraint(name = "unq_nome", columnNames = {"nome"} ) },
        indexes = {@Index (name = "idx_nome", columnList = "nome")})
public class Produto extends EntidadeBaseInteger{

	@Column(name = "dataCriacao", updatable = false)
	private LocalDateTime dataCriacao;

	@Column(name = "data_ultima_atualizacao", insertable = false)
	private LocalDateTime dataUltimaAtualizacao;

	@Column(length = 100, nullable = false)
    private String nome;

	@Column(columnDefinition = "varchar(275) not null default 'descricao'")
    private String descricao;

	@Column(precision = 10, scale = 2)
    private BigDecimal preco;

    @Lob
    private byte[] foto;
    
    @ManyToMany
    @JoinTable(
    		name="produto_categoria",
    		joinColumns = @JoinColumn(name = "produto_id"),
    		inverseJoinColumns = @JoinColumn(name = "categoria_id"))
    private List<Categoria> categorias;

    @OneToOne(mappedBy = "produto")
    private Estoque estoque;

    /* Criação da tabela produto_tag usando coluna produto_id para referenciar tabela de produto */
    @ElementCollection
    @CollectionTable(name = "produto_tag", joinColumns = @JoinColumn(name = "produto_id"))
    @Column(name = "tag")
    private List<String> tags;

    @ElementCollection
    @CollectionTable(name = "produto_atributo", joinColumns = @JoinColumn(name = "produto_id"))
    private List<Atributo> atributos;
}
