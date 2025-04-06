/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package sistemarestaurantepresentacion.ModuloProductos;

import java.util.List;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import sistemarestaurantedominio.Ingrediente;
import sistemarestaurantedominio.Producto;
import sistemarestaurantedominio.UnidadMedidaIngrediente;
import sistemarestaurantedominio.dtos.NuevoIngredienteProductoDTO;
import sistemarestaurantedominio.dtos.NuevoProductoDTO;
import sistemarestaurantenegocio.IIngredientesProductosBO;
import sistemarestaurantenegocio.IProductosBO;
import sistemarestaurantenegocio.excepciones.NegocioException;
import sistemarestaurantepresentacion.frmMenuPrincipal;

/**
 *
 * @author gael_
 */
public class frmAnadirIngredienteProducto extends javax.swing.JFrame {

    private ControladorProductos controlador;
    private IIngredientesProductosBO ingredientesProductosBO;
    private IProductosBO productosBO;
     private static final Logger LOG = Logger.getLogger(frmAnadirIngredienteProducto.class.getName());
    
    /**
     * Creates new form frmAnadirIngredienteProducto
     */
    public frmAnadirIngredienteProducto(IIngredientesProductosBO ingredientesProductosBO, IProductosBO productosBO, ControladorProductos controlador) {
        this.controlador = controlador;
        this.ingredientesProductosBO=ingredientesProductosBO;
        this.productosBO=productosBO;
        initComponents();
        LlenarComboBoxIngrediente();
        LlenarTablaProductos();
    }
    
    public void LlenarTablaProductos(){
        List<Producto> listaProductos = productosBO.consultarProducto();
        DefaultTableModel modeloTabla = (DefaultTableModel)this.TablaProductos.getModel();
        modeloTabla.setRowCount(0);
        
        for(Producto p : listaProductos){
            Object[] fila = {
                p.getNombre(),
                p.getTipo()
            };
            modeloTabla.addRow(fila);
        }
    }


   
      private void LlenarComboBoxIngrediente() {
        List<Ingrediente> ingredientes = ingredientesProductosBO.obtenerIngredientes();

        jComboBoxIngrediente.removeAllItems();

        for (Ingrediente ingrediente : ingredientes) {
            String nombreCompleto = ingrediente.getNombre() + " - " + ingrediente.getUnidadMedida();
            jComboBoxIngrediente.addItem(nombreCompleto);
        }
    }
    
