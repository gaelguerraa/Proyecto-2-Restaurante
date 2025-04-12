package sistemarestaurantenegocio.implementaciones;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import sistemarestaurantedominio.DetallesComanda;
import sistemarestaurantedominio.dtos.NuevoDetalleComandaDTO;
import sistemarestaurantenegocio.IDetallesComandasBO;
import sistemarestaurantenegocio.excepciones.NegocioException;
import sistemarestaurantepersistencia.exception.PersistenciaException;
import sistemarestaurantepersistencia.interfaces.IDetallesComandasDAO;

public class DetallesComandasBO implements IDetallesComandasBO {

    private IDetallesComandasDAO detallesComandasDAO;
    private final Float CERO = 0.0f;
    private final Float LIMITE_MAX = 10000.0f;

    /**
     * Constructor de la clase DetallesComandasBO.
     *
     * @param detallesComandasDAO DAO para interactuar con la persistencia de detalles de comanda.
     */
    public DetallesComandasBO(IDetallesComandasDAO detallesComandasDAO) {
        this.detallesComandasDAO = detallesComandasDAO;
    }

    /**
     * Guarda un nuevo detalle de comanda en el sistema.
     *
     * @param detalleComanda DTO con la información del nuevo detalle de comanda.
     * @return Detalle de comanda registrado.
     * @throws NegocioException si el detalle no cumple con las validaciones de negocio.
     */
    @Override
    public DetallesComanda guardarDetalleComanda(NuevoDetalleComandaDTO detalleComanda) throws NegocioException {
        if (detalleComanda.getProduto() == null) {
            throw new NegocioException("El producto no puede ser nulo.");
        }
        if (detalleComanda.getCantidad() == null || detalleComanda.getCantidad() <= CERO) {
            throw new NegocioException("La cantidad no puede ser nula o menor a 0.");
        }
        if (detalleComanda.getCantidad() > LIMITE_MAX) {
            throw new NegocioException("La cantidad no puede ser mayor a 10,000.");
        }
        if (detalleComanda.getPrecioActual() == null || detalleComanda.getPrecioActual() <= CERO) {
            throw new NegocioException("El precio no puede ser nulo o menor a 0.");
        }

        return detallesComandasDAO.guardarDetalleComanda(detalleComanda);
    }

    /**
     * Obtiene los detalles de una comanda según su ID.
     *
     * @param id ID de la comanda.
     * @return Lista de detalles asociados a la comanda.
     * @throws NegocioException si ocurre un error al obtener los detalles.
     */
    @Override
    public List<DetallesComanda> obtenerDetallesComanda(Long id) throws NegocioException {
        try {
            return detallesComandasDAO.obtenerDetallesComanda(id);
        } catch (PersistenciaException ex) {
            throw new NegocioException("No se pudieron obtener los detalles de la comanda!");
        }
    }

    /**
     * Elimina todos los detalles asociados a una comanda por su ID.
     *
     * @param id ID de la comanda.
     * @throws NegocioException si ocurre un error al intentar eliminar los detalles.
     */
    @Override
    public void eliminarDetallesComanda(Long id) throws NegocioException {
        try {
            detallesComandasDAO.eliminarDetallesPorComanda(id);
        } catch (PersistenciaException ex) {
            throw new NegocioException("No se pudieron eliminar los detalles de la comanda!");
        }
    }
}
