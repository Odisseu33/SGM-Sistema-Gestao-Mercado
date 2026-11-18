package com.mercado.repository;
import com.mercado.model.Usuario; import org.springframework.data.jpa.repository.JpaRepository; import java.util.*;
public interface UsuarioRepository extends JpaRepository<Usuario,Long> { Optional<Usuario> findByUsername(String username); }
