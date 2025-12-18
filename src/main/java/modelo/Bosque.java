package modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Bosque")

public class Bosque {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   private String nombre;
   private int nivelPeligro;
   @OneToOne // O @ManyToOne, si varios bosques pueden tener el mismo Monstruo
    @JoinColumn(name = "monstruo_jefe_id")
   private Monstruo monstruoJefe;

   public Bosque(){}

   public Bosque(String nombre, int nivelPeligro, Monstruo monstruoJefe){
    this.nombre=nombre;
    this.nivelPeligro=nivelPeligro;
    this.monstruoJefe=monstruoJefe;

   }
   

    public String getNombre() {
        return nombre;
    }

    public int getNivelPeligro() {
        return nivelPeligro;
    }
     public Monstruo getMonstruoJefe() {
        return monstruoJefe;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

     public void setNivelPeligro(int nivelPeligro) {
        this.nivelPeligro = nivelPeligro;
    }

    public void setMonstruoJefe(Monstruo monstruoJefe) {
        this.monstruoJefe = monstruoJefe;
    }

     public void mostrarJefe(){
        System.out.println("Nombre: "+ this.monstruoJefe.getNombre());
        System.out.println("vida: "+ this.monstruoJefe.getVida());
        System.out.println("fuerza: "+ this.monstruoJefe.getFuerza());
    }

     public void cambiarJefe(Monstruo nuevoJefe){
        this.monstruoJefe= nuevoJefe;
        System.out.println("El nuevo monstruo jefe del bosque " +this.nombre+" es: " + monstruoJefe.getNombre());
    }



   public Long getId() {
    return id;
   }

    
     
    
   
}
