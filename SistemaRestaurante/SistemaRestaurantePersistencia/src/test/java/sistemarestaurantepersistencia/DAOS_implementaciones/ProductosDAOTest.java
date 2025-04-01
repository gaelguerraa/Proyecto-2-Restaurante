/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package sistemarestaurantepersistencia.DAOS_implementaciones;

import java.util.List;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import sistemarestaurantedominio.Producto;
import sistemarestaurantedominio.TipoProducto;
import sistemarestaurantedominio.dtos.NuevoProductoDTO;

/**
 *
 * @author gael_
 */
public class ProductosDAOTest {
    
    public ProductosDAOTest() {
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
     * Test of guardar method, of class ProductosDAO.
     */
    @Test
    public void testGuardar() {
        ProductosDAO productosDAO= new ProductosDAO(); 
        NuevoProductoDTO nuevoProducto = new NuevoProductoDTO("Tortilla", 10.50f, TipoProducto.PLATILLO);
        
        Producto productoGuardado = productosDAO.guardar(nuevoProducto);
        assertNotNull(productoGuardado.getNombre());
    }

    /**
     * Test of obtenerProductos method, of class ProductosDAO.
     */
    @Test
    public void testObtenerProductos() {
        EntityManager em = ManejadorConexiones.getEntityManager();
        em.getTransaction().begin();
        ProductosDAO productoDAO = new ProductosDAO();

        em.getTransaction().begin();
        Producto producto1 = new Producto("Pizza", 120.0f, TipoProducto.PLATILLO);
        Producto producto2 = new Producto("Coca Cola", 20.0f, TipoProducto.BEBIDA);
        em.persist(producto1);
        em.persist(producto2);
        em.getTransaction().commit();

         List<Producto> productos = productoDAO.obtenerProductos();

        assertNotNull(productos);
        assertFalse(productos.isEmpty());
        assertEquals(2, productos.size());

        em.getTransaction().begin();
        em.createQuery("DELETE FROM Producto").executeUpdate();
        em.getTransaction().commit();

        em.close();
        em.close();   
    }
    
}
