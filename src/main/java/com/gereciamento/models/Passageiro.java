package com.gereciamento.models;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.swing.JOptionPane;

@Entity
public class Passageiro extends Pessoa {
  @Column(nullable = false)
  private String numeroPassaporte;

  public Passageiro() {
  }

  public Passageiro(String nome, Long cpf, LocalDate dataNascimento, String numeroPassaporte) {
    super(nome, cpf, dataNascimento);
    this.numeroPassaporte = numeroPassaporte;
  }

  public String getNumeroPassaporte() {
    return numeroPassaporte;
  }

  public void setNumeroPassaporte(String numeroPassaporte) {
    this.numeroPassaporte = numeroPassaporte;
  }

  public Passageiro create() {
    String nome = JOptionPane.showInputDialog("Digite seu Nome: ");
    Long cpf = Long.parseLong(JOptionPane.showInputDialog("Digite seu CPF: "));
    String dateString = JOptionPane.showInputDialog("Digite sua Data de Nascimento (dd/mm/aaaa): ");
    String numeroPassaporte = JOptionPane.showInputDialog("Digite seu Numero de Passaporte: ");

    String[] dateArray = dateString.split("/");

    String dd = dateArray[0];
    String mm = dateArray[1];
    String aaaa = dateArray[2];
    int day = Integer.parseInt(dd);
    int month = Integer.parseInt(mm);
    int year = Integer.parseInt(aaaa);

    LocalDate date = LocalDate.of(year, month, day);

    return new Passageiro(nome, cpf, date, numeroPassaporte);
  }
}
