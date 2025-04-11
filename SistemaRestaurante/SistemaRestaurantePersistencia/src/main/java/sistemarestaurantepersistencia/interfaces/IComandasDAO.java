package sistemarestaurantepersistencia.interfaces;

import java.time.LocalDate;
import java.util.List;
import sistemarestaurantedominio.Comanda;
import sistemarestaurantedominio.Mesa;
import sistemarestaurantedominio.dtos.NuevaComandaDTO;
import sistemarestaurantedominio.dtos.ProductoComandaDTO;

public interface IComandasDAO {
    public abstract Comanda guardarComanda(NuevaComandaDTO nuevaComanda);
    public abstract List<NuevaComandaDTO> consultarComandas();
    public abstract List<NuevaComandaDTO> consultarComandasPorRangoFechas(LocalDate fechaInicio, LocalDate fechaFin);
    //van en mesasDAO
    public abstract List<Mesa> obtenerMesas();
    public abstract Mesa buscarMesaPorNumero(int numeroMesa);
    //si van aqui
    public abstract int obtenerConsecutivoDelDia();
    public abstract Comanda buscarPorFolio(String folio);
    public abstract List<ProductoComandaDTO> obtenerProductosComanda(String folioComanda);
    public abstract List<ProductoComandaDTO> obtenerProductosDetalladosComanda(String folioComanda);
    
}
