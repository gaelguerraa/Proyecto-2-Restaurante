/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package sistemarestaurantepersistencia.interfaces;

import java.util.List;
import sistemarestaurantedominio.DetallesComanda;
import sistemarestaurantedominio.dtos.NuevoDetalleComandaDTO;
import sistemarestaurantepersistencia.exception.PersistenciaException;

/**
 *
 * @author gael_
 */
public interface IDetallesComandasDAO {
    public abstract DetallesComanda guardarDetalleComanda(NuevoDetalleComandaDTO detalleComanda);
    
    public abstract List<DetallesComanda> obtenerDetallesComanda(Long id) throws PersistenciaException;
    
    public abstract void eliminarDetallesPorComanda(Long idComanda) throws PersistenciaException;
}


