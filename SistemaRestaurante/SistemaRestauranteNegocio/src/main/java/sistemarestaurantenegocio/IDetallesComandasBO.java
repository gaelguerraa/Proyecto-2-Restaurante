/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package sistemarestaurantenegocio;

import java.util.List;
import sistemarestaurantedominio.DetallesComanda;
import sistemarestaurantedominio.dtos.NuevoDetalleComandaDTO;
import sistemarestaurantenegocio.excepciones.NegocioException;

/**
 *
 * @author gael_
 */
public interface IDetallesComandasBO {
    public abstract DetallesComanda guardarDetalleComanda(NuevoDetalleComandaDTO detalleComanda) throws NegocioException;
    
    public abstract List<DetallesComanda> obtenerDetallesComanda(Long id) throws NegocioException;
    
    public abstract void eliminarDetallesComanda(Long id) throws NegocioException;
    
}
