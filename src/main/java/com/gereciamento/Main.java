package com.gereciamento;

import java.time.LocalDate;
import java.util.List;

import javax.swing.JOptionPane;

import com.gereciamento.db.AviaoDB;
import com.gereciamento.db.PassageiroDB;
import com.gereciamento.models.Aviao;
import com.gereciamento.models.Passageiro;
import com.gereciamento.models.Voo;

public class Main {
  public static List<Passageiro> populateListPassageiros() {
    PassageiroDB passageiroDB = new PassageiroDB();

    return passageiroDB.read();
  }

  public static List<Aviao> populateListAvioes() {
    AviaoDB AviaoDB = new AviaoDB();

    return AviaoDB.read();
  }

  public static void showList(Passageiro passageiro, List<Passageiro> listaPassageiros) {
    if (listaPassageiros.isEmpty()) {
      JOptionPane.showMessageDialog(null, "Nao ha passageiros cadastrados");
    } else {
      JOptionPane.showMessageDialog(null, passageiro.list(listaPassageiros));
    }
  }

  public static void showList(Aviao aviao, List<Aviao> listaAvioes) {
    if (listaAvioes.isEmpty()) {
      JOptionPane.showMessageDialog(null, "Nao ha nenhum aviao cadastrados");
    } else {
      JOptionPane.showMessageDialog(null, aviao.list(listaAvioes));
    }
  }

  public static void showList(Voo voo, List<Passageiro> passageiros) {
    if (passageiros.isEmpty()) {
      JOptionPane.showMessageDialog(null, "Nao ha nenhum Voo agendado");
    } else {
      JOptionPane.showMessageDialog(null, voo.list(passageiros));
    }
  }

  public static void passageiroGUI(Passageiro passageiro, List<Passageiro> listaPassageiros) {
    int opt = -1;
    do {
      opt = Integer.parseInt(JOptionPane.showInputDialog(null,
          "Selecione uma opcao: \n\n ==> Passageiro" + "\n1- Adicionar Passageiro \n" + "2- Editar Passageiro \n"
              + "3- Deletar Passageiro \n" + "4- Listar Passageiros \n\n" + "0- Sair \n\n"));
      switch (opt) {
      case 1:
        passageiro.create(listaPassageiros);
        break;
      case 2:
        passageiro.update(listaPassageiros);
        break;
      case 3:
        passageiro.remove(listaPassageiros);
        break;
      case 4:
        showList(passageiro, listaPassageiros);
        break;
      case 0:
        JOptionPane.showMessageDialog(null, "Voltando...");
        break;
      default:
        JOptionPane.showMessageDialog(null, "Opcao Invalida, tente novamente");
        break;
      }
    } while (opt != 0);
  }

  public static void aviaoGUI(Aviao aviao, List<Aviao> listaAvioes) {
    int opt = -1;
    do {
      opt = Integer
          .parseInt(JOptionPane.showInputDialog(null, "Selecione uma opcao: \n\n ==> Aviao" + "\n1- Adicionar Aviao \n"
              + "2- Editar Aviao \n" + "3- Deletar Aviao \n" + "4- Listar Avioes \n\n" + "0- Sair \n\n"));
      switch (opt) {
      case 1:
        aviao.create(listaAvioes);
        break;
      case 2:
        aviao.update(listaAvioes);
        break;
      case 3:
        aviao.remove(listaAvioes);
        break;
      case 4:
        showList(aviao, listaAvioes);
        break;
      case 0:
        JOptionPane.showMessageDialog(null, "Voltando...");
        break;
      default:
        JOptionPane.showMessageDialog(null, "Opcao Invalida, tente novamente");
        break;
      }
    } while (opt != 0);
  }

  public static void vooGUI(Voo voo, List<Passageiro> listaPassageiros) {
    int opt = -1;
    do {
      opt = Integer.parseInt(JOptionPane.showInputDialog(null,
          "Selecione uma opcao: \n\n ==> Voo" + "\n1- Agendar Voo \n" + "2- Listar Voo \n\n" + "0- Sair \n\n"));
      switch (opt) {
      case 1:
        voo.bookFlight();
        break;
      case 2:
        showList(voo, listaPassageiros);
      case 0:
        JOptionPane.showMessageDialog(null, "Voltando...");
        break;
      default:
        JOptionPane.showMessageDialog(null, "Opcao Invalida, tente novamente");
        break;
      }
    } while (opt != 0);
  }

  public static void main(String[] args) {
    List<Passageiro> listaPassageiros = populateListPassageiros();
    List<Aviao> listaAvioes = populateListAvioes();
    Passageiro passageiro = new Passageiro();
    Aviao aviao = new Aviao();
    Voo voo = new Voo();

    int opt = -1;

    do {
      opt = Integer.parseInt(JOptionPane.showInputDialog(null,
          "Selecione uma opcao: \n\n1- Passageiro \n" + "2- Aviao \n" + "3- Voo \n0- Sair\n\n"));
      switch (opt) {
      case 1:
        /** Passageiro */
        passageiroGUI(passageiro, listaPassageiros);
        break;
      case 2:
        /** Avião */
        aviaoGUI(aviao, listaAvioes);
        break;
      case 3:
        /** Voo */
        vooGUI(voo, listaPassageiros);
        break;
      case 0:
        JOptionPane.showMessageDialog(null, "Saindo do sistema, obrigado pela preferencia");
        break;
      default:
        JOptionPane.showMessageDialog(null, "Opcao Invalida, tente novamente");
        break;
      }
    } while (opt != 0);

  }
}