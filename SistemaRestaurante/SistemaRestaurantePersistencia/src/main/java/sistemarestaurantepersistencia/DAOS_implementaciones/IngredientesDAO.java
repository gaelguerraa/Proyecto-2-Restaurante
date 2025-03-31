/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemarestaurantepersistencia.DAOS_implementaciones;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import sistemarestaurantedominio.Ingrediente;
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
    
}
