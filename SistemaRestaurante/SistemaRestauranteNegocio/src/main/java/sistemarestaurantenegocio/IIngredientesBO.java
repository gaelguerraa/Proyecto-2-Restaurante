/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package sistemarestaurantenegocio;

import java.util.List;
import sistemarestaurantedominio.Ingrediente;
import sistemarestaurantedominio.UnidadMedidaIngrediente;
import sistemarestaurantedominio.dtos.NuevoIngredienteDTO;
import sistemarestaurantenegocio.excepciones.NegocioException;

/**
 *
 * @author jalt2
 */
public interface IIngredientesBO {
    public abstract Ingrediente registrarIngrediente(NuevoIngredienteDTO nuevoIngrediente)throws NegocioException;
    public abstract List<Ingrediente> consultarIngredientes() throws NegocioException;
    public abstract List<Ingrediente> consultarIngredientesPorNombre(String Nombre) throws NegocioException;
    public abstract List<Ingrediente> consultarIngredientesPorUnidadMedida(UnidadMedidaIngrediente unidadMedida) throws NegocioException;
    public abstract Integer aumentarStock(Ingrediente ingredienteStock, Integer cantidadAumentar)throws NegocioException;
}
