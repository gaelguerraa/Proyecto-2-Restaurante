package sistemarestaurantepersistencia.interfaces;

import java.util.List;
import sistemarestaurantedominio.ClienteFrecuente;
import sistemarestaurantedominio.dtos.NuevoClienteFrecuenteDTO;

public interface IClientesFrecuentesDAO {
    public abstract ClienteFrecuente registrarClienteFrecuente(NuevoClienteFrecuenteDTO nuevoClienteFrecuente);
    
    public abstract ClienteFrecuente obtenerClientePorID(Long idClienteFrecuente);
    
    public abstract ClienteFrecuente obtenerClientePorTelefono(String telefono);
    
    public abstract ClienteFrecuente obtenerClientePorCorreo(String correo);
    
    public abstract List<ClienteFrecuente> obtenerClientePorNombre(String nombre);
    
    public abstract List<ClienteFrecuente> buscarClientesPorMinimoVisitas(int minimoVisitas);
}
