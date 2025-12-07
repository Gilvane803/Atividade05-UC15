package dao;

import java.util.List;

import javax.swing.JOptionPane;

import jakarta.persistence.TypedQuery;
import model.Aluno;
import model.Escola;

public class AlunoDAO extends DAO {
     public void inserir(Aluno aluno)
        {
          try
              {
                 em.getTransaction().begin();
                 em.persist(aluno);
                 em.getTransaction().commit();
              }
        catch (Exception e)
              {
                if (em.getTransaction().isActive()) 
                {
                em.getTransaction().rollback();
                }
                  JOptionPane.showMessageDialog(null,"Erro ao cadastrar Aluno:" + e.getMessage());
              }
        }
     
     public List<Aluno> listar(Escola escola)
     {         
         try 
         {
            TypedQuery<Aluno> query = em.createQuery(
              "SELECT a FROM Aluno a WHERE a.turma.escola.id = :idEscola",
              Aluno.class
            );

          query.setParameter("idEscola", escola.getId());

          return query.getResultList();
         } 
         finally 
         {
             em.close();
         }
     }
     
     public void excluir(int id)
      {
          try
            {
              
                Aluno f = em.find(Aluno.class, id);
              if( f != null)
                {
                  em.getTransaction().begin();
                  em.remove(f);
                  em.getTransaction().commit();
                }
            }
          catch (Exception e)
            {
              em.getTransaction().rollback();
              throw  e;
                      
            }
      }
     
     public Aluno atualizar(Aluno f)
      {
          try
            {
              em.getTransaction().begin();
              em.merge(f);
              em.getTransaction().commit();
            
            } 
          catch (Exception e)
            {
              em.getTransaction().rollback();
              throw e;
            }
         return null;
      }
}
