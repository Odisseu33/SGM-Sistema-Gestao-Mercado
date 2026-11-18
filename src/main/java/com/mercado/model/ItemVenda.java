package com.mercado.model;
import jakarta.persistence.*; import java.math.BigDecimal;
@Entity public class ItemVenda {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @ManyToOne(fetch=FetchType.LAZY,optional=false) private Venda venda;
 @ManyToOne(fetch=FetchType.LAZY,optional=false) private Produto produto;
 private Integer quantidade;
 @Column(precision=12,scale=2) private BigDecimal precoUnitario;
 @Column(precision=12,scale=2) private BigDecimal precoCustoUnitario;
 @Column(precision=12,scale=2) private BigDecimal subtotal;
 public Long getId(){return id;} public Venda getVenda(){return venda;} public void setVenda(Venda v){venda=v;}
 public Produto getProduto(){return produto;} public void setProduto(Produto v){produto=v;}
 public Integer getQuantidade(){return quantidade;} public void setQuantidade(Integer v){quantidade=v;}
 public BigDecimal getPrecoUnitario(){return precoUnitario;} public void setPrecoUnitario(BigDecimal v){precoUnitario=v;}
 public BigDecimal getPrecoCustoUnitario(){return precoCustoUnitario;} public void setPrecoCustoUnitario(BigDecimal v){precoCustoUnitario=v;}
 public BigDecimal getSubtotal(){return subtotal;} public void setSubtotal(BigDecimal v){subtotal=v;}
}
