/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemarestaurantepersistencia.DAOS_implementaciones;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;
import sistemarestaurantedominio.Ingrediente;
import sistemarestaurantedominio.UnidadMedidaIngrediente;
import sistemarestaurantedominio.dtos.NuevoIngredienteDTO;
import sistemarestaurantepersistencia.interfaces.IIngredientesDAO;

/**
 *
 * @author gael_
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
    public List<Ingrediente> obtenerIngredientesPorUnidadMedida(UnidadMedidaIngrediente unidadMedida) {
        EntityManager  em = ManejadorConexiones.getEntityManager();
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Ingrediente> criteria = builder.createQuery(Ingrediente.class);
        Root<Ingrediente> root = criteria.from(Ingrediente.class);
        
        //No recuerdo si root.get() se ponia el atributo o el nombre de la columna, puse el de el atributo me hizo mas logica
        criteria = criteria.select(root).where(builder.like(root.get("unidadMedida"), "%"+unidadMedida+"%"));
        
        TypedQuery query = em.createQuery(criteria);
        
        List<Ingrediente> listaResultadoBusquedaIngredientes = query.getResultList();
        
        return listaResultadoBusquedaIngredientes;
        
    }
    
    
    //Verificar si funciona
    @Override
    public Integer aumentarStock(Ingrediente ingredienteStock, Integer cantidadAumentar) {
        EntityManager  em = ManejadorConexiones.getEntityManager();
        em.getTransaction().begin();
        
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaUpdate<Ingrediente> update = builder.createCriteriaUpdate(Ingrediente.class);
        Root<Ingrediente> root = update.from(Ingrediente.class);
        
        update = update.set(root.get("stock"), cantidadAumentar).where(builder.equal(root.get("id"), ingredienteStock.getId()));
        
        int resultado = em.createQuery(update).executeUpdate();
        em.getTransaction().commit();
        
        return resultado;
    }
    
}
