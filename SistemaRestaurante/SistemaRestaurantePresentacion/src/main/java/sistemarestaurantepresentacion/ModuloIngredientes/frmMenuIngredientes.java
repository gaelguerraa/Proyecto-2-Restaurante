package sistemarestaurantepresentacion.ModuloIngredientes;

import java.util.logging.Level;
import java.util.logging.Logger;
import sistemarestaurantenegocio.excepciones.NegocioException;

public class FrmMenuIngredientes extends javax.swing.JFrame {
    
    private ControlNavegacionIngredientes control;
    
    /**
     * Creates new form frmMenuIngredientes
     */
    public FrmMenuIngredientes(ControlNavegacionIngredientes control) {
        this.control=control;
        initComponents();
        setTitle("Menu ingredientes");
        setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlMenuIngredientes = new javax.swing.JPanel();
        btnRegistrarIngrediente = new javax.swing.JButton();
        btnBuscarIngrediente = new javax.swing.JButton();
        btnVolverMenuPrincipal = new javax.swing.JButton();
        lblBuscarIngrediente = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(241, 209, 165));

        pnlMenuIngredientes.setBackground(new java.awt.Color(241, 209, 165));
        pnlMenuIngredientes.setBorder(javax.swing.BorderFactory.createTitledBorder("Menu"));
        pnlMenuIngredientes.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        btnRegistrarIngrediente.setBackground(new java.awt.Color(220, 145, 79));
        btnRegistrarIngrediente.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnRegistrarIngrediente.setText("REGISTRAR INGREDIENTE");
        btnRegistrarIngrediente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarIngredienteActionPerformed(evt);
            }
        });

        btnBuscarIngrediente.setBackground(new java.awt.Color(220, 145, 79));
        btnBuscarIngrediente.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnBuscarIngrediente.setText("BUSCAR INGREDIENTE");
        btnBuscarIngrediente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarIngredienteActionPerformed(evt);
            }
        });

        btnVolverMenuPrincipal.setBackground(new java.awt.Color(220, 145, 79));
        btnVolverMenuPrincipal.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnVolverMenuPrincipal.setText("VOLVER");
        btnVolverMenuPrincipal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolverMenuPrincipalActionPerformed(evt);
            }
        });

        lblBuscarIngrediente.setBackground(new java.awt.Color(241, 209, 165));
        lblBuscarIngrediente.setFont(new java.awt.Font("Verdana", 1, 36)); // NOI18N
        lblBuscarIngrediente.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblBuscarIngrediente.setText("INGREDIENTES");

        javax.swing.GroupLayout pnlMenuIngredientesLayout = new javax.swing.GroupLayout(pnlMenuIngredientes);
        pnlMenuIngredientes.setLayout(pnlMenuIngredientesLayout);
        pnlMenuIngredientesLayout.setHorizontalGroup(
            pnlMenuIngredientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblBuscarIngrediente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnlMenuIngredientesLayout.createSequentialGroup()
                .addGap(313, 313, 313)
                .addGroup(pnlMenuIngredientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnRegistrarIngrediente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnBuscarIngrediente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnVolverMenuPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(324, Short.MAX_VALUE))
        );
        pnlMenuIngredientesLayout.setVerticalGroup(
            pnlMenuIngredientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMenuIngredientesLayout.createSequentialGroup()
                .addComponent(lblBuscarIngrediente, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(88, 88, 88)
                .addComponent(btnRegistrarIngrediente)
                .addGap(18, 18, 18)
                .addComponent(btnBuscarIngrediente)
                .addGap(74, 74, 74)
                .addComponent(btnVolverMenuPrincipal)
                .addContainerGap(125, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlMenuIngredientes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlMenuIngredientes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegistrarIngredienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarIngredienteActionPerformed
        // TODO add your handling code here:
        control.IniciarFrmRegistrarIngrediente();
        dispose();
    }//GEN-LAST:event_btnRegistrarIngredienteActionPerformed

    private void btnBuscarIngredienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarIngredienteActionPerformed
        try {
            // TODO add your handling code here:
            control.IniciarFrmBuscarIngredientes();
        } catch (NegocioException ex) {
            Logger.getLogger(FrmMenuIngredientes.class.getName()).log(Level.SEVERE, null, ex);
        }
        dispose();
    }//GEN-LAST:event_btnBuscarIngredienteActionPerformed

    private void btnVolverMenuPrincipalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverMenuPrincipalActionPerformed
        // TODO add your handling code here:
        control.salir();
    }//GEN-LAST:event_btnVolverMenuPrincipalActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscarIngrediente;
    private javax.swing.JButton btnRegistrarIngrediente;
    private javax.swing.JButton btnVolverMenuPrincipal;
    private javax.swing.JLabel lblBuscarIngrediente;
    private javax.swing.JPanel pnlMenuIngredientes;
    // End of variables declaration//GEN-END:variables
}
