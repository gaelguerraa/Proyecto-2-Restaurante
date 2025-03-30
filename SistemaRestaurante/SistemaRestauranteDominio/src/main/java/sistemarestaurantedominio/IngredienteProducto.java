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

/**
 *
 * @author jalt2
 */
@Entity
@Table(name="ingredientes_productos")
public class IngredienteProducto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_ingrediente_producto")
    private Long id;
    
    @Column(name="cantidad_ingredientes", nullable=false)
    private Float cantidadIngrediente;
    
    //muchos IngredienteProducto estan asociados a un ingrediente
    @ManyToOne()
    @JoinColumn(name="id_ingrediente", nullable=false)
    private Ingrediente ingrediente;
    
    //muchos IngredienteProducto estan asociados a un ingrediente
    @ManyToOne()
    @JoinColumn(name="id_producto", nullable=false)
    private Producto producto;
    
    
    
    
    public IngredienteProducto() {
    }

    public IngredienteProducto(Float cantidadIngrediente, Ingrediente ingrediente, Producto producto) {
        this.cantidadIngrediente = cantidadIngrediente;
        this.ingrediente = ingrediente;
        this.producto = producto;
    }
    
    
    
    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getCantidadIngrediente() {
        return cantidadIngrediente;
    }

    public void setCantidadIngrediente(Float cantidadIngrediente) {
        this.cantidadIngrediente = cantidadIngrediente;
    }

    public Ingrediente getIngrediente() {
        return ingrediente;
    }

    public void setIngrediente(Ingrediente ingrediente) {
        this.ingrediente = ingrediente;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
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
        if (!(object instanceof IngredienteProducto)) {
            return false;
        }
        IngredienteProducto other = (IngredienteProducto) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sistemarestaurantedominio.ingrediente_producto[ id=" + id + " ]";
    }
    
}
