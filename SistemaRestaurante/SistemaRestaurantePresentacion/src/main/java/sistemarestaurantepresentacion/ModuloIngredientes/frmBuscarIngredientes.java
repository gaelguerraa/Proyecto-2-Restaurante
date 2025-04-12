package sistemarestaurantepresentacion.ModuloIngredientes;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import sistemarestaurantedominio.Ingrediente;
import sistemarestaurantedominio.UnidadMedidaIngrediente;
import sistemarestaurantenegocio.IIngredientesBO;
import sistemarestaurantenegocio.excepciones.NegocioException;

public class FrmBuscarIngredientes extends javax.swing.JFrame {

    private ControlNavegacionIngredientes control;
    private IIngredientesBO ingredientesBO;
    private Ingrediente ingredienteSeleccionado;
    private static final Logger LOG = Logger.getLogger(FrmBuscarIngredientes.class.getName());

    /**
     * Creates new form frmBuscarIngredientes
     */
    public FrmBuscarIngredientes(ControlNavegacionIngredientes control, IIngredientesBO ingredientesBO) throws NegocioException {
        initComponents();
        this.control = control;
        this.ingredientesBO = ingredientesBO;
        this.llenarComboBoxMedida();
        this.llenarTablaCompleta();
        setTitle("Buscar ingredientes");
        setLocationRelativeTo(null);
    }

