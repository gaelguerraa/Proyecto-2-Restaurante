/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemarestaurantenegocio.implementaciones;

import java.util.List;
import sistemarestaurantedominio.Ingrediente;
import sistemarestaurantedominio.Producto;
import sistemarestaurantedominio.dtos.NuevoIngredienteProductoDTO;
import sistemarestaurantenegocio.IIngredientesProductosBO;
import sistemarestaurantenegocio.excepciones.NegocioException;
import sistemarestaurantepersistencia.DAOS_implementaciones.IngredientesDAO;
import sistemarestaurantepersistencia.DAOS_implementaciones.ProductosDAO;
import sistemarestaurantepersistencia.interfaces.IIngredientesProductosDAO;

/**
 *
 * @author gael_
 */
/**
 * Clase de lógica de negocio para la gestión de la relación entre productos e ingredientes.
 * Implementa la interfaz {@link IIngredientesProductosBO}.
 * Proporciona métodos para registrar la relación entre un ingrediente y un producto,
 * así como para obtener la lista de productos e ingredientes disponibles.
 */
public class IngredientesProductosBO implements IIngredientesProductosBO {
    
    private IIngredientesProductosDAO ingredientesProductosDAO;
    private IngredientesDAO ingredientesDAO;
    private ProductosDAO productosDAO;

    
    private final Float CERO = 0.0f;
    private final Float LIMITE = 1000.0f;
    
     /**
     * Constructor para inicializar las dependencias de la clase.
     *
     * @param ingredientesProductosDAO  Instancia de la interfaz IIngredientesProductosDAO para operaciones DAO.
     * @param ingredientesDAO           Instancia de la clase IngredientesDAO para acceder a ingredientes.
     * @param productosDAO              Instancia de la clase ProductosDAO para acceder a productos.
     */
    public IngredientesProductosBO(IIngredientesProductosDAO ingredientesProductosDAO,
                                    IngredientesDAO ingredientesDAO,
                                    ProductosDAO productosDAO){
        this.ingredientesProductosDAO = ingredientesProductosDAO;
        this.ingredientesDAO = ingredientesDAO;
        this.productosDAO = productosDAO;
    }

     /**
     * Registra una nueva relación entre un producto y un ingrediente.
     * Valida que el producto, el ingrediente y la cantidad sean correctos antes de registrar la relación.
     *
     * @param nuevoIngredienteProducto DTO que contiene los datos del nuevo ingrediente y producto a registrar.
     * @throws NegocioException Si los datos proporcionados son inválidos.
     */
    @Override
    public void registrarIngredienteProductoBO(NuevoIngredienteProductoDTO nuevoIngredienteProducto) throws NegocioException {

        Producto producto = nuevoIngredienteProducto.getProducto();
        Ingrediente ingrediente = nuevoIngredienteProducto.getIngrediente();
        
        if (producto == null || ingrediente == null) {
            throw new NegocioException("Producto o Ingrediente inválido.");
        }
        if(nuevoIngredienteProducto.getCantidadIngrediente() <= CERO ){
            throw new NegocioException("La cantidad de producto no puede ser cero o menos.");
        }
 
        ingredientesProductosDAO.registrarIngredienteProducto(nuevoIngredienteProducto);
    }
        
    /**
     * Obtiene una lista de todos los productos registrados.
     *
     * @return Lista de productos.
     */
    @Override
    public List<Producto> obtenerProductos() {
        return productosDAO.obtenerProductos();
    }

     /**
     * Obtiene una lista de todos los ingredientes registrados.
     *
     * @return Lista de ingredientes.
     */
    @Override
    public List<Ingrediente> obtenerIngredientes() {
        return ingredientesDAO.obtenerIngredientes();
    }
}
