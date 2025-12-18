package modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name="id")
class Rayo extends Hechizo {
    private final int danio = 35; 
    @Override
    public void aplicarEfecto(Mago lanzador, Monstruo objetivo) {
        objetivo.setVida(objetivo.getVida() - danio);
        System.out.println("Rayo: " + objetivo.getNombre() + " recibe " + danio + " de daño. Vida restante: " + objetivo.getVida());
    }
}