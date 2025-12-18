package modelo;

import javax.annotation.processing.Generated;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;

@Entity
@Table(name = "Hechizos")
@Inheritance(strategy= InheritanceType.JOINED)
public abstract class Hechizo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
   
    public abstract void aplicarEfecto(Mago lanzador, Monstruo objetivo);

    public Hechizo(){}

    
    

}
