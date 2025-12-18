package com.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import controlador.JuegoControler;
import modelo.Bosque;
import modelo.Dragon;
import modelo.Hechizo;
import modelo.Mago;
import modelo.Monstruo;
import modelo.Tipos;

public final class Principal {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        JuegoControler juegoControler = null;

        try {
            Mago ma1 = crearMago(sc);
            Monstruo mo1 = crearMonstruo(sc);
            Dragon d1= crearDragon(sc);
            Bosque bo = crearBosque(sc, mo1);

            juegoControler = new JuegoControler();
            juegoControler.guardarEntidades(mo1, ma1, bo,d1);

            juegoControler.iniciarBatalla(ma1, mo1,bo,d1);
            mostrarResultado(ma1, mo1);
            
        } catch (Exception e) {
            System.out.println("Error al ejecutar el programa");
        } finally {
            if (juegoControler!=null) {
                juegoControler.cerrarRecursos();
            }
            sc.close();
        }

    }

    static Mago crearMago(Scanner sc) {
        System.out.println("----Creación del mago----");
        System.out.println("Introduzca el nombre del mago: ");
        String nombre = sc.nextLine();
        int vida = leerEntero(sc, "Introduzca la vida del mago: ");
        int nivelMagia = leerEntero(sc, "Introduzca el nivel de magia: ");
        List<Hechizo> conjuros= new ArrayList<>();
        System.out.println("Introduzca 3 hechizos para el mago: ");
      

        Mago m1 = new Mago(nombre, vida, nivelMagia);
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
                tipo=Tipos.OGRO;
                break;

        }
        Monstruo m = new Monstruo(nombre, vida, fuerza, tipo);
        return m;

    }

    static Dragon crearDragon(Scanner sc){
        System.out.println("------Creación del Dragón------");
        System.out.println("Introduzca el nombre del dragon: ");
        String nombre= sc.nextLine();
        int intensidadFuego= leerEntero(sc, "Introduzca la intensidad de fuego del dragon" +nombre+": ");
        int resistencia= leerEntero(sc, "Introduzca la resistencia del fuego del dragon "+ nombre);
        Dragon dragon= new Dragon(nombre,intensidadFuego,resistencia);
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
     * @param mago
     * @param jefe
     */
    private static void mostrarResultado(Mago mago, Monstruo jefe) {
    System.out.println("\n---RESULTADO FINAL DE LA BATALLA---");
    
    int vidaMago = Math.max(0, mago.getVida());
    int vidaMonstruo = Math.max(0, jefe.getVida());
    
    System.out.println("Mago " + mago.getNombre() + " Vida Final: " + vidaMago);
    System.out.println("Monstruo " + jefe.getNombre() + " Vida Final: " + vidaMonstruo);
    
    System.out.println("----------------------------------------");

    if (vidaMago > 0) {
    
        System.out.println("El Mago " + mago.getNombre() + "ha vencido al Monstruo Jefe");
    } else if (vidaMonstruo > 0) {
        
        System.out.println("El Monstruo Jefe " + jefe.getNombre() + " ha defendido el bosque");
    } else {
        
        System.out.println("Empate, Ambos combatientes han caído a cero.");
    }
}

}
