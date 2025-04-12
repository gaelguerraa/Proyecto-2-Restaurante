package sistemarestaurantedominio.dtos;

import sistemarestaurantedominio.ClienteFrecuente;
import sistemarestaurantedominio.Mesa;


public class ActualizarComandaDTO {
    private String folio;
    private float total;
    private Mesa numeroMesa;
    private ClienteFrecuente cliente;

    public ActualizarComandaDTO(String folio, float total, Mesa numeroMesa, ClienteFrecuente cliente) {
        this.folio = folio;
        this.total = total;
        this.numeroMesa = numeroMesa;
        this.cliente = cliente;
    }

    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public Mesa getNumeroMesa() {
        return numeroMesa;
    }

    public void setNumeroMesa(Mesa numeroMesa) {
        this.numeroMesa = numeroMesa;
    }

    public ClienteFrecuente getCliente() {
        return cliente;
    }

    public void setCliente(ClienteFrecuente cliente) {
        this.cliente = cliente;
    }
    
    
}
