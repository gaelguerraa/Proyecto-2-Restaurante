/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package sistemarestaurantepersistencia.DAOS_implementaciones;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import sistemarestaurantedominio.ClienteFrecuente;
import sistemarestaurantedominio.dtos.NuevoClienteFrecuenteDTO;

/**
 *
 * @author jorge
 */
public class ClientesFrecuentesDAOTest {
    
    public ClientesFrecuentesDAOTest() {
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
     * Test of registrarClienteFrecuente method, of class ClientesFrecuentesDAO.
     */
    @Test
    public void testRegistrarClienteFrecuente() {
        ClientesFrecuentesDAO clientesFrecuentesDAO = new ClientesFrecuentesDAO();
        NuevoClienteFrecuenteDTO nuevoClienteFrecuente = new NuevoClienteFrecuenteDTO("Jorge", "Cuevas", "Gastelum", "6441222916", "jorge@hotmail.com", 0.0, 0);
        ClienteFrecuente clienteRegistrado = clientesFrecuentesDAO.registrarClienteFrecuente(nuevoClienteFrecuente);
        
        assertNotNull(clienteRegistrado.getIdCliente());
    }
    
}
