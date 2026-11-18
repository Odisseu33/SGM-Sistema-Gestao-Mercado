package com.mercado.repository;
import com.mercado.model.Fornecedor; import org.springframework.data.jpa.repository.JpaRepository; import java.util.*;
public interface FornecedorRepository extends JpaRepository<Fornecedor,Long> { List<Fornecedor> findByNomeContainingIgnoreCase(String nome); }
