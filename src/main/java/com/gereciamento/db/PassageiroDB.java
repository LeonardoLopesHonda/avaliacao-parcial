package com.gereciamento.db;

import javax.swing.JOptionPane;

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
      JOptionPane.showMessageDialog(null, "Passageiro Cadastrado com sucesso");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  // public void remover(Long idAluno) {
  //   AlunoModelo aluno = conexao.entityManager.find(AlunoModelo.class, idAluno);

  //   try {
  //     conexao.entityManager.getTransaction().begin();
  //     conexao.entityManager.remove(aluno);
  //     conexao.entityManager.getTransaction().commit();
  //   } catch (Exception e) {
  //     e.printStackTrace();
  //   }
  // }

  // public String editar(AlunoModelo aluno, Long idAluno) {
  //   try {
  //     conexao.entityManager.getTransaction().begin();
  //     aluno.setIdPessoa(idAluno);
  //     conexao.entityManager.merge(aluno);
  //     conexao.entityManager.getTransaction().commit();
  //     return "Editado com Sucesso.";
  //   } catch (Exception e) {
  //     return e.getMessage();
  //   }
  // }

  // public List<AlunoModelo> read() {
  //   return conexao.entityManager.createQuery("SELECT a FROM AlunoModelo a", AlunoModelo.class).getResultList();
  // }
}
