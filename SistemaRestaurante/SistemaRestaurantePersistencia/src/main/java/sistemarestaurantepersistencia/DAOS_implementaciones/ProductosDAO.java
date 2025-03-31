/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemarestaurantepersistencia.DAOS_implementaciones;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import sistemarestaurantedominio.Ingrediente;
import sistemarestaurantedominio.Producto;
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
}
