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
    
//    public ProductosDAOTest() {
//    }
//    
//    @BeforeAll
//    public static void setUpClass() {
//    }
//    
//    @AfterAll
//    public static void tearDownClass() {
//    }
//    
//    @BeforeEach
//    public void setUp() {
//    }
//    
//    @AfterEach
//    public void tearDown() {
//    }
//
//    /**
//     * Test of guardar method, of class ProductosDAO.
//     */
//    @Test
//    public void testGuardar() {
//        ProductosDAO productosDAO = new ProductosDAO(); 
//        NuevoProductoDTO nuevoProducto = new NuevoProductoDTO("Callitos", 13.50f, TipoProducto.PLATILLO);
//        
//        Producto productoGuardado = productosDAO.guardar(nuevoProducto);
//        assertNotNull(productoGuardado.getNombre());
//    }
//
//    /**
//     * Test of obtenerProductos method, of class ProductosDAO.
//     */
//    @Test
//    public void testObtenerProductos() {
//        EntityManager em = ManejadorConexiones.getEntityManager();
//         ProductosDAO productoDAO = new ProductosDAO();
//
//        em.getTransaction().begin();
//        Producto producto1 = new Producto("Pizzaaa", 120.0f, TipoProducto.PLATILLO);
//        Producto producto2 = new Producto("Coca Colaaa", 20.0f, TipoProducto.BEBIDA);
//        em.persist(producto1);
//        em.persist(producto2);
//        em.getTransaction().commit();
//
//         List<Producto> productos = productoDAO.obtenerProductos();
//
//        assertNotNull(productos);
//        assertFalse(productos.isEmpty());
//
//
//       em.close();   
//    }
//    
//     @Test
//    public void testObtenerProductosFiltroNombre() {
//        EntityManager em = ManejadorConexiones.getEntityManager();
//        ProductosDAO productosDAO = new ProductosDAO();
//        em.getTransaction().begin();
//        em.persist(new Producto("Tortillo", 10.50f, TipoProducto.PLATILLO));
//        em.persist(new Producto("Tortillota con caca", 69.9f, TipoProducto.PLATILLO));
//        em.persist(new Producto("Tostada", 12.00f, TipoProducto.PLATILLO));
//        em.persist(new Producto("Coca Cola", 20.00f, TipoProducto.BEBIDA));
//        em.getTransaction().commit();
//        em.close();
//
//        List<Producto> productosFiltrados = productosDAO.obtenerProductosFiltroNombre("Tort");
//
//        assertNotNull(productosFiltrados);
//        assertFalse(productosFiltrados.isEmpty());
//        
//        // Verificar que al menos uno de los productos encontrados contiene "Tort"
//        boolean contieneTortilla = productosFiltrados.stream()
//                .anyMatch(p -> p.getNombre().contains("Tort"));
//        assertTrue(contieneTortilla);
//    }
//    
//    @Test
//    public void testObtenerProductosPorTipo() {
//        EntityManager em = ManejadorConexiones.getEntityManager();
//        ProductosDAO productosDAO = new ProductosDAO();
//        em.getTransaction().begin();
//        em.persist(new Producto("Tacos", 14.50f, TipoProducto.PLATILLO));
//        em.persist(new Producto("Pescado", 75.00f, TipoProducto.PLATILLO));
//        em.persist(new Producto("Alimento para perro", 29.00f, TipoProducto.POSTRE));
//        em.persist(new Producto("Pepsi", 15.00f, TipoProducto.BEBIDA));
//        em.getTransaction().commit();
//        em.close();
//
//        // Obtener solo los productos de tipo PLATILLO
//        List<Producto> productosPlatillo = productosDAO.obtenerProductosPorTipo(TipoProducto.PLATILLO);
//
//        assertNotNull(productosPlatillo);
//        assertFalse(productosPlatillo.isEmpty());
//
//        // Verificar que todos los productos obtenidos sean del tipo correcto
//        boolean todosSonPlatillos = productosPlatillo.stream()
//                .allMatch(p -> p.getTipo() == TipoProducto.PLATILLO);
//        assertTrue(todosSonPlatillos);
//    }
//    
//    @Test
//    public void testObtenerProductosPorTipoYNombre(){
//         EntityManager em = ManejadorConexiones.getEntityManager();
//        ProductosDAO productosDAO = new ProductosDAO();
//        em.getTransaction().begin();
//        em.persist(new Producto("Camarones", 94.50f, TipoProducto.PLATILLO));
//        em.persist(new Producto("Pasta", 175.00f, TipoProducto.PLATILLO));
//        em.persist(new Producto("Frijoles", 9.00f, TipoProducto.PLATILLO));
//        em.persist(new Producto("Pepsi", 15.00f, TipoProducto.BEBIDA));
//        em.getTransaction().commit();
//        
//        List<Producto> resultado = productosDAO.obtenerProductosPorTipoNombre("Pepsi", TipoProducto.BEBIDA);
//        assertNotNull(resultado);
//        
//    }

    
}
