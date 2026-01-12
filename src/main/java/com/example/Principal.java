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
        JuegoControler juegoControler = null;

        try {
            Mago ma1;
            Mago ma2;
            Mago ma3;
            Mago ma4;

            int numMagos = leerEntero(sc, "Seleccione cuantos magos quiere en su partida mínimo 2, máximo 4.");
            if (numMagos == 2) {
                ma1 = crearMago(sc);
                ma2 = crearMago(sc);
                Monstruo monstruoJefe = crearMonstruo(sc);
                Dragon d1 = crearDragon(sc);
                Bosque bo = crearBosque(sc, monstruoJefe);
                d1.setBosque(bo);
                juegoControler= new JuegoControler();
                juegoControler.guardarEntidades(monstruoJefe, ma1, ma2, bo, d1); 
                juegoControler.iniciarBatalla(ma1, ma2, monstruoJefe, bo, d1);


            } else if (numMagos == 3) {
                ma1 = crearMago(sc);
                ma2 = crearMago(sc);
                ma3 = crearMago(sc);
            } else if (numMagos == 4) {
                ma1 = crearMago(sc);
                ma2 = crearMago(sc);
                ma3 = crearMago(sc);
            } else {
                System.out.println("Entrada inválida");
            }

           // Monstruo mo1 = crearMonstruo(sc);
            //Dragon d1 = crearDragon(sc);
            //Bosque bo = crearBosque(sc, mo1);

            //d1.setBosque(bo);

            

            System.out.println("\n--- PRUEBA DE GESTIÓN (CRUD) ---");
            juegoControler.listarMagos();

            System.out.println("Introduce el ID del mago que quieres buscar:");
            long idBusqueda = Long.parseLong(sc.nextLine());
            Mago encontrado = juegoControler.obtenerMago(idBusqueda);

            if (encontrado != null) {
                System.out.println("Se ha encontrado a: " + encontrado.getNombre());
            }

        } catch (Exception e) {
            System.out.println("Error al ejecutar el programa");
        } finally {
            if (juegoControler != null) {
                juegoControler.cerrarRecursos();
                ;
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
        List<Hechizo> conjuros = new ArrayList<>();

        System.out.println("Introduzca al menos 2 hechizos para el mago: 1.BolaFuego");
        System.out.println("2.Bola Nieve.");
        System.out.println("3.Rayo");
        System.out.println("4.Drenaje");
        System.out.println("5.Confirmar Hechizos.");
        int conjuro = 0;
        while (conjuro != 5) {
            conjuro = leerEntero(sc, "");
            switch (conjuro) {
                case 1:
                    BolaFuego b1 = new BolaFuego();
                    conjuros.add(b1);
                    break;
                case 2:
                    BolaNieve bn = new BolaNieve();
                    conjuros.add(bn);
                    break;
                case 3:
                    Rayo ra = new Rayo();
                    conjuros.add(ra);
                    break;
                case 4:
                    Drenaje dr = new Drenaje();
                    conjuros.add(dr);
                    break;
                case 5:
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
    private static void mostrarResultado(Mago mago1,Mago mago2, Monstruo jefe, Dragon dragon) {
        System.out.println("\n---RESULTADO FINAL DE LA BATALLA---");

        int vidaMago1 = Math.max(0, mago1.getVida());
        int vidaMago2= Math.max(0,mago2.getVida());
        int vidaMonstruo = Math.max(0, jefe.getVida());

        System.out.println("Mago " + mago1.getNombre() + " Vida Final: " + vidaMago1);
        System.out.println("Monstruo " + jefe.getNombre() + " Vida Final: " + vidaMonstruo);

        System.out.println("----------------------------------------");

        if (vidaMago1 > 0 || vidaMago2 >0) {

            System.out.println("El Mago " + mago1.getNombre() + "ha vencido al Monstruo Jefe");
        } else if (vidaMonstruo > 0) {

            System.out.println("El Monstruo Jefe " + jefe.getNombre() + " ha defendido el bosque");
        } else {

            System.out.println("Empate, Ambos combatientes han caído a cero.");
        }
    }

}
