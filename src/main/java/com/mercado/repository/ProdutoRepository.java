package com.mercado.repository;
import com.mercado.model.Produto; import jakarta.persistence.LockModeType; import org.springframework.data.jpa.repository.JpaRepository; import java.util.*; import org.springframework.data.domain.*; import org.springframework.data.jpa.repository.*; import org.springframework.data.repository.query.Param;
public interface ProdutoRepository extends JpaRepository<Produto,Long> { @Override @EntityGraph(attributePaths={"categoria","fornecedor"}) List<Produto> findAll();
 @EntityGraph(attributePaths={"categoria","fornecedor"}) Page<Produto> findByNomeContainingIgnoreCase(String nome, Pageable pageable);
 Optional<Produto> findByCodigoBarras(String codigoBarras);
 @Lock(LockModeType.PESSIMISTIC_WRITE) @Query("select p from Produto p where p.id=:id") Optional<Produto> buscarParaAtualizacao(@Param("id") Long id);
 long countByQuantidadeEstoqueLessThanEqualAndAtivoTrue(Integer limite); }
