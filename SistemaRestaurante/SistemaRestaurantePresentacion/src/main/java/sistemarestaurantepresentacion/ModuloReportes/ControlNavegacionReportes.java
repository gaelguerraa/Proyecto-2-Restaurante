package sistemarestaurantepresentacion.ModuloReportes;

import java.time.LocalDate;
import sistemarestaurantenegocio.IClientesFrecuentesBO;
import sistemarestaurantenegocio.IComandasBO;
import sistemarestaurantenegocio.fabrica.FabricaObjetosNegocio;
import sistemarestaurantepresentacion.frmMenuPrincipal;

public class ControlNavegacionReportes {
    IComandasBO comandasBO;
    IClientesFrecuentesBO clientesBO;
    frmMenuPrincipal frameMenuPrincipal;
    frmMenuReportes frameMenuReportes;
    frmReportesComandas frameReportesComandas;
    frmReportesComandasSeleccionarFecha frameComandaSeleccionFecha;
    frmReportesClientesFrecuentes frameReportesClientesFrecuentes;

    public ControlNavegacionReportes() {
        comandasBO = FabricaObjetosNegocio.crearComandasBO();
        clientesBO = FabricaObjetosNegocio.crearClientesFrecuentesBO();
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
    
    public void seleccionFechaComanda(){
        this.frameComandaSeleccionFecha = new frmReportesComandasSeleccionarFecha(this);
        frameComandaSeleccionFecha.setVisible(true);
        frameMenuReportes.dispose();
    }
    
    public void volverMenu(){
        this.frameComandaSeleccionFecha.dispose();
        this.frameMenuReportes.setVisible(true);
        
    }
    
    public void mostrarReporteComandas(LocalDate fechaInicio, LocalDate fechaFin){
        this.frameComandaSeleccionFecha.dispose();
        this.frameReportesComandas = new frmReportesComandas(this, comandasBO, fechaInicio, fechaFin);
        frameReportesComandas.setVisible(true);
    }
    
    public void regresarSeleccionFecha(){
        this.frameReportesComandas.dispose();
        this.frameComandaSeleccionFecha = new frmReportesComandasSeleccionarFecha(this);
        frameComandaSeleccionFecha.setVisible(true);
    }
    
    public void reporteClientes(){
        this.frameMenuReportes.dispose();
        this.frameReportesClientesFrecuentes = new frmReportesClientesFrecuentes(this, clientesBO);
        frameReportesClientesFrecuentes.setVisible(true);
    }
    
    public void regresarReporteClientes(){
        this.frameReportesClientesFrecuentes.dispose();
        this.frameMenuReportes = new frmMenuReportes(this);
        frameMenuReportes.setVisible(true);
    }
}
