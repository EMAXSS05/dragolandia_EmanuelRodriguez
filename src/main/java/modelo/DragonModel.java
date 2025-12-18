package modelo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;



public class DragonModel {

    private SessionFactory sessionFactory;

    public DragonModel(SessionFactory sessionFactory){
       this.sessionFactory= sessionFactory;
    }

    
    public void guardarDragon(Dragon dragon){
        Transaction transaction= null;
        try(Session session= sessionFactory.openSession()) {
            transaction= session.beginTransaction();
            session.persist(dragon);
            transaction.commit();
            
            
        } catch (Exception e) {
            System.out.println("Error al guardar el bosque "+e.getMessage());
            if (transaction!=null) {
                transaction.rollback();
            }
        }
        
    }

    

}
