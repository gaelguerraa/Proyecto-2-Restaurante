package sistemarestaurantepersistencia.DAOS_implementaciones;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import sistemarestaurantedominio.ClienteFrecuente;
import sistemarestaurantedominio.Comanda;
import sistemarestaurantedominio.DetallesComanda;
import sistemarestaurantedominio.EstadoComanda;
import sistemarestaurantedominio.Mesa;
import sistemarestaurantedominio.dtos.ActualizarComandaDTO;
import sistemarestaurantedominio.dtos.NuevaComandaDTO;
import sistemarestaurantedominio.dtos.ProductoComandaDTO;
import sistemarestaurantepersistencia.exception.PersistenciaException;
import sistemarestaurantepersistencia.interfaces.IComandasDAO;

public class ComandasDAO implements IComandasDAO {

    /**
     * Metodo que permite guardar una nueva comanda usando una DTO
     * @param nuevaComanda Recibe una NuevaComandaDTO
     * @return retorna una comanda
     */
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

    /**
     * Metodo que retorna una lista de comandas con su cliente, numero de mesa y los ordena por fecha
     * @return Regresa una lista de comandas
     */
    @Override
    public List<NuevaComandaDTO> consultarComandas() {
        EntityManager entityManager = ManejadorConexiones.getEntityManager();
        String jpql = """
        SELECT new sistemarestaurantedominio.dtos.NuevaComandaDTO(
            c.folio,
            c.estado,
            c.fechaHora,
            c.total,
            c.numeroMesa,
            c.cliente
        )
        FROM Comanda c
        LEFT JOIN c.cliente cl
        JOIN c.numeroMesa m
        ORDER BY c.fechaHora DESC
    """;

        return entityManager.createQuery(jpql, NuevaComandaDTO.class).getResultList();
    }

    /**
     * Metodo que consulta comandas en base a un periodo dado
     * @param fechaInicio recibe una fecha de inicio de tipo LocalDate
     * @param fechaFin recibe una fecha de fin de tipo LocalDate
     * @return 
     */
    @Override
    public List<NuevaComandaDTO> consultarComandasPorRangoFechas(LocalDate fechaInicio, LocalDate fechaFin) {
        EntityManager entityManager = ManejadorConexiones.getEntityManager();
        String jpql = """
        SELECT new sistemarestaurantedominio.dtos.NuevaComandaDTO(
            c.folio,
            c.estado,
            c.fechaHora,
            c.total,
            c.numeroMesa,
            c.cliente
        )
        FROM Comanda c
        LEFT JOIN c.cliente cl
        JOIN c.numeroMesa m
        WHERE c.fechaHora >= :fechaInicio
        AND c.fechaHora <= :fechaFin
        ORDER BY c.fechaHora DESC
    """;

        return entityManager.createQuery(jpql, NuevaComandaDTO.class)
                .setParameter("fechaInicio", fechaInicio.atStartOfDay())
                .setParameter("fechaFin", fechaFin.atTime(23, 59, 59))
                .getResultList();
    }

    /**
     * _Metodo que obtiene la lista de mesas
     * @return regresa una lista de mesas
     */
    @Override
    public List<Mesa> obtenerMesas() {
        EntityManager entityManager = ManejadorConexiones.getEntityManager();

        entityManager.getTransaction().begin();

        TypedQuery<Mesa> query = entityManager.createQuery("SELECT m FROM Mesa m", Mesa.class);

        List<Mesa> mesas = query.getResultList();

        entityManager.close();
        return mesas;
    }

    /**
     * Metodo que regresa una mesa y sus detalles en base a su numero
     * @param numeroMesa Recibe un numero de mesa
     * @return regresa una Mesa
     */
    @Override
    public Mesa buscarMesaPorNumero(int numeroMesa) {
        EntityManager em = ManejadorConexiones.getEntityManager();
        TypedQuery<Mesa> query = em.createQuery("""
                                                SELECT m FROM Mesa m WHERE m.numeroMesa = :numeroMesa
                                                """, Mesa.class);
        query.setParameter("numeroMesa", numeroMesa);
        return query.getSingleResult();
    }

    /**
     * Metodo para obtener el numero de comanda consecutivo del dia
     * @return regresa un numero entero
     */
    @Override
    public int obtenerConsecutivoDelDia() {
        EntityManager em = ManejadorConexiones.getEntityManager();

        LocalDate hoy = LocalDate.now();
        LocalDateTime inicioDelDia = hoy.atStartOfDay();
        LocalDateTime finDelDia = hoy.atTime(LocalTime.MAX);

        TypedQuery<Long> query = em.createQuery("""
            SELECT COUNT(c) FROM Comanda c
            WHERE c.fechaHora BETWEEN :inicio AND :fin
        """, Long.class);

        query.setParameter("inicio", inicioDelDia);
        query.setParameter("fin", finDelDia);

        Long total = query.getSingleResult();
        return total.intValue() + 1; // Se suma 1 para el siguiente folio
    }
    
     /**
     * Método que busca una comanda por su folio.
     * @param folio Folio de la comanda a buscar.
     * @return Comanda correspondiente al folio proporcionado.
     */
    
    @Override
    public Comanda buscarPorFolio(String folio) {
        EntityManager em = ManejadorConexiones.getEntityManager();

        TypedQuery<Comanda> query = em.createQuery("""
                                                   SELECT c FROM Comanda c WHERE c.folio = :folio
                                                   """, Comanda.class);
        query.setParameter("folio", folio);
        Comanda comanda = query.getSingleResult();
        return comanda;
    }
    
