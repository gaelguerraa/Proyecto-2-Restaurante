/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemarestaurantenegocio.fabrica;

import sistemarestaurantenegocio.IClientesFrecuentesBO;
import sistemarestaurantenegocio.IIngredientesProductosBO;
import sistemarestaurantenegocio.IProductosBO;
import sistemarestaurantenegocio.implementaciones.ClientesFrecuentesBO;
import sistemarestaurantenegocio.implementaciones.IngredientesProductosBO;
import sistemarestaurantenegocio.implementaciones.ProductosBO;
import sistemarestaurantepersistencia.DAOS_implementaciones.ClientesFrecuentesDAO;
import sistemarestaurantepersistencia.DAOS_implementaciones.IngredientesDAO;
import sistemarestaurantepersistencia.DAOS_implementaciones.IngredientesProductosDAO;
import sistemarestaurantepersistencia.DAOS_implementaciones.ProductosDAO;
import sistemarestaurantepersistencia.interfaces.IClientesFrecuentesDAO;
import sistemarestaurantepersistencia.interfaces.IIngredientesDAO;
import sistemarestaurantepersistencia.interfaces.IIngredientesProductosDAO;
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
    
    public static IClientesFrecuentesBO crearClientesFrecuentesBO(){
        IClientesFrecuentesDAO clientesFrecuentesDAO = new ClientesFrecuentesDAO();
        IClientesFrecuentesBO clientesFrecuentesBO = new ClientesFrecuentesBO(clientesFrecuentesDAO);
        return clientesFrecuentesBO;
    }
    
    public static IIngredientesProductosBO crearIngredientesProductosBO(){
        IIngredientesProductosDAO ingredientesProductosDAO = new IngredientesProductosDAO();
        IngredientesDAO ingredientesDAO = new IngredientesDAO();
        ProductosDAO productosDAO = new ProductosDAO();
        IIngredientesProductosBO ingredientesProductosBO = new IngredientesProductosBO(ingredientesProductosDAO, ingredientesDAO, productosDAO);
        return ingredientesProductosBO;
    }
}


