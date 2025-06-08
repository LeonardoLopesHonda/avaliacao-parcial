package com.gereciamento.db;

import java.util.List;

import com.gereciamento.models.Aviao;

public class AviaoDB {
  protected Repository repository;

  public AviaoDB() {
    this.repository = Repository.getInstance();
  }

  public void create(Aviao aviao) {
    try {
      repository.entityManager.getTransaction().begin();
      repository.entityManager.persist(aviao);
      repository.entityManager.getTransaction().commit();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public List<Aviao> read() {
    return repository.entityManager.createQuery("SELECT a FROM Aviao a", Aviao.class).getResultList();
  }

  public void update(Aviao aviao, Long id) {
    try {
      repository.entityManager.getTransaction().begin();
      aviao.setId(id);
      repository.entityManager.merge(aviao);
      repository.entityManager.getTransaction().commit();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void delete(Long id) {
    Aviao aviao = repository.entityManager.find(Aviao.class, id);
    try {
      repository.entityManager.getTransaction().begin();
      repository.entityManager.remove(aviao);
      repository.entityManager.getTransaction().commit();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public Aviao findOneById(Long id) {
    List<Aviao> list = repository.entityManager.createQuery("SELECT a FROM Aviao a WHERE id = " + id, Aviao.class)
        .getResultList();

    return list.getFirst();
  }
}
