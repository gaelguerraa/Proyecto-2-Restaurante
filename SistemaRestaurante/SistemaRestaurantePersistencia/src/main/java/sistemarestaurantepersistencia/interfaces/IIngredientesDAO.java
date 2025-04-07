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
    public abstract List<Ingrediente> obtenerIngredientesPorUnidadMedida(String unidadMedida);
    public abstract List<Ingrediente> obtenerIngredientePorNombreYMedida(String nombre, String unidadMedida);
    public abstract Integer aumentarStock(Ingrediente ingredienteStock,Float cantidadAumentar);
    public abstract Integer disminuirStock(Ingrediente ingredienteStock, Float cantidadDisminuir);
    
}
