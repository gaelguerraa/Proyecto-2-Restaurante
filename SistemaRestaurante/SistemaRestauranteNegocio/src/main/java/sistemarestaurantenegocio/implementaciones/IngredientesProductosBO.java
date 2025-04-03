/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemarestaurantenegocio.implementaciones;

import java.util.List;
import sistemarestaurantedominio.Ingrediente;
import sistemarestaurantedominio.IngredienteProducto;
import sistemarestaurantedominio.Producto;
import sistemarestaurantedominio.dtos.NuevoIngredienteProductoDTO;
import sistemarestaurantenegocio.IIngredientesProductosBO;
import sistemarestaurantenegocio.excepciones.NegocioException;
import sistemarestaurantepersistencia.DAOS_implementaciones.IngredientesDAO;
import sistemarestaurantepersistencia.DAOS_implementaciones.IngredientesProductosDAO;
import sistemarestaurantepersistencia.DAOS_implementaciones.ProductosDAO;
import sistemarestaurantepersistencia.interfaces.IIngredientesProductosDAO;

/**
 *
 * @author gael_
 */
public class IngredientesProductosBO implements IIngredientesProductosBO {
    
    private IIngredientesProductosDAO ingredientesProductosDAO;
    private IngredientesDAO ingredientesDAO;
    private ProductosDAO productosDAO;

    
    private final Float CERO = 0.0f;
    private final Float LIMITE = 1000.0f;
    
    public IngredientesProductosBO(IIngredientesProductosDAO ingredientesProductosDAO,
                                    IngredientesDAO ingredientesDAO,
                                    ProductosDAO productosDAO){
        this.ingredientesProductosDAO = ingredientesProductosDAO;
        this.ingredientesDAO = ingredientesDAO;
        this.productosDAO = productosDAO;
    }

    @Override
    public void registrarIngredienteProductoBO(NuevoIngredienteProductoDTO nuevoIngredienteProducto) throws NegocioException {

        Producto producto = nuevoIngredienteProducto.getProducto();
        Ingrediente ingrediente = nuevoIngredienteProducto.getIngrediente();
        
        if (producto == null || ingrediente == null) {
            throw new NegocioException("Producto o Ingrediente inválido.");
        }
 
        ingredientesProductosDAO.registrarIngredienteProducto(nuevoIngredienteProducto);
    }
        
    @Override
    public List<Producto> obtenerProductos() {
        return productosDAO.obtenerProductos();
    }

    @Override
    public List<Ingrediente> obtenerIngredientes() {
        return ingredientesDAO.obtenerIngredientes();
    }
}
