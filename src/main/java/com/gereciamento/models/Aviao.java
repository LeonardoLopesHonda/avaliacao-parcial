package com.gereciamento.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.swing.JOptionPane;

import com.gereciamento.db.AviaoDB;

@Entity
@Table(name = "avioes")
public class Aviao {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  @Column(nullable = false)
  private String modelo;
  @Column(nullable = false)
  private String fabricante;
  @Column(nullable = false)
  private int capacidadePassageiros;
  @Column(nullable = false)
  private int anoFabricacao;
  @Column(nullable = false)
  private int registro;

  public Aviao() {
  }

  public Aviao(String modelo, String fabricante, int capacidadePassageiros, int anoFabricacao, int registro) {
    this.modelo = modelo;
    this.fabricante = fabricante;
    this.capacidadePassageiros = capacidadePassageiros;
    this.anoFabricacao = anoFabricacao;
    this.registro = registro;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getModelo() {
    return modelo;
  }

  public void setModelo(String modelo) {
    this.modelo = modelo;
  }

  public String getFabricante() {
    return fabricante;
  }

  public void setFabricante(String fabricante) {
    this.fabricante = fabricante;
  }

  public int getCapacidadePassageiros() {
    return capacidadePassageiros;
  }

  public void setCapacidadePassageiros(int capacidadePassageiros) {
    this.capacidadePassageiros = capacidadePassageiros;
  }

  public int getAnoFabricacao() {
    return anoFabricacao;
  }

  public void setAnoFabricacao(int anoFabricacao) {
    this.anoFabricacao = anoFabricacao;
  }

  public int getRegistro() {
    return registro;
  }

  public void setRegistro(int registro) {
    this.registro = registro;
  }

  private static void removeFromList(List<Aviao> listaAvioes, Long indexOfAviao) {
    for (int i = 0; i < listaAvioes.size(); i++) {
      if (listaAvioes.get(i).getId() == Integer.parseInt(Long.toString(indexOfAviao))) {
        listaAvioes.remove(i);
        break;
      }
    }
  }

  private static void updateFromList(List<Aviao> listaAvioes, Aviao aviao, Long indexOfAviao) {
    for (int i = 0; i < listaAvioes.size(); i++) {
      if (listaAvioes.get(i).getId() == Integer.parseInt(Long.toString(indexOfAviao))) {
        listaAvioes.set(i, aviao);
        break;
      }
    }
  }

  public String show() {
    String message = "Id: " + this.getId() + " | Modelo: " + this.getModelo() + " | Fabricante: " + this.getFabricante()
        + " | Capacidade de passageiros: " + this.getCapacidadePassageiros() + " | Ano de Fabricacao: "
        + this.getAnoFabricacao() + " | Registro: " + this.getRegistro();
    return message;
  }

  public String list(List<Aviao> listaAvioes) {
    String message = "";

    for (Aviao aviao : listaAvioes) {
      message += aviao.show() + "\n";
    }

    return message;
  }

  public void create(List<Aviao> listaAvioes) {
    AviaoDB aviaoDB = new AviaoDB();

    String modelo = "";
    do {
      modelo = JOptionPane.showInputDialog("Digite seu Modelo: ");
      if (modelo.equals("")) {
        JOptionPane.showMessageDialog(null, "Preencha com um modelo valido");
      }
    } while (modelo.equals(""));

    String fabricante = "";
    do {
      fabricante = JOptionPane.showInputDialog("Digite seu Fabricante: ");
      if (fabricante.equals("")) {
        JOptionPane.showMessageDialog(null, "Preencha com um fabricante valido");
      }
    } while (fabricante.equals(""));

    int capacidadePassageiros = 0;
    do {
      capacidadePassageiros = Integer
          .parseInt(JOptionPane.showInputDialog("Digite a capacidade de passageiros do Aviao: "));
      if (capacidadePassageiros <= 0) {
        JOptionPane.showMessageDialog(null, "Preencha com uma capacidade de passageiros valida");
      }
    } while (capacidadePassageiros <= 0);

    int anoFabricacao = 0;
    do {
      anoFabricacao = Integer.parseInt(JOptionPane.showInputDialog("Digite seu ano de fabricacao: "));
      if (anoFabricacao <= 1940) {
        JOptionPane.showMessageDialog(null, "Preencha com um ano de fabricacao valido");
      }
    } while (anoFabricacao <= 1940);

    int registro = 0;
    do {
      registro = Integer.parseInt(JOptionPane.showInputDialog("Digite seu registro (Apenas numeros): "));
      if (registro <= 0) {
        JOptionPane.showMessageDialog(null, "Preencha com um registro valido");
      }
    } while (registro <= 0);

    Aviao aviao = new Aviao(modelo, fabricante, capacidadePassageiros, anoFabricacao, registro);
    aviaoDB.create(aviao);
    listaAvioes.add(aviao);

    JOptionPane.showMessageDialog(null, "Aviao Criado com sucesso.\n" + this.list(listaAvioes));
  }

  public void remove(List<Aviao> listaAvioes) {
    AviaoDB AviaoDB = new AviaoDB();
    Long id = Long.parseLong(JOptionPane.showInputDialog(this.list(listaAvioes) + "\nDigite o ID a ser deletado: "));

    AviaoDB.delete(id);
    removeFromList(listaAvioes, id);

    JOptionPane.showMessageDialog(null, "Aviao Removido com sucesso.\n" + this.list(listaAvioes));
  }

  public void update(List<Aviao> listaAvioes) {
    AviaoDB aviaoDB = new AviaoDB();

    Long id = Long.parseLong(JOptionPane.showInputDialog(this.list(listaAvioes) + "\nDigite o ID a ser editado: "));

    Aviao aviao = aviaoDB.findOneById(id);

    String modelo = "";
    do {
      modelo = JOptionPane.showInputDialog("Digite o modelo (0 para manter) : \nAtual: " + aviao.getModelo() + "\n");
      if (!modelo.equals("0")) {
        aviao.setModelo(modelo);
      }
      if (modelo.equals("") && !modelo.equals("0")) {
        JOptionPane.showMessageDialog(null, "Preencha com um modelo valido");
      }
    } while (modelo.equals(""));

    String fabricante = "";
    do {
      fabricante = JOptionPane
          .showInputDialog("Digite seu Fabricante (0 para manter): \nAtual: " + aviao.getFabricante() + "\n");
      if (!fabricante.equals("0")) {
        aviao.setFabricante(fabricante);
      }
      if (fabricante.equals("") && !fabricante.equals("0")) {
        JOptionPane.showMessageDialog(null, "Preencha com um fabricante valido");
      }
    } while (fabricante.equals(""));

    int capacidadePassageiros = -1;
    do {
      capacidadePassageiros = Integer
          .parseInt(JOptionPane.showInputDialog("Digite a capacidade de passageiros do Aviao (0 para manter): \nAtual: "
              + aviao.getCapacidadePassageiros() + "\n"));
      if (capacidadePassageiros < 0) {
        JOptionPane.showMessageDialog(null, "Preencha com uma capacidade de passageiros valida");
      }
      if (capacidadePassageiros != 0 && capacidadePassageiros >= 1) {
        aviao.setCapacidadePassageiros(capacidadePassageiros);
      }
    } while (capacidadePassageiros < 0);

    int anoFabricacao = 0;
    do {
      anoFabricacao = Integer.parseInt(JOptionPane
          .showInputDialog("Digite seu ano de fabricacao (0 para manter): \nAtual: " + aviao.getFabricante() + "\n"));
      if (anoFabricacao != 0 && anoFabricacao > 1940) {
        aviao.setAnoFabricacao(anoFabricacao);
      }
      if (anoFabricacao <= 1940 && anoFabricacao != 0) {
        JOptionPane.showMessageDialog(null, "Preencha com um ano de fabricacao valido");
      }
    } while (anoFabricacao <= 1940 && anoFabricacao != 0);

    int registro = 0;
    do {
      registro = Integer.parseInt(JOptionPane.showInputDialog(
          "Digite seu registro (0 para manter e apenas numeros): \nAtual: " + aviao.getRegistro() + "\n"));
      if (registro < 0) {
        JOptionPane.showMessageDialog(null, "Preencha com um registro valido");
      }
      if (registro != 0 && registro > 0) {
        aviao.setRegistro(registro);
      }
    } while (registro < 0);

    aviaoDB.update(aviao, id);
    updateFromList(listaAvioes, aviao, id);

    JOptionPane.showMessageDialog(null, "Aviao Editado com sucesso.\n" + this.list(listaAvioes));
  }
}
