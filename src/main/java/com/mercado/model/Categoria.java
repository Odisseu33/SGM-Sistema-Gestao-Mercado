package com.mercado.model;
import jakarta.persistence.*;
@Entity public class Categoria {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @Column(nullable=false,unique=true) private String nome; private String descricao; private boolean ativo=true;
 public Long getId(){return id;} public String getNome(){return nome;} public void setNome(String v){nome=v;}
 public String getDescricao(){return descricao;} public void setDescricao(String v){descricao=v;}
 public boolean isAtivo(){return ativo;} public void setAtivo(boolean v){ativo=v;}
}
