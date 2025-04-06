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
     * Este método recibe un objeto de tipo {@link NuevoProductoDTO}, crea un nuevo
     * objeto {@link Producto} con los datos proporcionados, y lo persiste en la
     * base de datos. Luego de guardar el producto, se realiza un commit a la 
     * transacción y se devuelve el producto guardado.
     * 
     * @param NuevoProducto Objeto que contiene la información del producto a guardar.
     * @return El producto guardado con los datos de {@link NuevoProductoDTO}.
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
     * en la base de datos, los cuales son retornados como una lista de objetos
     * {@link Producto}.
     * 
     * @return Lista de objetos {@link Producto} que representan todos los productos
     *         almacenados en la base de datos.
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
     * de datos, filtrando productos cuyo nombre contenga el texto dado en el parámetro
     * {@code filtroBusqueda}. Retorna una lista de objetos {@link Producto}.
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
    
    //ya sirve
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
    
    @Override
    public Producto consultarProductoPorNombre(String nombre){
        EntityManager entityManager = ManejadorConexiones.getEntityManager();

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Producto> criteria = builder.createQuery(Producto.class);
        Root<Producto> producto = criteria.from(Producto.class);

        criteria = criteria.select(producto).where(builder.like(producto.get("nombre"), nombre));

        TypedQuery<Producto> query = entityManager.createQuery(criteria);
        return query.getSingleResult();
    }
    
    
 
    
    
}