     /**
     * Método que obtiene los productos de una comanda dado su folio.
     * @param folioComanda Folio de la comanda de la cual se quieren obtener los productos.
     * @return Lista de productos asociados a la comanda en forma de DTOs.
     */
    @Override
    public List<ProductoComandaDTO> obtenerProductosComanda(String folioComanda) {
        Comanda comanda = buscarPorFolio(folioComanda);

        // Lista para almacenar los DTOs de productos asociados a la comanda
        List<ProductoComandaDTO> productosComanda = new ArrayList<>();

        //la comanda existe y tiene detalles
        if (comanda != null && comanda.getDetallesComanda() != null) {
            for (DetallesComanda detalle : comanda.getDetallesComanda()) {
                // Crear el DTO con la cantidad y el nombre del producto
                ProductoComandaDTO productoDTO = new ProductoComandaDTO(
                        comanda.getFolio(),
                        detalle.getProducto().getNombre(),
                        detalle.getCantidadProducto()
                );
                productosComanda.add(productoDTO);
            }
        }

        return productosComanda;
    }

    /**
     * Metodo que obtiene los productos con detalle de una comanda dado el folio de la comanda
     * @param folioComanda Recibe el folio de una comanda con productos
     * @return Regresa una lista de productos con detalles
     */
    @Override
    public List<ProductoComandaDTO> obtenerProductosDetalladosComanda(String folioComanda) {
        EntityManager em = ManejadorConexiones.getEntityManager();
        try {
            String jpql = """
            SELECT NEW sistemarestaurantedominio.dtos.ProductoComandaDTO(
                c.folio,
                p.nombre,
                d.cantidadProducto,
                d.nota,
                d.precioUnitarioProducto,
                d.importeTotal
            )
            FROM Comanda c
            JOIN c.detallesComanda d
            JOIN d.producto p
            WHERE c.folio = :folio
            ORDER BY d.id
        """;

            List<ProductoComandaDTO> resultados = em.createQuery(jpql, ProductoComandaDTO.class)
                    .setParameter("folio", folioComanda)
                    .getResultList();

            return resultados;

        } catch (Exception e) {
            return Collections.emptyList();
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    /**
     * Metodo que actualiza el estado de una comanda dada
     * @param comanda Recibe una comanda
     * @param estado Recibe un estado el cual tiene que ser parte de el enum EstadoComanda
     * @return regresa el numero de cambios (1 si se realizo)
     */
    @Override
    public Integer actualizarEstadoComanda(Comanda comanda, EstadoComanda estado) {
        EntityManager em = ManejadorConexiones.getEntityManager();

        //Registros afectados
        int resultado = 0;
        em.getTransaction().begin();
        String jpqlQuery = """
                           UPDATE Comanda c SET c.estado = :nuevoEstado
                           WHERE c.id = :idComanda
                           """;
        Query query = em.createQuery(jpqlQuery);
        query.setParameter("idComanda", comanda.getId());
        query.setParameter("nuevoEstado", estado);

        resultado = query.executeUpdate();

        em.getTransaction().commit();

        return resultado;
    }

    /**
     * Metodo que actualiza los datos basicos de una comanda
     * @param comandaDTO Recibe una DTO colos datos nuevos de la comanda
     * @throws PersistenceException 
     */
    @Override
    public void actualizarComanda(ActualizarComandaDTO comandaDTO) throws PersistenceException {

        EntityManager entityManager = ManejadorConexiones.getEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = entityManager.getTransaction();
            transaction.begin();

            String jpql = """
            UPDATE Comanda c 
            SET c.total = :total, 
                c.numeroMesa = :mesa, 
                c.clienteFrecuente = :cliente,
                c.fechaModificacion = :fechaModificacion
            WHERE c.folio = :folio
        """;

            Mesa mesa = entityManager.find(Mesa.class, comandaDTO.getNumeroMesa().getNumeroMesa());
            ClienteFrecuente cliente = (comandaDTO.getCliente() != null)
                    ? entityManager.find(ClienteFrecuente.class, comandaDTO.getCliente().getIdCliente()) : null;

            int updatedCount = entityManager.createQuery(jpql)
                    .setParameter("total", comandaDTO.getTotal())
                    .setParameter("mesa", mesa)
                    .setParameter("cliente", cliente)
                    .setParameter("fechaModificacion", LocalDateTime.now())
                    .setParameter("folio", comandaDTO.getFolio())
                    .executeUpdate();

            if (updatedCount == 0) {
                throw new PersistenciaException("No se actualizó ninguna comanda. Folio no encontrado: " + comandaDTO.getFolio());
            }

            transaction.commit();
        } catch (Exception e) {
        } finally {
        }
    }

    /**
     * Metodo que actualiza el total de una comanda por uno nuevo otorgado
     * @param idComanda Recibe un id de una comanda existente
     * @param nuevoTotal Recibe un total nuevo
     * @throws PersistenciaException 
     */
    @Override
    public void actualizarTotalComanda(Long idComanda, Float nuevoTotal) throws PersistenciaException {
        EntityManager entityManager = ManejadorConexiones.getEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = entityManager.getTransaction();
            transaction.begin();

            // Consulta JPQL para actualizar solo el total
            String jpql = "UPDATE Comanda c SET c.total = :nuevoTotal WHERE c.id = :idComanda";

            int updated = entityManager.createQuery(jpql)
                    .setParameter("nuevoTotal", nuevoTotal)
                    .setParameter("idComanda", idComanda)
                    .executeUpdate();

            if (updated == 0) {
                throw new PersistenciaException("No se encontró la comanda con ID: " + idComanda);
            }

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw new PersistenciaException("Error al actualizar total de comanda: " + e.getMessage(), e);
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }

}
