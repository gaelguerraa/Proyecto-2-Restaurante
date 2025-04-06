package sistemarestaurantenegocio;

import java.util.List;
import sistemarestaurantedominio.ClienteFrecuente;

public interface IReportesBO {
    public abstract List<ClienteFrecuente> generarReporteClienteFrecuente();
}
