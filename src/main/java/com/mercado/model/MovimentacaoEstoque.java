package com.mercado.model;
import jakarta.persistence.*; import java.time.LocalDateTime; import com.mercado.model.Enums.*;
@Entity public class MovimentacaoEstoque {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @ManyToOne(optional=false,fetch=FetchType.LAZY) private Produto produto;
 @Enumerated(EnumType.STRING) private TipoMovimentacao tipo;
 private Integer quantidade;
 @Enumerated(EnumType.STRING) private MotivoMovimentacao motivo;
 private LocalDateTime dataMovimentacao=LocalDateTime.now(); private String observacao;
 private Integer saldoAnterior,saldoPosterior;
 @ManyToOne(fetch=FetchType.LAZY) private Venda venda;
 public Long getId(){return id;} public Produto getProduto(){return produto;} public void setProduto(Produto v){produto=v;}
 public TipoMovimentacao getTipo(){return tipo;} public void setTipo(TipoMovimentacao v){tipo=v;}
 public Integer getQuantidade(){return quantidade;} public void setQuantidade(Integer v){quantidade=v;}
 public MotivoMovimentacao getMotivo(){return motivo;} public void setMotivo(MotivoMovimentacao v){motivo=v;}
 public LocalDateTime getDataMovimentacao(){return dataMovimentacao;} public String getObservacao(){return observacao;} public void setObservacao(String v){observacao=v;}
 public Integer getSaldoAnterior(){return saldoAnterior;} public void setSaldoAnterior(Integer v){saldoAnterior=v;}
 public Integer getSaldoPosterior(){return saldoPosterior;} public void setSaldoPosterior(Integer v){saldoPosterior=v;}
 public Venda getVenda(){return venda;} public void setVenda(Venda v){venda=v;}
}
