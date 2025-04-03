package sistemarestaurantepersistencia.DAOS_implementaciones;

import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
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

    @Override
    public ClienteFrecuente obtenerClientePorID(Long idClienteFrecuente) {
        EntityManager entityManager = ManejadorConexiones.getEntityManager();
        ClienteFrecuente cliente = entityManager.find(ClienteFrecuente.class, idClienteFrecuente);
        return cliente;
    }

    @Override
    public ClienteFrecuente obtenerClientePorTelefono(String telefono) {
        EntityManager entityManager = ManejadorConexiones.getEntityManager();

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();

        CriteriaQuery<ClienteFrecuente> criteria = builder.createQuery(ClienteFrecuente.class);
        Root<ClienteFrecuente> entidadClienteFrecuente = criteria.from(ClienteFrecuente.class);

        criteria = criteria.select(entidadClienteFrecuente).where(builder.like(entidadClienteFrecuente.get("telefono"), telefono));

        TypedQuery<ClienteFrecuente> query = entityManager.createQuery(criteria);
        return query.getSingleResult();
    }

    @Override
    public ClienteFrecuente obtenerClientePorCorreo(String correo) {
        EntityManager entityManager = ManejadorConexiones.getEntityManager();

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();

        CriteriaQuery<ClienteFrecuente> criteria = builder.createQuery(ClienteFrecuente.class);
        Root<ClienteFrecuente> entidadClienteFrecuente = criteria.from(ClienteFrecuente.class);

        criteria = criteria.select(entidadClienteFrecuente).where(builder.like(entidadClienteFrecuente.get("correo"), correo + "%"));

        TypedQuery<ClienteFrecuente> query = entityManager.createQuery(criteria);
        return query.getSingleResult();
    }

    @Override
    public List<ClienteFrecuente> obtenerClientePorNombre(String nombre) {
        EntityManager entityManager = ManejadorConexiones.getEntityManager();

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();

        CriteriaQuery<ClienteFrecuente> criteria = builder.createQuery(ClienteFrecuente.class);
        Root<ClienteFrecuente> entidadClienteFrecuente = criteria.from(ClienteFrecuente.class);

        criteria = criteria.select(entidadClienteFrecuente).where(builder.like(entidadClienteFrecuente.get("nombre"), "%" + nombre + "%"));

        TypedQuery<ClienteFrecuente> query = entityManager.createQuery(criteria);
        List<ClienteFrecuente> clientes = query.getResultList();
        return clientes;
    }

    @Override
    public List<ClienteFrecuente> buscarClientesPorMinimoVisitas(int minimoVisitas) {
        //pendiente, ya que se necesita tener clientes relacionados con comandas para ver la cantidad de visitas
        return null;
    }



}
