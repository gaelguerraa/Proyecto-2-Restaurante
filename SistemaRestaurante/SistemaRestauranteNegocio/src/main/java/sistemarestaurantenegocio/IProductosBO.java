/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package sistemarestaurantenegocio;

import java.util.List;
import sistemarestaurantedominio.Producto;
import sistemarestaurantedominio.dtos.NuevoProductoDTO;
import sistemarestaurantenegocio.excepciones.NegocioException;

/**
 *
 * @author gael_
 */
public interface IProductosBO {
    public abstract Producto registrar(NuevoProductoDTO nuevoProducto) throws NegocioException;
    
    public abstract List<Producto> consultarProducto() throws NegocioException;
}
