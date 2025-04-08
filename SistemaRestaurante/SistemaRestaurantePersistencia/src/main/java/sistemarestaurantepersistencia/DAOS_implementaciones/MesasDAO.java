package sistemarestaurantepersistencia.DAOS_implementaciones;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import sistemarestaurantedominio.Mesa;
import sistemarestaurantepersistencia.interfaces.IMesasDAO;

public class MesasDAO implements IMesasDAO {

    /**
     * Metodo que realiza una insercion masiva de mesas en la base de datos de 20 mesas, su capacidad es aleatoria entre 2 y 6
     * 
     */
    @Override
    public void cargarMesas() {
        EntityManager em = ManejadorConexiones.getEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();

            for (int i = 1; i <= 20; i++) {
                Mesa mesa = new Mesa();
                mesa.setNumeroMesa(i);            // Asignamos numero del 1 al 20
                int capacidadAleatoria = (int) (Math.random() * (6 - 2 + 1)) + 2; // se pone una capacidad entre 2 y 6 ALEATORIA
                mesa.setCapacidad(capacidadAleatoria);

                em.persist(mesa);
            }

            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }

    }

}
