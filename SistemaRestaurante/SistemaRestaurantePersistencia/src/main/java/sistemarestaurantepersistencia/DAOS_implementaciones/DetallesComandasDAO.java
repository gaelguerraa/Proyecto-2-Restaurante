/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemarestaurantepersistencia.DAOS_implementaciones;

import javax.persistence.EntityManager;
import sistemarestaurantedominio.DetallesComanda;
import sistemarestaurantedominio.dtos.NuevoDetalleComandaDTO;
import sistemarestaurantepersistencia.interfaces.IDetallesComandasDAO;

/**
 *
 * @author gael_
 */
public class DetallesComandasDAO implements IDetallesComandasDAO {

    @Override
    public DetallesComanda guardarDetalleComanda(NuevoDetalleComandaDTO detalleComanda) {
        
        EntityManager em = ManejadorConexiones.getEntityManager();
        em.getTransaction().begin();
        
        DetallesComanda dc = new DetallesComanda();
        dc.setCantidadProducto(detalleComanda.getCantidad());
        dc.setPrecioUnitarioProducto(detalleComanda.getPrecioActual());
        dc.setImporteTotal(detalleComanda.getImporte());
        dc.setNota(detalleComanda.getNota());
        dc.setProducto(detalleComanda.getProduto());
        
        em.persist(dc);
        em.getTransaction().commit();
        
        return dc;
    }
    
}
