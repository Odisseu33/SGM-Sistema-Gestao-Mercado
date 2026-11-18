package com.mercado.repository;
import com.mercado.model.MovimentacaoEstoque; import org.springframework.data.jpa.repository.JpaRepository; import java.util.*; import org.springframework.data.domain.Sort; import org.springframework.data.jpa.repository.EntityGraph;
public interface MovimentacaoEstoqueRepository extends JpaRepository<MovimentacaoEstoque,Long> { @Override @EntityGraph(attributePaths="produto") List<MovimentacaoEstoque> findAll(Sort sort);
 @EntityGraph(attributePaths="produto") List<MovimentacaoEstoque> findTop10ByOrderByDataMovimentacaoDesc(); }
