/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemarestaurantedominio.dtos;

import sistemarestaurantedominio.Comanda;
import sistemarestaurantedominio.Producto;

/**
 *
 * @author gael_
 */
public class NuevoDetalleComandaDTO {
    private Producto produto;
    private Comanda comanda;
    private Float precioActual;
    private String nota;
    private Integer cantidad;
    private Float importe;

    public NuevoDetalleComandaDTO() {
    }
    
    

    public NuevoDetalleComandaDTO(Producto produto, Comanda comanda, Float precioActual, String nota, Integer cantidad, Float importe) {
        this.produto = produto;
        this.comanda = comanda;
        this.precioActual = precioActual;
        this.nota = nota;
        this.cantidad = cantidad;
        this.importe = importe;
    }

    public Producto getProduto() {
        return produto;
    }

    public Float getPrecioActual() {
        return precioActual;
    }

    public String getNota() {
        return nota;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public Float getImporte() {
        return importe;
    }

    public Comanda getComanda() {
        return comanda;
    }

    public void setComanda(Comanda comanda) {
        this.comanda = comanda;
    }
    
        public void setProduto(Producto produto) {
        this.produto = produto;
    }

    public void setPrecioActual(Float precioActual) {
        this.precioActual = precioActual;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public void setImporte(Float importe) {
        this.importe = importe;
    }
    
    
    
}
