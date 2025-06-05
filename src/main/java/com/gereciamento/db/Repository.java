package com.gereciamento.db;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Repository {
  private static Repository instance;
  protected EntityManager entityManager;

  public Repository() {
    entityManager = getEntityManager();
  }

  private EntityManager getEntityManager() {
    EntityManagerFactory factory = Persistence.createEntityManagerFactory("gerenciamento");

    if (entityManager == null) {
      entityManager = factory.createEntityManager();
    }

    return entityManager;
  }

  public static Repository getInstance() {
    if (instance == null) {
      instance = new Repository();
    }

    return instance;
  }
}
