/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemarestaurantedominio.dtos;

import java.time.LocalDateTime;
import sistemarestaurantedominio.ClienteFrecuente;
import sistemarestaurantedominio.EstadoComanda;
import sistemarestaurantedominio.Mesa;

/**
 *
 * @author jalt2
 */
public class NuevaComandaDTO {
    private String folio;
    private EstadoComanda estado;
    private LocalDateTime fechaHora;
    private Float total;
    private Mesa numeroMesa;
    private ClienteFrecuente clienteFrecuente;

    public NuevaComandaDTO() {
    }
    
    
    public NuevaComandaDTO(String folio, EstadoComanda estado, LocalDateTime fechaHora, Float total, Mesa numeroMesa) {
        this.folio = folio;
        this.estado = estado;
        this.fechaHora = fechaHora;
        this.total = total;
        this.numeroMesa = numeroMesa;
        
    }

    public NuevaComandaDTO(String folio, EstadoComanda estado, LocalDateTime fechaHora, Float total, Mesa numeroMesa, ClienteFrecuente clienteFrecuente) {
        this.folio = folio;
        this.estado = estado;
        this.fechaHora = fechaHora;
        this.total = total;
        this.numeroMesa = numeroMesa;
        this.clienteFrecuente = clienteFrecuente;
    }

    

    public String getFolio() {
        return folio;
    }

    public EstadoComanda getEstado() {
        return estado;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public Float getTotal() {
        return total;
    }

    public Mesa getNumeroMesa() {
        return numeroMesa;
    }

    public ClienteFrecuente getClienteFrecuente() {
        return clienteFrecuente;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public void setEstado(EstadoComanda estado) {
        this.estado = estado;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public void setTotal(Float total) {
        this.total = total;
    }

    public void setNumeroMesa(Mesa numeroMesa) {
        this.numeroMesa = numeroMesa;
    }

    public void setClienteFrecuente(ClienteFrecuente clienteFrecuente) {
        this.clienteFrecuente = clienteFrecuente;
    }
    
    
    
}
