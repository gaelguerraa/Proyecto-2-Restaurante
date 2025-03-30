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
import sistemarestaurantedominio.Producto;

/**
 *
 * @author gael_
 */
@Entity
public class DetallesComanda implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column (name="cantidad", nullable=false)
    private Integer cantidadProducto;
    
    @Column (name="precioUnitario", nullable = false, precision = 10, scale = 2)
    private double precioUnitarioProducto;
    
    @Column (name = "importeTotal", nullable = false, precision = 10, scale = 2)
    private double importeTotal;
    
    @Column(name = "nota", nullable = true, length = 100)
    private String nota;
    
    @ManyToOne
    @JoinColumn(name = "idProducto", nullable = false)
    private Producto producto;
    
//    @ManyToOne(mappedBy = "comada", cascade = {CascadeType.PERSIST})
//    private Comanda comanda;

    
    
    //private Comanda comanda;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
