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
import sistemarestaurantedominio.Ingrediente;
import sistemarestaurantedominio.Producto;
import sistemarestaurantedominio.TipoProducto;
import sistemarestaurantedominio.dtos.NuevoProductoDTO;
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

    /**
     * Obtiene una lista de productos filtrados por el tipo proporcionado.
     * 
     * Este método utiliza Criteria API para realizar una consulta que filtra los
     * productos de acuerdo a su tipo, el cual se pasa como parámetro. Retorna una
     * lista de objetos {@link Producto} que coinciden con el tipo indicado.
     * 
     * @param tipo El tipo de producto que se usará como filtro para la consulta.
     * @return Lista de productos cuyo tipo coincide con el tipo proporcionado.
     */
    @Override
    public List<Producto> obtenerProductosPorTipo(TipoProducto tipo) {
        
      EntityManager em = ManejadorConexiones.getEntityManager();
      CriteriaBuilder builder = em.getCriteriaBuilder();
      CriteriaQuery<Producto> criteria = builder.createQuery(Producto.class);
      Root<Producto> entidadProducto = criteria.from(Producto.class);

      criteria.select(entidadProducto).where(builder.equal(entidadProducto.get("tipo"), tipo));

      TypedQuery<Producto> query = em.createQuery(criteria);
      List<Producto> productosTipo = query.getResultList();

      return productosTipo;

    }

    @Override
    public List<Producto> obtenerProductosPorTipoNombre(String filtroBusqueda, TipoProducto tipo) {
        EntityManager em = ManejadorConexiones.getEntityManager();
      CriteriaBuilder builder = em.getCriteriaBuilder();
      CriteriaQuery<Producto> criteria = builder.createQuery(Producto.class);
      Root<Producto> entidadProducto = criteria.from(Producto.class);
      
      criteria.select(entidadProducto).where(
                builder.and(
                        builder.like(entidadProducto.get("nombre"), "%" + filtroBusqueda + "%"),
                        builder.equal(entidadProducto.get("tipo"), tipo)
                )
        );
      
        TypedQuery<Producto> query = em.createQuery(criteria);
        return query.getResultList();
    }
    
    
    
 
    
    
}
