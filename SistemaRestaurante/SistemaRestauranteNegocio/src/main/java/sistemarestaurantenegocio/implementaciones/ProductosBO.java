/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemarestaurantenegocio.implementaciones;

import java.util.List;
import sistemarestaurantedominio.Producto;
import sistemarestaurantedominio.TipoProducto;
import sistemarestaurantedominio.dtos.NuevoProductoDTO;
import sistemarestaurantenegocio.IProductosBO;
import sistemarestaurantenegocio.excepciones.NegocioException;
import sistemarestaurantepersistencia.interfaces.IProductosDAO;

/**
 * La clase {@code ProductosBO} maneja la lógica de negocio relacionada con la gestión de productos.
 * Implementa la interfaz {@code IProductosBO} y proporciona métodos para registrar y consultar productos.
 * 
 * <p>Esta clase se encarga de la validación de los datos ingresados antes de guardarlos en la base de datos,
 * incluyendo la validación del nombre del producto y su precio. Si los datos no son válidos, se lanza una
 * excepción {@code NegocioException}.</p>
 * 
 * @author gael_
 */
public class ProductosBO implements IProductosBO {
    
    private IProductosDAO productosDAO;
    private Float CERO = 0.0f;
    private Float PRECIO_MAXIMO_EJEMPLO = 99999.9f;

      /**
     * Constructor de la clase {@code ProductosBO}.
     * 
     * @param productosDAO La instancia de {@code IProductosDAO} que se utilizará para interactuar con la base de datos.
     */
    public ProductosBO(IProductosDAO productosDAO){
        this.productosDAO=productosDAO;
    }
    
     /**
     * Registra un nuevo producto después de realizar las validaciones correspondientes.
     * 
     * <p>El nombre del producto no puede estar vacío y solo puede contener letras y espacios.
     * El precio debe ser un número positivo, menor o igual al precio máximo permitido y debe cumplir con un
     * formato específico que permite números, comas, puntos y el símbolo '$'. Si alguna de estas condiciones no
     * se cumple, se lanzará una excepción {@code NegocioException} con un mensaje adecuado.</p>
     * 
     * @param nuevoProducto El objeto {@code NuevoProductoDTO} que contiene los datos del nuevo producto a registrar.
     * @return El objeto {@code Producto} guardado.
     * @throws NegocioException Si alguno de los datos no es válido (nombre vacío, precio incorrecto o formato inválido).
     */
    @Override
    public Producto registrar(NuevoProductoDTO nuevoProducto) throws NegocioException {
        if(nuevoProducto.getNombre().isEmpty()){
            throw new NegocioException("El nombre del producto no puede estar vacio.");
        }
         if(!nuevoProducto.getNombre().matches("[A-Za-zÁÉÍÓÚáéíóúÑñ\\s]+")) {
            throw new NegocioException("El nombre del producto solo puede contener letras y espacios.");
        }
        if(nuevoProducto.getPrecio() == null || nuevoProducto.getPrecio() <= CERO || nuevoProducto.getPrecio() > PRECIO_MAXIMO_EJEMPLO){
            throw new NegocioException("El precio no puede ser nulo, negativo, ni mayor a cien mil pesos");
        }
         
        return this.productosDAO.guardar(nuevoProducto);
    }

     /**
     * Consulta todos los productos registrados en la base de datos.
     * 
     * @return Una lista de productos almacenados en la base de datos.
     * @throws NegocioException Si ocurre algún error al consultar los productos.
     */
    @Override
    public List<Producto> consultarProducto() {
        return this.productosDAO.obtenerProductos();
    }
    
    @Override
     public List<Producto> obtenerProductosFiltroNombre(String filtroBusqueda){
         return this.productosDAO.obtenerProductosFiltroNombre(filtroBusqueda);
     }
    
    @Override 
    public List<Producto> obtenerProductosPorTipo(TipoProducto tipo){
        return this.productosDAO.obtenerProductosPorTipo(tipo);
    }
    
    @Override
    public List<Producto> obtenerProductosPorTipoNombre(String filtroBusqueda, TipoProducto tipo){
        return this.productosDAO.obtenerProductosPorTipoNombre(filtroBusqueda, tipo);
    }
    
    
}
