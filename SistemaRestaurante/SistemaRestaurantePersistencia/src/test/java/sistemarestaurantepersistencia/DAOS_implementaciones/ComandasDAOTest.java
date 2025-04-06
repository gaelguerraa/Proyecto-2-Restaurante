/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package sistemarestaurantepersistencia.DAOS_implementaciones;

import java.time.LocalDateTime;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import sistemarestaurantedominio.Comanda;
import sistemarestaurantedominio.EstadoComanda;
import sistemarestaurantedominio.Mesa;
import sistemarestaurantedominio.dtos.NuevaComandaDTO;
import static org.junit.jupiter.api.Assertions.*;
import sistemarestaurantedominio.ClienteFrecuente;

/**
 *
 * @author jalt2
 */
public class ComandasDAOTest {
    
    public ComandasDAOTest() {
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
     * Test of guardarComanda method, of class ComandasDAO.
     */
//    @Test
//    public void testGuardarComandaSinClienteOk() {
//        ComandasDAO comandaDAO = new ComandasDAO();
//        
//        //Aqui poner la mesa que tengas en tu bd en workbench
//        Mesa mesaComanda = new Mesa(1l,1, 4);
//        NuevaComandaDTO nuevaComanda = new NuevaComandaDTO();
//        
//        nuevaComanda.setFolio("123456789");
//        nuevaComanda.setEstado(EstadoComanda.ENTREGADA);
//        nuevaComanda.setFechaHora(LocalDateTime.now());
//        nuevaComanda.setTotal(200.0f);
//        nuevaComanda.setNumeroMesa(mesaComanda);
//        
//        Comanda comandaGuardar = comandaDAO.guardarComanda(nuevaComanda);
//        
//        assertNotNull(comandaGuardar.getFolio());
//    }
//    
//    @Test
//    public void testGuardarComandaConClienteOk() {
//        ComandasDAO comandaDAO = new ComandasDAO();
//        
//        //Aqui poner la mesa que tengas en tu bd en workbench
//        Mesa mesaComanda = new Mesa(1l,1, 4);
//        //Aqui poner al cliente que tengas
//        ClienteFrecuente cliente = new ClienteFrecuente(1l, "", "");
//        NuevaComandaDTO nuevaComanda = new NuevaComandaDTO();
//        
//        nuevaComanda.setFolio("123456789");
//        nuevaComanda.setEstado(EstadoComanda.ENTREGADA);
//        nuevaComanda.setFechaHora(LocalDateTime.now());
//        nuevaComanda.setTotal(200.0f);
//        nuevaComanda.setNumeroMesa(mesaComanda);
//        nuevaComanda.setClienteFrecuente(cliente);
//        
//        Comanda comandaGuardar = comandaDAO.guardarComanda(nuevaComanda);
//        
//        assertNotNull(comandaGuardar.getFolio());
//    }

    /**
     * Test of consultarComandas method, of class ComandasDAO.
     */
    @Test
    public void testConsultarComandas() {
    }
    
}
