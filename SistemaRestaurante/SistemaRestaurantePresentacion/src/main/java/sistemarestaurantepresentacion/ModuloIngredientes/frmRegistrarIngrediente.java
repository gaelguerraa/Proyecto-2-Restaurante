/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package sistemarestaurantepresentacion.ModuloIngredientes;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import sistemarestaurantedominio.UnidadMedidaIngrediente;
import sistemarestaurantedominio.dtos.NuevoIngredienteDTO;
import sistemarestaurantenegocio.IIngredientesBO;
import sistemarestaurantenegocio.excepciones.NegocioException;

/**
 *
 * @author jalt2
 */
public class FrmRegistrarIngrediente extends javax.swing.JFrame {
    private ControlNavegacionIngredientes control;
    private IIngredientesBO ingredientesBO;
    private static final Logger LOG = Logger.getLogger(FrmRegistrarIngrediente.class.getName());
    
    /**
     * Creates new form frmRegistrarIngrediente
     */
    public FrmRegistrarIngrediente(ControlNavegacionIngredientes control,IIngredientesBO ingredientesBO) {
        initComponents();
        this.control = control;
        this.ingredientesBO=ingredientesBO;
        this.llenarComboBoxMedida();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        pnlRegistroIngrediente = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtNombreIngrediente = new javax.swing.JTextField();
        cmbUnidadMedida = new javax.swing.JComboBox<>();
        txtStock = new javax.swing.JTextField();
        btnRegistrar = new javax.swing.JButton();
        btnRegresar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(241, 209, 165));
        setForeground(new java.awt.Color(187, 187, 187));

        jLabel1.setFont(new java.awt.Font("Verdana", 1, 36)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("REGISTRAR INGREDIENTE");

        pnlRegistroIngrediente.setBorder(javax.swing.BorderFactory.createTitledBorder("Registro"));

        jLabel2.setText("Nombre");

        jLabel3.setText("UnIdad de medida");

        jLabel4.setText("Stock");

        javax.swing.GroupLayout pnlRegistroIngredienteLayout = new javax.swing.GroupLayout(pnlRegistroIngrediente);
        pnlRegistroIngrediente.setLayout(pnlRegistroIngredienteLayout);
        pnlRegistroIngredienteLayout.setHorizontalGroup(
            pnlRegistroIngredienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlRegistroIngredienteLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(pnlRegistroIngredienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(txtNombreIngrediente, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(83, 83, 83)
                .addGroup(pnlRegistroIngredienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmbUnidadMedida, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 106, Short.MAX_VALUE)
                .addGroup(pnlRegistroIngredienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtStock, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(24, 24, 24))
        );
        pnlRegistroIngredienteLayout.setVerticalGroup(
            pnlRegistroIngredienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlRegistroIngredienteLayout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addGroup(pnlRegistroIngredienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlRegistroIngredienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNombreIngrediente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbUnidadMedida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtStock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(107, Short.MAX_VALUE))
        );

        btnRegistrar.setText("REGISTRAR");
        btnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarActionPerformed(evt);
            }
        });

        btnRegresar.setText("REGRESAR");
        btnRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 622, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnRegresar)
                    .addComponent(btnRegistrar))
                .addGap(266, 266, 266))
            .addComponent(pnlRegistroIngrediente, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(pnlRegistroIngrediente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnRegistrar)
                .addGap(18, 18, 18)
                .addComponent(btnRegresar)
                .addContainerGap(58, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed
        // TODO add your handling code here:
        control.IniciarFrmMenuIngredientes();
        dispose();
    }//GEN-LAST:event_btnRegresarActionPerformed

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed
        // TODO add your handling code here:
        this.registrarIngrediente();
        
    }//GEN-LAST:event_btnRegistrarActionPerformed
    
    private void llenarComboBoxMedida(){
        for(UnidadMedidaIngrediente unidadMedida : UnidadMedidaIngrediente.values()){
            this.cmbUnidadMedida.addItem(unidadMedida.toString());
        }
    }
    
    private void registrarIngrediente(){
        String nombreIngrediente = this.txtNombreIngrediente.getText();
        String unidadMedida = this.cmbUnidadMedida.getSelectedItem().toString();
        String sotck = this.txtStock.getText();
        
        
        
        try {
            Float stockFloat = Float.valueOf(sotck);
            NuevoIngredienteDTO nuevoIngrediente = new NuevoIngredienteDTO(nombreIngrediente, UnidadMedidaIngrediente.valueOf(unidadMedida), stockFloat);
            System.out.println("Voy a llamar a ingredientesBO.registrarIngrediente");
            this.ingredientesBO.registrarIngrediente(nuevoIngrediente);
            control.IniciarFrmMensajeRegistroIngredienteExitoso();
            dispose();
            
        } catch (NegocioException e) {
            LOG.severe("No fue posible registrar el ingrediente: " + e.getMessage());
            JOptionPane.showMessageDialog(this, e.getMessage(),"Error" , JOptionPane.WARNING_MESSAGE);
            
        }catch (NumberFormatException e) {
            LOG.severe("No fue posible registrar el ingrediente: " + e.getMessage());
            JOptionPane.showMessageDialog(this, "El stock debe ser un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
        }    
        this.limpiarFormulario();
    }
    
    private void limpiarFormulario(){
        this.txtNombreIngrediente.setText("");
        this.txtStock.setText("");
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRegistrar;
    private javax.swing.JButton btnRegresar;
    private javax.swing.JComboBox<String> cmbUnidadMedida;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel pnlRegistroIngrediente;
    private javax.swing.JTextField txtNombreIngrediente;
    private javax.swing.JTextField txtStock;
    // End of variables declaration//GEN-END:variables
}
