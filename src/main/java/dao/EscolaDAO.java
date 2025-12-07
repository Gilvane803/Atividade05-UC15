package dao;

import java.util.List;

import javax.swing.JOptionPane;

import jakarta.persistence.TypedQuery;
import model.Escola;

public class EscolaDAO extends DAO {
     public void inserir(Escola escola)
        {
             
          try
              {
                 em.getTransaction().begin();
                 em.persist(escola);
                 em.getTransaction().commit();
              }
        catch (Exception e)
              {
                if (em.getTransaction().isActive()) 
                {
                em.getTransaction().rollback();
                }
                  JOptionPane.showMessageDialog(null,"Erro ao cadastrar escola:" + e.getMessage());
              }
        }
     
     public List<Escola> listar()
     {         
         try 
         {
            TypedQuery<Escola> query = em.createQuery("SELECT e FROM Escola e", Escola.class);
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
              
                Escola f = em.find(Escola.class, id);
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
     
     public Escola atualizar(Escola f)
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
