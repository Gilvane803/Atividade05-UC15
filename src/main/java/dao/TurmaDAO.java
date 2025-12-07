package dao;

import java.util.List;

import javax.swing.JOptionPane;

import jakarta.persistence.TypedQuery;
import model.Escola;
import model.Turma;

public class TurmaDAO extends DAO {
     public void inserir(Turma turma)
        {
          try
              {
                 em.getTransaction().begin();
                 em.persist(turma);
                 em.getTransaction().commit();
              }
        catch (Exception e)
              {
                if (em.getTransaction().isActive()) 
                {
                em.getTransaction().rollback();
                }
                  JOptionPane.showMessageDialog(null,"Erro ao cadastrar Turma:" + e.getMessage());
              }
        }
     
     public List<Turma> listar(Escola escola)
     {         
         try 
         {
            TypedQuery<Turma> query = em.createQuery(
              "SELECT t FROM Turma t WHERE t.escola = :escola",
              Turma.class
            );

            query.setParameter("escola", escola);
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
              
                Turma f = em.find(Turma.class, id);
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
     
     public Turma atualizar(Turma f)
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
