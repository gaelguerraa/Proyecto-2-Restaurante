/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemarestaurantenegocio.implementaciones;

import java.time.LocalDateTime;
import java.util.Base64;
import java.util.List;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import org.eclipse.persistence.exceptions.DatabaseException;
import sistemarestaurantedominio.ClienteFrecuente;
import sistemarestaurantedominio.dtos.NuevoClienteFrecuenteDTO;
import sistemarestaurantenegocio.IClientesFrecuentesBO;
import sistemarestaurantenegocio.excepciones.NegocioException;
import sistemarestaurantepersistencia.interfaces.IClientesFrecuentesDAO;

/**
 * Clase de negocio para clientesFrecuentes
 *
 * @author jorge
 */
public class ClientesFrecuentesBO implements IClientesFrecuentesBO {

    private IClientesFrecuentesDAO clientesFrecuentesDAO;
    private static final String ALGORITMO = "AES";
    private static final byte[] CLAVE_SECRETA = "clave12345678901".getBytes();

    /**
     * Constructor que recibe una DAO para poder utilizar sus metodos
     *
     * @param clientesFrecuentesDAO recibe una DAO para poder utilizar sus
     * metodos
     */
    public ClientesFrecuentesBO(IClientesFrecuentesDAO clientesFrecuentesDAO) {
        this.clientesFrecuentesDAO = clientesFrecuentesDAO;
    }

    /**
     * Metodo que permite registrar un cliente nuevo
     *
     * @param nuevoCliente recibe como parametro una DTO de
     * NuevoClienteFrecuente
     * @return regresa un ClienteFrecuente
     * @throws NegocioException Lanza la excepcion en caso de que exista un
     * error con el telefono del cliente
     */
    @Override
    public ClienteFrecuente registrarCliente(NuevoClienteFrecuenteDTO nuevoCliente) throws NegocioException {
        try {
            if (nuevoCliente.getTelefono() == null || nuevoCliente.getTelefono().isEmpty()) {
                throw new NegocioException("El teléfono no puede estar vacío.");
            }
            nuevoCliente.setTelefono(encriptarTelefono(nuevoCliente.getTelefono()));
            return clientesFrecuentesDAO.registrarClienteFrecuente(nuevoCliente);

        } catch (DatabaseException e) {
            throw new NegocioException("Error al registrar el cliente, telefono duplicado");
        }
    }

    /**
     * Metodo que obtiene una lista de clientes segun el nombre especificado
     *
     * @param nombre recibe como parametro un nombre a filtrar
     * @return regresa una lista de clientes
     */
    @Override
    public List<ClienteFrecuente> consultarClientesNombre(String nombre) {
        return clientesFrecuentesDAO.obtenerClientesPorNombre(nombre).stream()
                .peek(cliente -> cliente.setTelefono(desencriptarTelefono(cliente.getTelefono())))
                .toList();
    }

    /**
     * Metodo que permite consultar un cliente segun el telefono otorgado
     *
     * @param telefono Recibe como parametro un telefono
     * @return Regresa un clienteFrecuente en caso de ser encontrado
     */
    @Override
    public ClienteFrecuente consultarClienteTelefono(String telefono) {
        // agregar excepciones
        String telefonoEncriptado = encriptarTelefono(telefono);
        try {
            ClienteFrecuente cliente = clientesFrecuentesDAO.obtenerClientePorTelefono(telefonoEncriptado);
            if (cliente != null) {
                cliente.setTelefono(desencriptarTelefono(cliente.getTelefono()));
            }
            return cliente;
        } catch (Exception e) {
            System.out.println("No se encontro un cliente");
        }
        return null;
    }

