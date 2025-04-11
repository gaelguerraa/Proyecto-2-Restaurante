package sistemarestaurantenegocio;

import java.time.LocalDate;
import java.util.List;
import sistemarestaurantedominio.Comanda;
import sistemarestaurantedominio.DetallesComanda;
import sistemarestaurantedominio.EstadoComanda;
import sistemarestaurantedominio.Mesa;
import sistemarestaurantedominio.dtos.NuevaComandaDTO;
import sistemarestaurantedominio.dtos.ProductoComandaDTO;

public interface IComandasBO {
    public abstract Comanda registrarComanda(NuevaComandaDTO nuevaComanda);    
    public abstract List<NuevaComandaDTO> consultarComandas();    
    public abstract List<NuevaComandaDTO> consultarComandasPorRangoFechas(LocalDate fechaInicio, LocalDate fechafin);
    public abstract Integer actualizarEstadoComanda(Comanda comanda, EstadoComanda estado);
    //talvez en mesasBO
    public abstract List<Mesa> obtenerMesas();    
    public abstract Mesa buscarMesaPorNumero(int numeroMesa);
    public abstract int obtenerConsecutivoDelDia();
    public abstract Comanda buscarPorFolio(String folio);
    public abstract List<ProductoComandaDTO> obtenerProductosComanda(String folio);
    public abstract List<ProductoComandaDTO> obtenerProductosDetalladosComanda(String folio);
}
