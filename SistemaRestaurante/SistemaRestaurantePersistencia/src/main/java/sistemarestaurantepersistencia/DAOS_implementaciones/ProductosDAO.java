/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemarestaurantepersistencia.DAOS_implementaciones;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import sistemarestaurantedominio.Producto;
import sistemarestaurantedominio.TipoProducto;
import sistemarestaurantedominio.dtos.NuevoProductoDTO;
import sistemarestaurantedominio.dtos.ProductoIngredienteDTO;
import sistemarestaurantepersistencia.interfaces.IProductosDAO;

/**
 *
 * @author gael_
 */
public class ProductosDAO implements IProductosDAO{

    
    /**
     * Guarda un nuevo producto en la base de datos.
     * 
     * Este método recibe un objeto de tipo NuevoProductoDTO para crear un nuevo
     * objeto Producto con los datos proporcionados, y lo persiste en la
     * base de datos. Luego de guardar el producto, se realiza un commit a la 
     * transacción y se devuelve el producto guardado.
     * 
     * @param NuevoProducto Objeto que contiene la información del producto a guardar.
     * @return El producto guardado con los datos de NuevoProductoDTO.
     */
    @Override
    public Producto guardar(NuevoProductoDTO NuevoProducto) {
        EntityManager em = ManejadorConexiones.getEntityManager();
        em.getTransaction().begin();
        
        Producto producto = new Producto();
        producto.setNombre(NuevoProducto.getNombre());
        producto.setPrecio(NuevoProducto.getPrecio());
        producto.setTipo(NuevoProducto.getTipo());

        em.persist(producto);
        em.getTransaction().commit();
        
        return producto;
        
    }

    /**
     * Obtiene todos los productos almacenados en la base de datos.
     * 
     * Este método crea una consulta para obtener todos los registros de productos
     * en la base de datos, los cuales son retornados como una lista de objetos Producto.
     * 
     * @return Lista de Productos que representan todos los productos almacenados en la BD.
     */
    @Override
    public List<Producto> obtenerProductos() {
         EntityManager em = ManejadorConexiones.getEntityManager();
         em.getTransaction().begin();
         
        TypedQuery<Producto> query = em.createQuery("SELECT p FROM Producto p", Producto.class);
        return query.getResultList();
    }

    /**
     * Obtiene una lista de productos cuyo nombre contiene el filtro de búsqueda
     * proporcionado.
     * 
     * Este método utiliza Criteria API para realizar una consulta dinámica en la base
     * de datos, filtrando productos cuyo nombre contenga el texto del parametro filtroBusqueda.
     * 
     * @param filtroBusqueda Texto con el cual se filtrarán los productos por su nombre.
     * @return Lista de productos cuyo nombre contiene el filtro de búsqueda.
     */
    @Override
    public List<Producto> obtenerProductosFiltroNombre(String filtroBusqueda) {
        EntityManager em = ManejadorConexiones.getEntityManager();
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Producto> criteria = builder.createQuery(Producto.class);
        Root<Producto> entidadProducto = criteria.from(Producto.class);
        
        criteria.select(entidadProducto).where(builder.like(entidadProducto.get("nombre"), "%" + filtroBusqueda+ "%"));
        TypedQuery <Producto> query = em.createQuery(criteria);
        List<Producto> productos = query.getResultList();
        return productos;
    }


    /**
     * Consulta los productos que tienen un mismo tipo.
     * 
     * Obtiene todos los productos que sean del tipo que el usuario seleccione usando jpql query
     * 
     * @param tipo
     * @return Lista de productos del mismo tipo.
     */
    @Override
    public List<Producto> obtenerProductosPorTipo(String tipo) {
        
      EntityManager em = ManejadorConexiones.getEntityManager();
      String jpqlQuery = """
                         SELECT p FROM Producto p WHERE p.tipo = :tipo
                         """;

       TipoProducto tipoEnum = TipoProducto.valueOf(tipo);
      TypedQuery<Producto> productosTipo =em.createQuery(jpqlQuery, Producto.class);
       productosTipo.setParameter("tipo", tipoEnum);

      return productosTipo.getResultList();

    }


    /**
     * Obtiene los productos en base al nombre y tipo del producto
     * 
     * Consulta los productos que correspondan al nombre introducido por el usuario y por el tipo indicado por el usuario
     * usando una query jpql
     * 
     * @param filtroBusqueda
     * @param tipo
     * @return La lista de productos que correspondan con el filtroBusqueda y el tipo indicado por el usuario.
     */
    @Override
    public List<Producto> obtenerProductosPorTipoNombre(String filtroBusqueda, String tipo) {
       EntityManager em = ManejadorConexiones.getEntityManager();
       
      String jpqlQuery = """
                         SELECT p FROM Producto p WHERE p.nombre LIKE :nombre AND p.tipo = :tipo
                         """;
      
      TipoProducto tipoEnum = TipoProducto.valueOf(tipo);
      TypedQuery<Producto> productosNombreTipo = em.createQuery(jpqlQuery, Producto.class);
      
      productosNombreTipo.setParameter("nombre", "%" + filtroBusqueda + "%"); // búsqueda parcial
      productosNombreTipo.setParameter("tipo", tipoEnum);
      
      return productosNombreTipo.getResultList();
    }
    
    /**
     * Consulta los productos que tengan ingredientes mostrando el nombre del producto, tipo, nombre del ingrediente,
     * su unidad de medida y la cantidad requerida de ingrediente para el producto usando joins con jpql query
     * 
     * @return Lista de ProductoIngredienteDTO mostrando el producto con sus ingredientes.
     */
    @Override
    public List<ProductoIngredienteDTO> obtenerProductosJoin(){
        EntityManager em = ManejadorConexiones.getEntityManager();
        String jpqlQuery = """
                           SELECT new sistemarestaurantedominio.dtos.ProductoIngredienteDTO(
                           P.nombre, P.tipo, I.nombre, I.unidadMedida, IP.cantidadIngrediente
                           )
                           FROM Producto P
                           JOIN IngredienteProducto IP ON IP.producto = P
                           JOIN Ingrediente I ON I = IP.ingrediente
                           
                           """;
        
        TypedQuery<ProductoIngredienteDTO> query = em.createQuery(jpqlQuery, ProductoIngredienteDTO.class);
        return query.getResultList();
    }
    
    /**
     * Busca el producto seleccionado por el usuario en el frmBuscarProducto para insertarlo en comandas usando Criteria API  
     * 
     * @param nombre del producto
     * @return producto seleccionado
     */
    @Override
    public Producto consultarProductoPorNombre(String nombre){
        EntityManager entityManager = ManejadorConexiones.getEntityManager();

        String jpql = "SELECT p FROM Producto p WHERE p.nombre LIKE :nombre";
    
        TypedQuery<Producto> query = entityManager.createQuery(jpql, Producto.class);
        query.setParameter("nombre", nombre);
        return query.getSingleResult();
    }
    
    /**
     * Cambia el precio de un producto por un precio nuevo.
     * 
     * @param producto
     * @param nuevoPrecio 
     */
    @Override
    public void actualizarPrecioProducto(Producto producto, Float nuevoPrecio){
        EntityManager em = ManejadorConexiones.getEntityManager();
        em.getTransaction().begin();
        em.createQuery("UPDATE Producto p SET p.precio = :precio WHERE p.nombre = :nombre")
        .setParameter("precio", nuevoPrecio)
        .setParameter("nombre", producto.getNombre())
                
        .executeUpdate();
        producto.setPrecio(nuevoPrecio);

        em.getTransaction().commit();
        em.close();
    }
 
    
    
}
