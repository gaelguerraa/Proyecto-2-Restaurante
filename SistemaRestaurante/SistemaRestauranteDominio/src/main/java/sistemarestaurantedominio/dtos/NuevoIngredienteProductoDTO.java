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
    private UnidadMedidaIngrediente Unidadmedida;        

    public NuevoIngredienteProductoDTO() {
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

    public UnidadMedidaIngrediente getUnidadmedida() {
        return Unidadmedida;
    }

    public void setUnidadmedida(UnidadMedidaIngrediente Unidadmedida) {
        this.Unidadmedida = Unidadmedida;
    }
    
    
    
}
