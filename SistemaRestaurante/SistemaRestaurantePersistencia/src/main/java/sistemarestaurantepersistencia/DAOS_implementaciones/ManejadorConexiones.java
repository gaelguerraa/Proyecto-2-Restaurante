package sistemarestaurantepersistencia.DAOS_implementaciones;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ManejadorConexiones {

    /**
     * Metodo de fabrica que establece la conexion con la base de datos y retorna un entityManager para poder realizar cambios, consultas, etc.
     * @return regresa una entity manager
     */
    public static EntityManager getEntityManager() {
        EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("com.252522_SistemaRestaurantePersistencia_jar_1.0-SNAPSHOTPU");
        EntityManager entityManager = emFactory.createEntityManager();
        return entityManager;

    }
}
