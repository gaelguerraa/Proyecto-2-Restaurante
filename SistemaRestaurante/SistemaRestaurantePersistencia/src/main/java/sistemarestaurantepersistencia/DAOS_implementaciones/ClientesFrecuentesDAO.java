package sistemarestaurantepersistencia.DAOS_implementaciones;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import sistemarestaurantedominio.ClienteFrecuente;
import sistemarestaurantedominio.Comanda;
import sistemarestaurantedominio.EstadoComanda;
import sistemarestaurantedominio.dtos.NuevoClienteFrecuenteDTO;
import sistemarestaurantepersistencia.interfaces.IClientesFrecuentesDAO;

/**
 * Clase DAO para clientes frecuentes
 *
 * @author jorge
 */
public class ClientesFrecuentesDAO implements IClientesFrecuentesDAO {

    /**
     * Metodo que permite registrar un cliente, sus valores de visitas, puntos y
     * monto gastado se establecen en 0
     *
     * @param nuevoClienteFrecuente Recibe como parametro una DTO de
     * nuevoClienteFrecuente
     * @return Regresa un ClienteFrecuente
     */
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

    /**
     * Metodo que obtiene un cliente por ID
     *
     * @param idClienteFrecuente Recibe una ID de tipo Long
     * @return regresa un cliente en caso de ser encontrado
     */
    @Override
    public ClienteFrecuente obtenerClientePorID(Long idClienteFrecuente) {
        EntityManager entityManager = ManejadorConexiones.getEntityManager();
        ClienteFrecuente cliente = entityManager.find(ClienteFrecuente.class, idClienteFrecuente);
        return cliente;
    }

    /**
     * Metodo que permite consultar un cliente segun el telefono otorgado
     *
     * @param telefono Recibe como parametro un telefono encriptado
     * @return Regresa un clienteFrecuente en caso de ser encontrado
     */
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

    /**
     * Metodo que permite consultar un cliente mediante un correo otorgado
     *
     * @param correo Recibe como parametro un correo
     * @return Regresa un clienteFrecuente en caso de ser encontrado
     */
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

    /**
     * Metodo que obtiene una lista de clientes segun el nombre especificado
     *
     * @param nombre recibe como parametro un nombre a filtrar
     * @return regresa una lista de clientes
     */
    @Override
    public List<ClienteFrecuente> obtenerClientesPorNombre(String nombre) {
        EntityManager entityManager = ManejadorConexiones.getEntityManager();

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();

        CriteriaQuery<ClienteFrecuente> criteria = builder.createQuery(ClienteFrecuente.class);
        Root<ClienteFrecuente> entidadClienteFrecuente = criteria.from(ClienteFrecuente.class);

        criteria = criteria.select(entidadClienteFrecuente).where(builder.like(entidadClienteFrecuente.get("nombre"), "%" + nombre + "%"));

        TypedQuery<ClienteFrecuente> query = entityManager.createQuery(criteria);
        List<ClienteFrecuente> clientes = query.getResultList();
        return clientes;
    }

