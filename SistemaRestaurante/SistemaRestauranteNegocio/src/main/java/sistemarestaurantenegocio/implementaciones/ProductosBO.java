/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemarestaurantenegocio.implementaciones;

import java.util.List;
import java.util.Optional;
import sistemarestaurantedominio.Producto;
import sistemarestaurantedominio.TipoProducto;
import sistemarestaurantedominio.dtos.NuevoProductoDTO;
import sistemarestaurantedominio.dtos.ProductoIngredienteDTO;
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
        
        List<Producto> productosExistentes = productosDAO.obtenerProductosFiltroNombre(nuevoProducto.getNombre().trim());
        if (!productosExistentes.isEmpty()) {
            throw new NegocioException("Ya existe un producto con el nombre '" + nuevoProducto.getNombre() + "'.");
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
    
     /**
     * Obtiene una lista de productos que coinciden con el filtro de búsqueda por nombre.
     * El filtro se utiliza para buscar productos cuyo nombre contenga el texto proporcionado.
     *
     * @param filtroBusqueda Texto que se utilizará para filtrar los productos por nombre.
     * @return Lista de productos cuyo nombre contiene el filtro de búsqueda.
     */
    @Override
     public List<Producto> obtenerProductosFiltroNombre(String filtroBusqueda){
         return this.productosDAO.obtenerProductosFiltroNombre(filtroBusqueda);
     }
    
     /**
     * Obtiene una lista de productos filtrados por tipo.
     * El tipo de producto se especifica a través del parámetro {@link TipoProducto}.
     *
     * @param tipo El tipo de producto que se utilizará para filtrar los productos.
     * @return Lista de productos que corresponden al tipo especificado.
     */ 
    @Override 
    public List<Producto> obtenerProductosPorTipo(TipoProducto tipo){
        return this.productosDAO.obtenerProductosPorTipo(tipo);
    }
    
     /**
     * Obtiene una lista de productos filtrados por nombre y tipo.
     * El filtro de nombre y el tipo de producto se combinan para obtener los productos
     * que coinciden con ambos criterios.
     *
     * @param filtroBusqueda Texto que se utilizará para filtrar los productos por nombre.
     * @param tipo El tipo de producto que se utilizará para filtrar los productos.
     * @return Lista de productos que coinciden con el filtro de nombre y tipo.
     */
    @Override
    public List<Producto> obtenerProductosPorTipoNombre(String filtroBusqueda, TipoProducto tipo){
        return this.productosDAO.obtenerProductosPorTipoNombre(filtroBusqueda, tipo);
    }

    @Override
    public List<ProductoIngredienteDTO> obtenerProductosJoin() {
        return this.productosDAO.obtenerProductosJoin();
    }
<<<<<<< Updated upstream
<<<<<<< Updated upstream
=======
    
    
>>>>>>> Stashed changes
=======
    
    
>>>>>>> Stashed changes
    
    
}
