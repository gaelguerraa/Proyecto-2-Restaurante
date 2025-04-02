/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemarestaurantenegocio.implementaciones;

import sistemarestaurantedominio.IngredienteProducto;
import sistemarestaurantedominio.dtos.NuevoIngredienteProductoDTO;
import sistemarestaurantenegocio.IIngredientesProductosBO;
import sistemarestaurantenegocio.excepciones.NegocioException;
import sistemarestaurantepersistencia.DAOS_implementaciones.IngredientesProductosDAO;

/**
 *
 * @author gael_
 */
public class IngredientesProductosBO implements IIngredientesProductosBO {
    
    private IngredientesProductosDAO ingredientesProductosDAO;
    private final Float CERO = 0.0f;
    private final Float LIMITE = 1000.0f;
    
    public IngredientesProductosBO(IngredientesProductosDAO ingredientesProductosDAO){
        this.ingredientesProductosDAO=ingredientesProductosDAO;
    }

    @Override
    public IngredienteProducto registrarIngredienteProductoBO(NuevoIngredienteProductoDTO nuevoIngredienteProducto) throws NegocioException {
        if(nuevoIngredienteProducto.getCantidadIngrediente() == null){
            throw new NegocioException("La cantidad del ingrediente no puede ser nula.");
        }
        if(nuevoIngredienteProducto.getCantidadIngrediente() <= CERO){
            throw new NegocioException("La cantidad no puede ser cero o menor a cero.");
        }
        if(nuevoIngredienteProducto.getCantidadIngrediente() >= LIMITE){
            throw new NegocioException("La cantidad es muy alta.");
        }
        return this.ingredientesProductosDAO.registrarIngredienteProducto(nuevoIngredienteProducto);
    }
}