    /**
     * Metodo que permite obtener las comandas de un cliente
     *
     * @param cliente Recibe como parametro un clienteFrecuente
     * @return regresa una lista de comandas de las cuales un cliente esta
     * relacionado
     */
    @Override
    public List<Comanda> obtenerComandasPorCliente(ClienteFrecuente cliente) {
        EntityManager entityManager = ManejadorConexiones.getEntityManager();
        List<Comanda> comandas = new ArrayList<>();

        try {
            TypedQuery<Comanda> query = entityManager.createQuery(
                    "SELECT c FROM Comanda c WHERE c.cliente = :cliente", Comanda.class);
            query.setParameter("cliente", cliente);
            comandas = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return comandas;
    }

    @Override
    public List<Comanda> obtenerComandasCompletadasPorCliente(ClienteFrecuente cliente) {
        EntityManager entityManager = ManejadorConexiones.getEntityManager();
        List<Comanda> comandas = new ArrayList<>();

        try {
            TypedQuery<Comanda> query = entityManager.createQuery(
                    "SELECT c FROM Comanda c WHERE c.cliente = :cliente AND c.estado = :estado",
                    Comanda.class);
            query.setParameter("cliente", cliente);
            query.setParameter("estado", EstadoComanda.ENTREGADA);
            comandas = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return comandas;
    }

    /**
     * Metodo que obtiene el monto gastado de un cliente segun las comandas que
     * tenga relacionadas
     *
     * @param cliente Recibe como parametro un ClienteFrecuente
     * @return Regresa un float siendo la cantidad gastada
     */
    @Override
    public float obtenerMontoGastado(ClienteFrecuente cliente) {
        List<Comanda> comandas = this.obtenerComandasCompletadasPorCliente(cliente);

        float montoTotal = 0;

        for (Comanda comanda : comandas) {
            if (comanda.getTotal() != null) {
                montoTotal += comanda.getTotal();
            }
        }

        return montoTotal;
    }

    /**
     * Metodo que permite obtener el numero de visitas de un cliente segun las
     * comandas que tenga relacionadas
     *
     * @param cliente Recibe como parametro un ClienteFrecuente
     * @return Regresa un numero entero de las visitas que tenga un cliente
     */
    @Override
    public int obtenerNumeroVisitas(ClienteFrecuente cliente) {
        List<Comanda> comandas = this.obtenerComandasPorCliente(cliente);
        return comandas.size();
    }

    /**
     * Metodo que obtiene la ultima visita de un cliente segun sus comandas
     * relacionadas
     *
     * @param cliente Recibe como parametro un ClienteFrecuente
     * @return regresa una fecha de tipo LocalDateTime
     */
    @Override
    public LocalDateTime obtenerUltimaVisita(ClienteFrecuente cliente) {
        EntityManager entityManager = ManejadorConexiones.getEntityManager();
        try {
            String query = "SELECT MAX(c.fechaHora) FROM Comanda c WHERE c.cliente.id = :clienteId";
            TypedQuery<LocalDateTime> typedQuery = entityManager.createQuery(query, LocalDateTime.class);
            typedQuery.setParameter("clienteId", cliente.getIdCliente());
            return typedQuery.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    /**
     * Metodo que permite obtener una lista de clientes por medio de un telefono
     * otorgado
     *
     * @param telefono recibe como parametro un telefono encriptado
     * @return regresa una lista de clientes que correspondan al telefono
     */
    @Override
    public List<ClienteFrecuente> obtenerClientesPorTelefono(String telefono) {
        EntityManager entityManager = ManejadorConexiones.getEntityManager();

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();

        CriteriaQuery<ClienteFrecuente> criteria = builder.createQuery(ClienteFrecuente.class);
        Root<ClienteFrecuente> entidadClienteFrecuente = criteria.from(ClienteFrecuente.class);

        criteria = criteria.select(entidadClienteFrecuente).where(builder.like(entidadClienteFrecuente.get("telefono"), telefono));

        TypedQuery<ClienteFrecuente> query = entityManager.createQuery(criteria);
        List<ClienteFrecuente> clientes = query.getResultList();
        return clientes;
    }

    /**
     * Metodo que permite obtener una lista de clientes por medio de un correo
     * otorgado
     *
     * @param correo recibe como parametro un correo de tipo String
     * @return regresa una lista de clientes que correspondan con el correo
     */
    @Override
    public List<ClienteFrecuente> obtenerClientesPorCorreo(String correo) {
        EntityManager entityManager = ManejadorConexiones.getEntityManager();

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();

        CriteriaQuery<ClienteFrecuente> criteria = builder.createQuery(ClienteFrecuente.class);
        Root<ClienteFrecuente> entidadClienteFrecuente = criteria.from(ClienteFrecuente.class);

        criteria = criteria.select(entidadClienteFrecuente).where(builder.like(entidadClienteFrecuente.get("correo"), correo + "%"));

        TypedQuery<ClienteFrecuente> query = entityManager.createQuery(criteria);
        List<ClienteFrecuente> clientes = query.getResultList();
        return clientes;
    }

    /**
     * Metodo que regresa una lista de clientes cuyo minimo de visitas sea igual
     * o mayor (comandas relacionadas)
     *
     * @param minimoVisitas Recibe un int como numero minimo de visitas
     * @return Retorna una lista de clientes
     */
    @Override
    public List<ClienteFrecuente> buscarClientesPorMinimoVisitas(int minimoVisitas) {
        EntityManager em = ManejadorConexiones.getEntityManager();
        String jpql = """
        SELECT c
        FROM ClienteFrecuente c
        WHERE (
            SELECT COUNT(co)
            FROM Comanda co
            WHERE co.cliente = c
        ) >= :minimoVisitas
        """;

        return em.createQuery(jpql, ClienteFrecuente.class)
                .setParameter("minimoVisitas", minimoVisitas)
                .getResultList();
    }

    @Override
    public void procesarPuntosClientePorComandaEntregada(Long idComanda) throws PersistenceException {
        EntityManager em = ManejadorConexiones.getEntityManager();
        try {
            em.getTransaction().begin();

            Comanda comanda = em.find(Comanda.class, idComanda);

            if (comanda == null) {
                throw new PersistenceException("La comanda no existe.");
            }

            if (comanda.getEstado() != EstadoComanda.ENTREGADA) {
                // Si no esta entregada, no se procesan puntos
                return;
            }

            ClienteFrecuente cliente = comanda.getCliente();
            if (cliente != null) {
                // Calcular nuevos puntos
                int nuevosPuntos = (int) (comanda.getTotal() / 20);
                cliente.setPuntosFidelidad(cliente.getPuntosFidelidad() + nuevosPuntos);

                // Persistir cambios
                em.merge(cliente);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new PersistenceException("Error al procesar puntos del cliente: " + ex.getMessage(), ex);
        } finally {
            em.close();
        }
    }

}
