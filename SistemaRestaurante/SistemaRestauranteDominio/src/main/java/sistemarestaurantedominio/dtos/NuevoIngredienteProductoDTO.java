/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemarestaurantedominio.dtos;

import sistemarestaurantedominio.UnidadMedidaIngrediente;

/**
 *
 * @author gael_
 */
public class NuevoIngredienteProductoDTO {
    private long idProducto;
    private long igIngrediente;
    private Float cantidadIngrediente;
    private UnidadMedidaIngrediente Unidadmedida;        

    public NuevoIngredienteProductoDTO() {
    }

    public long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(long idProducto) {
        this.idProducto = idProducto;
    }

    public long getIgIngrediente() {
        return igIngrediente;
    }

    public void setIgIngrediente(long igIngrediente) {
        this.igIngrediente = igIngrediente;
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
