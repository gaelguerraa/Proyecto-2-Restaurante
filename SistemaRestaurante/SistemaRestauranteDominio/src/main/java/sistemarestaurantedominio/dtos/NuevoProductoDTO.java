/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemarestaurantedominio.dtos;

import sistemarestaurantedominio.TipoProducto;

/**
 *
 * @author gael_
 */
public class NuevoProductoDTO {
    private String nombre;
    private Float precio;
    private TipoProducto tipo;

    public NuevoProductoDTO(String nombre, Float precio, TipoProducto tipo) {
        this.nombre = nombre;
        this.precio = precio;
        this.tipo = tipo;
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
    
    
}
