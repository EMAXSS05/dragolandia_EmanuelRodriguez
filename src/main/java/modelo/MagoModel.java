package modelo;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;


public class MagoModel {
    
    public void guardarMago(Mago m){
        EntityManager em= util.HibernateUtil.getEntityManager();
        EntityTransaction et= em.getTransaction();
        
        try {
            et.begin();
            em.persist(m);
            et.commit();
        } catch (Exception e) {
          System.out.println("Error al guardar al mago "+ e.getMessage());
          if (et.isActive() ) {
            et.rollback();
          }
        } finally{
            em.close();
        }
    }

   public void actualizarMago(Mago m){
       EntityManager em= util.HibernateUtil.getEntityManager();
       EntityTransaction et= em.getTransaction();
       try {
         et.begin();
         em.merge(m);
         et.commit();
       } catch (Exception e) {
        System.out.println("Error al actualizar el mago "+ e.getMessage());
       } finally{
        em.close();
       }
    }

   public Mago leerPorId(Long id){
      
        EntityManager em= util.HibernateUtil.getEntityManager();
        try {
            return em.find(Mago.class, id);
        } catch (Exception e) {
            System.out.println("Error al buscar el mago con id: "+ id);
            return null;
        }finally{
            em.close();
        }
      
    }

    public List<Mago> obtenerTodos(){
        EntityManager em= util.HibernateUtil.getEntityManager();
        try {
            return em.createQuery("FROM Mago", Mago.class).getResultList();
        } catch (Exception e) {
            System.out.println("Error al obtener todos los magos "+e.getMessage());
            return null;
        }finally{
            em.close();
        }
    }

    public void borrarMago(Long id){
       EntityManager em= util.HibernateUtil.getEntityManager();
       EntityTransaction et= em.getTransaction();
       try {
        et.begin();
        Mago ma= em.find(Mago.class, id);
        if (ma!=null) {
            em.remove(ma);
        }
        et.commit();
       } catch (Exception e) {
        System.out.println("No se pudo borrar el mago con id: "+id + e.getMessage());
       } finally{
        em.close();
       }
    }

    

}
