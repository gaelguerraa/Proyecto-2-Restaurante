/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemarestaurantedominio.dtos;

import sistemarestaurantedominio.Ingrediente;
import sistemarestaurantedominio.Producto;
import sistemarestaurantedominio.UnidadMedidaIngrediente;

/**
 *
 * @author gael_
 */
public class NuevoIngredienteProductoDTO {
    private NuevoProductoDTO producto;
    private Ingrediente ingrediente;    
    private Float cantidadIngrediente;
       

    public NuevoIngredienteProductoDTO(NuevoProductoDTO productoSeleccionado, Ingrediente ingredienteSeleccionado, Float cantidad) {
    }

    public NuevoProductoDTO getNuevoProductoDTO() {
        return producto;
    }

    public void setNuevoProductoDTO(NuevoProductoDTO producto) {
        this.producto = producto;
    }

    public Ingrediente getIngrediente() {
        return ingrediente;
    }

    public void setIngrediente(Ingrediente ingrediente) {
        this.ingrediente = ingrediente;
    }

    
    public Float getCantidadIngrediente() {
        return cantidadIngrediente;
    }

    public void setCantidadIngrediente(Float cantidadIngrediente) {
        this.cantidadIngrediente = cantidadIngrediente;
    }


    
    
}
