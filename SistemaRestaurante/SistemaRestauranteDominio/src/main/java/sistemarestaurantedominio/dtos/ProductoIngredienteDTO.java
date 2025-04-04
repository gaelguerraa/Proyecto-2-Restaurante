/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemarestaurantedominio.dtos;

import sistemarestaurantedominio.TipoProducto;
import sistemarestaurantedominio.UnidadMedidaIngrediente;

/**
 *
 * @author gael_
 */
public class ProductoIngredienteDTO {
    private String nombreProducto;
    private TipoProducto tipoProducto;
    private String nombreIngrediente;
    private UnidadMedidaIngrediente medidaIngrediente;
    private Float cantidadIngrediente;

    public ProductoIngredienteDTO(String nombreProducto,
            TipoProducto tipoProducto,
            String nombreIngrediente, UnidadMedidaIngrediente medidaIngrediente,
            Float cantidadIngrediente) {
        this.nombreProducto = nombreProducto;
        this.tipoProducto = tipoProducto;
        this.nombreIngrediente = nombreIngrediente;
        this.medidaIngrediente = medidaIngrediente;
        this.cantidadIngrediente = cantidadIngrediente;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public TipoProducto getTipoProducto() {
        return tipoProducto;
    }

    public String getNombreIngrediente() {
        return nombreIngrediente;
    }

    public UnidadMedidaIngrediente getMedidaIngrediente() {
        return medidaIngrediente;
    }

    public Float getCantidadIngrediente() {
        return cantidadIngrediente;
    }
    
    
    
}
