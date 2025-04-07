package sistemarestaurantepersistencia.DAOS_implementaciones;

import java.time.LocalDate;
import java.util.Collections;
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

}
