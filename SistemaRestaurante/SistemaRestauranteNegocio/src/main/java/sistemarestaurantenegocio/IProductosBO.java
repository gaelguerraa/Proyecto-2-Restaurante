/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package sistemarestaurantenegocio;

import java.util.List;
import sistemarestaurantedominio.Producto;
import sistemarestaurantedominio.TipoProducto;
import sistemarestaurantedominio.dtos.NuevoProductoDTO;
import sistemarestaurantenegocio.excepciones.NegocioException;

/**
 *
 * @author gael_
 */
public interface IProductosBO {
    public abstract Producto registrar(NuevoProductoDTO nuevoProducto) throws NegocioException;
    
    public abstract List<Producto> consultarProducto();
    
     public abstract List<Producto> obtenerProductosFiltroNombre(String filtroBusqueda);
    
    public abstract List<Producto> obtenerProductosPorTipo(TipoProducto tipo);
    
    public abstract List<Producto> obtenerProductosPorTipoNombre(String filtroBusqueda, TipoProducto tipo);
}
