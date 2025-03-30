/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemarestaurantedominio;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author gael_
 */
@Entity
@Table(name="productos")
public class Producto implements Serializable {

   
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_producto")
    private Long id;
    
    @Column(name = "nombre", nullable=false)
    private String nombre;
    
    @Column (name = "precio", nullable=false, precision = 10, scale = 2 )
    private Float precio;
    
    @Enumerated(EnumType.STRING)
    @Column (name = "tipo", nullable=false)
    private TipoProducto tipo;
    
    //relacion con IngredientesProducto(un producto puede tener varios ingredientes)
    //No se borra el producto si se borra el IngredienteProducto
    @OneToMany(mappedBy = "producto", cascade = CascadeType.PERSIST)
    private List<IngredienteProducto> ingredientes;
    
    //un producto puede aparecer en varias detalles comandas, si se borra un producto detalleComanda no se borrara
    //SI SE BORRA UN PRODUCTO QUE ESTA EN DETALLECOMANDA SALTARA UN ERROR DE VIOLACION DE LLAVE FORANEA
    @OneToMany(mappedBy = "producto", cascade = CascadeType.PERSIST)
    private List<DetallesComanda> detallesComanda;

    public Producto() {
    }

    public Producto(String nombre, Float precio, TipoProducto tipo) {
        this.nombre = nombre;
        this.precio = precio;
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(Float precio) {
        this.precio = precio;
    }

    public TipoProducto getTipo() {
        return tipo;
    }

    public void setTipo(TipoProducto tipo) {
        this.tipo = tipo;
    }

    public List<IngredienteProducto> getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(List<IngredienteProducto> ingredientes) {
        this.ingredientes = ingredientes;
    }

    public List<DetallesComanda> getDetallesComanda() {
        return detallesComanda;
    }

    public void setDetallesComanda(List<DetallesComanda> detallesComanda) {
        this.detallesComanda = detallesComanda;
    }
            
           
    
    

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
        if (!(object instanceof Producto)) {
            return false;
        }
        Producto other = (Producto) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sistemarestaurantedominio.Producto[ id=" + id + " ]";
    }
    
}