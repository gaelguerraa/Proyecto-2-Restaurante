package sistemarestaurantenegocio.implementaciones;

import sistemarestaurantedominio.Comanda;
import sistemarestaurantedominio.dtos.NuevaComandaDTO;
import sistemarestaurantenegocio.IComandasBO;
import sistemarestaurantepersistencia.interfaces.IComandasDAO;


public class ComandasBO implements IComandasBO {
    
    IComandasDAO comandasDAO;
    
    public ComandasBO(IComandasDAO comandasDAO) {
        this.comandasDAO = comandasDAO;
    }

    @Override
    public Comanda registrarComanda(NuevaComandaDTO nuevaComanda) {
        return comandasDAO.guardarComanda(nuevaComanda);
    }
    
}
