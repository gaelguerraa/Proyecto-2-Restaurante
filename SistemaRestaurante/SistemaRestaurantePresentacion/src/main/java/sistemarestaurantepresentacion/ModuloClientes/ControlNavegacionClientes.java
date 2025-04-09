/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemarestaurantepresentacion.ModuloClientes;

import javax.swing.JDialog;
import javax.swing.JFrame;
import sistemarestaurantedominio.ClienteFrecuente;
import sistemarestaurantenegocio.IClientesFrecuentesBO;
import sistemarestaurantenegocio.fabrica.FabricaObjetosNegocio;
import sistemarestaurantepresentacion.frmMenuPrincipal;

/**
 *
 * @author jorge
 */
public class ControlNavegacionClientes {

    frmMenuClientes frameMenuClientes;
    frmRegistroCliente frameRegistroCliente;
    frmBuscarCliente frameBuscarCliente;
    frmInformacionCliente frameInformacionCliente;
    IClientesFrecuentesBO clientesBO;
    frmMenuPrincipal frameMenuPrincipal;

    public ControlNavegacionClientes() {
        clientesBO = FabricaObjetosNegocio.crearClientesFrecuentesBO();
    }

    public void iniciarMenu() {
        this.frameMenuClientes = new frmMenuClientes(this);
        frameMenuClientes.setVisible(true);
    }

    public void consultar() {
        this.frameBuscarCliente = new frmBuscarCliente(clientesBO, this);
        this.frameBuscarCliente.setVisible(true);
        this.frameMenuClientes.dispose();
    }

    public void salir() {
        this.frameMenuClientes.dispose();
        frameMenuPrincipal = new frmMenuPrincipal();
        frameMenuPrincipal.setVisible(true);

    }

    public void regresarMenuClientesRegistro() {
        this.frameMenuClientes = new frmMenuClientes(this);
        this.frameMenuClientes.setVisible(true);
        this.frameRegistroCliente.dispose();
    }

    public void regresarMenuClientesConsulta() {
        this.frameMenuClientes = new frmMenuClientes(this);
        this.frameMenuClientes.setVisible(true);
        this.frameBuscarCliente.dispose();
    }

    public void registrar() {
        this.frameRegistroCliente = new frmRegistroCliente(clientesBO, this);
        frameRegistroCliente.setVisible(true);
        frameMenuClientes.dispose();
    }

    public void verInformacionCliente(ClienteFrecuente cliente) {
        this.frameBuscarCliente.setVisible(false);
        this.frameInformacionCliente = new frmInformacionCliente(clientesBO, cliente, this);
        this.frameInformacionCliente.setVisible(true);
    }

    public void buscarOtroCliente() {
        this.frameInformacionCliente.dispose();
        this.frameBuscarCliente.setVisible(true);
    }

    public void regresarMenuPrincipal() {
        this.frameInformacionCliente.dispose();
        this.frameMenuPrincipal = new frmMenuPrincipal();
        frameMenuPrincipal.setVisible(true);
    }

    //metodo para regresar al cliente seleccionado se llamaria desde comanda
    public ClienteFrecuente regresarClienteSeleccionado() {
        frmBuscarCliente dialog = new frmBuscarCliente(clientesBO, this);
        return dialog.mostrarYObtenerClienteSeleccionado();

    }

}
