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
 * Fábrica de objetos de negocio del sistema de restaurante.
 * Esta clase se encarga de instanciar y devolver implementaciones
 * concretas de las interfaces de la capa de negocio utilizando
 * sus respectivos DAOs.
 * 
 */
public class FabricaObjetosNegocio {

    /**
     * Crea una instancia de ProductosBO con su DAO correspondiente.
     * 
     * @return IProductosBO instancia del objeto de negocio.
     */
    public static IProductosBO crearProductosBO() {
        IProductosDAO productosDAO = new ProductosDAO();
        return new ProductosBO(productosDAO);
    }

    /**
     * Crea una instancia de ClientesFrecuentesBO con su DAO correspondiente.
     * 
     * @return IClientesFrecuentesBO instancia del objeto de negocio.
     */
    public static IClientesFrecuentesBO crearClientesFrecuentesBO() {
        IClientesFrecuentesDAO clientesFrecuentesDAO = new ClientesFrecuentesDAO();
        return new ClientesFrecuentesBO(clientesFrecuentesDAO);
    }

    /**
     * Crea una instancia de IngredientesProductosBO con sus DAOs correspondientes.
     * 
     * @return IIngredientesProductosBO instancia del objeto de negocio.
     */
    public static IIngredientesProductosBO crearIngredientesProductosBO() {
        IIngredientesProductosDAO ingredientesProductosDAO = new IngredientesProductosDAO();
        IngredientesDAO ingredientesDAO = new IngredientesDAO();
        ProductosDAO productosDAO = new ProductosDAO();
        return new IngredientesProductosBO(ingredientesProductosDAO, ingredientesDAO, productosDAO);
    }

    /**
     * Crea una instancia de IngredientesBO con su DAO correspondiente.
     * 
     * @return IIngredientesBO instancia del objeto de negocio.
     * @throws NegocioException si ocurre un error en la lógica de negocio.
     */
    public static IIngredientesBO crearIngredientesBO() throws NegocioException {
        IIngredientesDAO ingredientesDAO = new IngredientesDAO();
        return new IngredientesBO(ingredientesDAO);
    }

    /**
     * Crea una instancia de ComandasBO con su DAO correspondiente.
     * 
     * @return IComandasBO instancia del objeto de negocio.
     */
    public static IComandasBO crearComandasBO() {
        IComandasDAO comandasDAO = new ComandasDAO();
        return new ComandasBO(comandasDAO);
    }

    /**
     * Crea una instancia de MesasBO con su DAO correspondiente.
     * 
     * @return IMesasBO instancia del objeto de negocio.
     */
    public static IMesasBO crearMesasBO() {
        IMesasDAO mesasDAO = new MesasDAO();
        return new MesasBO(mesasDAO);
    }

    /**
     * Crea una instancia de DetallesComandasBO con su DAO correspondiente.
     * 
     * @return IDetallesComandasBO instancia del objeto de negocio.
     */
    public static IDetallesComandasBO crearDetallesComadasBO() {
        IDetallesComandasDAO detallesComandasDAO = new DetallesComandasDAO();
        return new DetallesComandasBO(detallesComandasDAO);
    }
}
