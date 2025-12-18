package modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

    public Dragon(){}

    
    public Dragon(String nombre, int intensidadFuego, int resistencia) {
        this.nombre = nombre;
        this.intensidadFuego = intensidadFuego;
        this.resistencia = resistencia;
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



    public void exhalar(Monstruo m ){
       int danio= this.intensidadFuego;
       m.setVida(m.getVida()-danio);
    }





    public String getNombre() {
        return nombre;
    }





    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    


}
