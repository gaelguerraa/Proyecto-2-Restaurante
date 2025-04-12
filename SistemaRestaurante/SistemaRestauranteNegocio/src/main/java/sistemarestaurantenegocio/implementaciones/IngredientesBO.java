package sistemarestaurantenegocio.implementaciones;

import java.util.List;
import sistemarestaurantedominio.Ingrediente;
import sistemarestaurantedominio.UnidadMedidaIngrediente;
import sistemarestaurantedominio.dtos.NuevoIngredienteDTO;
import sistemarestaurantenegocio.IIngredientesBO;
import sistemarestaurantenegocio.excepciones.NegocioException;
import sistemarestaurantepersistencia.interfaces.IIngredientesDAO;

public class IngredientesBO implements IIngredientesBO{
    
    private IIngredientesDAO ingredienteDAO;
    private final Float CERO = 0.0f;
    private final int LIMITE_CARACTERES_NOMBRE = 50;
    //Obtiene todos los ingredientes para validar que no haya 2 con mismo nombre y unidad de medida
    private List<Ingrediente> ingredientes;

    public IngredientesBO(IIngredientesDAO ingredienteDAO) throws NegocioException {
        this.ingredienteDAO = ingredienteDAO;
        this.ingredientes = this.consultarIngredientes();
    }

    @Override
    public Ingrediente registrarIngrediente(NuevoIngredienteDTO nuevoIngrediente) throws NegocioException{
        /*Validaciones*/
        /*Nombre vacio*/
        if (nuevoIngrediente.getNombre().isEmpty()) {
            throw new NegocioException("El nombre del ingrediente no puede estar vacio");
        }
        
        /*Stock menor a 0*/
        if (nuevoIngrediente.getStock()<CERO||nuevoIngrediente.getStock()==null) {
            throw new NegocioException("El stock inicial no puede ser menor a 0");
        }
        
        /*Validar que el nombre pueda ser el mismo pero la medida no*/
        //Obtiene todos los ingredientes para validar que no haya 2 con mismo nombre y unidad de medida
//        List<Ingrediente> ingredientes = this.consultarIngredientes();
        for (Ingrediente ingrediente : ingredientes) {
            boolean existeNombre = ingrediente.getNombre().trim().toLowerCase().equals(nuevoIngrediente.getNombre().trim().toLowerCase());
            boolean existeUnidadMedida = ingrediente.getUnidadMedida().equals(nuevoIngrediente.getUnidadMedida());
            
            if (existeNombre && existeUnidadMedida) {
                throw new NegocioException("No puede haber 2 ingredientes con el mismo nombre y unidad de medida");
            }
        }
        
        
        return ingredienteDAO.guardarIngrediente(nuevoIngrediente);
    }

    @Override
    public List<Ingrediente> consultarIngredientes() throws NegocioException {
        //Agregar mas validaciones is hacen falta
//        if (ingredienteDAO.obtenerIngredientes().isEmpty()) {
//            throw new NegocioException("No se encontro ningun ingrediente");
//        }
        
        return ingredienteDAO.obtenerIngredientes();
    }

    @Override
    public List<Ingrediente> consultarIngredientesPorNombre(String nombre) throws NegocioException {
        
        /*Validaciones*/
        /*Se alcanzo el numero maximo de caracteres en el nombre*/
        if (nombre.length()>=LIMITE_CARACTERES_NOMBRE) {
            throw new NegocioException("Alcanzaste el limite de caracteres para el nombre");
        }
//        if (ingredienteDAO.obtenerIngredientesPorNombre(nombre).isEmpty()) {
//            throw new NegocioException("No se encontro ningun ingrediente");
//        }
//        
        //Agregar mas validaciones is hacen falta
        
        return ingredienteDAO.obtenerIngredientesPorNombre(nombre);
    }

    @Override
    public List<Ingrediente> consultarIngredientesPorUnidadMedida(String unidadMedida) throws NegocioException {
//        if (ingredienteDAO.obtenerIngredientesPorUnidadMedida(unidadMedida).isEmpty()) {
//            throw new NegocioException("No se encontro ningun ingrediente");
//        }
        return ingredienteDAO.obtenerIngredientesPorUnidadMedida(unidadMedida);
    }

    @Override
    public Integer aumentarStock(Ingrediente ingredienteStock, Float cantidadAumentar)throws NegocioException {
        /*Validaciones*/
        //La cantidad es menor o igual a 0
        if (cantidadAumentar<=CERO) {
            throw new NegocioException("La cantidad a aumentar debe ser mayor a 0");
        }
        
        return ingredienteDAO.aumentarStock(ingredienteStock, cantidadAumentar);
    }

    @Override
    public List<Ingrediente> consultarIngredientePorNombreYMedida(String nombre, String unidadMedida) throws NegocioException {
//        if (ingredienteDAO.obtenerIngredientePorNombreYMedida(nombre, unidadMedida).isEmpty()) {
//            throw new NegocioException("No se encontro ningun ingrediente");
//        }
        return ingredienteDAO.obtenerIngredientePorNombreYMedida(nombre, unidadMedida);
    }

    @Override
    public Integer disminuirStock(Ingrediente ingredienteStock, Float cantidadDisminuir) throws NegocioException {
        if (cantidadDisminuir>ingredienteStock.getStock()) {
            throw new NegocioException("La cantidad no puede ser mayor al stock actual");
        }
        
        return ingredienteDAO.disminuirStock(ingredienteStock, cantidadDisminuir);
    }
    
    
    
}
