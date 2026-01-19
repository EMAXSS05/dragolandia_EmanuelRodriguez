package controlador;


import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import modelo.Dragon;



public class DragonControlador {

 public void guardarDragon(Dragon dragon) {
        EntityManager em= util.HibernateUtil.getEntityManager();
        EntityTransaction et= em.getTransaction();
        
            try {
                et.begin();
                em.persist(dragon);
                et.commit();
            } catch (Exception e) {
               
                if (et.isActive() ) {
                    et.rollback();
                }
                System.err.println("Error al guardar el Dragón: " + e.getMessage());
                e.printStackTrace(); 
            } finally{
                em.close();
            }
        
    }



    public void actualizarDragon(Dragon dragon){
        EntityManager em= util.HibernateUtil.getEntityManager();
        EntityTransaction et= em.getTransaction();

        try {
            et.begin();
            em.merge(dragon);
            et.commit();
        } catch (Exception e) {
            if (et.isActive()) {
                et.rollback();
            }
        }
    }

    /**
     * Método que nos ayuda a consultar info de un Dragon segun su id
     * @param id
     * @return retorna un Dragon
     */
public Dragon leerPorId(Long id){

    EntityManager em= util.HibernateUtil.getEntityManager();
    try{
        return em.find(Dragon.class,id );
    }finally{
        em.close();
    }


}

/**
 * Método que nos ayuda a visualizar todos los dragones 
 * @return retorna una lista de todos los Dragones
 */
public List<Dragon> obtenerTodos(){
 EntityManager em= util.HibernateUtil.getEntityManager();
 try{
    return em.createQuery("FROM Dragon", Dragon.class).getResultList();
 } catch(Exception e){
 System.out.println(e.getMessage());
 return null;
 } 
 finally{
    em.close();
 }
}

    

}
