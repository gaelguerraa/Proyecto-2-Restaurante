package sistemarestaurantepersistencia.DAOS_implementaciones;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import sistemarestaurantedominio.Ingrediente;
import sistemarestaurantedominio.dtos.NuevoIngredienteDTO;
import sistemarestaurantepersistencia.interfaces.IIngredientesDAO;

/**
 * Implementación de la interfaz IIngredientesDAO para el manejo de la persistencia de ingredientes.
 * Proporciona métodos para consultar, guardar, actualizar y buscar ingredientes en la base de datos.
 */
public class IngredientesDAO implements IIngredientesDAO {

    @Override
    public List<Ingrediente> obtenerIngredientes() {
        EntityManager em = ManejadorConexiones.getEntityManager();
        em.getTransaction().begin();
         
        TypedQuery<Ingrediente> query = em.createQuery("SELECT i FROM Ingrediente i", Ingrediente.class);
        return query.getResultList();
    }

    @Override
    public Ingrediente guardarIngrediente(NuevoIngredienteDTO nuevoIngrediente) {
        EntityManager em = ManejadorConexiones.getEntityManager();
        em.getTransaction().begin();
        
        Ingrediente ingrediente = new Ingrediente();
        ingrediente.setNombre(nuevoIngrediente.getNombre());
        ingrediente.setUnidadMedida(nuevoIngrediente.getUnidadMedida());
        ingrediente.setStock(nuevoIngrediente.getStock());
        
        em.persist(ingrediente);
        em.getTransaction().commit();
        
        return ingrediente;
    }

    @Override
    public List<Ingrediente> obtenerIngredientesPorNombre(String nombre) {
        EntityManager em = ManejadorConexiones.getEntityManager();
        CriteriaBuilder builder = em.getCriteriaBuilder();
        
        CriteriaQuery<Ingrediente> criteria = builder.createQuery(Ingrediente.class);
        
        Root<Ingrediente> root = criteria.from(Ingrediente.class);
        
        criteria = criteria.select(root).where(builder.like(root.get("nombre"), "%"+nombre+"%"));
        
        TypedQuery query = em.createQuery(criteria);
        
        List<Ingrediente> listaResultadoBusquedaIngredientes = query.getResultList();

        return listaResultadoBusquedaIngredientes;
    }

    @Override
    public List<Ingrediente> obtenerIngredientesPorUnidadMedida(String unidadMedida) {
        EntityManager em = ManejadorConexiones.getEntityManager();
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Ingrediente> criteria = builder.createQuery(Ingrediente.class);
        Root<Ingrediente> root = criteria.from(Ingrediente.class);
        
        criteria = criteria.select(root).where(builder.like(root.get("unidadMedida"), "%"+unidadMedida.toUpperCase()+"%"));
        
        TypedQuery query = em.createQuery(criteria);
        
        List<Ingrediente> listaResultadoBusquedaIngredientes = query.getResultList();
        
        return listaResultadoBusquedaIngredientes;
    }

    /**
     * Busca ingredientes cuyo nombre y unidad de medida coincidan con los valores proporcionados.
     * @param nombre El nombre del ingrediente a buscar.
     * @param unidadMedida La unidad de medida a buscar.
     * @return Lista de ingredientes que coinciden con los criterios de búsqueda.
     */
    @Override
    public List<Ingrediente> obtenerIngredientePorNombreYMedida(String nombre, String unidadMedida) {
        EntityManager em = ManejadorConexiones.getEntityManager();
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Ingrediente> criteria = builder.createQuery(Ingrediente.class);
        Root<Ingrediente> root = criteria.from(Ingrediente.class);
        
        Predicate condicionNombre = builder.like(root.get("nombre"), "%"+nombre.toLowerCase()+"%");
        Predicate condicionUnidadMedida = builder.like(root.get("unidadMedida"), "%"+unidadMedida.toUpperCase()+"%");
        
        criteria = criteria.select(root).where(builder.and(condicionNombre, condicionUnidadMedida));
        
        List<Ingrediente> ingredienteEncontrado = em.createQuery(criteria).getResultList();
        em.close();
        
        return ingredienteEncontrado;        
    }

    /**
     * Aumenta el stock de un ingrediente específico.
     * @param ingredienteStock Ingrediente al cual se le va a aumentar el stock.
     * @param cantidadAumentar Cantidad que se va a sumar al stock actual.
     * @return Número de registros afectados.
     */
    @Override
    public Integer aumentarStock(Long idIngrediente, Float cantidadAumentar) {
        EntityManager em = ManejadorConexiones.getEntityManager();
        
        int resultado = 0; 
        em.getTransaction().begin();
        String jpqlQuery = """
                           UPDATE Ingrediente i SET i.stock = i.stock + :cantidadAumentar
                           WHERE i.id = :idIngrediente
                           """;
        Query query = em.createQuery(jpqlQuery);
        query.setParameter("idIngrediente", idIngrediente);
        query.setParameter("cantidadAumentar", cantidadAumentar);
       
        resultado = query.executeUpdate();
        em.flush(); // Asegura que los cambios se envíen a la base de datos

        em.clear(); // Limpia el contexto de persistencia para evitar inconsistencias
        em.getTransaction().commit();
        System.out.println("Filas afectadas: "+resultado);
        
        return resultado;
    }

    /**
     * Disminuye el stock de un ingrediente específico.
     * @param idIngrediente id del ingrediente
     * @param cantidadDisminuir Cantidad que se va a restar del stock actual.
     * @return Número de registros afectados.
     */
    @Override
    public Integer disminuirStock(Long idIngrediente, Float cantidadDisminuir) {
        EntityManager em = ManejadorConexiones.getEntityManager();
        try {
            em.getTransaction().begin();

            // Re-obtener la entidad gestionada
            Ingrediente ingrediente = em.find(Ingrediente.class, idIngrediente);

            if (ingrediente == null) {
                em.getTransaction().rollback();
                return 0;
            }

            Float nuevoStock = ingrediente.getStock() - cantidadDisminuir;
            ingrediente.setStock(nuevoStock);

            em.getTransaction().commit();
            return 1;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            return -1;
        } finally {
            if (em.isOpen()){
                em.close();
            }

        }
    
        
    }
}
