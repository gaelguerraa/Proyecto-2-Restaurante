/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemarestaurantenegocio.implementaciones;

import sistemarestaurantedominio.DetallesComanda;
import sistemarestaurantedominio.dtos.NuevoDetalleComandaDTO;
import sistemarestaurantenegocio.IDetallesComandasBO;
import sistemarestaurantenegocio.excepciones.NegocioException;
import sistemarestaurantepersistencia.interfaces.IDetallesComandasDAO;

/**
 *
 * @author gael_
 */
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
    
}
