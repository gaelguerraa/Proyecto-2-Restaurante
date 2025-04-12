package sistemarestaurantepersistencia.interfaces;

import java.time.LocalDate;
import java.util.List;
import javax.persistence.PersistenceException;
import sistemarestaurantedominio.Comanda;
import sistemarestaurantedominio.EstadoComanda;
import sistemarestaurantedominio.Mesa;
import sistemarestaurantedominio.dtos.ActualizarComandaDTO;
import sistemarestaurantedominio.dtos.NuevaComandaDTO;
import sistemarestaurantedominio.dtos.ProductoComandaDTO;
import sistemarestaurantepersistencia.exception.PersistenciaException;

public interface IComandasDAO {
    public abstract Comanda guardarComanda(NuevaComandaDTO nuevaComanda);
    public abstract List<NuevaComandaDTO> consultarComandas();
    public abstract List<NuevaComandaDTO> consultarComandasPorRangoFechas(LocalDate fechaInicio, LocalDate fechaFin);
    public abstract Integer actualizarEstadoComanda(Comanda comanda, EstadoComanda estado);
    //van en mesasDAO
    public abstract List<Mesa> obtenerMesas();
    public abstract Mesa buscarMesaPorNumero(int numeroMesa);
    //si van aqui
    public abstract int obtenerConsecutivoDelDia();
    public abstract Comanda buscarPorFolio(String folio);
    public abstract List<ProductoComandaDTO> obtenerProductosComanda(String folioComanda);
    public abstract List<ProductoComandaDTO> obtenerProductosDetalladosComanda(String folioComanda);
    public abstract void actualizarComanda(ActualizarComandaDTO comandaDTO) throws PersistenceException;
    public abstract void actualizarTotalComanda(Long idComanda, Float nuevoTotal) throws PersistenciaException;
}
