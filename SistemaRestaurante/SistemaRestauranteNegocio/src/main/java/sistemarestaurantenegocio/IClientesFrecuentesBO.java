package sistemarestaurantenegocio;

import java.time.LocalDateTime;
import java.util.List;
import sistemarestaurantedominio.ClienteFrecuente;
import sistemarestaurantedominio.dtos.NuevoClienteFrecuenteDTO;
import sistemarestaurantenegocio.excepciones.NegocioException;

public interface IClientesFrecuentesBO {
    public abstract ClienteFrecuente registrarCliente(NuevoClienteFrecuenteDTO nuevoCliente) throws NegocioException;
    
    public abstract List<ClienteFrecuente> consultarClientesNombre(String nombre);
    
    public abstract List<ClienteFrecuente> consultarClientesTelefono(String telefono);
    
    public abstract List<ClienteFrecuente> consultarClientesCorreo(String correo);
    
    public abstract ClienteFrecuente consultarClienteID(Long idCliente) throws NegocioException;
    
    public abstract ClienteFrecuente consultarClienteTelefono(String telefono);
    
    public abstract ClienteFrecuente consultarClienteCorreo(String correo) throws NegocioException;
    
    public abstract String encriptarTelefono(String telefono);
    
    public abstract String desencriptarTelefono(String telefono);
    
    public abstract Float obtenerMontoGastado(ClienteFrecuente cliente);
    
    public abstract int obtenerNumVisitas(ClienteFrecuente cliente);
    
    public abstract LocalDateTime obtenerUltimaVisita(ClienteFrecuente cliente) throws NegocioException;
    
    public abstract List<ClienteFrecuente> consultarClientesMinimoVisita(int minimoVisita) throws NegocioException;
   
}
