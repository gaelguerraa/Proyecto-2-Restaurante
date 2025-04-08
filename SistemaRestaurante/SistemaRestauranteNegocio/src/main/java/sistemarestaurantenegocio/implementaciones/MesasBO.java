
package sistemarestaurantenegocio.implementaciones;

import sistemarestaurantenegocio.IMesasBO;
import sistemarestaurantepersistencia.interfaces.IMesasDAO;


public class MesasBO implements IMesasBO {
    private IMesasDAO mesasDAO;

    public MesasBO(IMesasDAO mesasDAO) {
        this.mesasDAO = mesasDAO;
    }
    
    /**
     * Metodo que hace una insercion masiva de 20 mesas con capacidades aleatorias.
     */
    @Override
    public void cargarMesas() {
        this.mesasDAO.cargarMesas();
    }
    
}
