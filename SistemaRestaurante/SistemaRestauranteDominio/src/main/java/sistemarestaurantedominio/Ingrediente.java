/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemarestaurantedominio;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author jalt2
 */
@Entity
@Table(name="ingredientes")
public class Ingrediente implements Serializable {

    private static final long serialVersionUID = 1L;
    
    /*Atributos*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_ingrediente")
    private Long id;
    
    @Column(name="nombre", nullable=false)
    private String nombre;
    
    @Column(name="unidadMedida", nullable=false)
    private UnidadMedidaIngrediente unidadMedida;
    
    /*Falta el cascade*/
    @OneToMany(mappedBy = "ingrediente")
    private List<IngredienteProducto> productos;
    
    /*Constructores*/
    public Ingrediente() {
    }

    public Ingrediente(String nombre, UnidadMedidaIngrediente unidadMedida) {
        this.nombre = nombre;
        this.unidadMedida = unidadMedida;
    }
    
    
    
    /*Metodos*/
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public UnidadMedidaIngrediente getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(UnidadMedidaIngrediente unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    public List<IngredienteProducto> getProductos() {
        return productos;
    }

    public void setProductos(List<IngredienteProducto> productos) {
        this.productos = productos;
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
        if (!(object instanceof Ingrediente)) {
            return false;
        }
        Ingrediente other = (Ingrediente) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sistemarestaurantedominio.Ingrediente[ id=" + id + " ]";
    }
    
}
