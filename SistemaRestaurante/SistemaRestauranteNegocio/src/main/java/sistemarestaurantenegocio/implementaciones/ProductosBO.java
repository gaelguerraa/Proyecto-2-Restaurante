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
 * 
 * @author gael_
 */
public class ProductosBO implements IProductosBO {
    
    private IProductosDAO productosDAO;
    private Float CERO = 0.0f;
    private Float PRECIO_MAXIMO_EJEMPLO = 99999.9f;

      /**
     * Constructor de la clase 
     * 
     * @param productosDAO La instancia de ProductosDAO que se utilizará para interactuar con la base de datos.
     */
    public ProductosBO(IProductosDAO productosDAO){
        this.productosDAO=productosDAO;
    }
    
     /**
     * Registra un nuevo producto después de realizar las validaciones correspondientes.
     * 
     * @param nuevoProducto El NuevoProductoDTO que contiene los datos del nuevo producto a registrar.
     * @return El Producto guardado.
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
    public List<Producto> obtenerProductosPorTipo(String tipo){
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
    public List<Producto> obtenerProductosPorTipoNombre(String filtroBusqueda, String tipo){
        return this.productosDAO.obtenerProductosPorTipoNombre(filtroBusqueda, tipo);
    }

    /**
     * Obtiene una lista de Productos con su nombre y tipo mas los ingredientes que estan registrados con el producto
     * con su unidad de medida y su cantidad.
     * 
     * @return Lista de ProductoIngredienteDTO.  
     */
    @Override
    public List<ProductoIngredienteDTO> obtenerProductosJoin() {
        return this.productosDAO.obtenerProductosJoin();
    }

    /**
     * Obtiene el producto que se selecciona en frmBuscarProducto para buscar un producto e insertarlo en una comanda.
     * 
     * @param nombre del producto seleccionado.
     * @return Producto seleccionado.
     */
    @Override
    public Producto consultarProductoPorNombre(String nombre) {
        return this.productosDAO.consultarProductoPorNombre(nombre);
    }

    /**
     * Cambia el precio de un producto por un precio nuevo.
     * 
     * @param producto
     * @param nuevoPrecio
     * @throws NegocioException 
     */
    @Override
    public void actualizarPrecioProducto(Producto producto, Float nuevoPrecio) throws NegocioException {
        if(nuevoPrecio <= CERO || nuevoPrecio > PRECIO_MAXIMO_EJEMPLO){
            throw new NegocioException("Eliga un precio mayor a cero y menor a 10 mil.");
        }
        this.productosDAO.actualizarPrecioProducto(producto, nuevoPrecio);
    }

    
}
