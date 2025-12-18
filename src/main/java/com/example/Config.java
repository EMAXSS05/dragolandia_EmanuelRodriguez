package com.example;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Config {

    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory(){
        if (sessionFactory==null) {
            
        
     try  {
        sessionFactory= new Configuration().configure().buildSessionFactory();
        
     } catch (Throwable e) {
        System.out.println("Errro al crear session Factory "+ e.getMessage());
        e.printStackTrace(System.err);
       
    
     }
    }

     return sessionFactory;
    }


    public static void closeFactory(){
        if (sessionFactory!=null) {
            sessionFactory.close();
        }
    }




}
