/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package sistemarestaurantepersistencia.interfaces;

import java.util.List;
import sistemarestaurantedominio.Ingrediente;

/**
 *
 * @author gael_
 */
public interface IIngredientesDAO  {
    
    public abstract List<Ingrediente> obtenerIngredientes();
}
