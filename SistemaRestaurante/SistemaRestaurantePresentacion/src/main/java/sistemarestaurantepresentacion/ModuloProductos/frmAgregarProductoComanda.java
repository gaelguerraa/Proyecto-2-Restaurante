/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package sistemarestaurantepresentacion.ModuloProductos;

import java.util.List;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import sistemarestaurantedominio.Comanda;
import sistemarestaurantedominio.Producto;
import sistemarestaurantedominio.TipoProducto;
import sistemarestaurantedominio.dtos.NuevoDetalleComandaDTO;
import sistemarestaurantenegocio.IProductosBO;

/**
 *
 * @author gael_
 */
public class frmAgregarProductoComanda extends javax.swing.JFrame {

    private ControlNavegacionProductos controlador;
    private Comanda comandaActual;
    private IProductosBO productosBO;
    private NuevoDetalleComandaDTO detalleComanda;
    private static final Logger LOG = Logger.getLogger(frmAgregarProductoComanda.class.getName());
    
    /**
     * Creates new form frmAgregarProducto
     */
    public frmAgregarProductoComanda(ControlNavegacionProductos controlador,
                                    Comanda comandaActual) {
        this.controlador=controlador;
        this.comandaActual=comandaActual;
        initComponents();
        LlenarTablaProductos();
        txtFolio.setText(String.valueOf(comandaActual.getFolio()));
        setTitle("Agregar producto");
    }
    
    private void LlenarComboBoxTipoProducto(){
            for(TipoProducto tipo : TipoProducto.values()){
                ComboboxTipo.addItem(tipo.toString());
            }
    }
    
         
    private void LlenarTablaProductos(){
        String nombreFiltro = txtNombre.getText().trim();
        String tipoSeleccionado = (String) ComboboxTipo.getSelectedItem();
        List<Producto> productosConsultados;
        
        String nombreProducto = txtNombre.getText().trim();
        String tipoSeleccionadoo = (String) ComboboxTipo.getSelectedItem();
        if(nombreProducto.isEmpty() && tipoSeleccionadoo == "CUALQUIERA"){
            productosConsultados = productosBO.consultarProducto();
            DefaultTableModel modeloTabla = (DefaultTableModel) this.TablaProductos.getModel();
            modeloTabla.setRowCount(0);
            for(Producto p : productosConsultados){
                Object[] fila = {
                    p.getNombre(),
                    p.getTipo(),
                    p.getPrecio()
                };
                modeloTabla.addRow(fila);
            }
        }else if(!nombreProducto.isEmpty() && tipoSeleccionadoo == "CUALQUIERA"){
            productosConsultados = productosBO.obtenerProductosFiltroNombre(nombreProducto);
            DefaultTableModel modeloTabla = (DefaultTableModel) this.TablaProductos.getModel();
            modeloTabla.setRowCount(0);
            for(Producto p : productosConsultados){
                Object[] fila = {
                    p.getNombre(),
                    p.getTipo(),
                    p.getPrecio()
                };
                modeloTabla.addRow(fila);
            }
        }else if(nombreProducto.isEmpty() && tipoSeleccionadoo != null){
            productosConsultados = productosBO.obtenerProductosPorTipo(tipoSeleccionado);
            DefaultTableModel modeloTabla = (DefaultTableModel) this.TablaProductos.getModel();
            modeloTabla.setRowCount(0);
            for(Producto p : productosConsultados){
                Object[] fila = {
                    p.getNombre(),
                    p.getTipo(),
                    p.getPrecio()
                };
                modeloTabla.addRow(fila);
            }
        }else if(!nombreProducto.isEmpty() && tipoSeleccionadoo != null){
            productosConsultados = productosBO.obtenerProductosPorTipoNombre(nombreProducto, tipoSeleccionado);
            DefaultTableModel modeloTabla = (DefaultTableModel) this.TablaProductos.getModel();
            for(Producto p : productosConsultados){
                Object[] fila = {
                    p.getNombre(),
                    p.getTipo(),
                    p.getPrecio()
                };
                modeloTabla.addRow(fila);
            }
        }
        
    } 
    
