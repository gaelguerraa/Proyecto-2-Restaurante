/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemarestaurantedominio.dtos;

import java.util.List;
import sistemarestaurantedominio.IngredienteProducto;
import sistemarestaurantedominio.TipoProducto;

/**
 *
 * @author gael_
 */
public class NuevoProductoDTO {
    private String nombre;
    private Float precio;
    private TipoProducto tipo;
    private List<IngredienteProducto> ingredientes;

    public NuevoProductoDTO(String nombre, Float precio, TipoProducto tipo, List<IngredienteProducto> ingredientes) {
        this.nombre = nombre;
        this.precio = precio;
        this.tipo = tipo;
        this.ingredientes = ingredientes;
    }

    public String getNombre() {
        return nombre;
    }

    public Float getPrecio() {
        return precio;
    }

    public TipoProducto getTipo() {
        return tipo;
    }

    public List<IngredienteProducto> getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(List<IngredienteProducto> ingredientes) {
        this.ingredientes = ingredientes;
    }
    
    
}
