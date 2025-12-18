package modelo;



import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Magos")
public class Mago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private int vida;
    private int nivelMagia;
    private List<Hechizo>conjuros;
    

    public Long getId() {
        return id;
    }

    public Mago(){}
    
    
    

    public Mago(String nombre, int vida, int nivelMagia, List<Hechizo> conjuros) {
        this.nombre = nombre;
        this.vida = vida;
        this.nivelMagia = nivelMagia;
        this.conjuros = conjuros;
    }


    

    public Mago(String nombre, int vida, int nivelMagia) {
        this.nombre = nombre;
        this.vida = vida;
        this.nivelMagia = nivelMagia;
    }

    public String getNombre() {
        return nombre;
    }
    
    public int getNivelMagia() {
        return nivelMagia;
    }

    public int getVida() {
        return vida;
    }

  
     public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }
    
    public void setNivelMagia(int nivelMagia) {
        this.nivelMagia = nivelMagia;
    }

  
    


    public List<Hechizo> getConjuros() {
        return conjuros;
    }

    public void setConjuros(List<Hechizo> conjuros) {
        this.conjuros = conjuros;
    }

    public  void lanzarHechizo(Monstruo m){
       m.setVida(m.getVida()-nivelMagia);;
       
    }

   public void lanzarHechizo(Monstruo objetivo, Hechizo hechizo) {
        if (this.conjuros.contains(hechizo)) {
            hechizo.aplicarEfecto(this, objetivo); 
        } else {
            this.setVida(this.vida - 1); 
            System.out.println(this.nombre + " intentó lanzar un conjuro desconocido y falló. Perdió 1 de vida.");
        }
    }

    

   
}

 

