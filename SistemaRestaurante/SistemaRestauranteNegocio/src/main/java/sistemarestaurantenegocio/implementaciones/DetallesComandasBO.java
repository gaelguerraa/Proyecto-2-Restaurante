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
    private Float CERO = 0.0f;
    private Float LIMITE_MAX = 10000.0f;

    public DetallesComandasBO(IDetallesComandasDAO detallesComandasDAO) {
        this.detallesComandasDAO = detallesComandasDAO;
    }
    
    
    
    @Override
    public DetallesComanda guardarDetalleComanda(NuevoDetalleComandaDTO detalleComanda) throws NegocioException {
         if (detalleComanda.getProduto() == null) {
            throw new NegocioException("El producto no puede ser nulo.");
         }
         if(detalleComanda.getCantidad() == null || detalleComanda.getCantidad() <= CERO){
            throw new NegocioException("La cantidad no puede ser nula o menor a 0.");
         }
         if(detalleComanda.getCantidad() > LIMITE_MAX){
            throw new NegocioException("La cantidad no puede ser mayor a 10,000."); 
         }
         if(detalleComanda.getPrecioActual() == null || detalleComanda.getPrecioActual() <= CERO){
            throw new NegocioException("El precio no puede ser nulo o menor a 0.");
         }
         return detallesComandasDAO.guardarDetalleComanda(detalleComanda);
    }

    @Override
    public List<DetallesComanda> obtenerDetallesComanda(Long id) throws NegocioException {
        try {
            return detallesComandasDAO.obtenerDetallesComanda(id);
        } catch (PersistenciaException ex) {
            throw new NegocioException("no se pudieron obtener los detalles de la comanda!");
        }
    }

    @Override
    public void eliminarDetallesComanda(Long id) throws NegocioException {
        try {
            detallesComandasDAO.eliminarDetallesPorComanda(id);
        } catch (PersistenciaException ex) {
            throw new NegocioException("no se pudieron eliminar los detalles de la comanda!");
        }
    }
    
}
