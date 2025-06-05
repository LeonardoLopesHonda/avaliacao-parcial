package com.gereciamento.models;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Pessoa {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  @Column(nullable = false)
  private String nome;
  @Column(nullable = false)
  private Long cpf;
  @Column(nullable = false)
  private LocalDate dataNascimento;

  public Pessoa() {
  }

  public Pessoa(String nome, Long cpf, LocalDate dataNascimento) {
    this.nome = nome;
    this.cpf = cpf;
    this.dataNascimento = dataNascimento;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public Long getCpf() {
    return cpf;
  }

  public void setCpf(Long cpf) {
    this.cpf = cpf;
  }

  public LocalDate getDataNascimento() {
    return dataNascimento;
  }

  public void setDataNascimento(LocalDate dataNascimento) {
    this.dataNascimento = dataNascimento;
  }
}
