/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemarestaurantepersistencia.DAOS_implementaciones;

import javax.persistence.EntityManager;
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
    
}
