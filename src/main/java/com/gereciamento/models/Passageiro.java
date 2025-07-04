package com.gereciamento.models;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.swing.JOptionPane;

import com.gereciamento.db.PassageiroDB;

@Entity
@Table(name = "passageiros")
public class Passageiro extends Pessoa {
  @Column(nullable = false)
  private String numeroPassaporte;

  @ManyToOne
  @JoinColumn(name = "idVoo", nullable = true)
  private Voo voo;

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

  public Voo getVoo() {
    return voo;
  }

  public void setVoo(Voo voo) {
    this.voo = voo;
  }

  private static void removeFromList(List<Passageiro> listaPassageiros, Long indexOfPassageiro) {
    for (int i = 0; i < listaPassageiros.size(); i++) {
      if (listaPassageiros.get(i).getId() == Integer.parseInt(Long.toString(indexOfPassageiro))) {
        listaPassageiros.remove(i);
        break;
      }
    }
  }

  private static void updateFromList(List<Passageiro> listaPassageiros, Passageiro passageiro, Long indexOfPassageiro) {
    for (int i = 0; i < listaPassageiros.size(); i++) {
      if (listaPassageiros.get(i).getId() == Integer.parseInt(Long.toString(indexOfPassageiro))) {
        listaPassageiros.set(i, passageiro);
        break;
      }
    }
  }

  private static LocalDate convertStringToDate(String dateString) {
    String[] dateArray = dateString.split("/");

    String dd = dateArray[0];
    String mm = dateArray[1];
    String aaaa = dateArray[2];
    int day = Integer.parseInt(dd);
    int month = Integer.parseInt(mm);
    int year = Integer.parseInt(aaaa);

    return LocalDate.of(year, month, day);
  }

  private static String validateDate(String dateString) {
    if (dateString.equals("0"))
      return "0";
    String[] dateArray = dateString.split("/");

    String dd = dateArray[0];
    String mm = dateArray[1];
    String aaaa = dateArray[2];
    int day = Integer.parseInt(dd);
    int month = Integer.parseInt(mm);
    int year = Integer.parseInt(aaaa);

    if (day > 0 && day <= 31 && month > 0 && month <= 12 && year > 1930 && year <= 2025) {
      return dateString;
    }

    JOptionPane.showMessageDialog(null, "Data de Nascimento invalida");
    return "";
  }

  private static Long validateCpf(String cpfString) {
    cpfString = cpfString.replaceAll("[.-]", "");
    Long cpf = Long.parseLong(cpfString);
    if (cpf == 0L) {
      return cpf;
    }

    if (cpf < 100_000_000_00L) {
      JOptionPane.showMessageDialog(null, "CPF Invalido");
      return 1L;
    }

    return cpf;
  }

  public String show() {
    String message = "Id: " + this.getId() + " | Nome: " + this.getNome() + " | CPF: " + this.getCpf()
        + " | Data de Nascimento: " + this.getDataNascimento() + " | Numero de Passaporte: "
        + this.getNumeroPassaporte();
    return message;
  }

  public String list(List<Passageiro> listaPassageiros) {
    String message = "";

    for (Passageiro passageiro : listaPassageiros) {
      message += passageiro.show() + "\n";
    }

    return message;
  }

