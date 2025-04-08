package sistemarestaurantepersistencia.interfaces;

import java.time.LocalDateTime;
import java.util.List;
import sistemarestaurantedominio.ClienteFrecuente;
import sistemarestaurantedominio.Comanda;
import sistemarestaurantedominio.dtos.NuevoClienteFrecuenteDTO;

public interface IClientesFrecuentesDAO {
    public abstract ClienteFrecuente registrarClienteFrecuente(NuevoClienteFrecuenteDTO nuevoClienteFrecuente);
    
    public abstract ClienteFrecuente obtenerClientePorID(Long idClienteFrecuente);
    
    public abstract ClienteFrecuente obtenerClientePorTelefono(String telefono);
    
    public abstract ClienteFrecuente obtenerClientePorCorreo(String correo);
    
    public abstract List<ClienteFrecuente> obtenerClientesPorNombre(String nombre);
    
    public abstract List<ClienteFrecuente> obtenerClientesPorTelefono(String telefono);
    
    public abstract List<ClienteFrecuente> obtenerClientesPorCorreo(String correo);
    
    public abstract List<Comanda> obtenerComandasPorCliente(ClienteFrecuente cliente);
    
    public abstract float obtenerMontoGastado(ClienteFrecuente cliente);
    
    public abstract int obtenerNumeroVisitas(ClienteFrecuente cliente);
    
    public abstract LocalDateTime obtenerUltimaVisita(ClienteFrecuente cliente);
    
    public abstract List<ClienteFrecuente> buscarClientesPorMinimoVisitas(int minimoVisitas);
    
}
