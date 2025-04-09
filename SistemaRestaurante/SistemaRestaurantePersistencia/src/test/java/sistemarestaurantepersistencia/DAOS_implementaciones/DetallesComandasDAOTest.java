/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package sistemarestaurantepersistencia.DAOS_implementaciones;

import javax.persistence.EntityManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import sistemarestaurantedominio.DetallesComanda;
import sistemarestaurantedominio.Producto;
import sistemarestaurantedominio.TipoProducto;
import sistemarestaurantedominio.dtos.NuevoDetalleComandaDTO;

/**
 *
 * @author gael_
 */
public class DetallesComandasDAOTest {
    
    public DetallesComandasDAOTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of guardarDetalleComanda method, of class DetallesComandasDAO.
     */
//    @Test
//    public void testGuardarDetalleComanda() {
//        DetallesComandasDAO dt = new DetallesComandasDAO();
//        EntityManager em = ManejadorConexiones.getEntityManager();
//        em.getTransaction().begin();
//        Producto producto = new Producto("Hamburguesa", 79.99f, TipoProducto.PLATILLO);
//        em.persist(producto);
//        em.getTransaction().commit();
//        
//        NuevoDetalleComandaDTO dcDTO = new NuevoDetalleComandaDTO();
//        
//        dcDTO.setProduto(producto);
//        dcDTO.setCantidad(4);
//        dcDTO.setPrecioActual(producto.getPrecio());
//        dcDTO.setImporte(producto.getPrecio() * dcDTO.getCantidad());
//        dcDTO.setNota("sin carne");
//        
//        DetallesComanda detalleGuardado = dt.guardarDetalleComanda(dcDTO);
//        assertNotNull(detalleGuardado);
//        assertEquals(dcDTO.getCantidad(), detalleGuardado.getCantidadProducto());
//    }
    
}
