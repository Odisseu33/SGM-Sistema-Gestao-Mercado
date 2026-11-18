package com.mercado.service;
import com.mercado.model.*; import com.mercado.repository.*; import com.mercado.exception.BusinessException;
import org.springframework.stereotype.Service; import org.springframework.transaction.annotation.Transactional; import java.math.BigDecimal;
@Service
public class CadastroService {
 public final ProdutoRepository produtos; public final CategoriaRepository categorias; public final FornecedorRepository fornecedores;
 public CadastroService(ProdutoRepository p,CategoriaRepository c,FornecedorRepository f){produtos=p;categorias=c;fornecedores=f;}
 @Transactional public Produto salvarProduto(Produto p,Long categoriaId,Long fornecedorId){
  if(p.getPrecoCusto()==null||p.getPrecoVenda()==null||p.getPrecoCusto().signum()<0||p.getPrecoVenda().signum()<0)throw new BusinessException("Preços devem ser informados e não podem ser negativos.");
  if(p.getEstoqueMinimo()==null||p.getEstoqueMinimo()<0)throw new BusinessException("Estoque mínimo inválido.");
  if(p.getCodigoBarras()!=null){p.setCodigoBarras(p.getCodigoBarras().trim());if(p.getCodigoBarras().isBlank())p.setCodigoBarras(null);}
  if(p.getCodigoBarras()!=null)produtos.findByCodigoBarras(p.getCodigoBarras()).ifPresent(ex->{if(!ex.getId().equals(p.getId()))throw new BusinessException("Código de barras já cadastrado.");});
  if(categoriaId!=null)p.setCategoria(categorias.findById(categoriaId).orElseThrow(()->new BusinessException("Categoria não encontrada.")));
  if(fornecedorId!=null)p.setFornecedor(fornecedores.findById(fornecedorId).orElseThrow(()->new BusinessException("Fornecedor não encontrado.")));
  if(p.getId()!=null){Produto atual=produtos.findById(p.getId()).orElseThrow();p.setQuantidadeEstoque(atual.getQuantidadeEstoque());}
  return produtos.save(p);
 }
}
