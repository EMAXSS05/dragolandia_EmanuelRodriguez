package modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class BolaFuego extends Hechizo {
    private final double factorDanio = 1.5;

    @Override
    public void aplicarEfecto(Mago lanzador, Monstruo objetivo) {
        int danio = (int) (lanzador.getNivelMagia() * factorDanio);
        objetivo.setVida(objetivo.getVida() - danio);

        System.out.println("Bola de Fuego: " + lanzador.getNombre() + " inflige " + danio +
                " de daño a " + objetivo.getNombre() +
                ". Vida restante: " + objetivo.getVida());
    }
}