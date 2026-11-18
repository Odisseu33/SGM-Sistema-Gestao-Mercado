package com.mercado.service;
import com.mercado.model.*; import com.mercado.model.Enums.*; import com.mercado.repository.*; import com.mercado.exception.BusinessException;
import org.springframework.stereotype.Service; import org.springframework.transaction.annotation.Transactional; import java.math.*; import java.util.*;
@Service
public class VendaService {
 private final VendaRepository vendas; private final ProdutoRepository produtos; private final EstoqueService estoque;
 public VendaService(VendaRepository v,ProdutoRepository p,EstoqueService e){vendas=v;produtos=p;estoque=e;}
 public record Linha(Long produtoId,Integer quantidade){}
 @Transactional public Venda registrar(List<Linha> linhas,FormaPagamento pagamento,String observacao){
  if(linhas==null||linhas.isEmpty())throw new BusinessException("Adicione ao menos um produto.");
  Venda venda=new Venda();venda.setFormaPagamento(Objects.requireNonNull(pagamento));venda.setObservacao(observacao);
  BigDecimal total=BigDecimal.ZERO;
  for(Linha l:linhas){
   if(l.quantidade()==null||l.quantidade()<=0)throw new BusinessException("Quantidade inválida.");
   Produto p=produtos.buscarParaAtualizacao(l.produtoId()).orElseThrow(()->new BusinessException("Produto não encontrado."));
   if(!p.isAtivo()||p.getQuantidadeEstoque()<l.quantidade())throw new BusinessException("Estoque insuficiente ou produto inativo: "+p.getNome());
   ItemVenda item=new ItemVenda();item.setProduto(p);item.setQuantidade(l.quantidade());item.setPrecoUnitario(p.getPrecoVenda());item.setPrecoCustoUnitario(p.getPrecoCusto());
   item.setSubtotal(p.getPrecoVenda().multiply(BigDecimal.valueOf(l.quantidade())).setScale(2,RoundingMode.HALF_UP));venda.adicionarItem(item);total=total.add(item.getSubtotal());
  }
  venda.setValorTotal(total);vendas.save(venda);
  for(ItemVenda i:venda.getItens())estoque.movimentar(i.getProduto().getId(),TipoMovimentacao.SAIDA,i.getQuantidade(),MotivoMovimentacao.VENDA,"Venda #"+venda.getId(),venda);
  return venda;
 }
 @Transactional public void cancelar(Long id){
  Venda v=vendas.findById(id).orElseThrow(()->new BusinessException("Venda não encontrada."));
  if(v.getStatus()==StatusVenda.CANCELADA)throw new BusinessException("Esta venda já foi cancelada.");
  v.setStatus(StatusVenda.CANCELADA);
  for(ItemVenda i:v.getItens())estoque.movimentar(i.getProduto().getId(),TipoMovimentacao.ENTRADA,i.getQuantidade(),MotivoMovimentacao.DEVOLUCAO,"Cancelamento da venda #"+id,v);
 }
}
