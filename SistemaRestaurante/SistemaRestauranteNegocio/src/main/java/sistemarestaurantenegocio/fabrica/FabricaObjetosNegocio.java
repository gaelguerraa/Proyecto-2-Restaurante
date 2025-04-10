/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemarestaurantenegocio.fabrica;

import sistemarestaurantenegocio.IClientesFrecuentesBO;
import sistemarestaurantenegocio.IComandasBO;
import sistemarestaurantenegocio.IDetallesComandasBO;
import sistemarestaurantenegocio.IIngredientesBO;
import sistemarestaurantenegocio.IIngredientesProductosBO;
import sistemarestaurantenegocio.IMesasBO;
import sistemarestaurantenegocio.IProductosBO;
import sistemarestaurantenegocio.excepciones.NegocioException;
import sistemarestaurantenegocio.implementaciones.ClientesFrecuentesBO;
import sistemarestaurantenegocio.implementaciones.ComandasBO;
import sistemarestaurantenegocio.implementaciones.DetallesComandasBO;
import sistemarestaurantenegocio.implementaciones.IngredientesBO;
import sistemarestaurantenegocio.implementaciones.IngredientesProductosBO;
import sistemarestaurantenegocio.implementaciones.MesasBO;
import sistemarestaurantenegocio.implementaciones.ProductosBO;
import sistemarestaurantepersistencia.DAOS_implementaciones.ClientesFrecuentesDAO;
import sistemarestaurantepersistencia.DAOS_implementaciones.ComandasDAO;
import sistemarestaurantepersistencia.DAOS_implementaciones.DetallesComandasDAO;
import sistemarestaurantepersistencia.DAOS_implementaciones.IngredientesDAO;
import sistemarestaurantepersistencia.DAOS_implementaciones.IngredientesProductosDAO;
import sistemarestaurantepersistencia.DAOS_implementaciones.MesasDAO;
import sistemarestaurantepersistencia.DAOS_implementaciones.ProductosDAO;
import sistemarestaurantepersistencia.interfaces.IClientesFrecuentesDAO;
import sistemarestaurantepersistencia.interfaces.IComandasDAO;
import sistemarestaurantepersistencia.interfaces.IDetallesComandasDAO;
import sistemarestaurantepersistencia.interfaces.IIngredientesDAO;
import sistemarestaurantepersistencia.interfaces.IIngredientesProductosDAO;
import sistemarestaurantepersistencia.interfaces.IMesasDAO;
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
    
    public static IIngredientesBO crearIngredientesBO() throws NegocioException{
        IIngredientesDAO ingredientesDAO = new IngredientesDAO();
        IIngredientesBO ingredientesBO = new IngredientesBO(ingredientesDAO);
        return ingredientesBO;
    }
    
    public static IComandasBO crearComandasBO(){
        IComandasDAO comandasDAO = new ComandasDAO();
        IComandasBO comandasBO = new ComandasBO(comandasDAO);
        return comandasBO;
    }
    
    public static IMesasBO crearMesasBO(){
        IMesasDAO mesasDAO = new MesasDAO();
        IMesasBO mesasBO = new MesasBO(mesasDAO);
        return mesasBO;
    }
    
    public static IDetallesComandasBO crearDetallesComadasBO(){
        IDetallesComandasDAO detallesComandasDAO = new DetallesComandasDAO();
        IDetallesComandasBO detallesComandasBO = new DetallesComandasBO(detallesComandasDAO);
        return detallesComandasBO;
    }
   
}


