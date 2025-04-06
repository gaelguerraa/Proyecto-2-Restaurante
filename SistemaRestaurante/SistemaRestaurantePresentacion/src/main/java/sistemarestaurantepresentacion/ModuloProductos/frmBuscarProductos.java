/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package sistemarestaurantepresentacion.ModuloProductos;

import java.util.List;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import static sistemarestaurantedominio.Ingrediente_.productos;
import sistemarestaurantedominio.Producto;
import sistemarestaurantedominio.TipoProducto;
import sistemarestaurantenegocio.IProductosBO;

/**
 *
 * @author gael_
 */
public class frmBuscarProductos extends javax.swing.JFrame {

    private IProductosBO productosBO;
    private ControladorProductos controlador;
    private Producto productoSeleccionado; 
    private String nombreProducto;
    private boolean confirmado = false;
    private boolean modoSeleccion = false;
    private static final Logger LOG = Logger.getLogger(frmBuscarProductos.class.getName());
    
    
    /**
     * Creates new form frmBuscarProductos
     */
    public frmBuscarProductos(IProductosBO productosBO, ControladorProductos controlador) {
        this.productosBO=productosBO;
        this.controlador=controlador;
        initComponents();
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

    }
    
    private void LlenarComboBoxTipoProducto(){
        for(TipoProducto tipo : TipoProducto.values()){
            ComboBoxFiltro.addItem(tipo.toString());
        }
    }
    
    private void LlenarTablaProductos(){
        String nombreFiltro = txtFiltro.getText().trim();
        String tipoSeleccionado = (String) ComboBoxFiltro.getSelectedItem();
        List<Producto> productosConsultados;
        
        String nombreProducto = txtFiltro.getText().trim();
        String tipoSeleccionadoo = (String) ComboBoxFiltro.getSelectedItem();
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
        if (productoSeleccionado != null) {
            return productoSeleccionado; // Devuelve el producto seleccionado
        } else {
            JOptionPane.showMessageDialog(this, "No has seleccionado un producto.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return null; // Si no se ha seleccionado un producto, devuelve null
        }
 
      }  

        
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        LabelTitulo = new javax.swing.JLabel();
        LabelTipo = new javax.swing.JLabel();
        LabelNombre = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TablaProductos = new javax.swing.JTable();
        BotonSeleccionarProducto = new javax.swing.JButton();
        ComboBoxFiltro = new javax.swing.JComboBox<>();
        txtFiltro = new javax.swing.JTextField();
        BotonBuscar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(850, 561));

        jPanel2.setBackground(new java.awt.Color(241, 209, 165));

        jPanel3.setBackground(new java.awt.Color(241, 209, 165));

        LabelTitulo.setFont(new java.awt.Font("Segoe UI Semibold", 1, 36)); // NOI18N
        LabelTitulo.setText("BUSCAR PRODUCTOS");

        LabelTipo.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        LabelTipo.setText("TIPO:");

        LabelNombre.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        LabelNombre.setText("NOMBRE:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(LabelTipo)
                .addGap(131, 131, 131)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(LabelNombre))
                    .addComponent(LabelTitulo))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(LabelTitulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LabelTipo)
                    .addComponent(LabelNombre))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        TablaProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre:", "Tipo:", "Precio:"
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
        BotonSeleccionarProducto.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        BotonSeleccionarProducto.setText("SELECCIONAR PRODUCTO");
        BotonSeleccionarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonSeleccionarProductoActionPerformed(evt);
            }
        });

        ComboBoxFiltro.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "CUALQUIERA" }));

        txtFiltro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFiltroActionPerformed(evt);
            }
        });

        BotonBuscar.setBackground(new java.awt.Color(171, 118, 46));
        BotonBuscar.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
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
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(ComboBoxFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 377, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(48, 48, 48)
                                .addComponent(BotonBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 766, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(276, 276, 276)
                        .addComponent(BotonSeleccionarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(40, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ComboBoxFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BotonBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BotonSeleccionarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(37, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BotonBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonBuscarActionPerformed
        LlenarTablaProductos();
    }//GEN-LAST:event_BotonBuscarActionPerformed

    private void txtFiltroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFiltroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFiltroActionPerformed

    private void BotonSeleccionarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonSeleccionarProductoActionPerformed
        if (modoSeleccion) {
        Producto producto = devolverProducto(); // Llama a devolverProducto
        if (producto != null) {
            confirmado = true; // Esto hace que el proceso de selección continúe
            // Aquí puedes agregar el código para continuar con el proceso de selección del producto, 
            // o hacer lo que sea necesario con el producto devuelto.
        } else {
            JOptionPane.showMessageDialog(this, "Selecciona un producto antes de continuar.", "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    } else {
        // Si no estás en modo selección, puedes agregar otro comportamiento si lo deseas
        // controlador.regresarMenuProductos();
    }
    }//GEN-LAST:event_BotonSeleccionarProductoActionPerformed

    /**
     * @param args the command line arguments
     */
  

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BotonBuscar;
    private javax.swing.JButton BotonSeleccionarProducto;
    private javax.swing.JComboBox<String> ComboBoxFiltro;
    private javax.swing.JLabel LabelNombre;
    private javax.swing.JLabel LabelTipo;
    private javax.swing.JLabel LabelTitulo;
    private javax.swing.JTable TablaProductos;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txtFiltro;
    // End of variables declaration//GEN-END:variables
}
