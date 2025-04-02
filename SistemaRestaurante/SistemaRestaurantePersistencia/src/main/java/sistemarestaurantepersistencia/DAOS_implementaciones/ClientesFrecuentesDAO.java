package sistemarestaurantepersistencia.DAOS_implementaciones;

import java.time.LocalDateTime;
import javax.persistence.EntityManager;
import sistemarestaurantedominio.ClienteFrecuente;
import sistemarestaurantedominio.dtos.NuevoClienteFrecuenteDTO;
import sistemarestaurantepersistencia.interfaces.IClientesFrecuentesDAO;

public class ClientesFrecuentesDAO implements IClientesFrecuentesDAO {

    @Override
    public ClienteFrecuente registrarClienteFrecuente(NuevoClienteFrecuenteDTO nuevoClienteFrecuente) {
        EntityManager entityManager = ManejadorConexiones.getEntityManager();
        
        //iniciar transaccion 
        entityManager.getTransaction().begin();
        
        ClienteFrecuente cliente = new ClienteFrecuente();
        cliente.setNombre(nuevoClienteFrecuente.getNombre());
        cliente.setApellidoPaterno(nuevoClienteFrecuente.getApellidoPaterno());
        cliente.setApellidoMaterno(nuevoClienteFrecuente.getApellidoMaterno());
        cliente.setTelefono(nuevoClienteFrecuente.getTelefono());
        cliente.setCorreo(nuevoClienteFrecuente.getCorreo());
        cliente.setPuntosFidelidad(0);
        cliente.setTotalGastado(0.0);
        cliente.setFechaRegistro(LocalDateTime.now());
        
        entityManager.persist(cliente);
        
        //termina la transaccion (ejecuta los cambios en la BD)
        entityManager.getTransaction().commit();
        
        return cliente;
    }
    
}
