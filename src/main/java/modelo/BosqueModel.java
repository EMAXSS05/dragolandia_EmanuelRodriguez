package modelo;

import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class BosqueModel {
    
   public void guardarBosque(Bosque bosque) {
        EntityManager em= util.HibernateUtil.getEntityManager();
        EntityTransaction et= em.getTransaction();
        try {
            et.begin();
            em.persist(bosque);
            et.commit();

        } catch (Exception e) {
            System.out.println("Error al guardar el bosque " + e.getMessage());
            if (et.isActive()) {
                et.rollback();
            }
        }finally{
            em.close();
        }
    }
    public Bosque obtenerBosque(Long id) {
        EntityManager em= util.HibernateUtil.getEntityManager();
        try {
             
            return  em.find(Bosque.class, id);
        } catch (Exception e) {
            System.out.println("Error al obtener el bosque: " + e.getMessage());
            return null;
        }finally{
            em.close();
        }
    }

    public List<Bosque> obtenerTodos(){
        EntityManager em= util.HibernateUtil.getEntityManager();
        try {
            return em.createQuery("FROM Bosque", Bosque.class).getResultList();
        } catch (Exception e) {
            System.out.println("Error al obtener todos los bosques");
            return null;
        }finally{
            em.close();
        }
    }

    public void actualizarBosque(Bosque bosque) {
        EntityManager em= util.HibernateUtil.getEntityManager();
        EntityTransaction et= em.getTransaction();
        try   {
           et.begin();
        em.merge(bosque);
            et.commit();

        } catch (Exception e) {
            System.out.println("Error al actualizar el bosque " + e.getMessage());
            if (et.isActive()) {
                et.rollback();
            }
        } finally{
            em.close();
        }
    }

   public void eliminar(Long id){
    EntityManager em= util.HibernateUtil.getEntityManager();
    EntityTransaction et= em.getTransaction();
    try {
        et.begin();
        Bosque bo= em.find(Bosque.class, id);
        if (bo!= null) {
            em.remove(bo);
        }
        et.commit();
    } catch (Exception e) {
        System.out.println("");
    }
   }

    

}
