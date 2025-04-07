package sistemarestaurantepresentacion.ModuloComandas;

import sistemarestaurantenegocio.IComandasBO;
import sistemarestaurantenegocio.fabrica.FabricaObjetosNegocio;
import sistemarestaurantepresentacion.frmMenuPrincipal;

public class ControlNavegacionComandas {

    private IComandasBO comandasBO;
    private frmMenuPrincipal frameMenuPrincipal;
    private frmMenuComandas frameMenuComandas;
    private frmVerComandas frameVerComandas;

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
