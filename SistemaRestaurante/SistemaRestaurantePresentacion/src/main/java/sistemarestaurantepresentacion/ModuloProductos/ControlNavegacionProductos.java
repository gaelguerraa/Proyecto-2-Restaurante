/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemarestaurantepresentacion.ModuloProductos;

import sistemarestaurantedominio.Producto;
import sistemarestaurantenegocio.IIngredientesProductosBO;
import sistemarestaurantenegocio.IProductosBO;
import sistemarestaurantenegocio.fabrica.FabricaObjetosNegocio;
import sistemarestaurantenegocio.implementaciones.ProductosBO;
import sistemarestaurantepersistencia.DAOS_implementaciones.ProductosDAO;

/**
 *
 * @author gael_
 */
public class ControlNavegacionProductos {
    
    private IProductosBO productosBO;
    private IIngredientesProductosBO ingredientesProductosBO;
    private frmMenuProductos menuProductos;
    private frmRegistrarProducto registrarProducto;
    private frmAnadirIngredienteProducto anadirIngredienteProducto;
    private frmMostrarProductos mostrarProductos;
     private frmBuscarProducto buscarProducto;
     


    public ControlNavegacionProductos() {
        this.productosBO= FabricaObjetosNegocio.crearProductosBO();
        this.ingredientesProductosBO = FabricaObjetosNegocio.crearIngredientesProductosBO();
    }
    
    
    public void mostrarMenuProductos() {
        menuProductos.setVisible(true);
        registrarProducto.setVisible(false);
        anadirIngredienteProducto.setVisible(false);
        mostrarProductos.setVisible(false);
    }

    public void mostrarRegistrarProducto() {
        menuProductos.setVisible(false);
        registrarProducto.setVisible(true);
        anadirIngredienteProducto.setVisible(false);
        mostrarProductos.setVisible(false);
    }

    public void mostrarAnadirIngredienteProducto() {
        menuProductos.setVisible(false);
        registrarProducto.setVisible(false);
        anadirIngredienteProducto.setVisible(true);
        mostrarProductos.setVisible(false);
    }
    
    public void mostrarProductos(){
        menuProductos.setVisible(false);
        registrarProducto.setVisible(false);
        anadirIngredienteProducto.setVisible(false);
        mostrarProductos.setVisible(true);
    }
    
    //metodo para devolver producto a comandas
    public Producto regresarProductoSeleccionado(){
        this.buscarProducto = new frmBuscarProducto(productosBO, this);
        return buscarProducto.devolverProducto();
    }
    
    
    
}
