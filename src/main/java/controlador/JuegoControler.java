package controlador;


import java.util.List;

import com.example.Config;

import modelo.Bosque;
import modelo.BosqueModel;
import modelo.Dragon;
import modelo.DragonModel;
import modelo.Mago;
import modelo.MagoModel;
import modelo.Monstruo;
import modelo.MonstruoModel;

public class JuegoControler {

    private MagoModel magoModel;
    private MonstruoModel monstruoModel;
    private BosqueModel bosqueModel;
    private DragonModel dragonModel;
    

    public JuegoControler(){
        this.monstruoModel = new MonstruoModel();
        this.magoModel= new MagoModel();
        this.bosqueModel= new BosqueModel();
        this.dragonModel= new DragonModel();
    }

    /**
     * 
     * @param mo es el monstruo
     * @param ma es el mago
     * @param bo es el bosque
     * @param dragon es el dragon
     */

    public void guardarEntidades(Monstruo mo, Mago ma, Bosque bo, Dragon dragon ){
        try{
        monstruoModel.guardarMonstruo(mo);
        magoModel.guardarMago(ma);
        bosqueModel.guardarBosque(bo);
        dragonModel.guardarDragon(dragon);
        
        
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

    public void iniciarBatalla(Mago ma, Monstruo mo, Bosque bo, Dragon dragon){
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

    }


    private void finalizarBatalla(Mago mago, Monstruo jefe) {
        magoModel.actualizarMago(mago);
        monstruoModel.actualizarMonstruo(jefe);

        
    }
    
    public void cerrarRecursos() {
        util.HibernateUtil.close();
    }

    public void listarMagos(){
        List<Mago> magos = magoModel.obtenerTodos();
        if (magos.isEmpty()) {
            System.out.println("No hay magos registrados");
        } else{
            for (Mago mago : magos) {
                System.out.println("Mago con id: "+ mago.getId()+ "Nombre: "+mago.getNombre()+" HP: "+mago.getVida());
            }
        }
    }

    public Mago obtenerMago(Long id){
       return magoModel.leerPorId(id);
    }

    public void borrarMago(Long id){
        magoModel.borrarMago(id);
        System.out.println("Mago con id: "+id +" ha sido eliminado");
    }

}
