package modelo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class MonstruoModel {
    private SessionFactory sessionFactory;

    public MonstruoModel(SessionFactory sessionFactory){
        this.sessionFactory=sessionFactory;
    }

    public void guardarMonstruo(Monstruo mo){
        Transaction transaction= null;
       try (Session session= sessionFactory.openSession()) {
        transaction= session.beginTransaction();
        session.persist(mo);
        transaction.commit();
            
       } catch (Exception e) {
       System.out.println("Error al guardar el monstruo "+e.getMessage());
       if (transaction!=null) {
        transaction.rollback();
       }
       }
    }

    public void actualizarMonstruo(Monstruo mo){
        Transaction transaction= null;
        try (Session session= sessionFactory.openSession()) {
            transaction= session.beginTransaction();
            session.merge(mo);
            transaction.commit();

            
        } catch (Exception e) {
            System.out.println("No se pudo actualizar el monstruo "+ e.getMessage());
            if (transaction!= null) {
                transaction.rollback();
            }
        }
    }

    Monstruo leerPorId(int id){
       try (Session session= sessionFactory.openSession()) {
        Monstruo monstruo= session.find(Monstruo.class, id);
        return monstruo;
        
       } catch (Exception e) {
        System.out.println("No se pudo leer el monstruo con id: "+id +" "+ e.getMessage());
        return null;
       }
    }

}
