package sistemarestaurantenegocio;

import java.time.LocalDate;
import java.util.List;
import sistemarestaurantedominio.Comanda;
import sistemarestaurantedominio.Mesa;
import sistemarestaurantedominio.dtos.NuevaComandaDTO;

public interface IComandasBO {
    public abstract Comanda registrarComanda(NuevaComandaDTO nuevaComanda);
    
    public abstract List<NuevaComandaDTO> consultarComandas();
    
    public abstract List<NuevaComandaDTO> consultarComandasPorRangoFechas(LocalDate fechaInicio, LocalDate fechafin);
    
    public abstract List<Mesa> obtenerMesas();
    
    public abstract Mesa buscarMesaPorNumero(int numeroMesa);
    
    public abstract int obtenerConsecutivoDelDia();
}
