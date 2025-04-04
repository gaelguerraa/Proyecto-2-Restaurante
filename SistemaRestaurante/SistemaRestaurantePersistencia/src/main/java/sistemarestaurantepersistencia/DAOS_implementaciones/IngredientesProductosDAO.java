/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemarestaurantepersistencia.DAOS_implementaciones;

import javax.persistence.EntityManager;
import sistemarestaurantedominio.IngredienteProducto;
import sistemarestaurantedominio.dtos.NuevoIngredienteProductoDTO;
import sistemarestaurantepersistencia.interfaces.IIngredientesProductosDAO;

/**
 *
 * @author gael_
 */
public class IngredientesProductosDAO implements IIngredientesProductosDAO{

    /**
    * Registra una relación entre un ingrediente y un producto.
    *
    * @param ingredienteProducto DTO con los datos del ingrediente y producto a registrar.
    * @return El objeto IngredienteProducto registrado.
    */
    @Override
    public IngredienteProducto registrarIngredienteProducto(NuevoIngredienteProductoDTO ingredienteProducto) {
        EntityManager em = ManejadorConexiones.getEntityManager();
        em.getTransaction().begin();
        
        IngredienteProducto ip = new IngredienteProducto();
        ip.setProducto(ingredienteProducto.getProducto());
        ip.setIngrediente(ingredienteProducto.getIngrediente());
        ip.setCantidadIngrediente(ingredienteProducto.getCantidadIngrediente());
        
        em.persist(ip);
        em.getTransaction().commit();
        
        return ip;
    }
    
}
