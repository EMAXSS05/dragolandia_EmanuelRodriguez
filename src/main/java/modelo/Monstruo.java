package modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Monstruos")
public class Monstruo {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   private String nombre;
   private int vida;
   @Enumerated(EnumType.STRING)

   private Tipos tipo;
   private int fuerza;

   public Monstruo(){}



   public Long getId() {
    return id;
}



   public Tipos getTipo() {
    return tipo;
   }



   public Monstruo(String nombre, int vida, int fuerza, Tipos tipo){
    this.nombre=nombre;
    this.tipo= tipo;
    this.vida=vida;
    this.fuerza=fuerza;
   }

    public String getNombre() {
        return nombre;
    }

    public int getVida() {
        return vida;
    }

    public int getFuerza() {
        return fuerza;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public void setFuerza(int fuerza) {
        this.fuerza = fuerza;
    }

    public void atacar(Mago mago){
      mago.setVida(mago.getVida()- fuerza);
      System.out.println("El monstruo ha atacado al mago y ahora el mago tiene: "+ mago.getVida() +"puntos de vida");
    }

    public void recibirDaño(int daño){
     int vida= this.vida-daño;
     System.out.println("El monstruo ha recibido "+ daño+ "daño, el monstruo ahora tiene "+ vida+"puntos de vida");
      
    }

    

    
   
   
}
