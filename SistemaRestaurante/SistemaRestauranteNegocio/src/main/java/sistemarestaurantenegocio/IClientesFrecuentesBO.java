package sistemarestaurantenegocio;

import java.util.List;
import sistemarestaurantedominio.ClienteFrecuente;
import sistemarestaurantedominio.dtos.NuevoClienteFrecuenteDTO;

public interface IClientesFrecuentesBO {
    public abstract ClienteFrecuente registrarCliente(NuevoClienteFrecuenteDTO nuevoCliente);
    
    public abstract List<ClienteFrecuente> consultarClientesNombre(String nombre);
    
    public abstract ClienteFrecuente consultarClienteTelefono(String telefono);
    
    public abstract ClienteFrecuente consultarClienteCorreo(String correo);
}
