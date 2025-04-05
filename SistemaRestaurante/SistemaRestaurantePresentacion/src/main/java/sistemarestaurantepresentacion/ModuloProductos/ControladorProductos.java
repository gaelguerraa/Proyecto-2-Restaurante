/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemarestaurantepresentacion.ModuloProductos;

import sistemarestaurantenegocio.IIngredientesProductosBO;
import sistemarestaurantenegocio.IProductosBO;
import sistemarestaurantenegocio.implementaciones.ProductosBO;
import sistemarestaurantepersistencia.DAOS_implementaciones.ProductosDAO;

/**
 *
 * @author gael_
 */
public class ControladorProductos {
    
    
    private frmMenuProductos menuProductos;
    private frmRegistrarProducto registrarProducto;
    private frmAnadirIngredienteProducto anadirIngredienteProducto;
    private frmMostrarProductos mostrarProductos;
    
    public ControladorProductos(IIngredientesProductosBO ingredientesProductosBO, IProductosBO productosBO) {
        this.menuProductos = new frmMenuProductos(this);
        this.registrarProducto = new frmRegistrarProducto(productosBO, this);
        this.anadirIngredienteProducto = new frmAnadirIngredienteProducto(ingredientesProductosBO, productosBO, this);
        this.mostrarProductos = new frmMostrarProductos(productosBO, this);
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

    
}
