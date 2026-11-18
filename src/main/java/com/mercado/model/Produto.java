package com.mercado.model;
import jakarta.persistence.*; import java.math.BigDecimal; import java.time.LocalDateTime;
@Entity @Table(name="produtos", indexes=@Index(columnList="nome"))
public class Produto {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @Column(nullable=false) private String nome; @Column(unique=true) private String codigoBarras;
 @Column(length=1000) private String descricao;
 @Column(nullable=false,precision=12,scale=2) private BigDecimal precoCusto=BigDecimal.ZERO;
 @Column(nullable=false,precision=12,scale=2) private BigDecimal precoVenda=BigDecimal.ZERO;
 @Column(nullable=false) private Integer quantidadeEstoque=0; @Column(nullable=false) private Integer estoqueMinimo=0;
 private boolean ativo=true; private LocalDateTime dataCadastro=LocalDateTime.now(), dataAtualizacao=LocalDateTime.now();
 @ManyToOne(fetch=FetchType.LAZY) private Categoria categoria;
 @ManyToOne(fetch=FetchType.LAZY) private Fornecedor fornecedor;
 @PreUpdate void atualizar(){dataAtualizacao=LocalDateTime.now();}
 public Long getId(){return id;} public String getNome(){return nome;} public void setNome(String v){nome=v;}
 public String getCodigoBarras(){return codigoBarras;} public void setCodigoBarras(String v){codigoBarras=v;}
 public String getDescricao(){return descricao;} public void setDescricao(String v){descricao=v;}
 public BigDecimal getPrecoCusto(){return precoCusto;} public void setPrecoCusto(BigDecimal v){precoCusto=v;}
 public BigDecimal getPrecoVenda(){return precoVenda;} public void setPrecoVenda(BigDecimal v){precoVenda=v;}
 public Integer getQuantidadeEstoque(){return quantidadeEstoque;} public void setQuantidadeEstoque(Integer v){quantidadeEstoque=v;}
 public Integer getEstoqueMinimo(){return estoqueMinimo;} public void setEstoqueMinimo(Integer v){estoqueMinimo=v;}
 public boolean isAtivo(){return ativo;} public void setAtivo(boolean v){ativo=v;}
 public LocalDateTime getDataCadastro(){return dataCadastro;} public LocalDateTime getDataAtualizacao(){return dataAtualizacao;}
 public Categoria getCategoria(){return categoria;} public void setCategoria(Categoria v){categoria=v;}
 public Fornecedor getFornecedor(){return fornecedor;} public void setFornecedor(Fornecedor v){fornecedor=v;}
}
