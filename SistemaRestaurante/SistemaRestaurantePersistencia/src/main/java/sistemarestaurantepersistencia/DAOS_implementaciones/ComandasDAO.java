
package sistemarestaurantepersistencia.DAOS_implementaciones;

import java.util.List;
import javax.persistence.EntityManager;
import sistemarestaurantedominio.Comanda;
import sistemarestaurantedominio.dtos.NuevaComandaDTO;
import sistemarestaurantepersistencia.interfaces.IComandasDAO;

public class ComandasDAO implements IComandasDAO {

    @Override
    public Comanda guardarComanda(NuevaComandaDTO nuevaComanda) {
        EntityManager em = ManejadorConexiones.getEntityManager();
        
        em.getTransaction().begin();
        
        Comanda comandaGuardar = new Comanda();
        comandaGuardar.setFolio(nuevaComanda.getFolio());
        comandaGuardar.setEstado(nuevaComanda.getEstado());
        comandaGuardar.setFechaHora(nuevaComanda.getFechaHora());
        comandaGuardar.setNumeroMesa(nuevaComanda.getNumeroMesa());
        comandaGuardar.setTotal(nuevaComanda.getTotal());
        comandaGuardar.setCliente(nuevaComanda.getClienteFrecuente());
        em.persist(comandaGuardar);
        
        em.getTransaction().commit();
        return comandaGuardar;
    }

    @Override
    public List<Comanda> consultarComandasPorFecha() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
