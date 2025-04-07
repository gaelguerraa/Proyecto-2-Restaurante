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
import sistemarestaurantedominio.Ingrediente;
import sistemarestaurantedominio.UnidadMedidaIngrediente;
import sistemarestaurantedominio.dtos.NuevoIngredienteDTO;
import static org.junit.jupiter.api.Assertions.*;
import sistemarestaurantedominio.UnidadMedidaIngrediente;

/**
 *
 * @author jalt2
 */
public class IngredientesDAOTest {
    
    public IngredientesDAOTest() {
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
     * Test of obtenerIngredientes method, of class IngredientesDAO.
     */
    @Test
    public void testObtenerIngredientes() {
        
        
    }

    /**
     * Test of guardarIngrediente method, of class IngredientesDAO.
     */
//    @Test
//    public void testGuardarIngredienteOk() {
//        IngredientesDAO ingredientesDAO = new IngredientesDAO();
//        
//        NuevoIngredienteDTO nuevoIngrediente = new NuevoIngredienteDTO("Queso", UnidadMedidaIngrediente.GRAMOS, 200.0f);
//        
//        Ingrediente IngredienteGuardado = ingredientesDAO.guardarIngrediente(nuevoIngrediente);
//        
//        /*Que el ingrediente no venga con un nombre nulo*/
//        assertNotNull(IngredienteGuardado.getNombre());
//        
//    }
//
//    /**
//     * Test of obtenerIngredientesPorNombre method, of class IngredientesDAO.
//     */
//    @Test
//    public void testObtenerIngredientesPorNombreOk() {
//        /*Filtro por el que deseas buscar*/
//        String nombreIngrediente = "Pimien";
//        IngredientesDAO ingredientesDAO = new IngredientesDAO();
//        
//        /*En mi base de datos tengo 3 coincidencias aqui poner las que tengas*/
//        final int INGREDIENTES_ESPERADOS = 2;
//        
//        List<Ingrediente> ingredientesConsultados = ingredientesDAO.obtenerIngredientesPorNombre(nombreIngrediente);
//        
//        /*Que la lista no venga vacia y que el tamaño de la lista sea el esperado*/
//        assertNotNull(ingredientesConsultados);
//        assertEquals(INGREDIENTES_ESPERADOS, ingredientesConsultados.size());
//        
//        
//    }
//
//    /**
//     * Test of obtenerIngredientesPorUnidadMedida method, of class IngredientesDAO.
//     */
//    @Test
//    public void testObtenerIngredientesPorUnidadMedidaOk() {
//        String UnidadMedida = UnidadMedidaIngrediente.GRAMOS.toString();
//        
//        IngredientesDAO ingredienteDAO = new IngredientesDAO();
//        /*En la base de datos tengo 3 ingredientes con gramos*/
//        final int INGREDIENTES_ESPERADOS = 6;
//        
//        List<Ingrediente> ingredientesConsultados = ingredienteDAO.obtenerIngredientesPorUnidadMedida(UnidadMedida);
//        
//        assertNotNull(ingredientesConsultados);
//        assertEquals(INGREDIENTES_ESPERADOS, ingredientesConsultados.size());
//        
//    }
//    
//    @Test
//    public void testObtenerIngredientePorNombreYMedidaOk(){
//        /*Parametros de busqueda*/
//        String UnidadMedida = UnidadMedidaIngrediente.GRAMOS.toString();
//        String nombre = "Queso";
//        
//        IngredientesDAO ingredientesDAO = new IngredientesDAO();
//        
//        Ingrediente ingredienteConsultado = ingredientesDAO.obtenerIngredientePorNombreYMedida(nombre, UnidadMedida);
//        
//        assertEquals(nombre, ingredienteConsultado.getNombre());
//        assertEquals(UnidadMedida, ingredienteConsultado.getUnidadMedida());
//        
//    }
//    
    /**
     * Test of aumentarStock method, of class IngredientesDAO.
     */
    //Funciona
//    @Test
//    public void testAumentarStockOk() {
//        /*Ingrediente a aumentar stock*/
//        String nombre = "Queso";
//        String unidadMedida =  UnidadMedidaIngrediente.GRAMOS.toString();
//        
//        IngredientesDAO ingredienteDAO = new IngredientesDAO();
//        Ingrediente ingredienteAumentarStock = new Ingrediente(5l,"Sal", UnidadMedidaIngrediente.GRAMOS, 200.0f);
//        
//        
//        //Actualiza el stock
//        ingredienteDAO.aumentarStock(ingredienteAumentarStock, 20.0f);
//        
//        //Solo puede haber un ingrediente en la lista
//        //Obtiene stock actualizado y lo compara
//        List<Ingrediente>ingredienteEncontrado = ingredienteDAO.obtenerIngredientePorNombreYMedida(ingredienteAumentarStock.getNombre(), ingredienteAumentarStock.getUnidadMedida().toString());
//        assertEquals(220.0f, ingredienteEncontrado.getFirst().getStock());
//        
//    }
    
}
