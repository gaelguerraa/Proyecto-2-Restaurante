package sistemarestaurantenegocio.implementaciones;

import java.util.List;
import sistemarestaurantedominio.Ingrediente;
import sistemarestaurantedominio.dtos.NuevoIngredienteDTO;
import sistemarestaurantenegocio.IIngredientesBO;
import sistemarestaurantenegocio.excepciones.NegocioException;
import sistemarestaurantepersistencia.interfaces.IIngredientesDAO;

public class IngredientesBO implements IIngredientesBO {
    
    private IIngredientesDAO ingredienteDAO;
    private final Float CERO = 0.0f;
    private final int LIMITE_CARACTERES_NOMBRE = 50;
    private List<Ingrediente> ingredientes;

    /**
     * Constructor de la clase IngredientesBO.
     * 
     * @param ingredienteDAO DAO de ingredientes para acceder a los datos.
     * @throws NegocioException si ocurre un error al consultar los ingredientes.
     */
    public IngredientesBO(IIngredientesDAO ingredienteDAO) throws NegocioException {
        this.ingredienteDAO = ingredienteDAO;
        this.ingredientes = this.consultarIngredientes();
    }

    /**
     * Registra un nuevo ingrediente en el sistema.
     * 
     * @param nuevoIngrediente DTO con los datos del nuevo ingrediente.
     * @return Ingrediente registrado.
     * @throws NegocioException si el ingrediente no cumple las validaciones.
     */
    @Override
    public Ingrediente registrarIngrediente(NuevoIngredienteDTO nuevoIngrediente) throws NegocioException {
        if (nuevoIngrediente.getNombre().isEmpty()) {
            throw new NegocioException("El nombre del ingrediente no puede estar vacio");
        }

        if (nuevoIngrediente.getStock() < CERO || nuevoIngrediente.getStock() == null) {
            throw new NegocioException("El stock inicial no puede ser menor a 0");
        }

        for (Ingrediente ingrediente : ingredientes) {
            boolean existeNombre = ingrediente.getNombre().trim().toLowerCase().equals(nuevoIngrediente.getNombre().trim().toLowerCase());
            boolean existeUnidadMedida = ingrediente.getUnidadMedida().equals(nuevoIngrediente.getUnidadMedida());
            
            if (existeNombre && existeUnidadMedida) {
                throw new NegocioException("No puede haber 2 ingredientes con el mismo nombre y unidad de medida");
            }
        }
        
        return ingredienteDAO.guardarIngrediente(nuevoIngrediente);
    }

    /**
     * Consulta todos los ingredientes existentes.
     * 
     * @return Lista de ingredientes.
     * @throws NegocioException si ocurre un error durante la consulta.
     */
    @Override
    public List<Ingrediente> consultarIngredientes() throws NegocioException {
        return ingredienteDAO.obtenerIngredientes();
    }

    /**
     * Consulta ingredientes filtrados por nombre.
     * 
     * @param nombre Nombre del ingrediente a buscar.
     * @return Lista de ingredientes con el nombre especificado.
     * @throws NegocioException si el nombre supera el límite de caracteres.
     */
    @Override
    public List<Ingrediente> consultarIngredientesPorNombre(String nombre) throws NegocioException {
        if (nombre.length() >= LIMITE_CARACTERES_NOMBRE) {
            throw new NegocioException("Alcanzaste el limite de caracteres para el nombre");
        }
        
        return ingredienteDAO.obtenerIngredientesPorNombre(nombre);
    }

    /**
     * Consulta ingredientes por unidad de medida.
     * 
     * @param unidadMedida Unidad de medida a buscar.
     * @return Lista de ingredientes con la unidad de medida especificada.
     * @throws NegocioException si ocurre un error durante la consulta.
     */
    @Override
    public List<Ingrediente> consultarIngredientesPorUnidadMedida(String unidadMedida) throws NegocioException {
        return ingredienteDAO.obtenerIngredientesPorUnidadMedida(unidadMedida);
    }

    /**
     * Aumenta el stock de un ingrediente.
     * 
     * @param ingredienteStock Ingrediente al que se aumentará el stock.
     * @param cantidadAumentar Cantidad a aumentar.
     * @return Cantidad actualizada de stock.
     * @throws NegocioException si la cantidad es menor o igual a 0.
     */
    @Override
    public Integer aumentarStock(Ingrediente ingredienteStock, Float cantidadAumentar) throws NegocioException {
        if (cantidadAumentar <= CERO) {
            throw new NegocioException("La cantidad a aumentar debe ser mayor a 0");
        }
        
        return ingredienteDAO.aumentarStock(ingredienteStock, cantidadAumentar);
    }

    /**
     * Consulta un ingrediente por nombre y unidad de medida.
     * 
     * @param nombre Nombre del ingrediente.
     * @param unidadMedida Unidad de medida del ingrediente.
     * @return Lista de ingredientes que coincidan con nombre y unidad de medida.
     * @throws NegocioException si ocurre un error durante la consulta.
     */
    @Override
    public List<Ingrediente> consultarIngredientePorNombreYMedida(String nombre, String unidadMedida) throws NegocioException {
        return ingredienteDAO.obtenerIngredientePorNombreYMedida(nombre, unidadMedida);
    }

    /**
     * Disminuye el stock de un ingrediente.
     * 
     * @param ingredienteStock Ingrediente al que se disminuirá el stock.
     * @param cantidadDisminuir Cantidad a disminuir.
     * @return Cantidad actualizada de stock.
     * @throws NegocioException si la cantidad a disminuir es mayor al stock actual.
     */
    @Override
    public Integer disminuirStock(Ingrediente ingredienteStock, Float cantidadDisminuir) throws NegocioException {
        if (cantidadDisminuir > ingredienteStock.getStock()) {
            throw new NegocioException("La cantidad no puede ser mayor al stock actual");
        }
        
        return ingredienteDAO.disminuirStock(ingredienteStock, cantidadDisminuir);
    }
}
