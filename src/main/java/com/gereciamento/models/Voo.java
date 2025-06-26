package com.gereciamento.models;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.swing.JOptionPane;

import com.gereciamento.db.AviaoDB;
import com.gereciamento.db.PassageiroDB;
import com.gereciamento.db.VooDB;

@Entity
@Table(name = "voos")
public class Voo {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long idVoo;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "idAviao", referencedColumnName = "idAviao", nullable = false)
  private Aviao aviao;

  @Column(nullable = false)
  private String origem;

  @Column(nullable = false)
  private String destino;

  @Column(nullable = false)
  private LocalDate dataDePartida;

  @Column(nullable = false)
  private LocalDate dataDeChegada;

  @OneToMany(targetEntity = Passageiro.class, mappedBy = "voo", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  private List<Passageiro> passageiros;

  public Voo() {
  }

  public Voo(Aviao aviao, String origem, String destino, LocalDate dataDePartida, LocalDate dataDeChegada,
      List<Passageiro> passageiros) {
    this.aviao = aviao;
    this.origem = origem;
    this.destino = destino;
    this.dataDePartida = dataDePartida;
    this.dataDeChegada = dataDeChegada;
    this.passageiros = passageiros;
  }

  public Long getIdVoo() {
    return idVoo;
  }

  public void setIdVoo(Long idVoo) {
    this.idVoo = idVoo;
  }

  public Aviao getAviao() {
    return aviao;
  }

  public void setAviao(Aviao aviao) {
    this.aviao = aviao;
  }

  public String getOrigem() {
    return origem;
  }

  public void setOrigem(String origem) {
    this.origem = origem;
  }

  public String getDestino() {
    return destino;
  }

  public void setDestino(String destino) {
    this.destino = destino;
  }

  public LocalDate getDataDePartida() {
    return dataDePartida;
  }

  public void setDataDePartida(LocalDate dataDePartida) {
    this.dataDePartida = dataDePartida;
  }

  public LocalDate getDataDeChegada() {
    return dataDeChegada;
  }

  public void setDataDeChegada(LocalDate dataDeChegada) {
    this.dataDeChegada = dataDeChegada;
  }

  public List<Passageiro> getPassageiros() {
    return passageiros;
  }

  public void setPassageiros(List<Passageiro> passageiros) {
    this.passageiros = passageiros;
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

  private static String listPassageiros(List<Passageiro> passageiros) {
    String message = "";

    for (Passageiro passageiro : passageiros) {
      message += passageiro.show() + "\n";
    }

    return message;
  }

  public String show() {
    String message = "Id: " + this.getIdVoo() + "\nAviao: \n" + this.getAviao().show() + " \nOrigem: "
        + this.getOrigem() + " | Destino: " + this.getDestino() + " | Data de Partida: " + this.getDataDePartida()
        + " | Data de Chegada: " + this.getDataDeChegada() + "\nLista de Passageiros: \n"
        + listPassageiros(this.getPassageiros());
    return message;
  }

  public String list(List<Passageiro> listaPassageiros) {
    String message = "";

    for (Passageiro passageiro : listaPassageiros) {
      message += passageiro.show() + "================================\n";
    }

    return message;
  }

  public static Aviao selectAirplane() {
    AviaoDB aviaoDB = new AviaoDB();
    Long id = Long.parseLong(JOptionPane.showInputDialog(null,
        new Aviao().list(aviaoDB.read()) + "\n Selecione o id do Aviao para alocar: "));

    return aviaoDB.findOneById(id);
  }

  public static List<Passageiro> populateFlight(Aviao aviao) {
    PassageiroDB passageiroDB = new PassageiroDB();

    List<Passageiro> passageiros = passageiroDB.read();

    if (aviao.getCapacidadePassageiros() < passageiros.size()) {
      JOptionPane.showMessageDialog(null, "Quantidade de Pessoas excede a capacidade maxima, irao ser alocados os "
          + aviao.getCapacidadePassageiros() + " primeiros passageiros");
      passageiros = passageiros.subList(0, aviao.getCapacidadePassageiros());
    }

    return passageiros;
  }

  public void bookFlight() {
    Voo voo = new Voo();
    voo.setAviao(selectAirplane());

    String origem = "";
    do {
      origem = JOptionPane.showInputDialog("Digite o ponto de origem: ");
      if (origem.equals("")) {
        JOptionPane.showMessageDialog(null, "Preencha com um ponto de origem valido");
      }
    } while (origem == "");
    voo.setOrigem(origem);

    String destino = "";
    do {
      destino = JOptionPane.showInputDialog("Digite o destino: ");
      if (destino.equals("")) {
        JOptionPane.showMessageDialog(null, "Preencha com um destino valido");
      }
    } while (destino == "");
    voo.setDestino(destino);

    String dateStringPartida = "";
    do {
      dateStringPartida = JOptionPane.showInputDialog("Digite sua Data de Partida (dd/mm/aaaa): ");
      dateStringPartida = validateDate(dateStringPartida);
    } while (dateStringPartida == "");
    voo.setDataDePartida(convertStringToDate(dateStringPartida));

    String dateStringChegada = "";
    do {
      dateStringChegada = JOptionPane.showInputDialog("Digite sua Data de Chegada (dd/mm/aaaa): ");
      dateStringChegada = validateDate(dateStringChegada);
    } while (dateStringChegada == "");
    voo.setDataDeChegada(convertStringToDate(dateStringChegada));

    voo.setPassageiros(populateFlight(voo.getAviao()));

    VooDB vooDB = new VooDB();
    vooDB.create(voo);
  }
}
