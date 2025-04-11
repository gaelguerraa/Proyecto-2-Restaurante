package sistemarestaurantepersistencia.DAOS_implementaciones;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import sistemarestaurantedominio.Comanda;
import sistemarestaurantedominio.DetallesComanda;
import sistemarestaurantedominio.EstadoComanda;
import sistemarestaurantedominio.Mesa;
import sistemarestaurantedominio.dtos.NuevaComandaDTO;
import sistemarestaurantedominio.dtos.ProductoComandaDTO;
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

    @Override
    public List<Mesa> obtenerMesas() {
        EntityManager entityManager = ManejadorConexiones.getEntityManager();

        entityManager.getTransaction().begin();

        TypedQuery<Mesa> query = entityManager.createQuery("SELECT m FROM Mesa m", Mesa.class);

        List<Mesa> mesas = query.getResultList();

        entityManager.close();
        return mesas;
    }

    @Override
    public Mesa buscarMesaPorNumero(int numeroMesa) {
        EntityManager em = ManejadorConexiones.getEntityManager();
        TypedQuery<Mesa> query = em.createQuery("""
                                                SELECT m FROM Mesa m WHERE m.numeroMesa = :numeroMesa
                                                """, Mesa.class);
        query.setParameter("numeroMesa", numeroMesa);
        return query.getSingleResult();
    }

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

    @Override
    public List<ProductoComandaDTO> obtenerProductosDetalladosComanda(String folioComanda) {
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
                        detalle.getCantidadProducto(),
                        detalle.getNota(),
                        detalle.getPrecioUnitarioProducto(),
                        detalle.getTotal()
                );
                productosComanda.add(productoDTO);
            }
        }

        return productosComanda;
    }

    @Override
    public Integer actualizarEstadoComanda(Comanda comanda, EstadoComanda estado) {
        EntityManager  em = ManejadorConexiones.getEntityManager();
        
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

}
