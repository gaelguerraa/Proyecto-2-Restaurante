/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemarestaurantepersistencia.DAOS_implementaciones;

import javax.persistence.EntityManager;
import sistemarestaurantedominio.IngredienteProducto;
import static sistemarestaurantedominio.IngredienteProducto_.producto;
import sistemarestaurantedominio.dtos.NuevoIngredienteProductoDTO;
import sistemarestaurantepersistencia.interfaces.IIngredientesProductosDAO;

/**
 *
 * @author gael_
 */
public class IngedientesProductosDAO implements IIngredientesProductosDAO{

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