    private void seleccionarIngredienteAumentarStock() {
        this.tblIngredientes.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int filaSeleccionada = this.tblIngredientes.getSelectedRow();
                if (filaSeleccionada != -1) {
                    //Recuperar valores de la fila
                    String nombreIngrediente = this.tblIngredientes.getValueAt(filaSeleccionada, 0).toString();
                    String unidadMedida = this.tblIngredientes.getValueAt(filaSeleccionada, 1).toString();

                    String stockString = JOptionPane.showInputDialog(this, "Ingrese la cantidad de " + unidadMedida + " para " + nombreIngrediente);

                    try {
                        Float stockFloat = Float.valueOf(stockString);
                        ingredienteSeleccionado = new Ingrediente(nombreIngrediente, UnidadMedidaIngrediente.valueOf(unidadMedida), stockFloat);
                        ingredientesBO.aumentarStock(ingredienteSeleccionado.getId(), stockFloat);
                    } catch (NegocioException ex) {
                        LOG.severe("Error: " + ex.getMessage());
                    }
                }
            }

        });
    }

    private void seleccionarIngredienteEliminarStock() {
        this.tblIngredientes.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int filaSeleccionada = this.tblIngredientes.getSelectedRow();
                if (filaSeleccionada != -1) {
                    //Recuperar valores de la fila
                    String nombreIngrediente = this.tblIngredientes.getValueAt(filaSeleccionada, 0).toString();
                    String unidadMedida = this.tblIngredientes.getValueAt(filaSeleccionada, 1).toString();

                    String stockString = JOptionPane.showInputDialog(this, "Ingrese la cantidad de " + unidadMedida + " a eliminar para " + nombreIngrediente);

                    try {
                        Float stockFloat = Float.valueOf(stockString);
                        ingredienteSeleccionado = new Ingrediente(nombreIngrediente, UnidadMedidaIngrediente.valueOf(unidadMedida), stockFloat);
                        ingredientesBO.disminuirStock(ingredienteSeleccionado.getId(), -stockFloat);
                    } catch (NegocioException ex) {
                        LOG.severe("Error: " + ex.getMessage());
                    }
                }
            }

        });
    }

    private void filtrarIngredientes() throws NegocioException {
        String nombreIngrediente = this.txtNombreIngrediente.getText();
        String unidadMedida = this.cmbUnidadMedida.getSelectedItem().toString().toUpperCase();
        try {
            List<Ingrediente> ingredientesConsultados;

            //Consultar todos los ingredientes
            if (nombreIngrediente.isEmpty() && unidadMedida == "CUALQUIERA") {
                ingredientesConsultados = ingredientesBO.consultarIngredientes();
                this.llenarTabla(ingredientesConsultados);
            } else if (!nombreIngrediente.isEmpty() && unidadMedida == "CUALQUIERA") {
                ingredientesConsultados = ingredientesBO.consultarIngredientesPorNombre(nombreIngrediente);
                this.llenarTabla(ingredientesConsultados);
            } else if (nombreIngrediente.isEmpty() && unidadMedida != null) {
                ingredientesConsultados = ingredientesBO.consultarIngredientesPorUnidadMedida(unidadMedida);
                this.llenarTabla(ingredientesConsultados);
            } else if (!nombreIngrediente.isEmpty() && unidadMedida != null) {
                ingredientesConsultados = ingredientesBO.consultarIngredientePorNombreYMedida(nombreIngrediente, unidadMedida);
                this.llenarTabla(ingredientesConsultados);
            }

        } catch (NegocioException e) {
            LOG.severe("Error: " + e.getMessage());
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
        }

    }

    private void llenarTablaCompleta() throws NegocioException {
        DefaultTableModel modeloTabla = (DefaultTableModel) this.tblIngredientes.getModel();
        modeloTabla.setRowCount(0);
        List<Ingrediente> ingredientesConsultados = ingredientesBO.consultarIngredientes();

        for (Ingrediente ingrediente : ingredientesConsultados) {
            Object[] fila = {
                ingrediente.getNombre(),
                ingrediente.getUnidadMedida().name(),
                ingrediente.getStock()

            };
            modeloTabla.addRow(fila);
        }
    }

    private void llenarTabla(List<Ingrediente> ingredientesConsultados) throws NegocioException {
        DefaultTableModel modeloTabla = (DefaultTableModel) this.tblIngredientes.getModel();
        modeloTabla.setRowCount(0);

        for (Ingrediente ingrediente : ingredientesConsultados) {
            Object[] fila = {
                ingrediente.getNombre(),
                ingrediente.getUnidadMedida().name(),
                ingrediente.getStock()

            };
            modeloTabla.addRow(fila);
        }
    }

    private void llenarComboBoxMedida() {

        for (UnidadMedidaIngrediente unidadMedida : UnidadMedidaIngrediente.values()) {
            this.cmbUnidadMedida.addItem(unidadMedida.toString());
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

        pnlBusqueda = new javax.swing.JPanel();
        txtNombreIngrediente = new javax.swing.JTextField();
        cmbUnidadMedida = new javax.swing.JComboBox<>();
        lblNombreIngrediente = new javax.swing.JLabel();
        lblUnidadMedida = new javax.swing.JLabel();
        btnBuscar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblIngredientes = new javax.swing.JTable();
        txtVolver = new javax.swing.JButton();
        btnAumentarStock = new javax.swing.JButton();
        btnEliminarStock = new javax.swing.JButton();
        lblBuscarIngrediente = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(241, 209, 165));

        pnlBusqueda.setBackground(new java.awt.Color(241, 209, 165));
        pnlBusqueda.setBorder(javax.swing.BorderFactory.createTitledBorder("Buscar Ingrediente"));
        pnlBusqueda.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        cmbUnidadMedida.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "CUALQUIERA" }));

        lblNombreIngrediente.setText("Nombre");

        lblUnidadMedida.setText("UnIdad de medida");

        btnBuscar.setBackground(new java.awt.Color(220, 145, 79));
        btnBuscar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnBuscar.setText("BUSCAR");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        tblIngredientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Nombre", "Unidad Medida", "Stock"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblIngredientes);

        txtVolver.setBackground(new java.awt.Color(220, 145, 79));
        txtVolver.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtVolver.setText("VOLVER");
        txtVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtVolverActionPerformed(evt);
            }
        });

        btnAumentarStock.setBackground(new java.awt.Color(220, 145, 79));
        btnAumentarStock.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnAumentarStock.setText("Aumentar Stock");
        btnAumentarStock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAumentarStockActionPerformed(evt);
            }
        });

        btnEliminarStock.setBackground(new java.awt.Color(220, 145, 79));
        btnEliminarStock.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnEliminarStock.setText("Eliminar Stock");
        btnEliminarStock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarStockActionPerformed(evt);
            }
        });

        lblBuscarIngrediente.setBackground(new java.awt.Color(241, 209, 165));
        lblBuscarIngrediente.setFont(new java.awt.Font("Verdana", 1, 36)); // NOI18N
        lblBuscarIngrediente.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblBuscarIngrediente.setText("BUSCAR INGREDIENTE");

        javax.swing.GroupLayout pnlBusquedaLayout = new javax.swing.GroupLayout(pnlBusqueda);
        pnlBusqueda.setLayout(pnlBusquedaLayout);
        pnlBusquedaLayout.setHorizontalGroup(
            pnlBusquedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBusquedaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblBuscarIngrediente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(pnlBusquedaLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(pnlBusquedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlBusquedaLayout.createSequentialGroup()
                        .addComponent(btnEliminarStock)
                        .addGap(18, 18, 18)
                        .addComponent(btnAumentarStock)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtVolver, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(pnlBusquedaLayout.createSequentialGroup()
                        .addGroup(pnlBusquedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 721, Short.MAX_VALUE)
                            .addGroup(pnlBusquedaLayout.createSequentialGroup()
                                .addGroup(pnlBusquedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblNombreIngrediente)
                                    .addComponent(txtNombreIngrediente, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(83, 83, 83)
                                .addGroup(pnlBusquedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblUnidadMedida)
                                    .addComponent(cmbUnidadMedida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(42, 42, 42)
                                .addComponent(btnBuscar)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(27, 27, 27))))
        );
        pnlBusquedaLayout.setVerticalGroup(
            pnlBusquedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBusquedaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblBuscarIngrediente, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(57, 57, 57)
                .addGroup(pnlBusquedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNombreIngrediente)
                    .addComponent(lblUnidadMedida))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlBusquedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNombreIngrediente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbUnidadMedida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 59, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(121, 121, 121)
                .addGroup(pnlBusquedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtVolver)
                    .addComponent(btnAumentarStock)
                    .addComponent(btnEliminarStock))
                .addGap(17, 17, 17))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlBusqueda, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        try {
            // TODO add your handling code here:
            this.filtrarIngredientes();
        } catch (NegocioException ex) {
            Logger.getLogger(FrmBuscarIngredientes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void txtVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtVolverActionPerformed
        // TODO add your handling code here:
        control.IniciarFrmMenuIngredientes();
        dispose();
    }//GEN-LAST:event_txtVolverActionPerformed

    private void btnAumentarStockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAumentarStockActionPerformed
        // TODO add your handling code here:
        this.tblIngredientes.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int filaSeleccionada = this.tblIngredientes.getSelectedRow();
                if (filaSeleccionada != -1) {
                    //Recuperar valores de la fila
                    String nombreIngrediente = this.tblIngredientes.getValueAt(filaSeleccionada, 0).toString();
                    String unidadMedida = this.tblIngredientes.getValueAt(filaSeleccionada, 1).toString();
                    String stockString = JOptionPane.showInputDialog(this, "Ingrese la cantidad de " + unidadMedida + " para " + nombreIngrediente);

                    try {
                        Float stockFloat = Float.valueOf(stockString);
                        ingredienteSeleccionado = new Ingrediente(nombreIngrediente, UnidadMedidaIngrediente.valueOf(unidadMedida), stockFloat);
                        ingredientesBO.aumentarStock(ingredienteSeleccionado.getId(), stockFloat);
                    } catch (NegocioException ex) {
                        LOG.severe("Error: " + ex.getMessage());
                    }
                    JOptionPane.showMessageDialog(this, "Seleccione un ingrediente");
                    this.seleccionarIngredienteAumentarStock();

                }
            }

        });

    }//GEN-LAST:event_btnAumentarStockActionPerformed

    private void btnEliminarStockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarStockActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(this, "Seleccione un ingrediente");
        this.seleccionarIngredienteEliminarStock();

    }//GEN-LAST:event_btnEliminarStockActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAumentarStock;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnEliminarStock;
    private javax.swing.JComboBox<String> cmbUnidadMedida;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblBuscarIngrediente;
    private javax.swing.JLabel lblNombreIngrediente;
    private javax.swing.JLabel lblUnidadMedida;
    private javax.swing.JPanel pnlBusqueda;
    private javax.swing.JTable tblIngredientes;
    private javax.swing.JTextField txtNombreIngrediente;
    private javax.swing.JButton txtVolver;
    // End of variables declaration//GEN-END:variables
}
