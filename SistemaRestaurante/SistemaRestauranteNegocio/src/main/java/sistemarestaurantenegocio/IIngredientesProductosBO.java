/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package sistemarestaurantenegocio;

import sistemarestaurantedominio.IngredienteProducto;
import sistemarestaurantedominio.dtos.NuevoIngredienteProductoDTO;
import sistemarestaurantenegocio.excepciones.NegocioException;

/**
 *
 * @author gael_
 */
public interface IIngredientesProductosBO {
    
    
    public abstract IngredienteProducto registrarIngredienteProductoBO(NuevoIngredienteProductoDTO ingredienteProducto)throws NegocioException;
    
}
