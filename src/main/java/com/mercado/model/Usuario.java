package com.mercado.model;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import java.util.Collection; import java.util.List;
@Entity @Table(name="usuarios")
public class Usuario implements UserDetails {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @Column(nullable=false) private String nome;
 @Column(nullable=false,unique=true) private String username;
 @Column(nullable=false) private String password;
 private boolean ativo=true;
 private LocalDateTime dataCriacao=LocalDateTime.now();
 public Long getId(){return id;} public String getNome(){return nome;} public void setNome(String v){nome=v;}
 @Override public String getUsername(){return username;} public void setUsername(String v){username=v;}
 @Override public String getPassword(){return password;} public void setPassword(String v){password=v;}
 public boolean isAtivo(){return ativo;} public void setAtivo(boolean v){ativo=v;}
 public LocalDateTime getDataCriacao(){return dataCriacao;}
 @Override public Collection<? extends GrantedAuthority> getAuthorities(){return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"));}
 @Override public boolean isAccountNonExpired(){return true;} @Override public boolean isAccountNonLocked(){return ativo;}
 @Override public boolean isCredentialsNonExpired(){return true;} @Override public boolean isEnabled(){return ativo;}
}
