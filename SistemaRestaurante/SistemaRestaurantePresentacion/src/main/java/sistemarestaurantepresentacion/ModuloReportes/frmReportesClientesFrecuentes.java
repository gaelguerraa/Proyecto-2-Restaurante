/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package sistemarestaurantepresentacion.ModuloReportes;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import java.io.FileOutputStream;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import sistemarestaurantedominio.ClienteFrecuente;
import sistemarestaurantenegocio.IClientesFrecuentesBO;
import sistemarestaurantenegocio.excepciones.NegocioException;

/**
 *
 * @author jorge
 */
public class frmReportesClientesFrecuentes extends javax.swing.JFrame {

    private ControlNavegacionReportes control;
    private IClientesFrecuentesBO clientesFrecuentesBO;

    /**
     * Creates new form frmReportesClientesFrecuentes
     */
    public frmReportesClientesFrecuentes(ControlNavegacionReportes control, IClientesFrecuentesBO clientesBO) {
        initComponents();
        this.control = control;
        this.clientesFrecuentesBO = clientesBO;
        setLocationRelativeTo(null);
        this.llenarTablaClientes();
        setTitle("Reporte de clientes");
    }

    /**
     * Metodo que carga la tabla con los clientes segun los parametros que se
     * tengan
     */
    public void llenarTablaClientes() {
        String opcionSeleccionada = (String) boxTipoFiltro.getSelectedItem();
        String textoFiltro = txtTextoFiltrar.getText();
        if (opcionSeleccionada.equals("Nombre")) {
            List<ClienteFrecuente> clientes = clientesFrecuentesBO.consultarClientesNombre(textoFiltro);
            DefaultTableModel modeloTabla = (DefaultTableModel) this.tblTabla.getModel();
            //Limpia la tabla
            modeloTabla.setRowCount(0);
            for (ClienteFrecuente cliente : clientes) {
                float monto = clientesFrecuentesBO.obtenerMontoGastado(cliente);
                int numVisitas = clientesFrecuentesBO.obtenerNumVisitas(cliente);
                String ultimaVisitaStr = "N/A";
                String montoFormateado = String.format("$%,.2f", monto);
                LocalDateTime ultimaVisita;
                try {
                    ultimaVisita = clientesFrecuentesBO.obtenerUltimaVisita(cliente);
                    if (ultimaVisita != null) {
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
                        ultimaVisitaStr = ultimaVisita.format(formatter);
                    }
                } catch (NegocioException ex) {

                }

                Object[] fila = {
                    cliente.getNombre(),
                    cliente.getApellidoPaterno() + " " + cliente.getApellidoMaterno(),
                    numVisitas,
                    montoFormateado,
                    cliente.getPuntosFidelidad(),
                    ultimaVisitaStr
                };
                modeloTabla.addRow(fila);
            }
        } else if (opcionSeleccionada.equals("Min. Visitas")) {
            String numeroVisitasStr = txtTextoFiltrar.getText();
            List<ClienteFrecuente> clientes;
            try {
                int numeroVisitas = Integer.parseInt(numeroVisitasStr);
                clientes = clientesFrecuentesBO.consultarClientesMinimoVisita(numeroVisitas);

                DefaultTableModel modeloTabla = (DefaultTableModel) this.tblTabla.getModel();
                //Limpia la tabla
                modeloTabla.setRowCount(0);
                for (ClienteFrecuente cliente : clientes) {
                    float monto = clientesFrecuentesBO.obtenerMontoGastado(cliente);
                    int numVisitas = clientesFrecuentesBO.obtenerNumVisitas(cliente);
                    String ultimaVisitaStr = "N/A";
                    String montoFormateado = String.format("$%,.2f", monto);
                    LocalDateTime ultimaVisita;
                    try {
                        ultimaVisita = clientesFrecuentesBO.obtenerUltimaVisita(cliente);
                        if (ultimaVisita != null) {
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
                            ultimaVisitaStr = ultimaVisita.format(formatter);
                        }
                    } catch (NegocioException ex) {

                    }

                    Object[] fila = {
                        cliente.getNombre(),
                        cliente.getApellidoPaterno() + " " + cliente.getApellidoMaterno(),
                        numVisitas,
                        montoFormateado,
                        cliente.getPuntosFidelidad(),
                        ultimaVisitaStr
                    };
                    modeloTabla.addRow(fila);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Ingrese un numero para ese filtro!", "ERROR", JOptionPane.ERROR_MESSAGE);
            }

        }
    }

    /**
     * Metodo que genera PDF a partir de los datos obtenidos en la tabla segun los filtros proporcionados
     * Al pdf se le da formato, se genera y se almacena en la carpeta del proyecto bajo presentacion.
     */
    public void generarPDFClientes() {
        try {
            Document documento = new Document();
            PdfWriter.getInstance(documento, new FileOutputStream("ReporteClientes.pdf"));
            documento.open();

            // Título
            Font fontTitulo = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);
            Paragraph titulo = new Paragraph("Reporte de Clientes Frecuentes", fontTitulo);
            titulo.setAlignment(Element.ALIGN_CENTER);
            documento.add(titulo);

            // Filtro
            String opcionSeleccionada = (String) boxTipoFiltro.getSelectedItem();
            String textoFiltro = txtTextoFiltrar.getText();
            if (textoFiltro == null || textoFiltro.isEmpty()) {
                textoFiltro = "Sin filtro";
            }
            if (opcionSeleccionada.equals("Nombre")) {
                Paragraph textoFiltrado = new Paragraph("Clientes filtrados por nombre: " + textoFiltro, FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12));
                textoFiltrado.setAlignment(Element.ALIGN_CENTER);
                documento.add(textoFiltrado);
                documento.add(Chunk.NEWLINE);
            } else if (opcionSeleccionada.equals("Min. Visitas")) {
                Paragraph textoFiltrado = new Paragraph("Clientes filtrados minimo de visita: " + textoFiltro, FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12));
                textoFiltrado.setAlignment(Element.ALIGN_CENTER);
                documento.add(textoFiltrado);
                documento.add(Chunk.NEWLINE);
            }

