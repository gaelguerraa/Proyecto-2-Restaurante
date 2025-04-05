/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package sistemarestaurantepresentacion;

import sistemarestaurantedominio.ClienteFrecuente;
import sistemarestaurantenegocio.IClientesFrecuentesBO;
import sistemarestaurantenegocio.IIngredientesProductosBO;
import sistemarestaurantenegocio.IProductosBO;
import sistemarestaurantenegocio.fabrica.FabricaObjetosNegocio;
import sistemarestaurantepresentacion.ModuloClientes.ControlNavegacionClientes;
import sistemarestaurantepresentacion.ModuloClientes.frmBuscarCliente;
import sistemarestaurantepresentacion.ModuloProductos.ControladorProductos;
import sistemarestaurantepresentacion.ModuloProductos.frmMenuProductos;

/**
 *
 * @author gael_
 */
public class SistemaRestaurantePresentacion {

    public static void main(String[] args) {

        //PARA TESTEAR EL BUSCADOR CLIENTES
//        IClientesFrecuentesBO clientesBO = FabricaObjetosNegocio.crearClientesFrecuentesBO();
//        frmBuscarCliente formBuscarClientes = new frmBuscarCliente(clientesBO);
//        formBuscarClientes.setVisible(true);
    
        

//
//        IIngredientesProductosBO ingredientesProductosBO = FabricaObjetosNegocio.crearIngredientesProductosBO();
//        IProductosBO productosBO = FabricaObjetosNegocio.crearProductosBO();
//        
//        ControladorProductos controlador = new ControladorProductos(ingredientesProductosBO, productosBO);
//
//
//        frmMenuProductos menuProductos = new frmMenuProductos(controlador);
//        menuProductos.setVisible(true);  

        frmMenuPrincipal menu = new frmMenuPrincipal();
        menu.setVisible(true);
        

        


    }
}
