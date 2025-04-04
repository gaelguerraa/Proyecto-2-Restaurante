package sistemarestaurantepresentacion.ModuloClientes;

import java.util.List;
import javax.swing.table.DefaultTableModel;
import sistemarestaurantedominio.ClienteFrecuente;
import sistemarestaurantenegocio.IClientesFrecuentesBO;

public class frmBuscarCliente extends javax.swing.JFrame {

    private IClientesFrecuentesBO clientesFrecuentesBO;
    private String telefonoCliente;
    private ClienteFrecuente clienteSeleccionado;
    private ControlNavegacionClientes control;

    /**
     * Constructor que contiene un event listener para obtener el cliente al que se seleccione
     * @param clientesFrecuentesBO Recibe un bo de clientes frecuentes
     * @param control Recibe un control de navegacion
     */
    public frmBuscarCliente(IClientesFrecuentesBO clientesFrecuentesBO, ControlNavegacionClientes control) {
        initComponents();
        this.clientesFrecuentesBO = clientesFrecuentesBO;
        this.control = control;
        setLocationRelativeTo(null);
        this.llenarTablaClientes();
        tblClientes.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = tblClientes.getSelectedRow();
                if (selectedRow != -1) {
                    this.telefonoCliente = (String) tblClientes.getValueAt(selectedRow, 2);
                    this.clienteSeleccionado = clientesFrecuentesBO.consultarClienteTelefono(telefonoCliente);
                }
            }
        });
    }

    /**
     * Metodo que carga la tabla con los clientes segun los parametros que se tengan
     */
    public void llenarTablaClientes() {
        String opcionSeleccionada = (String) boxTipoFiltro.getSelectedItem();
        String textoFiltro = txtTextoFiltrar.getText();
        if (opcionSeleccionada.equals("Nombre")) {
            List<ClienteFrecuente> clientes = clientesFrecuentesBO.consultarClientesNombre(textoFiltro);
            DefaultTableModel modeloTabla = (DefaultTableModel) this.tblClientes.getModel();
            //Limpia la tabla
            modeloTabla.setRowCount(0);
            for (ClienteFrecuente cliente : clientes) {
                Object[] fila = {
                    cliente.getNombre(),
                    cliente.getApellidoPaterno() + " " + cliente.getApellidoMaterno(),
                    cliente.getTelefono(),
                    cliente.getCorreo()
                };
                modeloTabla.addRow(fila);
            }
        } else if (opcionSeleccionada.equals("Telefono")) {
            List<ClienteFrecuente> clientes = clientesFrecuentesBO.consultarClientesTelefono(textoFiltro);
            DefaultTableModel modeloTabla = (DefaultTableModel) this.tblClientes.getModel();
            //Limpia la tabla
            modeloTabla.setRowCount(0);
            for (ClienteFrecuente cliente : clientes) {
                Object[] fila = {
                    cliente.getNombre(),
                    cliente.getApellidoPaterno() + " " + cliente.getApellidoMaterno(),
                    cliente.getTelefono(),
                    cliente.getCorreo()
                };
                modeloTabla.addRow(fila);
            }
        } else if (opcionSeleccionada.equals("Correo")) {
            List<ClienteFrecuente> clientes = clientesFrecuentesBO.consultarClientesCorreo(textoFiltro);
            DefaultTableModel modeloTabla = (DefaultTableModel) this.tblClientes.getModel();
            //Limpia la tabla
            modeloTabla.setRowCount(0);
            for (ClienteFrecuente cliente : clientes) {
                Object[] fila = {
                    cliente.getNombre(),
                    cliente.getApellidoPaterno() + " " + cliente.getApellidoMaterno(),
                    cliente.getTelefono(),
                    cliente.getCorreo()
                };
                modeloTabla.addRow(fila);
            }
        }
    }

    /**
     * Metodo que es llamado desde comanda para acceder directamente al buscador y obtener un cliente
     * @return Regresa un ClienteFrecuente al seleccionarlo
     */
    public ClienteFrecuente mostrarYObtenerClienteSeleccionado() {
        this.setVisible(true);
        // Esperar a que el usuario cierre el frame o seleccione un cliente
        while (clienteSeleccionado == null) {
            try {
                Thread.sleep(100);  // Espera para que el usuario seleccione el cliente
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.dispose();
        return clienteSeleccionado;
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
        jLabel1 = new javax.swing.JLabel();
        btnRegresar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txtTextoFiltrar = new javax.swing.JTextField();
        boxTipoFiltro = new javax.swing.JComboBox<>();
        btnBuscar = new javax.swing.JButton();
        tblTablaClientes = new javax.swing.JScrollPane();
        tblClientes = new javax.swing.JTable();
        btnInformacion = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(241, 209, 165));

        jPanel2.setBackground(new java.awt.Color(241, 209, 165));

        jLabel1.setFont(new java.awt.Font("Verdana", 1, 36)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("BUSCAR CLIENTE");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE)
                .addContainerGap())
        );

        btnRegresar.setBackground(new java.awt.Color(171, 118, 46));
        btnRegresar.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        btnRegresar.setText("ACEPTAR");
        btnRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("FILTRO");

        boxTipoFiltro.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        boxTipoFiltro.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nombre", "Telefono", "Correo" }));
        boxTipoFiltro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boxTipoFiltroActionPerformed(evt);
            }
        });

        btnBuscar.setBackground(new java.awt.Color(171, 118, 46));
        btnBuscar.setFont(new java.awt.Font("Segoe UI Semibold", 1, 24)); // NOI18N
        btnBuscar.setText("BUSCAR");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        tblTablaClientes.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N

        tblClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre", "Apellido", "Telefono", "Correo"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblTablaClientes.setViewportView(tblClientes);
        if (tblClientes.getColumnModel().getColumnCount() > 0) {
            tblClientes.getColumnModel().getColumn(0).setResizable(false);
            tblClientes.getColumnModel().getColumn(1).setResizable(false);
            tblClientes.getColumnModel().getColumn(2).setResizable(false);
            tblClientes.getColumnModel().getColumn(3).setResizable(false);
        }

        btnInformacion.setBackground(new java.awt.Color(171, 118, 46));
        btnInformacion.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        btnInformacion.setText("VER INFORMACION");
        btnInformacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInformacionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(tblTablaClientes)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(boxTipoFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTextoFiltrar, javax.swing.GroupLayout.DEFAULT_SIZE, 415, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnInformacion, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRegresar, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(296, 296, 296))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtTextoFiltrar)
                        .addGap(1, 1, 1))
                    .addComponent(boxTipoFiltro)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tblTablaClientes, javax.swing.GroupLayout.DEFAULT_SIZE, 296, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnInformacion)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnRegresar)
                .addGap(24, 24, 24))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed
        control.regresarMenuClientesConsulta();

    }//GEN-LAST:event_btnRegresarActionPerformed

    private void boxTipoFiltroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boxTipoFiltroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_boxTipoFiltroActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        this.llenarTablaClientes();
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnInformacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInformacionActionPerformed
        control.verInformacionCliente(clientesFrecuentesBO.consultarClienteTelefono(telefonoCliente));
    }//GEN-LAST:event_btnInformacionActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> boxTipoFiltro;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnInformacion;
    private javax.swing.JButton btnRegresar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTable tblClientes;
    private javax.swing.JScrollPane tblTablaClientes;
    private javax.swing.JTextField txtTextoFiltrar;
    // End of variables declaration//GEN-END:variables
}
