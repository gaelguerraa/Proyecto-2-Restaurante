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
    private Producto producto;
    private Ingrediente ingrediente;    
    private Float cantidadIngrediente;
       

    public NuevoIngredienteProductoDTO() {
    }

    public NuevoIngredienteProductoDTO(Producto producto, Ingrediente ingrediente, Float cantidadIngrediente) {
        this.producto = producto;
        this.ingrediente = ingrediente;
        this.cantidadIngrediente = cantidadIngrediente;
    }
    
    

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
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
