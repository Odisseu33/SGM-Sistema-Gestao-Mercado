package com.mercado.service;
import com.mercado.repository.*; import com.mercado.model.*; import com.mercado.model.Enums.*; import org.springframework.stereotype.Service;
import java.time.*; import java.math.*; import java.util.*;
@Service public class DashboardService {
 private final ProdutoRepository p; private final CategoriaRepository c; private final FornecedorRepository f; private final VendaRepository v; private final MovimentacaoEstoqueRepository m;
 public DashboardService(ProdutoRepository p,CategoriaRepository c,FornecedorRepository f,VendaRepository v,MovimentacaoEstoqueRepository m){this.p=p;this.c=c;this.f=f;this.v=v;this.m=m;}
 public Map<String,Object> dados(){
  List<Produto> ps=p.findAll();List<Venda> vs=v.findAll();LocalDate hoje=LocalDate.now();LocalDateTime inicio=hoje.atStartOfDay();
  BigDecimal estoque=ps.stream().map(x->x.getPrecoCusto().multiply(BigDecimal.valueOf(x.getQuantidadeEstoque()))).reduce(BigDecimal.ZERO,BigDecimal::add);
  List<Venda> concluidas=vs.stream().filter(x->x.getStatus()==StatusVenda.CONCLUIDA).toList();
  BigDecimal dia=concluidas.stream().filter(x->x.getDataVenda().isAfter(inicio)).map(Venda::getValorTotal).reduce(BigDecimal.ZERO,BigDecimal::add);
  BigDecimal mes=concluidas.stream().filter(x->x.getDataVenda().getMonth()==hoje.getMonth()&&x.getDataVenda().getYear()==hoje.getYear()).map(Venda::getValorTotal).reduce(BigDecimal.ZERO,BigDecimal::add);
  Map<String,Object> d=new HashMap<>();d.put("produtos",ps.stream().filter(Produto::isAtivo).count());d.put("categorias",c.count());d.put("fornecedores",f.count());d.put("valorEstoque",estoque);d.put("estoqueBaixo",ps.stream().filter(x->x.isAtivo()&&x.getQuantidadeEstoque()<=x.getEstoqueMinimo()).count());d.put("vendasDia",concluidas.stream().filter(x->x.getDataVenda().isAfter(inicio)).count());d.put("faturamentoDia",dia);d.put("faturamentoMes",mes);d.put("ultimasVendas",v.findTop10ByOrderByDataVendaDesc());d.put("movimentacoes",m.findTop10ByOrderByDataMovimentacaoDesc());d.put("alertas",ps.stream().filter(x->x.isAtivo()&&x.getQuantidadeEstoque()<=x.getEstoqueMinimo()).toList());return d;
 }
}
