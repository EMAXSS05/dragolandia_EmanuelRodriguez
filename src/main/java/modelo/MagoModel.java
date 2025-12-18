package modelo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;


public class MagoModel {
    private SessionFactory sessionFactory;

    public MagoModel(SessionFactory sessionFactory){
        this.sessionFactory= sessionFactory;
    }


    public void guardarMago(Mago m){
        Transaction transaction= null;
        try (Session session= sessionFactory.openSession() ) {
            transaction= session.beginTransaction();
            session.persist(m);
            transaction.commit();

            
        } catch (Exception e) {
          System.out.println("Error al guardar al mago "+ e.getMessage());
          if (transaction !=null) {
            transaction.rollback();
          }
        }
    }

   public void actualizarMago(Mago m){
        Transaction transaction=null;
       try (Session session= sessionFactory.openSession()) {
        transaction= session.beginTransaction();
        session.merge(m);
        transaction.commit();
       } catch (Exception e) {
        System.out.println("Error al actualizar el mago "+ e.getMessage());
        if (transaction!=null) {
            transaction.rollback();
        }
       }
    }

    Mago leerPorId(int id){
        
        try (Session session= sessionFactory.openSession()) {
            
            Mago mago= session.find(Mago.class, id);
            return mago;
            
        }
        catch(Exception e){
         System.out.println("No se pudo leer el mago con id "+ id +" "+ e.getMessage());
         return null;
        }
    }

}
