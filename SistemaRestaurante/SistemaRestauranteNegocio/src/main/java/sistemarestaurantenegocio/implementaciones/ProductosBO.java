/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemarestaurantenegocio.implementaciones;

import java.util.List;
import sistemarestaurantedominio.Producto;
import sistemarestaurantedominio.dtos.NuevoProductoDTO;
import sistemarestaurantenegocio.IProductosBO;
import sistemarestaurantenegocio.excepciones.NegocioException;
import sistemarestaurantepersistencia.interfaces.IProductosDAO;

/**
 *
 * @author gael_
 */
public class ProductosBO implements IProductosBO {
    
    private IProductosDAO productosDAO;
    private Float CERO = 0.0f;
    private Float PRECIO_MAXIMO_EJEMPLO = 99999.9f;

    public ProductosBO(IProductosDAO productosDAO){
        this.productosDAO=productosDAO;
    }
    
    @Override
    public Producto registrar(NuevoProductoDTO nuevoProducto) throws NegocioException {
        if(nuevoProducto.getNombre().isEmpty()){
            throw new NegocioException("El nombre del producto no puede estar vacio.");
        }
        if(nuevoProducto.getPrecio() == null || nuevoProducto.getPrecio() <= CERO || nuevoProducto.getPrecio() > PRECIO_MAXIMO_EJEMPLO){
            throw new NegocioException("El precio no puede ser nulo, negativo, ni mayor a cien mil pesos");
        }
        return this.productosDAO.guardar(nuevoProducto);
    }

    @Override
    public List<Producto> consultarProducto() throws NegocioException {
        return this.productosDAO.obtenerProductos();
    }
    
    
}
