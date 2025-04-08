/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemarestaurantedominio;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import sistemarestaurantedominio.Producto;

/**
 *
 * @author gael_
 */
@Entity
@Table(name = "detalles_comandas")
public class DetallesComanda implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column (name="cantidad", nullable=false)
    private Integer cantidadProducto;
    
    @Column (name="precio_unitario", nullable = false, precision = 10, scale = 2)
    private Float precioUnitarioProducto;
    
    @Column (name = "importe_total", nullable = false, precision = 10, scale = 2)
    private Float importeTotal;
    
    @Column(name = "nota", nullable = true, length = 100)
    private String nota;
    
    //muchos DetallesComandas estan asociadas a un producto
    @ManyToOne
    @JoinColumn(name = "id_producto", nullable = false)
    private Producto producto;
    
    //muchos DetallesComandas estan asociadas a una comanda
    @ManyToOne
    @JoinColumn(name = "id_comanda", nullable = true)
    private Comanda comanda;

    public DetallesComanda() {
    }

    public DetallesComanda(Integer cantidadProducto, Float precioUnitarioProducto, Float importeTotal, String nota, Producto producto, Comanda comanda) {
        this.cantidadProducto = cantidadProducto;
        this.precioUnitarioProducto = precioUnitarioProducto;
        this.importeTotal = importeTotal;
        this.nota = nota;
        this.producto = producto;
        this.comanda = comanda;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCantidadProducto() {
        return cantidadProducto;
    }

    public void setCantidadProducto(Integer cantidadProducto) {
        this.cantidadProducto = cantidadProducto;
    }

    public Float getPrecioUnitarioProducto() {
        return precioUnitarioProducto;
    }

    public void setPrecioUnitarioProducto(Float precioUnitarioProducto) {
        this.precioUnitarioProducto = precioUnitarioProducto;
    }

    public Float getImporteTotal() {
        return importeTotal;
    }

    public void setImporteTotal(Float importeTotal) {
        this.importeTotal = importeTotal;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Comanda getComanda() {
        return comanda;
    }

    public void setComanda(Comanda comanda) {
        this.comanda = comanda;
    }

    public Float getTotal(){
        return producto.getPrecio() * cantidadProducto;
    }
    
      

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetallesComanda)) {
            return false;
        }
        DetallesComanda other = (DetallesComanda) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sistemarestaurantedominio.DetallesComandas[ id=" + id + " ]";
    }
    
}