      public Ingrediente enviarIngredienteSeleccionado() {
        String seleccionado = (String) jComboBoxIngrediente.getSelectedItem();
        
        if (seleccionado != null) {
            List<Ingrediente> ingredientes = ingredientesProductosBO.obtenerIngredientes();

            for (Ingrediente ingrediente : ingredientes) {
                String nombreCompleto = ingrediente.getNombre() + " - " + ingrediente.getUnidadMedida();
                if (nombreCompleto.equals(seleccionado)) {
                    return ingrediente;
                }
            }
        }
        return null;
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
    
    private void aniadirIngredienteProducto(){
        Producto productoSeleccionado = obtenerProductoSeleccionado();
        if(productoSeleccionado== null){
            return;
        }
        
        Ingrediente ingredienteSeleccionado = enviarIngredienteSeleccionado();
        if(ingredienteSeleccionado == null){
            JOptionPane.showMessageDialog(this, "Selecciona un ingrediente válido", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        Float cantidad;
        try {
            cantidad = Float.parseFloat(this.jTextFieldCantidad.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Ingresa una cantidad válida", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
         NuevoIngredienteProductoDTO nuevoIP = new NuevoIngredienteProductoDTO(productoSeleccionado, ingredienteSeleccionado, cantidad);
         
         try {
            ingredientesProductosBO.registrarIngredienteProductoBO(nuevoIP);
            JOptionPane.showMessageDialog(this, "Ingrediente añadido con éxito", "Información", JOptionPane.INFORMATION_MESSAGE);
            limpiarFormulario();
        } catch (NegocioException e) {
            LOG.severe("No fue posible registrar el ingrediente-producto " + e.getMessage());
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
         
    }    
    
    
    private void limpiarFormulario(){
        this.jTextFieldCantidad.setText("");

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabelANADIRINGREDIENTES = new javax.swing.JLabel();
        jLabelIngrediente = new javax.swing.JLabel();
        jLabelCantidad = new javax.swing.JLabel();
        jComboBoxIngrediente = new javax.swing.JComboBox<>();
        jTextFieldCantidad = new javax.swing.JTextField();
        BotonAnadirIngrediente = new javax.swing.JButton();
        BotonRegistrarProducto = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        TablaProductos = new javax.swing.JTable();
        BotonActTabla = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabelANADIRINGREDIENTES.setFont(new java.awt.Font("Verdana", 1, 36)); // NOI18N
        jLabelANADIRINGREDIENTES.setText("AÑADIR INGREDIENTES");

        jLabelIngrediente.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        jLabelIngrediente.setText("Ingrediente:");

        jLabelCantidad.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        jLabelCantidad.setText("Cantidad:");

        jComboBoxIngrediente.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jTextFieldCantidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldCantidadActionPerformed(evt);
            }
        });

        BotonAnadirIngrediente.setBackground(new java.awt.Color(171, 118, 46));
        BotonAnadirIngrediente.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        BotonAnadirIngrediente.setText("AÑADIR INGREDIENTE");
        BotonAnadirIngrediente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonAnadirIngredienteActionPerformed(evt);
            }
        });

        BotonRegistrarProducto.setBackground(new java.awt.Color(171, 118, 46));
        BotonRegistrarProducto.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        BotonRegistrarProducto.setText("REGISTRAR PRODUCTO");
        BotonRegistrarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonRegistrarProductoActionPerformed(evt);
            }
        });

        TablaProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre", "Tipo"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(TablaProductos);

        BotonActTabla.setText("Actualizar Tabla");
        BotonActTabla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonActTablaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(107, 107, 107)
                .addComponent(jLabelANADIRINGREDIENTES)
                .addContainerGap(118, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 383, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(BotonRegistrarProducto, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BotonAnadirIngrediente, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabelIngrediente)
                                    .addComponent(jLabelCantidad)
                                    .addComponent(jComboBoxIngrediente, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jTextFieldCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(26, 26, 26)))
                        .addGap(39, 39, 39))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(115, 115, 115)
                        .addComponent(BotonActTabla)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabelANADIRINGREDIENTES)
                .addGap(43, 43, 43)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 378, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(57, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabelIngrediente)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBoxIngrediente, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabelCantidad)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(44, 44, 44)
                        .addComponent(BotonAnadirIngrediente, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(BotonRegistrarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(BotonActTabla)
                        .addGap(35, 35, 35))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextFieldCantidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldCantidadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldCantidadActionPerformed

    private void BotonAnadirIngredienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonAnadirIngredienteActionPerformed
        aniadirIngredienteProducto();
        this.limpiarFormulario();
    }//GEN-LAST:event_BotonAnadirIngredienteActionPerformed

    private void BotonRegistrarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonRegistrarProductoActionPerformed
        controlador.mostrarMenuProductos();
        this.limpiarFormulario();
    }//GEN-LAST:event_BotonRegistrarProductoActionPerformed

    private void BotonActTablaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonActTablaActionPerformed
        LlenarTablaProductos();
    }//GEN-LAST:event_BotonActTablaActionPerformed

    /**
     * @param args the command line arguments
     */
    


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BotonActTabla;
    private javax.swing.JButton BotonAnadirIngrediente;
    private javax.swing.JButton BotonRegistrarProducto;
    private javax.swing.JTable TablaProductos;
    private javax.swing.JComboBox<String> jComboBoxIngrediente;
    private javax.swing.JLabel jLabelANADIRINGREDIENTES;
    private javax.swing.JLabel jLabelCantidad;
    private javax.swing.JLabel jLabelIngrediente;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextFieldCantidad;
    // End of variables declaration//GEN-END:variables
}
