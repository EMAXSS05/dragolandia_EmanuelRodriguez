package modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Dragones")
public class Dragon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private int intensidadFuego;
    private int resistencia;


    @ManyToOne // Varios dragones pueden vivir en el mismo bosque
    @JoinColumn(name = "bosque_id")
    private Bosque bosque;

    public Dragon() {
    }

    public Dragon(String nombre, int intensidadFuego, int resistencia) {
        this.nombre = nombre;
        // Validación básica:
        this.intensidadFuego = Math.max(0, intensidadFuego);
        this.resistencia = Math.max(0, resistencia);
    }

    // Método exigido por el enunciado
    public void exhalar(Monstruo m) {
        if (m != null) {
            int vidaActual = m.getVida();
            m.setVida(vidaActual - this.intensidadFuego);
            System.out.println("El dragón " + this.nombre + " exhala fuego sobre " +
                    m.getNombre() + " causando " + this.intensidadFuego + " de daño.");
        }
    }

    public Bosque getBosque() {
        return bosque;
    }

    public void setBosque(Bosque bosque) {
        this.bosque = bosque;
    }

    public Long getId() {
        return id;
    }



    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getIntensidadFuego() {
        return intensidadFuego;
    }

    public void setIntensidadFuego(int intensidadFuego) {
        this.intensidadFuego = intensidadFuego;
    }

    public int getResistencia() {
        return resistencia;
    }

    public void setResistencia(int resistencia) {
        this.resistencia = resistencia;
    }
    


}