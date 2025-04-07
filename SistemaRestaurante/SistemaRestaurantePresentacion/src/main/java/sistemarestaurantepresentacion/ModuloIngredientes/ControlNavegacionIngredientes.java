/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemarestaurantepresentacion.ModuloIngredientes;

import sistemarestaurantenegocio.IIngredientesBO;
import sistemarestaurantenegocio.excepciones.NegocioException;
import sistemarestaurantenegocio.fabrica.FabricaObjetosNegocio;
import sistemarestaurantepresentacion.frmMenuPrincipal;

/**
 *
 * @author jalt2
 */
public class ControlNavegacionIngredientes {
    private FrmMenuIngredientes frmMenuIngredientes;
    private FrmRegistrarIngrediente frmRegistrarIngrediente;
    private FrmBuscarIngredientes frmBuscarIngredientes;
    private FrmMensajeRegistroIngredienteExitoso frmMensajeRegistroIngredienteExitoso;
    private IIngredientesBO ingredientesBO;
    frmMenuPrincipal frameMenuPrincipal;

    public ControlNavegacionIngredientes() {
        this.ingredientesBO = FabricaObjetosNegocio.crearIngredientesBO();
    }
    
    public void IniciarFrmMenuIngredientes(){
        this.frmMenuIngredientes = new FrmMenuIngredientes(this);
        this.frmMenuIngredientes.setVisible(true);
        this.frmMenuIngredientes.setLocationRelativeTo(null);
    }
    
    public void IniciarFrmRegistrarIngrediente(){
        this.frmRegistrarIngrediente = new FrmRegistrarIngrediente(this,this.ingredientesBO);
        this.frmRegistrarIngrediente.setVisible(true);
        this.frmRegistrarIngrediente.setLocationRelativeTo(null);
    }
    
    public void IniciarFrmBuscarIngredientes() throws NegocioException{
        this.frmBuscarIngredientes = new FrmBuscarIngredientes(this,this.ingredientesBO);
        this.frmBuscarIngredientes.setVisible(true);
        this.frmBuscarIngredientes.setLocationRelativeTo(null);
    }

    public void IniciarFrmMensajeRegistroIngredienteExitoso(){
        this.frmMensajeRegistroIngredienteExitoso = new FrmMensajeRegistroIngredienteExitoso(this);
        this.frmMensajeRegistroIngredienteExitoso.setVisible(true);
        this.frmMensajeRegistroIngredienteExitoso.setLocationRelativeTo(null);
    }
    
    public void salir() {
        this.frmMenuIngredientes.dispose();
        frameMenuPrincipal = new frmMenuPrincipal();
        frameMenuPrincipal.setVisible(true);
    }
    
}
