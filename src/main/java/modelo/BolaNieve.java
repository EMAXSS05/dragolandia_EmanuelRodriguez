package modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "id")
class BolaNieve extends Hechizo {

    @Override
    public void aplicarEfecto(Mago lanzador, Monstruo objetivo) {
        objetivo.setVida(0); 
        System.out.println("Bola de Nieve: " + objetivo.getNombre() + " ha sido congelado. Vida = 0.");
    }



    
}