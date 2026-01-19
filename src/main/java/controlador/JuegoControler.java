package controlador;

import java.util.ArrayList;
import java.util.List;
import modelo.*;

public class JuegoControler {

    private MagoControlador magoControlador;
    private MonstruoControlador monstruoControlador;
    private BosqueControlador bosqueControlador;
    private DragonControlador dragonControlador;

    public JuegoControler() {
        this.monstruoControlador = new MonstruoControlador();
        this.magoControlador = new MagoControlador();
        this.bosqueControlador = new BosqueControlador();
        this.dragonControlador = new DragonControlador();
    }

    /**
     * Método que guarda todas las entidades 
     * @param monstruoJefe
     * @param ma1
     * @param ma2
     * @param bo
     * @param dragon
     */
public void guardarEntidades(List<Mago> magos, Monstruo monstruoJefe, Bosque bo, Dragon dragon) {
    try {
        bosqueControlador.guardarBosque(bo);
        monstruoControlador.guardarMonstruo(monstruoJefe);
        
       
        for (Mago m : magos) {
            magoControlador.guardarMago(m);
        }
        
        dragonControlador.guardarDragon(dragon);
    } catch (Exception e) {
        System.out.println("Error al guardar las entidades: " + e.getMessage());
    }
}

    /**
     * Metodo que inicia la batalla
     * @param magos
     * @param jefe
     * @param bo
     * @param dragon
     */
    public void iniciarBatallaReal(List<Mago> magos, Monstruo jefe, Bosque bo, Dragon dragon) {
        List<Monstruo> monstruosVivos = new ArrayList<>();
        monstruosVivos.add(jefe);

        Monstruo m2 = new Monstruo("Orco Gruñón", 40, 10, Tipos.OGRO);
        Monstruo m3 = new Monstruo("Espectro Errante", 30, 15, Tipos.ESPECTRO);
        monstruoControlador.guardarMonstruo(m2);
        monstruoControlador.guardarMonstruo(m3);
        monstruosVivos.add(m2);
        monstruosVivos.add(m3);

    
        List<Hechizo> conjurosDelMundo = List.of(new BolaFuego("Bola de Fuego"), new Rayo("Rayo"), new Drenaje("Drenaje"));

        int ronda = 1;
      
        while (!magos.isEmpty() && !monstruosVivos.isEmpty()) {
            System.out.println("\n--- RONDA " + ronda + " ---");

            
            for (Mago mago : new ArrayList<>(magos)) {
                for (Hechizo hGlobal : conjurosDelMundo) {
                    if (mago.getConjuros().contains(hGlobal)) { 
                        for (Monstruo mon : monstruosVivos) {
                            hGlobal.aplicarEfecto(mago, mon);
                        }
                    } else {
                        
                        mago.setVida(mago.getVida() - 1);
                    }
                }
            }

            for (Monstruo mon : monstruosVivos) {
                if (!magos.isEmpty()) {
                    Mago objetivo = magos.get(0);
                    mon.atacar(objetivo);
                }
            }

           
            if (bo.getMonstruoJefe() != null) {
                dragon.exhalar(bo.getMonstruoJefe());
            }

            
            actualizarEstadoPostTurno(magos, monstruosVivos, bo);

            sincronizarBD(magos, monstruosVivos, bo, dragon);
            
            ronda++;
        }
        System.out.println("\n--- BATALLA FINALIZADA ---");
    }

   /**
    * método que actualiza el estado de las entidades dependiendo de du vida, y asigna un nuevo monstruo jefe al bosque.
    * @param magos
    * @param monstruos
    * @param bo
    */
    private void actualizarEstadoPostTurno(List<Mago> magos, List<Monstruo> monstruos, Bosque bo) {
        magos.removeIf(m -> m.getVida() <= 0);

        Monstruo jefeActual = bo.getMonstruoJefe();
        if (jefeActual != null && jefeActual.getVida() <= 0) {
            System.out.println("¡EL JEFE " + jefeActual.getNombre() + " HA CAÍDO!");
            monstruos.remove(jefeActual);
            if (!monstruos.isEmpty()) {
                bo.setMonstruoJefe(monstruos.get(0));
                System.out.println("Nuevo jefe asignado: " + bo.getMonstruoJefe().getNombre());
            } else {
                bo.setMonstruoJefe(null);
            }
        }
        monstruos.removeIf(m -> m.getVida() <= 0);
    }
 
    /**
     * Método que actualiza las entidades desde la base de datos.
     * @param magos
     * @param monstruos
     * @param bo
     * @param dragon
     */
    private void sincronizarBD(List<Mago> magos, List<Monstruo> monstruos, Bosque bo, Dragon dragon) {
        for (Mago m : magos) magoControlador.actualizarMago(m);
        for (Monstruo mon : monstruos) monstruoControlador.actualizarMonstruo(mon);
        bosqueControlador.actualizarBosque(bo);
        dragonControlador.actualizarDragon(dragon);
    }

    /**
     * Método que libera los recursos de la base de datos
     */
    public void cerrarRecursos() {
        util.HibernateUtil.close();
    }

    public void listarMagos() {
        List<Mago> magos = magoControlador.obtenerTodos();
        for (Mago m : magos) System.out.println("Mago: " + m.getNombre() + " HP: " + m.getVida());
    }

    public Mago obtenerMago(Long id) { return magoControlador.leerPorId(id); }
    
    public void borrarBosque(Long id) { bosqueControlador.eliminar(id); }

    public Bosque obtenerBosque(Long id) { return bosqueControlador.obtenerBosque(id); }

} 