            // Tabla
            PdfPTable tabla = new PdfPTable(6);
            tabla.setWidthPercentage(100);

            // Encabezados
            String[] encabezados = {"Nombre", "Apellido", "Visitas", "Monto Gastado", "Puntos Fidelidad", "Ultima Visita"};
            for (String encabezado : encabezados) {
                PdfPCell celda = new PdfPCell(new Phrase(encabezado));
                celda.setBackgroundColor(BaseColor.LIGHT_GRAY);
                tabla.addCell(celda);
            }

            // Obtener los datos de la tabla de clientes
            DefaultTableModel modeloTabla = (DefaultTableModel) this.tblTabla.getModel();

            // Recorrer las filas de la tabla y añadirlas al documento
            for (int i = 0; i < modeloTabla.getRowCount(); i++) {
                String nombre = (String) modeloTabla.getValueAt(i, 0);
                String apellido = (String) modeloTabla.getValueAt(i, 1);
                int numVisitas = (int) modeloTabla.getValueAt(i, 2);
                String montoGastado = (String) modeloTabla.getValueAt(i, 3);
                int puntosFidelidad = (int) modeloTabla.getValueAt(i, 4);
                String ultimaVisita = (String) modeloTabla.getValueAt(i, 5);

                tabla.addCell(nombre);
                tabla.addCell(apellido);
                tabla.addCell(String.valueOf(numVisitas));
                tabla.addCell(montoGastado);
                tabla.addCell(String.valueOf(puntosFidelidad));
                tabla.addCell(ultimaVisita);
            }

            // Agregar la tabla al documento
            documento.add(tabla);
            documento.close();

            JOptionPane.showMessageDialog(this, "PDF generado correctamente!, lo puedes encontrar en la carpeta del proyecto (presentacion).", "Éxito", JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al generar el PDF: " + e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
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

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        boxTipoFiltro = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        txtTextoFiltrar = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        tblClientes = new javax.swing.JScrollPane();
        tblTabla = new javax.swing.JTable();
        btnGenerarPDF = new javax.swing.JButton();
        btnRegresar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(241, 209, 165));

        jPanel2.setBackground(new java.awt.Color(241, 209, 165));

        jLabel1.setFont(new java.awt.Font("Verdana", 1, 36)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("CLIENTES FRECUENTES");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
        );

        boxTipoFiltro.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        boxTipoFiltro.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nombre", "Min. Visitas" }));

        jLabel2.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("FILTRO");

        txtTextoFiltrar.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N

        btnBuscar.setBackground(new java.awt.Color(171, 118, 46));
        btnBuscar.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        btnBuscar.setText("BUSCAR");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        tblClientes.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N

        tblTabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Nombre", "Apellido", "Visitas", "Gastado", "Puntos", "Ultima Visita"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblClientes.setViewportView(tblTabla);
        if (tblTabla.getColumnModel().getColumnCount() > 0) {
            tblTabla.getColumnModel().getColumn(0).setResizable(false);
            tblTabla.getColumnModel().getColumn(1).setResizable(false);
            tblTabla.getColumnModel().getColumn(2).setResizable(false);
            tblTabla.getColumnModel().getColumn(3).setResizable(false);
            tblTabla.getColumnModel().getColumn(4).setResizable(false);
            tblTabla.getColumnModel().getColumn(5).setResizable(false);
        }

        btnGenerarPDF.setBackground(new java.awt.Color(171, 118, 46));
        btnGenerarPDF.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        btnGenerarPDF.setText("GENERAR PDF");
        btnGenerarPDF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarPDFActionPerformed(evt);
            }
        });

        btnRegresar.setBackground(new java.awt.Color(171, 118, 46));
        btnRegresar.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        btnRegresar.setText("REGRESAR");
        btnRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(tblClientes))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(boxTipoFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTextoFiltrar, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnGenerarPDF, javax.swing.GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE)
                    .addComponent(btnRegresar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(334, 334, 334))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTextoFiltrar, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(boxTipoFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tblClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnGenerarPDF, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnRegresar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12))
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

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        this.llenarTablaClientes();
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed
        control.regresarReporteClientes();
    }//GEN-LAST:event_btnRegresarActionPerformed

    private void btnGenerarPDFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarPDFActionPerformed
        generarPDFClientes();
    }//GEN-LAST:event_btnGenerarPDFActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> boxTipoFiltro;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnGenerarPDF;
    private javax.swing.JButton btnRegresar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane tblClientes;
    private javax.swing.JTable tblTabla;
    private javax.swing.JTextField txtTextoFiltrar;
    // End of variables declaration//GEN-END:variables
}
