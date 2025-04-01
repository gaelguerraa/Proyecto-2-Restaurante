/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package sistemarestaurantepersistencia.interfaces;

import java.util.List;
import sistemarestaurantedominio.Ingrediente;
import sistemarestaurantedominio.UnidadMedidaIngrediente;
import sistemarestaurantedominio.dtos.NuevoIngredienteDTO;

/**
 *
 * @author gael_
 */
public interface IIngredientesDAO  {
    public abstract Ingrediente guardarIngrediente(NuevoIngredienteDTO nuevoIngrediente);
    public abstract List<Ingrediente> obtenerIngredientes();
    public abstract List<Ingrediente> obtenerIngredientesPorNombre(String nombre);
    public abstract List<Ingrediente> obtenerIngredientesPorUnidadMedida(UnidadMedidaIngrediente unidadMedida);
    public abstract Integer aumentarStock(Ingrediente ingredienteStock,Integer cantidadAumentar);
}
