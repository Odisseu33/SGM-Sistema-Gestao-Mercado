package com.mercado.service;
import com.mercado.model.*; import com.mercado.model.Enums.*; import com.mercado.repository.*; import com.mercado.exception.BusinessException;
import org.springframework.stereotype.Service; import org.springframework.transaction.annotation.Transactional;
@Service
public class EstoqueService {
 private final ProdutoRepository produtos; private final MovimentacaoEstoqueRepository movimentos;
 public EstoqueService(ProdutoRepository p,MovimentacaoEstoqueRepository m){produtos=p;movimentos=m;}
 @Transactional public MovimentacaoEstoque movimentar(Long produtoId,TipoMovimentacao tipo,int quantidade,MotivoMovimentacao motivo,String obs,Venda venda){
  if(quantidade<=0) throw new BusinessException("A quantidade deve ser maior que zero.");
  Produto p=produtos.buscarParaAtualizacao(produtoId).orElseThrow(()->new BusinessException("Produto não encontrado."));
  if(!p.isAtivo()) throw new BusinessException("Produto inativo não pode movimentar estoque.");
  int antes=p.getQuantidadeEstoque(), depois=tipo==TipoMovimentacao.ENTRADA?antes+quantidade:antes-quantidade;
  if(depois<0) throw new BusinessException("Estoque insuficiente para "+p.getNome()+".");
  p.setQuantidadeEstoque(depois);
  MovimentacaoEstoque m=new MovimentacaoEstoque();m.setProduto(p);m.setTipo(tipo);m.setQuantidade(quantidade);m.setMotivo(motivo);m.setObservacao(obs);m.setSaldoAnterior(antes);m.setSaldoPosterior(depois);m.setVenda(venda);
  return movimentos.save(m);
 }
}