  public Passageiro create(List<Passageiro> listaPassageiros) {
    PassageiroDB passageiroDB = new PassageiroDB();

    String nome = "";
    do {
      nome = JOptionPane.showInputDialog("Digite seu Nome: ");
      if (nome.equals("")) {
        JOptionPane.showMessageDialog(null, "Preencha com um nome valido");
      }
    } while (nome.equals(""));

    // 000_000_000_00
    Long cpf = 0L;
    do {
      String input = JOptionPane.showInputDialog("Digite seu CPF (000.000.000-00): ");
      cpf = validateCpf(input);
    } while (cpf == 1L);

    String dateString = "";
    do {
      dateString = JOptionPane.showInputDialog("Digite sua Data de Nascimento (dd/mm/aaaa): ");
      dateString = validateDate(dateString);
    } while (dateString == "");

    String numeroPassaporte = "";
    do {
      numeroPassaporte = JOptionPane.showInputDialog("Digite seu Numero de Passaporte: ");
      if (numeroPassaporte.equals("")) {
        JOptionPane.showMessageDialog(null, "Preencha com um Numero de Passaporte valido");
      }
    } while (numeroPassaporte.equals(""));

    LocalDate date = convertStringToDate(dateString);

    Passageiro passageiro = new Passageiro(nome, cpf, date, numeroPassaporte);
    passageiroDB.create(passageiro);
    listaPassageiros.add(passageiro);

    JOptionPane.showMessageDialog(null, "Passageiro Criado com sucesso.\n" + this.list(listaPassageiros));

    return passageiro;
  }

  public void remove(List<Passageiro> listaPassageiros) {
    PassageiroDB passageiroDB = new PassageiroDB();
    Long id = Long
        .parseLong(JOptionPane.showInputDialog(this.list(listaPassageiros) + "\nDigite o ID a ser deletado: "));

    passageiroDB.delete(id);
    removeFromList(listaPassageiros, id);

    JOptionPane.showMessageDialog(null, "Passageiro Removido com sucesso.\n" + this.list(listaPassageiros));
  }

  public void update(List<Passageiro> listaPassageiros) {
    PassageiroDB passageiroDB = new PassageiroDB();
    Long id = Long
        .parseLong(JOptionPane.showInputDialog(this.list(listaPassageiros) + "\nDigite o ID a ser editado: "));

    Passageiro passageiro = passageiroDB.findOneById(id);

    String nome = "";
    do {
      nome = JOptionPane.showInputDialog("Digite seu Nome (0 para manter): \nAtual: " + passageiro.getNome() + "\n");
      if (nome.equals("")) {
        JOptionPane.showMessageDialog(null, "Preencha com um nome valido");
      }
      if (!nome.equals("0")) {
        passageiro.setNome(nome);
      }
    } while (nome.equals(""));

    Long cpf = 0L;
    do {
      String input = JOptionPane
          .showInputDialog("Digite seu CPF (0 para manter): \nAtual: " + passageiro.getCpf() + "\n");
      cpf = validateCpf(input);

      if (cpf != 0 && cpf != 1) {
        passageiro.setCpf(cpf);
      }
    } while (cpf == 1L);

    String dateString = "";
    do {
      dateString = JOptionPane.showInputDialog("Digite sua Data de Nascimento (dd/mm/aaaa) (0 para manter): \nAtual: "
          + passageiro.getDataNascimento() + "\n");
      dateString = validateDate(dateString);
    } while (dateString == "");

    String numeroPassaporte = "";
    do {
      numeroPassaporte = JOptionPane.showInputDialog(
          "Digite seu Numero de Passaporte(0 para manter): \nAtual: " + passageiro.getNumeroPassaporte() + "\n");
      if (numeroPassaporte.equals("")) {
        JOptionPane.showMessageDialog(null, "Preencha com um nome valido");
      }
      if (!numeroPassaporte.equals("0")) {
        passageiro.setNumeroPassaporte(numeroPassaporte);
      }
    } while (nome.equals(""));

    if (dateString.equals("0")) {
      passageiroDB.update(passageiro, id);
      JOptionPane.showMessageDialog(null, "Passageiro Atualizado com sucesso.\n" + this.list(listaPassageiros));
      return;
    }

    LocalDate date = convertStringToDate(dateString);
    passageiro.setDataNascimento(date);

    passageiroDB.update(passageiro, id);
    updateFromList(listaPassageiros, passageiro, id);

    JOptionPane.showMessageDialog(null, "Passageiro Atualizado com sucesso.\n" + this.list(listaPassageiros));
  }
}