    private Producto obtenerProductoSeleccionado() {
        int filaSeleccionada = TablaProductos.getSelectedRow();
            if (filaSeleccionada == -1) {
                JOptionPane.showMessageDialog(this, "Selecciona un producto de la tabla", "Error", JOptionPane.ERROR_MESSAGE);
                return null;
            }

            String nombreProducto = (String) TablaProductos.getValueAt(filaSeleccionada, 0); // Nombre en la primera columna
            List<Producto> listaProductos = productosBO.consultarProducto();

            for (Producto producto : listaProductos) {
                if (producto.getNombre().equals(nombreProducto)) {
                    return producto;
                }
            }

            return null;
    }
    
   
    
    public void guardarProductoComanda(){
        
        Producto productoSeleccionado = obtenerProductoSeleccionado();
        if(productoSeleccionado== null){
            return;
        }
        
        int cantidad = Integer.parseInt(txtCantidad.getText());
        String nota = txtNota.getText().trim();
//        Float precio = productosBO.obtenerPrecioActual(productoSeleccionado);
//        Float total = productosBO.calcularTotal(productoSeleccionado, cantidad);

//        NuevoDetalleComandaDTO detalleComanda = new NuevoDetalleComandaDTO(productoSeleccionado, comandaActual, precio, nota, cantidad, total);
        
        //detallesComandaBO.guardarDetalleComanda(detalleComanda);
        //crea esta madre sabiendo cual es su comanda y luego muestra todo en la tabla de crear comanda

        
    }
    
    public NuevoDetalleComandaDTO regresarDetalleComandaDTO(){
        return detalleComanda;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator1 = new javax.swing.JSeparator();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtCantidad = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtNota = new javax.swing.JTextField();
        BotonContinuar = new javax.swing.JButton();
        BotonCancelar = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        ComboboxTipo = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        BotonBuscar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        TablaProductos = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        txtFolio = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(241, 209, 165));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setText("AGREGAR PRODUCTOS");

        jLabel3.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        jLabel3.setText("Cantidad:");

        txtCantidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCantidadActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        jLabel4.setText("Notas: (Opcional)");

        BotonContinuar.setText("CONTINUAR");
        BotonContinuar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonContinuarActionPerformed(evt);
            }
        });

        BotonCancelar.setText("CANCELAR");

        jLabel5.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        jLabel5.setText("Tipo:");

        ComboboxTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "CUALQUIERA" }));

        jLabel2.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        jLabel2.setText("Nombre:");

        BotonBuscar.setText("BUSCAR PRODUCTO");

        TablaProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre:", "Tipo:", "Precio:"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Float.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(TablaProductos);

        jLabel6.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        jLabel6.setText("Folio Comanda:");

        txtFolio.setText("(mostrar folio aqui)");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(BotonCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(58, 58, 58)
                        .addComponent(BotonContinuar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(123, 123, 123))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(108, 108, 108))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addGap(18, 18, 18)
                                        .addComponent(ComboboxTipo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(27, 27, 27)
                                .addComponent(BotonBuscar))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 391, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel6)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtNota, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtFolio, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(20, 20, 20))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ComboboxTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtFolio, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(BotonBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)))))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 40, Short.MAX_VALUE)
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(txtNota, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(91, 91, 91)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BotonCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BotonContinuar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtCantidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCantidadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCantidadActionPerformed

    private void BotonContinuarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonContinuarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BotonContinuarActionPerformed



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BotonBuscar;
    private javax.swing.JButton BotonCancelar;
    private javax.swing.JButton BotonContinuar;
    private javax.swing.JComboBox<String> ComboboxTipo;
    private javax.swing.JTable TablaProductos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField txtCantidad;
    private javax.swing.JTextField txtFolio;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtNota;
    // End of variables declaration//GEN-END:variables
}
