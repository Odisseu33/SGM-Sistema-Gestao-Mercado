package com.mercado.repository;
import com.mercado.model.Categoria; import org.springframework.data.jpa.repository.JpaRepository; import java.util.*;
public interface CategoriaRepository extends JpaRepository<Categoria,Long> { List<Categoria> findAllByOrderByNome(); }
