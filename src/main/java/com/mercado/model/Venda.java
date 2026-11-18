package com.mercado.model;
import jakarta.persistence.*; import java.math.BigDecimal; import java.time.LocalDateTime; import java.util.*;
import com.mercado.model.Enums.*;
@Entity public class Venda {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 private LocalDateTime dataVenda=LocalDateTime.now();
 @Column(nullable=false,precision=12,scale=2) private BigDecimal valorTotal=BigDecimal.ZERO;
 @Enumerated(EnumType.STRING) private FormaPagamento formaPagamento;
 @Enumerated(EnumType.STRING) private StatusVenda status=StatusVenda.CONCLUIDA;
 @Column(length=1000) private String observacao;
 @OneToMany(mappedBy="venda",cascade=CascadeType.ALL,orphanRemoval=true) private List<ItemVenda> itens=new ArrayList<>();
 public Long getId(){return id;} public LocalDateTime getDataVenda(){return dataVenda;}
 public BigDecimal getValorTotal(){return valorTotal;} public void setValorTotal(BigDecimal v){valorTotal=v;}
 public FormaPagamento getFormaPagamento(){return formaPagamento;} public void setFormaPagamento(FormaPagamento v){formaPagamento=v;}
 public StatusVenda getStatus(){return status;} public void setStatus(StatusVenda v){status=v;}
 public String getObservacao(){return observacao;} public void setObservacao(String v){observacao=v;}
 public List<ItemVenda> getItens(){return itens;} public void adicionarItem(ItemVenda i){itens.add(i);i.setVenda(this);}
}
