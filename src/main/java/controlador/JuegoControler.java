package controlador;


import java.net.Socket;
import java.util.List;

import com.example.Config;

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

public class JuegoControler {

    private MagoControlador magoControlador;
    private MonstruoControlador monstruoControlador;
    private BosqueControlador bosqueControlador;
    private DragonControlador dragonControlador;
    

    public JuegoControler(){
        this.monstruoControlador = new MonstruoControlador();
        this.magoControlador= new MagoControlador();
        this.bosqueControlador= new BosqueControlador();
        this.dragonControlador= new DragonControlador();
    }

    /**
     * 
     * @param mo es el monstruo
     * @param ma es el mago
     * @param bo es el bosque
     * @param dragon es el dragon
     */

    public void guardarEntidades(Monstruo monstruoJefe, Mago ma1,Mago ma2, Bosque bo, Dragon dragon ){
        try{
        monstruoControlador.guardarMonstruo(monstruoJefe);
        magoControlador.guardarMago(ma1);
        magoControlador.guardarMago(ma2);
        bosqueControlador.guardarBosque(bo);
        dragonControlador.guardarDragon(dragon);
        
        
        }
        catch(Exception e){
            System.out.println("Error al guardar las entidades" + e.getMessage());
        }

    }

    /**
     * Metodo que da inicio al juego en si
     * @param ma
     * @param mo
     * @param bo
     * @param dragon
     */

    public void iniciarBatalla(Mago ma1,Mago ma2,  Monstruo monstruoJefe, Bosque bo, Dragon dragon){
      System.out.println("Comienza la batalla en el bosque "+ bo.getNombre());
      System.out.println("Mago: "+ ma1.getNombre()+ " vida: "+ma1.getVida()+ "vs Monstruo jefe: "+monstruoJefe.getNombre()+"vida: "+ monstruoJefe.getVida()+" y sus monstritos" );
      System.out.println("----------------------------------------");

      int turno=1;

         Monstruo mo1 = new Monstruo("Roberto", 200, 30, Tipos.TROL);
         monstruoControlador.guardarMonstruo(mo1);
         Monstruo mo2= new Monstruo("Feo", 200,35, Tipos.OGRO );
         monstruoControlador.guardarMonstruo(mo2);
         Monstruo mo3= new Monstruo("Venom",200, 40, Tipos.ESPECTRO );
         monstruoControlador.guardarMonstruo(mo3);

     
         System.out.println("-----Turno "+turno+"----------");
         
         BolaFuego bolaFuego= new BolaFuego("bola de fuego");

         BolaNieve bolaNieve= new BolaNieve("bola de nieve");

         Drenaje drenaje= new Drenaje("drenaje");
         Rayo rayo= new Rayo("rayo");



         System.out.println("Mago" + ma1.getNombre()+" lanza "+ bolaFuego.getNombre()+" al monstruo "+mo1.getNombre());
         ma1.lanzarHechizo(mo1,bolaFuego);
         
         System.out.println("Mago "+ma2.getNombre()+ " lanza "+drenaje.getNombre()+" al monstruo "+mo2.getNombre());
         
         System.out.println("----Turno del monstuo jefe y sus monstritos ---");
         System.out.println("Monstruo jefe "+ monstruoJefe.getNombre()+" ataca al mago"+ ma1.getNombre());
         monstruoJefe.atacar(ma1);
         System.out.println("Monstrito "+ mo1.getNombre()+" ataca al mago "+ ma2.getNombre());
         mo1.atacar(ma2);
         System.out.println("Monstrito "+ mo2.getNombre()+"ataca al mago "+ma1.getNombre());
         mo2.atacar(ma1);
         System.out.println("Monstrito "+ mo3.getNombre()+"ataca al mago "+ma2.getNombre());
         mo3.atacar(ma1);

         System.out.println("-----Turno del dragón ------");

         System.out.println("Dragon ataca al monstruo jefe "+ monstruoJefe.getNombre());
         dragon.exhalar(monstruoJefe);

         System.out.println("------RESULTADO DE LA RONDA "+turno+"-------");
         System.out.println("HP del monstruo jefe: "+ monstruoJefe.getVida());
         System.out.println("HP del monstruo: "+ mo1.getVida());
         System.out.println("HP del dragón: "+ dragon.getResistencia());
         System.out.println("HP del mago "+ma1.getVida());
         System.out.println("HP del mago "+ma2.getVida());





        
         System.out.println("El mago"+ma1.getNombre()+  " ahora tiene "+ ma1.getVida());
         turno++;


      

      finalizarBatalla(ma1, ma2, monstruoJefe, mo1,mo2,mo3);

    }


   /*   public void iniciarBatalla(Mago ma, Monstruo mo, Bosque bo, Dragon dragon){
      System.out.println("Comienza la batalla en el bosque "+ bo.getNombre());
      System.out.println("Mago: "+ ma.getNombre()+ "vida: "+ma.getVida()+ "vs Monstruo: "+mo.getNombre()+"vida: "+ mo.getVida());
      System.out.println("----------------------------------------");

      int turno=1;

      while (ma.getVida() > 0 & mo.getVida() >0) {
         System.out.println("-----Turno "+turno+"----------");
         System.out.println("Mago" + ma.getNombre()+" lancha hechizo");
         ma.lanzarHechizo(mo);
         System.out.println("El monstruo ahora tiene "+mo.getVida());
         if (mo.getVida() <=0) {
            break;
         }
         System.out.println("----Turno del monstuo---");
         System.out.println("Monstruo ataca al mago");
         mo.atacar(ma);
         System.out.println("El mago ahora tiene "+ ma.getVida());
         turno++;
      }

      finalizarBatalla(ma, mo);

    }*/



    private void finalizarBatalla(Mago mago1,Mago mago2,Monstruo jefe, Monstruo m1, Monstruo m2, Monstruo m3) {
        magoControlador.actualizarMago(mago1);
        magoControlador.actualizarMago(mago2);
        monstruoControlador.actualizarMonstruo(jefe);
        monstruoControlador.actualizarMonstruo(m1);
        monstruoControlador.actualizarMonstruo(m2);
        monstruoControlador.actualizarMonstruo(m3);
        
    }

     private void finalizarBatalla(Mago mago, Monstruo jefe) {
        magoControlador.actualizarMago(mago);
        monstruoControlador.actualizarMonstruo(jefe);
        
    }




    
    public void cerrarRecursos() {
        util.HibernateUtil.close();
    }

    public void listarMagos(){
        List<Mago> magos = magoControlador.obtenerTodos();
        if (magos.isEmpty()) {
            System.out.println("No hay magos registrados");
        } else{
            for (Mago mago : magos) {
                System.out.println("Mago con id: "+ mago.getId()+ "Nombre: "+mago.getNombre()+" HP: "+mago.getVida());
            }
        }
    }

    public Mago obtenerMago(Long id){
       return magoControlador.leerPorId(id);
    }

    public void borrarMago(Long id){
        magoControlador.borrarMago(id);
        System.out.println("Mago con id: "+id +" ha sido eliminado");
    }

}
