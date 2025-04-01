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

    @Override
    public List<Producto> obtenerProductos() {
         EntityManager em = ManejadorConexiones.getEntityManager();
         em.getTransaction().begin();
         
        TypedQuery<Producto> query = em.createQuery("SELECT p FROM Producto p", Producto.class);
        return query.getResultList();
    }

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
    
    
    
 
    
    
}
