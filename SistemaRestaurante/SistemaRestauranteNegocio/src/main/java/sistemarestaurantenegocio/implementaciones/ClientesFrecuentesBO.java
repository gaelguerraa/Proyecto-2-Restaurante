/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemarestaurantenegocio.implementaciones;

import java.util.Base64;
import java.util.List;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import sistemarestaurantedominio.ClienteFrecuente;
import sistemarestaurantedominio.dtos.NuevoClienteFrecuenteDTO;
import sistemarestaurantenegocio.IClientesFrecuentesBO;
import sistemarestaurantepersistencia.interfaces.IClientesFrecuentesDAO;

public class ClientesFrecuentesBO implements IClientesFrecuentesBO {

    private IClientesFrecuentesDAO clientesFrecuentesDAO;
    private static final String ALGORITMO = "AES";
    private static final byte[] CLAVE_SECRETA = "clave12345678901".getBytes(); // 16 bytes

    public ClientesFrecuentesBO(IClientesFrecuentesDAO clientesFrecuentesDAO) {
        this.clientesFrecuentesDAO = clientesFrecuentesDAO;
    }

    @Override
    public ClienteFrecuente registrarCliente(NuevoClienteFrecuenteDTO nuevoCliente) {
        //agregar excepciones
        nuevoCliente.setTelefono(encriptarTelefono(nuevoCliente.getTelefono()));
        return clientesFrecuentesDAO.registrarClienteFrecuente(nuevoCliente);
    }

    @Override
    public List<ClienteFrecuente> consultarClientesNombre(String nombre) {
        // agregar excepciones
        return clientesFrecuentesDAO.obtenerClientePorNombre(nombre).stream()
                .peek(cliente -> cliente.setTelefono(desencriptarTelefono(cliente.getTelefono())))
                .toList();
    }

    @Override
    public ClienteFrecuente consultarClienteTelefono(String telefono) {
        // agregar excepciones
        String telefonoEncriptado = encriptarTelefono(telefono);
        ClienteFrecuente cliente = clientesFrecuentesDAO.obtenerClientePorTelefono(telefonoEncriptado);
        if (cliente != null) {
            cliente.setTelefono(desencriptarTelefono(cliente.getTelefono()));
        }
        return cliente;
    }

    @Override
    public ClienteFrecuente consultarClienteCorreo(String correo) {
        // agregar excepciones
        ClienteFrecuente cliente = clientesFrecuentesDAO.obtenerClientePorCorreo(correo);
        if (cliente != null) {
            cliente.setTelefono(desencriptarTelefono(cliente.getTelefono()));
        }
        return cliente;
    }

    @Override
    public String encriptarTelefono(String telefono) {
        try {
            SecretKeySpec clave = new SecretKeySpec(CLAVE_SECRETA, ALGORITMO);
            Cipher cipher = Cipher.getInstance(ALGORITMO);
            cipher.init(Cipher.ENCRYPT_MODE, clave);
            byte[] textoCifrado = cipher.doFinal(telefono.getBytes());
            return Base64.getEncoder().encodeToString(textoCifrado);
        } catch (Exception e) {
            throw new RuntimeException("Error al encriptar", e);
        }
    }

    @Override
    public String desencriptarTelefono(String telefono) {
        try {
            SecretKeySpec clave = new SecretKeySpec(CLAVE_SECRETA, ALGORITMO);
            Cipher cipher = Cipher.getInstance(ALGORITMO);
            cipher.init(Cipher.DECRYPT_MODE, clave);
            byte[] bytesDecodificados = Base64.getDecoder().decode(telefono);
            byte[] telefonoClaro = cipher.doFinal(bytesDecodificados);
            return new String(telefonoClaro);
        } catch (Exception e) {
            throw new RuntimeException("Error al desencriptar", e);
        }
    }

    @Override
    public ClienteFrecuente consultarClienteID(Long idCliente) {
        //excepciones
        return clientesFrecuentesDAO.obtenerClientePorID(idCliente);
    }

}
