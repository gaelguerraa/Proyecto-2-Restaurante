/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package sistemarestaurantepresentacion.ModuloProductos;

import java.util.List;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import sistemarestaurantedominio.Producto;
import sistemarestaurantedominio.TipoProducto;
import sistemarestaurantenegocio.IProductosBO;

/**
 *
 * @author gael_
 */
public class frmBuscarProducto extends javax.swing.JDialog {

    
    private IProductosBO productosBO;
    private Producto productoSeleccionado; 
    private String nombreProducto;
    private boolean confirmado = false;
    private boolean modoSeleccion = false;
    private ControlNavegacionProductos controlador;

    
    
    /**
     * Creates new form frmBuscarProducto
     */
      public frmBuscarProducto(IProductosBO productosBO, ControlNavegacionProductos controlador) {
        super((JFrame) null, "Buscar Producto", true); 
        initComponents();
        this.productosBO=productosBO;
        this.controlador=controlador;
        setLocationRelativeTo(null);
        LlenarComboBoxTipoProducto();
        this.LlenarTablaProductos();
        
        TablaProductos.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = TablaProductos.getSelectedRow();
                if (selectedRow != -1) {
                    this.nombreProducto = (String) TablaProductos.getValueAt(selectedRow, 0);
                    this.productoSeleccionado = productosBO.consultarProductoPorNombre(nombreProducto);
                }
            }
        });

        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    }
    
        private void LlenarComboBoxTipoProducto(){
            for(TipoProducto tipo : TipoProducto.values()){
                ComboBoxFiltro.addItem(tipo.toString());
            }
        }
        
        private void LlenarTablaProductos(){
        List<Producto> productosConsultados;
        
        String nombreProducto = txtFiltro.getText().trim();
        String tipoSeleccionado = (String) ComboBoxFiltro.getSelectedItem();
        if(nombreProducto.isEmpty() && tipoSeleccionado.equals("CUALQUIERA")){
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
        }else if(!nombreProducto.isEmpty() && tipoSeleccionado.equals("CUALQUIERA")){
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
        }else if(nombreProducto.isEmpty() && tipoSeleccionado != null && !tipoSeleccionado.equals("CUALQUIERA")){
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
        }else if(!nombreProducto.isEmpty() && tipoSeleccionado != null && !tipoSeleccionado.equals("CUALQUIERA")){
            productosConsultados = productosBO.obtenerProductosPorTipoNombre(nombreProducto, tipoSeleccionado);
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
        }
        
    } 
        
     public Producto devolverProducto(){
        return productoSeleccionado;
      }      
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        titulo = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        ComboBoxFiltro = new javax.swing.JComboBox<>();
        txtFiltro = new javax.swing.JTextField();
        BotonBuscar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        TablaProductos = new javax.swing.JTable();
        BotonSeleccionarProducto = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(850, 670));

        jPanel1.setBackground(new java.awt.Color(241, 209, 165));

        jPanel2.setBackground(new java.awt.Color(241, 209, 165));

        titulo.setFont(new java.awt.Font("Segoe UI Semibold", 1, 36)); // NOI18N
        titulo.setText("BUSCAR PRODUCTO");

        jLabel1.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        jLabel1.setText("Tipo:");

        jLabel2.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabel2.setText("Nombre:");

        ComboBoxFiltro.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "CUALQUIERA" }));
        ComboBoxFiltro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboBoxFiltroActionPerformed(evt);
            }
        });

        txtFiltro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFiltroActionPerformed(evt);
            }
        });

        BotonBuscar.setBackground(new java.awt.Color(171, 118, 46));
        BotonBuscar.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        BotonBuscar.setText("BUSCAR");
        BotonBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonBuscarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(titulo))
                        .addGap(292, 292, 292))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(ComboBoxFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39)
                        .addComponent(txtFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 431, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                        .addComponent(BotonBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(titulo)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(ComboBoxFiltro)
                    .addComponent(BotonBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFiltro, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        TablaProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre", "Tipo", "Precio"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Object.class, java.lang.Float.class
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

        BotonSeleccionarProducto.setBackground(new java.awt.Color(171, 118, 46));
        BotonSeleccionarProducto.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        BotonSeleccionarProducto.setText("SELECCIONAR PRODUCTO");
        BotonSeleccionarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonSeleccionarProductoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jScrollPane1))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(277, 277, 277)
                .addComponent(BotonSeleccionarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(BotonSeleccionarProducto, javax.swing.GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE)
                .addGap(86, 86, 86))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ComboBoxFiltroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboBoxFiltroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ComboBoxFiltroActionPerformed

    private void txtFiltroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFiltroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFiltroActionPerformed

    private void BotonBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonBuscarActionPerformed
        LlenarTablaProductos();
    }//GEN-LAST:event_BotonBuscarActionPerformed

    private void BotonSeleccionarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonSeleccionarProductoActionPerformed
        if (productoSeleccionado != null) {
            confirmado = true;
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Selecciona un producto antes de continuar.",
                    "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_BotonSeleccionarProductoActionPerformed

    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BotonBuscar;
    private javax.swing.JButton BotonSeleccionarProducto;
    private javax.swing.JComboBox<String> ComboBoxFiltro;
    private javax.swing.JTable TablaProductos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel titulo;
    private javax.swing.JTextField txtFiltro;
    // End of variables declaration//GEN-END:variables
}
