package sistemarestaurantepresentacion.ModuloComandas;

import sistemarestaurantedominio.ClienteFrecuente;
import sistemarestaurantedominio.Producto;
import sistemarestaurantedominio.dtos.NuevoDetalleComandaDTO;
import sistemarestaurantenegocio.IComandasBO;
import sistemarestaurantenegocio.IDetallesComandasBO;
import sistemarestaurantenegocio.IProductosBO;
import sistemarestaurantenegocio.fabrica.FabricaObjetosNegocio;
import sistemarestaurantepresentacion.ModuloClientes.ControlNavegacionClientes;
import sistemarestaurantepresentacion.ModuloProductos.ControlNavegacionProductos;
import sistemarestaurantepresentacion.ModuloProductos.frmAgregarProductoComanda;
import sistemarestaurantepresentacion.frmMenuPrincipal;

public class ControlNavegacionComandas {

    private IComandasBO comandasBO;
    private IProductosBO productosBO;
    private IDetallesComandasBO detallesComandasBO;
    private frmMenuPrincipal frameMenuPrincipal;
    private frmMenuComandas frameMenuComandas;
    private frmVerComandas frameVerComandas;
    private frmCrearComanda frameCrearComanda;
    private ControlNavegacionClientes controlClientes;
    private ControlNavegacionProductos controlProductos;

    private frmAgregarProductoComanda frameAgregarProductoComanda;

    public ControlNavegacionComandas() {
        comandasBO = FabricaObjetosNegocio.crearComandasBO();
        productosBO = FabricaObjetosNegocio.crearProductosBO();
        detallesComandasBO = FabricaObjetosNegocio.crearDetallesComadasBO();
    }

    public void iniciarMenu() {
        this.frameMenuComandas = new frmMenuComandas(this);
        frameMenuComandas.setVisible(true);
    }

    public void salir() {
        this.frameMenuComandas.dispose();
        frameMenuPrincipal = new frmMenuPrincipal();
        frameMenuPrincipal.setVisible(true);
    }

    //Esta por verse como queda esto
    public void crearComanda() {
        this.frameMenuComandas.dispose();
        this.frameCrearComanda = new frmCrearComanda(this, comandasBO, productosBO, detallesComandasBO);
        frameCrearComanda.setVisible(true);
//        this.frameAgregarProductoComanda = new frmAgregarProductoComanda(this, comandaActual);

    }

    public void verListaComandas() {
        this.frameMenuComandas.dispose();
        this.frameVerComandas = new frmVerComandas(this, comandasBO);
        frameVerComandas.setVisible(true);
    }

    public void regresarMenu() {
        this.frameVerComandas.dispose();
        this.frameMenuComandas = new frmMenuComandas(this);
        frameMenuComandas.setVisible(true);
    }

    public void regresarMenuCrearComanda() {
        this.frameCrearComanda.dispose();
        this.frameMenuComandas = new frmMenuComandas(this);
        frameMenuComandas.setVisible(true);
    }

    public ClienteFrecuente obtenerCliente() {
        controlClientes = new ControlNavegacionClientes();
        return controlClientes.regresarClienteSeleccionado();

    }
    
    //
    public Producto obtenerProducto(){
        controlProductos = new ControlNavegacionProductos();
        return controlProductos.regresarProducto();
    }
    

}
