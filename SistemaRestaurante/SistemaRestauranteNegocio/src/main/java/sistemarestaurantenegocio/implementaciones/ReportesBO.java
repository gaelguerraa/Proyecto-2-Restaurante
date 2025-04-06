package sistemarestaurantenegocio.implementaciones;

import java.util.List;
import sistemarestaurantedominio.ClienteFrecuente;
import sistemarestaurantenegocio.IReportesBO;
import sistemarestaurantepersistencia.interfaces.IReportesDAO;

public class ReportesBO implements IReportesBO {
   private IReportesDAO reportesDAO;

    public ReportesBO(IReportesDAO reportesDAO) {
        this.reportesDAO = reportesDAO;
    }

    @Override
    public List<ClienteFrecuente> generarReporteClienteFrecuente() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
   
   
}
