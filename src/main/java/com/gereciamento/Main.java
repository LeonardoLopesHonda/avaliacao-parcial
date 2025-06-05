package com.gereciamento;

import com.gereciamento.db.PassageiroDB;
import com.gereciamento.models.Passageiro;

public class Main {
  public static void main(String[] args) {
    Passageiro passageiro = new Passageiro().create();
    PassageiroDB passageiroDB = new PassageiroDB();

    passageiroDB.create(passageiro);

    System.out.println("Nome: " + passageiro.getNome());
    System.out.println("Cpf: " + passageiro.getCpf());
    System.out.println("Data de Nascimento: " + passageiro.getDataNascimento());
    System.out.println("Numero de Passaporte: " + passageiro.getNumeroPassaporte());
  }
}