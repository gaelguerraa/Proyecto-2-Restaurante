package sistemarestaurantepresentacion.ModuloComandas;

import sistemarestaurantenegocio.IComandasBO;
import sistemarestaurantenegocio.fabrica.FabricaObjetosNegocio;
import sistemarestaurantepresentacion.ModuloProductos.frmAgregarProductoComanda;
import sistemarestaurantepresentacion.frmMenuPrincipal;

public class ControlNavegacionComandas {

    private IComandasBO comandasBO;
    private frmMenuPrincipal frameMenuPrincipal;
    private frmMenuComandas frameMenuComandas;
    private frmVerComandas frameVerComandas;
    private frmCrearComanda frameCrearComanda;
    
    private frmAgregarProductoComanda frameAgregarProductoComanda;

    public ControlNavegacionComandas() {
        comandasBO = FabricaObjetosNegocio.crearComandasBO();
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
    public void crearComanda(){
        this.frameMenuComandas.dispose();
//        this.frameCrearComanda = new frmCrearComanda(this);
//        frameCrearComanda.setVisible(true);
//        this.frameAgregarProductoComanda = new frmAgregarProductoComanda(this, comandaActual);
        
        
    }
    
    public void verListaComandas(){
        this.frameMenuComandas.dispose();
        this.frameVerComandas = new frmVerComandas(this, comandasBO);
        frameVerComandas.setVisible(true);
    }
    
    public void regresarMenu(){
        this.frameVerComandas.dispose();
        this.frameMenuComandas = new frmMenuComandas(this);
        frameMenuComandas.setVisible(true);
    }
    
}
