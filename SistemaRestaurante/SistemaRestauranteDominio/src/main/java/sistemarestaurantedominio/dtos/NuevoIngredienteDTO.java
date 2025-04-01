/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemarestaurantedominio.dtos;

import sistemarestaurantedominio.UnidadMedidaIngrediente;

/**
 *
 * @author jalt2
 */
public class NuevoIngredienteDTO {
    private String nombre;
    private UnidadMedidaIngrediente unidadMedida;
    private Float stock;

    public NuevoIngredienteDTO(String nombre, UnidadMedidaIngrediente unidadMedida, Float stock) {
        this.nombre = nombre;
        this.unidadMedida = unidadMedida;
        this.stock = stock;
    }

    public String getNombre() {
        return nombre;
    }

    public UnidadMedidaIngrediente getUnidadMedida() {
        return unidadMedida;
    }

    public Float getStock() {
        return stock;
    }
    
    
}
