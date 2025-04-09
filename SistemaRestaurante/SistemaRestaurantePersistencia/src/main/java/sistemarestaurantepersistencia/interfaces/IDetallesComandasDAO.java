/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package sistemarestaurantepersistencia.interfaces;

import sistemarestaurantedominio.DetallesComanda;
import sistemarestaurantedominio.dtos.NuevoDetalleComandaDTO;

/**
 *
 * @author gael_
 */
public interface IDetallesComandasDAO {
    public abstract DetallesComanda guardarDetalleComanda(NuevoDetalleComandaDTO detalleComanda);
}
