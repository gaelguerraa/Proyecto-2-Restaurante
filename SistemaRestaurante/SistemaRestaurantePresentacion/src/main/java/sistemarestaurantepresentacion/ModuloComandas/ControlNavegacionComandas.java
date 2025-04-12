package sistemarestaurantepresentacion.ModuloComandas;

import sistemarestaurantedominio.ClienteFrecuente;
import sistemarestaurantedominio.Comanda;
import sistemarestaurantedominio.Producto;
import sistemarestaurantenegocio.IClientesFrecuentesBO;
import sistemarestaurantenegocio.IComandasBO;
import sistemarestaurantenegocio.IDetallesComandasBO;
import sistemarestaurantenegocio.IIngredientesBO;
import sistemarestaurantenegocio.IProductosBO;
import sistemarestaurantenegocio.excepciones.NegocioException;
import sistemarestaurantenegocio.fabrica.FabricaObjetosNegocio;
import sistemarestaurantepresentacion.ModuloClientes.ControlNavegacionClientes;
import sistemarestaurantepresentacion.ModuloProductos.ControlNavegacionProductos;
import sistemarestaurantepresentacion.frmMenuPrincipal;

public class ControlNavegacionComandas {

    private IComandasBO comandasBO;
    private IIngredientesBO ingredientesBO;
    private IProductosBO productosBO;
    private IDetallesComandasBO detallesComandasBO;
    private IClientesFrecuentesBO clientesBO;
    private frmMenuPrincipal frameMenuPrincipal;
    private frmMenuComandas frameMenuComandas;
    private frmVerComandas frameVerComandas;
    private frmCrearComanda frameCrearComanda;
    private frmVerDetalleComanda frameDetalleComanda;
    private ControlNavegacionClientes controlClientes;
    private ControlNavegacionProductos controlProductos;

    public ControlNavegacionComandas() throws NegocioException {
        comandasBO = FabricaObjetosNegocio.crearComandasBO();
        productosBO = FabricaObjetosNegocio.crearProductosBO();
        detallesComandasBO = FabricaObjetosNegocio.crearDetallesComadasBO();
        clientesBO = FabricaObjetosNegocio.crearClientesFrecuentesBO();
        ingredientesBO = FabricaObjetosNegocio.crearIngredientesBO();
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
        this.frameCrearComanda = new frmCrearComanda(this, comandasBO, productosBO, detallesComandasBO, null);
        frameCrearComanda.setVisible(true);
//        this.frameAgregarProductoComanda = new frmAgregarProductoComanda(this, comandaActual);

    }

    public void verListaComandas() {
        this.frameMenuComandas.dispose();
        this.frameVerComandas = new frmVerComandas(this, comandasBO);
        frameVerComandas.setVisible(true);
    }

    public void detallesComanda(Comanda comandaSeleccionada) {
        this.frameVerComandas.dispose();
        this.frameDetalleComanda = new frmVerDetalleComanda(this.ingredientesBO, this.detallesComandasBO, comandaSeleccionada, this, comandasBO, clientesBO);
        this.frameDetalleComanda.setVisible(true);
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
    public Producto obtenerProducto() {
        controlProductos = new ControlNavegacionProductos();
        return controlProductos.regresarProducto();
    }

    public void editarComanda(String folio) {
        this.frameCrearComanda = new frmCrearComanda(this, comandasBO, productosBO, detallesComandasBO, folio);
        frameCrearComanda.setVisible(true);
        frameDetalleComanda.dispose();

    }

}
