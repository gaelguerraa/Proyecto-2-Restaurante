package sistemarestaurantepersistencia.interfaces;

import sistemarestaurantedominio.ClienteFrecuente;
import sistemarestaurantedominio.dtos.NuevoClienteFrecuenteDTO;

public interface IClientesFrecuentesDAO {
    public abstract ClienteFrecuente registrarClienteFrecuente(NuevoClienteFrecuenteDTO nuevoClienteFrecuente);
}
