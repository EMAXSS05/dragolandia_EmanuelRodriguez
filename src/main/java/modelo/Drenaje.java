package modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;

/**
 * Clase Drenaje que hereda de Hechizo
 */
@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Drenaje extends Hechizo {
    public Drenaje() { 
         
    }

    /**
     * función que quita la vida al mounstruo según la mitad de su nivel de magia y se cura.
     */

    @Override
    public void aplicarEfecto(Mago lanzador, Monstruo objetivo) {
        int roboVida = lanzador.getNivelMagia() / 2; 
        objetivo.setVida(objetivo.getVida() - roboVida);
        lanzador.setVida(lanzador.getVida() + roboVida);
        
        System.out.println("Drenaje: " + lanzador.getNombre() + " roba vida " + roboVida +
                " a " + objetivo.getNombre() + "y se cura"+
                ". Vida restante de "+ objetivo.getNombre()+ ": " + objetivo.getVida());
    }
}