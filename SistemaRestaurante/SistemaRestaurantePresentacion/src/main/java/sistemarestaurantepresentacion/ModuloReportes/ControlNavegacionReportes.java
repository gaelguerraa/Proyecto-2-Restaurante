package sistemarestaurantepresentacion.ModuloReportes;

import sistemarestaurantenegocio.IReportesBO;
import sistemarestaurantenegocio.fabrica.FabricaObjetosNegocio;
import sistemarestaurantepresentacion.frmMenuPrincipal;

public class ControlNavegacionReportes {
    IReportesBO reportesBO;
    frmMenuPrincipal frameMenuPrincipal;
    frmMenuReportes frameMenuReportes;

    public ControlNavegacionReportes() {
        reportesBO = FabricaObjetosNegocio.crearReportesBO();
    }
    
    public void iniciarMenu(){
        this.frameMenuReportes = new frmMenuReportes(this);
        frameMenuReportes.setVisible(true);
    }
    
    public void salir(){
        this.frameMenuReportes.dispose();
        frameMenuPrincipal = new frmMenuPrincipal();
        frameMenuPrincipal.setVisible(true);
    }
}
