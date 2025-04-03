/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemarestaurantenegocio.implementaciones;

import java.util.List;
import sistemarestaurantedominio.ClienteFrecuente;
import sistemarestaurantedominio.dtos.NuevoClienteFrecuenteDTO;
import sistemarestaurantenegocio.IClientesFrecuentesBO;
import sistemarestaurantepersistencia.interfaces.IClientesFrecuentesDAO;


public class ClientesFrecuentesBO implements IClientesFrecuentesBO {
    private IClientesFrecuentesDAO clientesFrecuentesDAO;

    public ClientesFrecuentesBO(IClientesFrecuentesDAO clientesFrecuentesDAO) {
        this.clientesFrecuentesDAO = clientesFrecuentesDAO;
    }

    @Override
    public ClienteFrecuente registrarCliente(NuevoClienteFrecuenteDTO nuevoCliente) {
        //agregar excepciones
        return clientesFrecuentesDAO.registrarClienteFrecuente(nuevoCliente);
    }

    @Override
    public List<ClienteFrecuente> consultarClientesNombre(String nombre) {
        // agregar excepciones
        return clientesFrecuentesDAO.obtenerClientePorNombre(nombre);
    }

    @Override
    public ClienteFrecuente consultarClienteTelefono(String telefono) {
        // agregar excepciones
        return clientesFrecuentesDAO.obtenerClientePorTelefono(telefono);
    }

    @Override
    public ClienteFrecuente consultarClienteCorreo(String correo) {
        // agregar excepciones
        return clientesFrecuentesDAO.obtenerClientePorCorreo(correo);
    }
    
    
    
    
}
