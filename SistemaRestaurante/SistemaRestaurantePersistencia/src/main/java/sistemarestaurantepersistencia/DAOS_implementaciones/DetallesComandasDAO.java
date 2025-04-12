/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemarestaurantepersistencia.DAOS_implementaciones;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import sistemarestaurantedominio.DetallesComanda;
import sistemarestaurantedominio.dtos.NuevoDetalleComandaDTO;
import sistemarestaurantepersistencia.exception.PersistenciaException;
import sistemarestaurantepersistencia.interfaces.IDetallesComandasDAO;

/**
 *
 * @author gael_
 */
public class DetallesComandasDAO implements IDetallesComandasDAO {

    /**
     * Metodo que registra un nuevo detalle para una comanda
     * @param detalleComanda Recibe como parametro un NuevoDetalleComandaDTO
     * @return Regresa un detalle comanda
     */
    @Override
    public DetallesComanda guardarDetalleComanda(NuevoDetalleComandaDTO detalleComanda) {

        EntityManager em = ManejadorConexiones.getEntityManager();
        em.getTransaction().begin();

        DetallesComanda dc = new DetallesComanda();
        dc.setCantidadProducto(detalleComanda.getCantidad());
        dc.setPrecioUnitarioProducto(detalleComanda.getPrecioActual());
        dc.setImporteTotal(detalleComanda.getImporte());
        dc.setNota(detalleComanda.getNota());
        dc.setComanda(detalleComanda.getComanda());
        dc.setProducto(detalleComanda.getProduto());

        em.persist(dc);
        em.getTransaction().commit();

        return dc;
    }

    /**
     * Metodo que obtiene los detalles (productos cantidades, etc) segun un id de una comanda existente
     * @param idComanda Recibe un id de una comanda existente
     * @return Regresa una lista de tipo DetallesComanda
     * @throws PersistenciaException 
     */
    @Override
    public List<DetallesComanda> obtenerDetallesComanda(Long idComanda) throws PersistenciaException {
        EntityManager entityManager = ManejadorConexiones.getEntityManager();
        try {
            String jpql = "SELECT d FROM DetallesComanda d WHERE d.comanda.id = :idComanda";

            TypedQuery<DetallesComanda> query = entityManager.createQuery(jpql, DetallesComanda.class);
            query.setParameter("idComanda", idComanda);

            return query.getResultList();
        } catch (Exception e) {
            throw new PersistenciaException("Error al obtener detalles de comanda: " + e.getMessage(), e);
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }

    /**
     * Metodo que elimina los detalles de una comanda, usado para actualizar comanda
     * @param idComanda Recibe un id de una comanda existente
     * @throws PersistenciaException 
     */
    @Override
    public void eliminarDetallesPorComanda(Long idComanda) throws PersistenciaException {
        EntityManager entityManager = ManejadorConexiones.getEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            String jpql = "DELETE FROM DetallesComanda d WHERE d.comanda.id = :idComanda";
            int deletedCount = entityManager.createQuery(jpql)
                    .setParameter("idComanda", idComanda)
                    .executeUpdate();

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw new PersistenciaException("Error al eliminar detalles de comanda: " + e.getMessage(), e);
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }

}
