/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package sistemarestaurantenegocio;

import java.util.List;
import sistemarestaurantedominio.Ingrediente;
import sistemarestaurantedominio.IngredienteProducto;
import sistemarestaurantedominio.Producto;
import sistemarestaurantedominio.dtos.NuevoIngredienteProductoDTO;
import sistemarestaurantenegocio.excepciones.NegocioException;

/**
 *
 * @author gael_
 */
public interface IIngredientesProductosBO {
    
    
    public abstract void registrarIngredienteProductoBO(NuevoIngredienteProductoDTO nuevoIngredienteProducto) throws NegocioException;
    
    public abstract List<Producto> obtenerProductos();
    
    public List<Ingrediente> obtenerIngredientes();
    
}
