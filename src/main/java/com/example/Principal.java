package com.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import controlador.JuegoControler;
import modelo.BolaFuego;
import modelo.BolaNieve;
import modelo.Bosque;
import modelo.Dragon;
import modelo.Drenaje;
import modelo.Hechizo;
import modelo.Mago;
import modelo.Monstruo;
import modelo.Rayo;
import modelo.Tipos;

public final class Principal {

    public static void main(String[] args) {
       Scanner sc = new Scanner(System.in);
        JuegoControler juegoControler = new JuegoControler();

        try {
            List<Mago> listaMagosPartida = new ArrayList<>();
            int numMagos = leerEntero(sc, "Seleccione cuántos magos quiere (mínimo 2, máximo 4):");

            if (numMagos >= 2 && numMagos <= 4) {
    for (int i = 0; i < numMagos; i++) {
        System.out.println("\nDatos para el Mago " + (i + 1) + ":");
        listaMagosPartida.add(crearMago(sc));
    }

    Monstruo monstruoJefe = crearMonstruo(sc);
    Dragon d1 = crearDragon(sc);
    Bosque bo = crearBosque(sc, monstruoJefe);
    d1.setBosque(bo);

 
    juegoControler.guardarEntidades(listaMagosPartida, monstruoJefe, bo, d1);

    
    juegoControler.iniciarBatallaReal(listaMagosPartida, monstruoJefe, bo, d1);

  
    mostrarResultado(listaMagosPartida, monstruoJefe);

} else {
    System.out.println("Cantidad de magos no permitida.");
}

        } catch (Exception e) {
            System.out.println("Error en el programa: " + e.getMessage());
        } finally {
            juegoControler.cerrarRecursos();
            sc.close();
        }
    
    }

    static Mago crearMago(Scanner sc) {
        System.out.println("----Creación del mago----");
        System.out.println("Introduzca el nombre del mago: ");
        String nombre = sc.nextLine();
        int vida = leerEntero(sc, "Introduzca la vida del mago: ");
        int nivelMagia = leerEntero(sc, "Introduzca el nivel de magia: ");
        List<Hechizo> conjuros = new ArrayList<>();

        System.out.println("Introduzca al menos 2 hechizos para el mago: 1.BolaFuego");
        System.out.println("2.Bola Nieve.");
        System.out.println("3.Rayo");
        System.out.println("4.Drenaje");
        System.out.println("5.Confirmar Hechizos.");
        int conjuro = 0;
        int contador=0;
        while (conjuro != 5) {
            
            conjuro = leerEntero(sc, "");
            switch (conjuro) {
                
                case 1:
                    BolaFuego b1 = new BolaFuego();
                    conjuros.add(b1);
                    contador++;
                    break;
                case 2:
                    BolaNieve bn = new BolaNieve();
                    conjuros.add(bn);
                    contador++;
                    break;
                case 3:
                    Rayo ra = new Rayo();
                    conjuros.add(ra);
                    contador++;
                    break;
                case 4:
                    Drenaje dr = new Drenaje();
                    conjuros.add(dr);
                    contador++;
                    break;
                case 5:
                    System.out.println("Has asignado "+ contador+ " hechizos al mago");
                    break;
                default:
                    break;
            }

        }

        Mago m1 = new Mago(nombre, vida, nivelMagia, conjuros);
        return m1;
    }

    static Bosque crearBosque(Scanner sc, Monstruo mo) {
        System.out.println("-----Creación del bosque----- ");
        System.out.println("Introduzca el nombre del bosque: ");
        String nombre = sc.nextLine();
        int nivelPeligro = leerEntero(sc, "Introduzca el nivel del peligro del bosque: ");
        System.out.println("Introduzca el monstruo jefe del bosque ");

        Bosque b1 = new Bosque(nombre, nivelPeligro, mo);

        System.out.println("Monstuo: " + mo.getNombre() + " asignado al bosque " + b1.getNombre());
        return b1;
    }

    static Monstruo crearMonstruo(Scanner sc) {
        System.out.println("-----Creación del Monstruo-----");
        System.out.println("Introduzca el nombre del monstruo: ");
        String nombre = sc.nextLine();
        int vida = leerEntero(sc, "Introduzca la vida del monstruo: ");
        int fuerza = leerEntero(sc, "Introduza la fuerza del monstruo: ");
        System.out.println("Seleccione el tipo del monstruo: ");
        int opcion = leerEntero(sc, "1.Ogro\n2.Trol\n3.Espectro");
        Tipos tipo = null;
        switch (opcion) {
            case 1:
                tipo = Tipos.OGRO;
                break;
            case 2:
                tipo = Tipos.TROL;
                break;
            case 3:
                tipo = Tipos.ESPECTRO;
                break;
            default:
                System.out.println("selección inválida, tipo Ogro asignado por defecto");
                tipo = Tipos.OGRO;
                break;

        }
        Monstruo m = new Monstruo(nombre, vida, fuerza, tipo);
        return m;

    }

    static Dragon crearDragon(Scanner sc) {
        System.out.println("------Creación del Dragón------");
        System.out.println("Introduzca el nombre del dragon: ");
        String nombre = sc.nextLine();
        int intensidadFuego = leerEntero(sc, "Introduzca la intensidad de fuego del dragon " + nombre + ": ");
        int resistencia = leerEntero(sc, "Introduzca la resistencia del dragon " + nombre);
        Dragon dragon = new Dragon(nombre, intensidadFuego, resistencia);
        return dragon;

    }

    static int leerEntero(Scanner sc, String mensaje) {
        boolean lecturaLeida = false;
        int valorLeido = 0;
        do {
            System.out.println(mensaje);
            try {
                String linea = sc.nextLine();
                valorLeido = Integer.parseInt(linea);
                lecturaLeida = true;

            } catch (NumberFormatException e) {
                System.out.println("Entrada incorrecta, introduzca un numero entero");
            }
        } while (!lecturaLeida);

        return valorLeido;
    }

    /**
     * Funcion que muestra el resultado de la batalla
     * 
     * @param mago
     * @param jefe
     */
    private static void mostrarResultado(List<Mago> magos, Monstruo jefe) {
    System.out.println("\n--- RESULTADO FINAL ---");
    
    boolean algunMagoVivo = false;
    for (Mago m : magos) {
        System.out.println("Mago " + m.getNombre() + " - HP Final: " + Math.max(0, m.getVida()));
        if (m.getVida() > 0) algunMagoVivo = true;
    }

    System.out.println("Jefe " + jefe.getNombre() + " - HP Final: " + Math.max(0, jefe.getVida()));

    if (algunMagoVivo) {
        System.out.println("¡Los Magos han purificado el bosque!");
    } else if (jefe.getVida() > 0) {
        System.out.println("El Monstruo Jefe ha reclamado el bosque.");
    } else {
        System.out.println("No hubo supervivientes en la batalla.");
    }
}

}
