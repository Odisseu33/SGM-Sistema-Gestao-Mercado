package com.mercado.repository;
import com.mercado.model.Venda; import org.springframework.data.jpa.repository.JpaRepository; import java.util.*; import org.springframework.data.jpa.repository.EntityGraph; import org.springframework.data.jpa.repository.Query;
public interface VendaRepository extends JpaRepository<Venda,Long> { @EntityGraph(attributePaths={"itens","itens.produto"}) @Query("select distinct v from Venda v") List<Venda> findAllComItens();
 @EntityGraph(attributePaths={"itens","itens.produto"})
 @Query("select v from Venda v where v.id = :id") Optional<Venda> findByIdComItens(@org.springframework.data.repository.query.Param("id") Long id);
 List<Venda> findTop10ByOrderByDataVendaDesc(); }
