/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package sistemarestaurantepersistencia.DAOS_implementaciones;

import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import sistemarestaurantedominio.Producto;
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
        System.out.println("guardar");
        NuevoProductoDTO NuevoProducto = null;
        ProductosDAO instance = new ProductosDAO();
        Producto expResult = null;
        Producto result = instance.guardar(NuevoProducto);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of obtenerProductos method, of class ProductosDAO.
     */
    @Test
    public void testObtenerProductos() {
        System.out.println("obtenerProductos");
        ProductosDAO instance = new ProductosDAO();
        List<Producto> expResult = null;
        List<Producto> result = instance.obtenerProductos();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
