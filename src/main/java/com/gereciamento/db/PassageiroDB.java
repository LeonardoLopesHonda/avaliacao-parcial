package com.gereciamento.db;

import java.util.List;

import com.gereciamento.models.Passageiro;

public class PassageiroDB {
  protected Repository repository;

  public PassageiroDB() {
    this.repository = Repository.getInstance();
  }

  public void create(Passageiro passageiro) {
    try {
      repository.entityManager.getTransaction().begin();
      repository.entityManager.persist(passageiro);
      repository.entityManager.getTransaction().commit();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void remove(Long id) {
    Passageiro passageiro = repository.entityManager.find(Passageiro.class, id);
    try {
      repository.entityManager.getTransaction().begin();
      repository.entityManager.remove(passageiro);
      repository.entityManager.getTransaction().commit();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public List<Passageiro> read() {
    return repository.entityManager.createQuery("SELECT p FROM Passageiro p", Passageiro.class).getResultList();
  }

  public void update(Passageiro passageiro, Long id) {
    try {
      repository.entityManager.getTransaction().begin();
      passageiro.setId(id);
      repository.entityManager.merge(passageiro);
      repository.entityManager.getTransaction().commit();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public Passageiro findOneById(Long id) {
    List<Passageiro> list = repository.entityManager
        .createQuery("SELECT p FROM Passageiro p WHERE id = " + id, Passageiro.class).getResultList();

    return list.getFirst();
  }
}
