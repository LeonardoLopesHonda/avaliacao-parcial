package com.gereciamento;

import java.util.List;

import javax.swing.JOptionPane;

import com.gereciamento.db.PassageiroDB;
import com.gereciamento.models.Passageiro;

public class Main {
  public static List<Passageiro> populateList(List<Passageiro> listaPassageiros) {
    PassageiroDB passageiroDB = new PassageiroDB();

    return passageiroDB.read();
  }

  public static void showList(Passageiro passageiro, List<Passageiro> listaPassageiros) {
    if (listaPassageiros.isEmpty()) {
      JOptionPane.showMessageDialog(null, "Nao ha passageiros cadastrados");
    } else {
      JOptionPane.showMessageDialog(null, passageiro.list(listaPassageiros));
    }
  }

  public static void main(String[] args) {
    List<Passageiro> listaPassageiros = populateList(null);
    Passageiro passageiro = new Passageiro();

    int opt = -1;

    do {
      opt = Integer.parseInt(JOptionPane.showInputDialog(null,
          "Selecione uma opcao: \n\n ==> Passageiro" + "\n1- Adicionar Passageiro \n" + "2- Editar Passageiros \n"
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
        JOptionPane.showMessageDialog(null, "Saindo do sistema, obrigado pela preferencia");
        break;
      default:
        JOptionPane.showMessageDialog(null, "Opcao Invalida, tente novamente");
        break;
      }
    } while (opt != 0);
  }
}