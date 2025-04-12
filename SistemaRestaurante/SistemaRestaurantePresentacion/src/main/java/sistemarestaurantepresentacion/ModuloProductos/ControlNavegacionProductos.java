package sistemarestaurantepresentacion.ModuloProductos;

import sistemarestaurantedominio.Producto;
import sistemarestaurantenegocio.IIngredientesProductosBO;
import sistemarestaurantenegocio.IProductosBO;
import sistemarestaurantenegocio.fabrica.FabricaObjetosNegocio;
import sistemarestaurantepresentacion.frmMenuPrincipal;

/**
 *
 * @author gael_
 */
public class ControlNavegacionProductos {
    
    private IProductosBO productosBO;
    private IIngredientesProductosBO ingredientesProductosBO;
    private frmMenuProductos menuProductos;
    private frmRegistrarProducto registrarProducto;
    private frmAnadirIngredienteProducto anadirIngredienteProducto;
    private frmMostrarProductos mostrarProductos;
    private frmMenuPrincipal menuPrincipal;    
    private frmBuscarProducto buscarProducto;
    private frmCambiarPrecioProducto cambiarPrecio;


    public ControlNavegacionProductos() {
        this.productosBO= FabricaObjetosNegocio.crearProductosBO();
        this.ingredientesProductosBO = FabricaObjetosNegocio.crearIngredientesProductosBO();
    }
    
    
    public void mostrarMenuProductos() {
        this.menuProductos = new frmMenuProductos(this);
        menuProductos.setVisible(true);

    }

    public void mostrarRegistrarProducto() {
        this.registrarProducto = new frmRegistrarProducto(productosBO, this);
        registrarProducto.setVisible(true);
   
    }

    public void mostrarAnadirIngredienteProducto() {
        this.anadirIngredienteProducto = new frmAnadirIngredienteProducto(ingredientesProductosBO, productosBO, this);
        anadirIngredienteProducto.setVisible(true);
    }
    
    public void mostrarProductos(){
        this.mostrarProductos = new frmMostrarProductos(productosBO, this);
        mostrarProductos.setVisible(true);
    }
    
    public void regresarMenuPrincipal(){
        this.menuProductos.dispose();
        menuPrincipal = new frmMenuPrincipal(); 
        menuPrincipal.setVisible(true);
    }
    
    //metodo para devolver producto a comandas
 
    public Producto regresarProducto(){
        this.buscarProducto = new frmBuscarProducto(productosBO, this);
        buscarProducto.setVisible(true);
        return buscarProducto.devolverProducto();
        
    }
    
    public void cambiarPrecioProducto(){
        this.cambiarPrecio = new frmCambiarPrecioProducto(productosBO, this);
        cambiarPrecio.setVisible(true);
    }
    
}
