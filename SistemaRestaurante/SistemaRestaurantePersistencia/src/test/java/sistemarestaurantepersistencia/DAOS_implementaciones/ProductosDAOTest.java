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
import sistemarestaurantedominio.dtos.ProductoIngredienteDTO;

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
//    @Test
//    public void testGuardar() {
//        ProductosDAO productosDAO = new ProductosDAO(); 
//        NuevoProductoDTO nuevoProducto = new NuevoProductoDTO("Tostitos", 13.50f, TipoProducto.PLATILLO);
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
//        Producto producto1 = new Producto("Pizza", 120.0f, TipoProducto.PLATILLO);
//        Producto producto2 = new Producto("Tequila", 20.0f, TipoProducto.BEBIDA);
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
//        em.persist(new Producto("Tortilla", 10.50f, TipoProducto.PLATILLO));
//        em.persist(new Producto("Callitos", 69.9f, TipoProducto.PLATILLO));
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
//        
//        List<Producto> productos = productosDAO.obtenerProductosPorTipo("BEBIDA");
//        
//        assertNotNull(productos);
//        assertEquals(8, productos.size());
//    }
//    
//    @Test
//    public void testObtenerProductosPorTipoYNombre(){
//         EntityManager em = ManejadorConexiones.getEntityManager();
//        ProductosDAO productosDAO = new ProductosDAO();
//        
//        String filtroBusqueda = "Pep";
//        String tipo = "BEBIDA";
//        
//        List<Producto> productos = productosDAO.obtenerProductosPorTipoNombre(filtroBusqueda, tipo);
//        
//        assertNotNull(productos);
//
//        
//    }
//    
//    @Test
//    public void testObtenerProudctosJoin(){
//        EntityManager em = ManejadorConexiones.getEntityManager();
//        ProductosDAO productosDAO = new ProductosDAO();
//        em.getTransaction().begin();
//        List<ProductoIngredienteDTO> productosJoin = productosDAO.obtenerProductosJoin();
//        
//        assertNotNull(productosJoin);
//        assertEquals(18, productosJoin.size());
//    } 
//    
//    @Test
//    public void testObtenerProductoPorNombre(){
//        EntityManager em = ManejadorConexiones.getEntityManager();
//        ProductosDAO productosDAO = new ProductosDAO();
//        
//        String nombre = "Pepsi";
//        
//        Producto producto = productosDAO.consultarProductoPorNombre(nombre);
//        
//        assertNotNull(producto);
//    }

    
}
