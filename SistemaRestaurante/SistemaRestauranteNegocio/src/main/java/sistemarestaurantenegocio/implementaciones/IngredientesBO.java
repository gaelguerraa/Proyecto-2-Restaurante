/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemarestaurantenegocio.implementaciones;

import java.util.List;
import sistemarestaurantedominio.Ingrediente;
import sistemarestaurantedominio.UnidadMedidaIngrediente;
import sistemarestaurantedominio.dtos.NuevoIngredienteDTO;
import sistemarestaurantenegocio.IIngredientesBO;
import sistemarestaurantenegocio.excepciones.NegocioException;
import sistemarestaurantepersistencia.interfaces.IIngredientesDAO;

/**
 *
 * @author jalt2
 */
public class IngredientesBO implements IIngredientesBO{
    
    private IIngredientesDAO ingredienteDAO;
    private final Float CERO = 0.0f;
    private final int LIMITE_CARACTERES_NOMBRE = 50;

    public IngredientesBO(IIngredientesDAO ingredienteDAO) {
        this.ingredienteDAO = ingredienteDAO;
    }

    @Override
    public Ingrediente registrarIngrediente(NuevoIngredienteDTO nuevoIngrediente) throws NegocioException{
        /*Validaciones*/
        /*Nombre vacio*/
        if (nuevoIngrediente.getNombre().isEmpty()) {
            throw new NegocioException("El nombre del ingrediente no puede estar vacio");
        }
        
        /*Stock menor a 0*/
        if (nuevoIngrediente.getStock()<CERO) {
            throw new NegocioException("El stock inicial no puede ser menor a 0");
        }
        
        /*Validar que el nombre pueda ser el mismo pero la medida no*/
        
        //Agregar mas validaciones is hacen falta
        
        return ingredienteDAO.guardarIngrediente(nuevoIngrediente);
    }

    @Override
    public List<Ingrediente> consultarIngredientes() throws NegocioException {
        //Agregar mas validaciones is hacen falta
        return ingredienteDAO.obtenerIngredientes();
    }

    @Override
    public List<Ingrediente> consultarIngredientesPorNombre(String nombre) throws NegocioException {
        
        /*Validaciones*/
        /*Se alcanzo el numero maximo de caracteres en el nombre*/
        if (nombre.length()>=LIMITE_CARACTERES_NOMBRE) {
            throw new NegocioException("Alcanzaste el limite de caracteres para el nombre");
        }
        
        //Agregar mas validaciones is hacen falta
        
        return ingredienteDAO.obtenerIngredientesPorNombre(nombre);
    }

    @Override
    public List<Ingrediente> consultarIngredientesPorUnidadMedida(String unidadMedida) throws NegocioException {
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
        
        return ingredienteDAO.obtenerIngredientePorNombreYMedida(nombre, unidadMedida);
    }
    
    
    
}
