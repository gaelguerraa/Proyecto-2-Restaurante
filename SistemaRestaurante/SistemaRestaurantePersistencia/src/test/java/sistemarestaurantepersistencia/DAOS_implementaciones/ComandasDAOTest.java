/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package sistemarestaurantepersistencia.DAOS_implementaciones;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.EntityManager;
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
//    @Test
//    public void testConsultarComandas() {
//        ComandasDAO comandaDAO = new ComandasDAO();
//        EntityManager em = ManejadorConexiones.getEntityManager();
//        
//         em.getTransaction().begin();
//         
//        Mesa mesa1 = new Mesa();
//        mesa1.setNumeroMesa(13);
//        mesa1.setCapacidad(10);
//        em.persist(mesa1); 
//        
//        Comanda c1 = new Comanda();
//        c1.setFolio("A001");
//        c1.setFechaHora(LocalDate.now().atTime(10, 0));
//        c1.setEstado(EstadoComanda.ABIERTA);
//        c1.setTotal(100.0f);
//        c1.setNumeroMesa(mesa1);
//
//        Comanda c2 = new Comanda();
//        c2.setFolio("A002");
//        c2.setFechaHora(LocalDate.now().atTime(11, 0));
//        c2.setEstado(EstadoComanda.CANCELADA);
//        c2.setTotal(200.0f);
//        c2.setNumeroMesa(mesa1);
//
//        em.persist(c1);
//        em.persist(c2);
//
//        em.getTransaction().commit();
//        em.close();
//
//        List<NuevaComandaDTO> comandas = comandaDAO.consultarComandas();
//        assertNotNull(comandas);
//        assertEquals(2, comandas.size());
//        
//    }
//    
//    @Test
//    public void testConsultarComandasRangoPorFechas(){
//            EntityManager em = ManejadorConexiones.getEntityManager();
//            ComandasDAO comandasDAO = new ComandasDAO();
//
//            em.getTransaction().begin();
//
//            Mesa mesa2 = new Mesa();
//            mesa2.setNumeroMesa(33);
//            mesa2.setCapacidad(13);
//            em.persist(mesa2); 
//            
//            Comanda c1 = new Comanda();
//            c1.setFolio("F002");
//            c1.setFechaHora(LocalDate.now().atTime(9, 0));
//            c1.setEstado(EstadoComanda.ABIERTA);
//            c1.setTotal(300.0f);
//            c1.setNumeroMesa(mesa2);
//
//            em.persist(c1);
//
//            em.getTransaction().commit();
//            em.close();
//
//            LocalDate hoy = LocalDate.now();
//            List<NuevaComandaDTO> resultado = comandasDAO.consultarComandasPorRangoFechas(hoy.minusDays(1), hoy.plusDays(1));
//
//            assertNotNull(resultado);
//            assertFalse(resultado.isEmpty());
//            assertEquals("F001", resultado.get(2).getFolio());
//    }
//    
//    @Test
//    public void testObtenerConsecutivoDelDia(){
//        EntityManager em = ManejadorConexiones.getEntityManager();
//        ComandasDAO comandasDAO = new ComandasDAO();
//
//        em.getTransaction().begin();
//
//        Mesa mesa3 = new Mesa();
//        mesa3.setNumeroMesa(23);
//        mesa3.setCapacidad(18);
//        em.persist(mesa3); 
//        
//        Comanda c1 = new Comanda();
//        c1.setFolio("Z001");
//        c1.setFechaHora(LocalDate.now().atTime(8, 0));
//        c1.setEstado(EstadoComanda.ENTREGADA);
//        c1.setTotal(150.0f);
//        c1.setNumeroMesa(mesa3);
//        
//        Comanda c2 = new Comanda();
//        c2.setFolio("Z002");
//        c2.setFechaHora(LocalDate.now().atTime(9, 30));
//        c2.setEstado(EstadoComanda.ENTREGADA);
//        c2.setTotal(250.0f);
//        c2.setNumeroMesa(mesa3);
//
//        em.persist(c1);
//        em.persist(c2);
//
//        em.getTransaction().commit();
//        em.close();
//
//        int consecutivo = comandasDAO.obtenerConsecutivoDelDia();
//        assertEquals(2, consecutivo);
//    }
//    
//    @Test
//    public void testBuscarComandaPorFolio(){
//        EntityManager em = ManejadorConexiones.getEntityManager();
//        ComandasDAO comandasDAO = new ComandasDAO();
//
//        em.getTransaction().begin();
//
//        Mesa mesa4 = new Mesa();
//        mesa4.setNumeroMesa(9);
//        mesa4.setCapacidad(98);
//        em.persist(mesa4); 
//        
//        Comanda c = new Comanda();
//        c.setFolio("X123");
//        c.setFechaHora(LocalDate.now().atTime(14, 0));
//        c.setEstado(EstadoComanda.ABIERTA);
//        c.setTotal(500.0f);
//        c.setNumeroMesa(mesa4);
//
//        em.persist(c);
//
//        em.getTransaction().commit();
//        em.close();
//
//        Comanda resultado = comandasDAO.buscarPorFolio("X123");
//        assertNotNull(resultado);
//        assertEquals("X123", resultado.getFolio());
//    }
//    
}
