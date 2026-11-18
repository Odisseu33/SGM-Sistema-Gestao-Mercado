package com.mercado.model;
import jakarta.persistence.*;
@Entity public class Fornecedor {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @Column(nullable=false) private String nome; @Column(unique=true) private String cnpj;
 private String telefone,email,endereco; private boolean ativo=true;
 public Long getId(){return id;} public String getNome(){return nome;} public void setNome(String v){nome=v;}
 public String getCnpj(){return cnpj;} public void setCnpj(String v){cnpj=v;}
 public String getTelefone(){return telefone;} public void setTelefone(String v){telefone=v;}
 public String getEmail(){return email;} public void setEmail(String v){email=v;}
 public String getEndereco(){return endereco;} public void setEndereco(String v){endereco=v;}
 public boolean isAtivo(){return ativo;} public void setAtivo(boolean v){ativo=v;}
}
