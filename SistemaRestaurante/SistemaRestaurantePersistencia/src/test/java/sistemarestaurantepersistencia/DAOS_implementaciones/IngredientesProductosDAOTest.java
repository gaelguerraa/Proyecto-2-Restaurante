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
import sistemarestaurantedominio.Ingrediente;
import sistemarestaurantedominio.IngredienteProducto;
import sistemarestaurantedominio.Producto;
import sistemarestaurantedominio.TipoProducto;
import sistemarestaurantedominio.UnidadMedidaIngrediente;
import sistemarestaurantedominio.dtos.NuevoIngredienteProductoDTO;

/**
 *
 * @author gael_
 */
public class IngredientesProductosDAOTest {
    
    public IngredientesProductosDAOTest() {
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

    @Test
    public void testRegistrarIngredienteProducto() {
        
       EntityManager em = ManejadorConexiones.getEntityManager(); 
       IngredientesProductosDAO ipDAO = new IngredientesProductosDAO();
       
       Producto producto = new Producto("Pizza", 100.0f, TipoProducto.PLATILLO);
       Ingrediente ingrediente = new Ingrediente("Queso", UnidadMedidaIngrediente.GRAMOS, 10.0f);

       NuevoIngredienteProductoDTO nipDTO = new NuevoIngredienteProductoDTO();
       nipDTO.setProducto(producto);
       nipDTO.setIngrediente(ingrediente);
       nipDTO.setCantidadIngrediente(1.0f);
       
       em.getTransaction().begin();
       
       em.persist(producto);
       em.persist(ingrediente);
       
       em.getTransaction().commit();
       
       IngredienteProducto ipRegistrado = ipDAO.registrarIngredienteProducto(nipDTO);
       
       assertNotNull(ipRegistrado);
        assertNotNull(ipRegistrado.getId());
        
    }
    
}
