package com.gereciamento.db;

import java.util.List;

import com.gereciamento.models.Voo;

public class VooDB {
  protected Repository repository;

  public VooDB() {
    this.repository = Repository.getInstance();
  }

  public void create(Voo voo) {
    try {
      repository.entityManager.getTransaction().begin();
      repository.entityManager.persist(voo);
      repository.entityManager.getTransaction().commit();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public List<Voo> read() {
    return repository.entityManager.createQuery("SELECT v FROM Voo v", Voo.class).getResultList();
  }

  public void update(Voo voo, Long id) {
    try {
      repository.entityManager.getTransaction().begin();
      voo.setIdVoo(id);
      repository.entityManager.merge(voo);
      repository.entityManager.getTransaction().commit();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void delete(Long id) {
    Voo voo = repository.entityManager.find(Voo.class, id);
    try {
      repository.entityManager.getTransaction().begin();
      repository.entityManager.remove(voo);
      repository.entityManager.getTransaction().commit();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public Voo findOneById(Long id) {
    List<Voo> list = repository.entityManager.createQuery("SELECT v FROM Voo v WHERE idVoo = " + id, Voo.class)
        .getResultList();

    return list.getFirst();
  }
}
