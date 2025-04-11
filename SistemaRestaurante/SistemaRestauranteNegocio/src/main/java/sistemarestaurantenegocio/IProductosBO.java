/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package sistemarestaurantenegocio;

import java.util.List;
import sistemarestaurantedominio.Producto;
import sistemarestaurantedominio.TipoProducto;
import sistemarestaurantedominio.dtos.NuevoProductoDTO;
import sistemarestaurantedominio.dtos.ProductoIngredienteDTO;
import sistemarestaurantenegocio.excepciones.NegocioException;

/**
 *
 * @author gael_
 */
public interface IProductosBO {
    public abstract Producto registrar(NuevoProductoDTO nuevoProducto) throws NegocioException;
    
    public abstract List<Producto> consultarProducto();
    
     public abstract List<Producto> obtenerProductosFiltroNombre(String filtroBusqueda);
    
    public abstract List<Producto> obtenerProductosPorTipo(String tipo);
    
    public abstract List<Producto> obtenerProductosPorTipoNombre(String filtroBusqueda, String tipo);
    
    public abstract List<ProductoIngredienteDTO> obtenerProductosJoin();
    
    public abstract Producto consultarProductoPorNombre(String nombre);
    
    public abstract void actualizarPrecioProducto(Producto producto, Float nuevoPrecio) throws NegocioException;
    
}
