package sistemarestaurantedominio.dtos;

public class ProductoComandaDTO {

    private String folioComanda;
    private String producto;
    private int cantidad;
    private String nota;
    private float costo;
    private float total;

    public ProductoComandaDTO(String folioComanda, String producto, int cantidad) {
        this.folioComanda = folioComanda;
        this.producto = producto;
        this.cantidad = cantidad;
    }

    public ProductoComandaDTO(String folioComanda, String producto, int cantidad, String nota, float costo, float total) {
        this.folioComanda = folioComanda;
        this.producto = producto;
        this.cantidad = cantidad;
        this.nota = nota;
        this.costo = costo;
        this.total = total;
    }
    
    

    public String getFolioComanda() {
        return folioComanda;
    }

    public String getProducto() {
        return producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    public float getCosto() {
        return costo;
    }

    public void setCosto(float costo) {
        this.costo = costo;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }
    
    
}
