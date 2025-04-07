package sistemarestaurantenegocio.implementaciones;

import java.time.LocalDate;
import java.util.List;
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

    @Override
    public List<NuevaComandaDTO> consultarComandas() {
        return comandasDAO.consultarComandas();
    }
    
    @Override
    public List<NuevaComandaDTO> consultarComandasPorRangoFechas(LocalDate fechaInicio, LocalDate fechaFin) {
        return comandasDAO.consultarComandasPorRangoFechas(fechaInicio, fechaFin);
    }
    
}
