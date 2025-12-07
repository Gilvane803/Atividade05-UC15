package dao;

import java.util.List;

import javax.swing.JOptionPane;

import jakarta.persistence.TypedQuery;
import model.Evento;

public class EventoDAO extends DAO {
     public void inserir(Evento evento)
        {
          try
              {
                 em.getTransaction().begin();
                 em.persist(evento);
                 em.getTransaction().commit();
              }
        catch (Exception e)
              {
                if (em.getTransaction().isActive()) 
                {
                em.getTransaction().rollback();
                }
                  JOptionPane.showMessageDialog(null,"Erro ao cadastrar Evento:" + e.getMessage());
              }
        }
     
     public List<Evento> listar()
     {         
         try 
         {
            TypedQuery<Evento> query = em.createQuery("SELECT t FROM Evento t", Evento.class);
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
              
                Evento f = em.find(Evento.class, id);
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
     
     public Evento atualizar(Evento f)
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
