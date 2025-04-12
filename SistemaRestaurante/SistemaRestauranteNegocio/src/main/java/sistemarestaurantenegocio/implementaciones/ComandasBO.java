package sistemarestaurantenegocio.implementaciones;

import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.PersistenceException;
import sistemarestaurantedominio.Comanda;
import sistemarestaurantedominio.DetallesComanda;
import sistemarestaurantedominio.EstadoComanda;
import sistemarestaurantedominio.Mesa;
import sistemarestaurantedominio.dtos.ActualizarComandaDTO;
import sistemarestaurantedominio.dtos.NuevaComandaDTO;
import sistemarestaurantedominio.dtos.ProductoComandaDTO;
import sistemarestaurantenegocio.IComandasBO;
import sistemarestaurantenegocio.excepciones.NegocioException;
import sistemarestaurantepersistencia.exception.PersistenciaException;
import sistemarestaurantepersistencia.interfaces.IComandasDAO;

/**
 * Gestiona el registro, consulta, actualización y operaciones relacionadas 
 * con las comandas
 */
public class ComandasBO implements IComandasBO {
    
    IComandasDAO comandasDAO;

    /**
     * Constructor de la clase ComandasBO.
     * 
     * @param comandasDAO DAO de comandas para acceder a los datos persistentes.
     */
    public ComandasBO(IComandasDAO comandasDAO) {
        this.comandasDAO = comandasDAO;
    }

    /**
     * Registra una nueva comanda.
     * 
     * @param nuevaComanda DTO con los datos de la nueva comanda.
     * @return Comanda registrada.
     */
    @Override
    public Comanda registrarComanda(NuevaComandaDTO nuevaComanda) {
        return comandasDAO.guardarComanda(nuevaComanda);
    }

    /**
     * Consulta todas las comandas registradas.
     * 
     * @return Lista de comandas.
     */
    @Override
    public List<NuevaComandaDTO> consultarComandas() {
        return comandasDAO.consultarComandas();
    }

    /**
     * Consulta comandas dentro de un rango de fechas.
     * 
     * @param fechaInicio Fecha de inicio del rango.
     * @param fechaFin Fecha de fin del rango.
     * @return Lista de comandas en el rango indicado.
     */
    @Override
    public List<NuevaComandaDTO> consultarComandasPorRangoFechas(LocalDate fechaInicio, LocalDate fechaFin) {
        return comandasDAO.consultarComandasPorRangoFechas(fechaInicio, fechaFin);
    }

    /**
     * Obtiene la lista de mesas registradas.
     * 
     * @return Lista de mesas disponibles.
     */
    @Override
    public List<Mesa> obtenerMesas() {
        return comandasDAO.obtenerMesas();
    }

    /**
     * Busca una mesa por su número.
     * 
     * @param numeroMesa Número de la mesa.
     * @return Objeto Mesa encontrado, o null si no existe.
     */
    @Override
    public Mesa buscarMesaPorNumero(int numeroMesa) {
        return comandasDAO.buscarMesaPorNumero(numeroMesa);
    }

    /**
     * Obtiene el número de comanda consecutivo del día.
     * 
     * @return Número consecutivo de comanda.
     */
    @Override
    public int obtenerConsecutivoDelDia() {
        return comandasDAO.obtenerConsecutivoDelDia();
    }

    /**
     * Busca una comanda por su folio.
     * 
     * @param folio Folio único de la comanda.
     * @return Comanda correspondiente al folio.
     */
    @Override
    public Comanda buscarPorFolio(String folio) {
        return comandasDAO.buscarPorFolio(folio);
    }

    /**
     * Obtiene los productos asociados a una comanda.
     * 
     * @param folio Folio de la comanda.
     * @return Lista de productos de la comanda.
     */
    @Override
    public List<ProductoComandaDTO> obtenerProductosComanda(String folio) {
        return comandasDAO.obtenerProductosComanda(folio);
    }

    /**
     * Obtiene los productos detallados de una comanda.
     * 
     * @param folio Folio de la comanda.
     * @return Lista de productos con detalles adicionales.
     */
    @Override
    public List<ProductoComandaDTO> obtenerProductosDetalladosComanda(String folio) {
        return comandasDAO.obtenerProductosDetalladosComanda(folio);
    }

    /**
     * Actualiza el estado de una comanda.
     * 
     * @param comanda Objeto comanda a actualizar.
     * @param estado Nuevo estado de la comanda.
     * @return Número de registros afectados.
     */
    @Override
    public Integer actualizarEstadoComanda(Comanda comanda, EstadoComanda estado) {
        return comandasDAO.actualizarEstadoComanda(comanda, estado);
    }

    /**
     * Actualiza la información de una comanda.
     * 
     * @param comandaDTO DTO con los datos a actualizar.
     * @throws NegocioException si ocurre un error en la actualización.
     */
    @Override
    public void actualizarComanda(ActualizarComandaDTO comandaDTO) throws NegocioException {
        try {
            comandasDAO.actualizarComanda(comandaDTO);
        } catch (PersistenceException ex) {
            throw new NegocioException("No fue posible actualizar la comanda" + ex.getMessage());
        }
    }

    /**
     * Actualiza el total de una comanda.
     * 
     * @param idComanda ID de la comanda.
     * @param nuevoTotal Nuevo total a establecer.
     * @throws NegocioException si ocurre un error en la operación.
     */
    @Override
    public void actualizarTotalComanda(Long idComanda, Float nuevoTotal) throws NegocioException {
        try {
            comandasDAO.actualizarTotalComanda(idComanda, nuevoTotal);
        } catch (PersistenciaException ex) {
            throw new NegocioException("No fue posible actualizar el precio" + ex.getMessage());
        }
    }
}
