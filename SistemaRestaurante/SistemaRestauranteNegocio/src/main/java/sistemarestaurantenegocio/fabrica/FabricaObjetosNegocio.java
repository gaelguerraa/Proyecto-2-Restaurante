/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemarestaurantenegocio.fabrica;

import sistemarestaurantenegocio.IProductosBO;
import sistemarestaurantenegocio.implementaciones.ProductosBO;
import sistemarestaurantepersistencia.DAOS_implementaciones.ProductosDAO;
import sistemarestaurantepersistencia.interfaces.IProductosDAO;

/**
 *
 * @author gael_
 */
public class FabricaObjetosNegocio {
    public static IProductosBO crearProductosBO(){
        IProductosDAO productosDAO = new ProductosDAO();
        IProductosBO productosBO = new ProductosBO(productosDAO);
        return productosBO;
    }
}


