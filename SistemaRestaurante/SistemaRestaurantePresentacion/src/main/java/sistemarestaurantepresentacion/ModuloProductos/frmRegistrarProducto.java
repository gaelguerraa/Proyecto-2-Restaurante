/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package sistemarestaurantepresentacion.ModuloProductos;


import java.util.logging.Logger;
import javax.swing.JOptionPane;
import sistemarestaurantedominio.TipoProducto;
import sistemarestaurantedominio.dtos.NuevoProductoDTO;
import sistemarestaurantenegocio.IProductosBO;
import sistemarestaurantenegocio.excepciones.NegocioException;


/**
 *
 * @author gael_
 */
public class frmRegistrarProducto extends javax.swing.JFrame {

    private ControlNavegacionProductos controlador;
    private IProductosBO productosBO;
    private static final Logger LOG = Logger.getLogger(frmRegistrarProducto.class.getName());
    
    
    /**
     * Creates new form frmRegistrarProducto
     */
    public frmRegistrarProducto(IProductosBO productosBO, ControlNavegacionProductos controlador) {
        this.controlador = controlador;
        initComponents();
        this.productosBO=productosBO;
        LlenarComboBoxTipoProducto();


    }
    
    private void LlenarComboBoxTipoProducto(){
        for(TipoProducto tipo : TipoProducto.values()){
            jComboBoxTipo.addItem(tipo.name());
        }
    }
    
    private void guardarProducto() {
        
        try {
            String nombre = this.jTextFieldNombre.getText().trim();
            if (nombre.isEmpty()) {
                throw new NegocioException("El nombre del producto no puede estar vacío.");
            }

            String precioTexto = this.jTextFieldPrecio.getText().trim();
            if (precioTexto.isEmpty()) {
                throw new NegocioException("El precio del producto no puede estar vacío.");
            }

            Float precio;
            try {
                precio = Float.parseFloat(precioTexto);
            } catch (NumberFormatException e) {
                throw new NegocioException("El precio debe ser un número válido.");
            }

            String tipoSeleccionado = (String) this.jComboBoxTipo.getSelectedItem();
            TipoProducto tipo = TipoProducto.valueOf(tipoSeleccionado);

            NuevoProductoDTO nuevoProducto = new NuevoProductoDTO(nombre, precio, tipo);
            this.productosBO.registrar(nuevoProducto);

            JOptionPane.showMessageDialog(this, "Éxito al registrar el producto", "Información", JOptionPane.INFORMATION_MESSAGE);
            limpiarFormulario();

        } catch (NegocioException e) {
            LOG.severe("No fue posible registrar el producto: " + e.getMessage());
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    
    private void limpiarFormulario(){
        this.jTextFieldNombre.setText("");
        this.jTextFieldPrecio.setText("");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabelRegistrarProducto = new javax.swing.JLabel();
        jLabelNombre = new javax.swing.JLabel();
        jLabelTipo = new javax.swing.JLabel();
        jTextFieldNombre = new javax.swing.JTextField();
        jLabelPrecio = new javax.swing.JLabel();
        jTextFieldPrecio = new javax.swing.JTextField();
        jComboBoxTipo = new javax.swing.JComboBox<>();
        BotonRegresar = new javax.swing.JButton();
        BotonContinuar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 153, 0));

        jLabelRegistrarProducto.setFont(new java.awt.Font("Verdana", 1, 36)); // NOI18N
        jLabelRegistrarProducto.setText("REGISTRAR PRODUCTO");

        jLabelNombre.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        jLabelNombre.setText("Nombre:");

        jLabelTipo.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        jLabelTipo.setText("Tipo:");

        jTextFieldNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldNombreActionPerformed(evt);
            }
        });

        jLabelPrecio.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        jLabelPrecio.setText("Precio:");

        jComboBoxTipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxTipoActionPerformed(evt);
            }
        });

        BotonRegresar.setBackground(new java.awt.Color(171, 118, 46));
        BotonRegresar.setFont(new java.awt.Font("Segoe UI Historic", 1, 18)); // NOI18N
        BotonRegresar.setText("REGRESAR");
        BotonRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonRegresarActionPerformed(evt);
            }
        });

        BotonContinuar.setBackground(new java.awt.Color(171, 118, 46));
        BotonContinuar.setFont(new java.awt.Font("Segoe UI Historic", 1, 18)); // NOI18N
        BotonContinuar.setText("CONTINUAR");
        BotonContinuar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonContinuarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(148, 148, 148)
                        .addComponent(jLabelRegistrarProducto))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addComponent(BotonRegresar, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(76, 76, 76)
                        .addComponent(BotonContinuar, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(259, 259, 259)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelNombre)
                            .addComponent(jTextFieldNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelPrecio)
                            .addComponent(jTextFieldPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelTipo)
                            .addComponent(jComboBoxTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(198, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabelRegistrarProducto)
                .addGap(27, 27, 27)
                .addComponent(jLabelNombre)
                .addGap(18, 18, 18)
                .addComponent(jTextFieldNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17)
                .addComponent(jLabelPrecio)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabelTipo)
                .addGap(18, 18, 18)
                .addComponent(jComboBoxTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BotonRegresar, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BotonContinuar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(29, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BotonRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonRegresarActionPerformed
        this.limpiarFormulario();
    }//GEN-LAST:event_BotonRegresarActionPerformed

    private void jTextFieldNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldNombreActionPerformed

    private void jComboBoxTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxTipoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxTipoActionPerformed

    private void BotonContinuarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonContinuarActionPerformed
        guardarProducto();
        controlador.mostrarMenuProductos();
        this.limpiarFormulario();
        this.dispose();

    }//GEN-LAST:event_BotonContinuarActionPerformed

    
    
    
    
   
    
    /**
     * @param args the command line arguments
     */

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BotonContinuar;
    private javax.swing.JButton BotonRegresar;
    private javax.swing.JComboBox<String> jComboBoxTipo;
    private javax.swing.JLabel jLabelNombre;
    private javax.swing.JLabel jLabelPrecio;
    private javax.swing.JLabel jLabelRegistrarProducto;
    private javax.swing.JLabel jLabelTipo;
    private javax.swing.JTextField jTextFieldNombre;
    private javax.swing.JTextField jTextFieldPrecio;
    // End of variables declaration//GEN-END:variables
}
