package modelo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class BosqueModel {
    private SessionFactory sessionFactory;

    public BosqueModel(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;

    }

   public void guardarBosque(Bosque bosque) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.persist(bosque);
            transaction.commit();

        } catch (Exception e) {
            System.out.println("Error al guardar el bosque " + e.getMessage());
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    public void actualizarBosque(Bosque bosque) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.merge(bosque);
            transaction.commit();

        } catch (Exception e) {
            System.out.println("Error al actualizar el bosque " + e.getMessage());
            if (transaction!=null) {
                transaction.rollback();
            }
        }
    }

    

}