    /**
     * Metodo que permite consultar un cliente mediante un correo otorgado
     *
     * @param correo Recibe como parametro un correo
     * @return Regresa un clienteFrecuente en caso de ser encontrado
     */
    @Override
    public ClienteFrecuente consultarClienteCorreo(String correo) throws NegocioException{
        // agregar excepciones
        ClienteFrecuente cliente = clientesFrecuentesDAO.obtenerClientePorCorreo(correo);
        if (cliente != null) {
            cliente.setTelefono(desencriptarTelefono(cliente.getTelefono()));
        }
        else{
            throw new NegocioException("No se encontró un cliente con el Correo proporcionado.");
        }
        return cliente;
    }

    /**
     * Metodo que permite encriptar el telefono del cliente
     * @param telefono Recibe un telefono como parametro de tipo String
     * @return regresa un String del telefono encriptado
     */
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

    /**
     * Metodo que permite desencriptar los telefonos de los clientes
     * @param telefono Recibe como parametro un telefono encriptado
     * @return Regresa un telefono desencriptado
     */
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

    /**
     * Metodo que permite consultar un cliente mediante ID
     * @param idCliente Recibe como parametro una ID tipo Long
     * @return Regresa un clienteFrecuente en caso de ser encontrado
     */
    @Override
    public ClienteFrecuente consultarClienteID(Long idCliente) throws NegocioException {
        ClienteFrecuente cliente = clientesFrecuentesDAO.obtenerClientePorID(idCliente);
        if(cliente == null){
            throw new NegocioException("No se encontró un cliente con el ID proporcionado.");
        }
        return cliente;
    }

    /**
     * Metodo que obtiene el montoGastado de un cliente
     * @param cliente Recibe como parametro un clienteFrecuente
     * @return regresa una cantidad de tipo Float
     */
    @Override
    public Float obtenerMontoGastado(ClienteFrecuente cliente) {
        // excepciones
        return clientesFrecuentesDAO.obtenerMontoGastado(cliente);
    }

    /**
     * Metodo que permite obtener el numero de visitas de un cliente
     * @param cliente Recibe como parametro un ClienteFrecuente
     * @return regresa un entero de el numero de visitas del cliente
     */
    @Override
    public int obtenerNumVisitas(ClienteFrecuente cliente) {
        // expceciones
        return clientesFrecuentesDAO.obtenerNumeroVisitas(cliente);
    }

    /**
     * Metodo que permite obtener la fecha de la ultima visita de un cliente
     * @param cliente Recibe como parametro un ClienteFrecuente
     * @return Regresa una fecha de tipo LocalDateTime
     * @throws NegocioException Lanza esta excepcion en caso de que el cliente no tenga visitas
     */
    @Override
    public LocalDateTime obtenerUltimaVisita(ClienteFrecuente cliente) throws NegocioException {
        LocalDateTime ultimaVisita = clientesFrecuentesDAO.obtenerUltimaVisita(cliente);

        if (ultimaVisita == null) {
            throw new NegocioException("El cliente no tiene ultima visita");
        }

        return ultimaVisita;

    }

    /**
     * Metodo que consulta una lista de clientes segun un telefono otorgado
     * @param telefono Recibe como parametro un telefono de tipo String
     * @return regresa una lista de clientes que concuerden con el telefono
     */
    @Override
    public List<ClienteFrecuente> consultarClientesTelefono(String telefono) {
        String telefonoEncriptado = encriptarTelefono(telefono);
        return clientesFrecuentesDAO.obtenerClientesPorTelefono(telefonoEncriptado).stream()
                .peek(cliente -> cliente.setTelefono(desencriptarTelefono(cliente.getTelefono())))
                .toList();
    }

    /**
     * Metodo que consulta una lista de clientes segun un correo otorgado
     * @param correo Recibe como parametro un correo de tipo String
     * @return regresa una lista de clientes que concuerden con el correo
     */
    @Override
    public List<ClienteFrecuente> consultarClientesCorreo(String correo) {
        return clientesFrecuentesDAO.obtenerClientesPorCorreo(correo).stream()
                .peek(cliente -> cliente.setTelefono(desencriptarTelefono(cliente.getTelefono())))
                .toList();
    }
}
