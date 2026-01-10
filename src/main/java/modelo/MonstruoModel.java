package modelo;


import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class MonstruoModel {
    

    public void guardarMonstruo(Monstruo mo){
        EntityManager em= util.HibernateUtil.getEntityManager();
        EntityTransaction et= em.getTransaction();
       try {
        et.begin();
        em.persist(mo);
        et.commit();
            
       } catch (Exception e) {
       System.out.println("Error al guardar el monstruo "+e.getMessage());
       if (et.isActive()) {
         et.rollback();
       }
       } finally{
        em.close();
       }
    }

    public void actualizarMonstruo(Monstruo mo){
       EntityManager em= util.HibernateUtil.getEntityManager();
       EntityTransaction et= em.getTransaction();
        try  {
            et.begin();
            em.merge(mo);
            et.commit();

            
        } catch (Exception e) {
            System.out.println("No se pudo actualizar el monstruo "+ e.getMessage());
            if (et.isActive()) {
                et.rollback();
            }
        }finally{
            em.close();
        }
    }

    public Monstruo leerPorId(Long id){
        EntityManager em= util.HibernateUtil.getEntityManager();
       try {
        return em.find(Monstruo.class , id);
        
        
       } catch (Exception e) {
        System.out.println("No se pudo leer el monstruo con id: "+id +" "+ e.getMessage());
        return null;
       } finally{
        em.close();
       }
    }

    public List<Monstruo> obtenerTodos(){
        EntityManager em= util.HibernateUtil.getEntityManager();
        try {
            return em.createQuery("FROM Monstruo", Monstruo.class).getResultList();

        } catch (Exception e) {
            System.out.println("No se pudo obtener todos los monstruos "+ e.getMessage());
            return null;
        } finally{
            em.close();
        }
    }

}
