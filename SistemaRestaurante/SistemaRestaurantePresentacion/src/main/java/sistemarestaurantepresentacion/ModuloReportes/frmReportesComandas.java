package sistemarestaurantepresentacion.ModuloReportes;

import java.time.LocalDate;
import java.io.FileOutputStream;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import sistemarestaurantedominio.dtos.NuevaComandaDTO;
import sistemarestaurantedominio.dtos.ProductoComandaDTO;
import sistemarestaurantenegocio.IComandasBO;

public class frmReportesComandas extends javax.swing.JFrame {

    private ControlNavegacionReportes control;
    private IComandasBO comandasBO;
    LocalDate fechaInicio;
    LocalDate fechaFin;

    /**
     * Creates new form frmReportesComandas
     */
    public frmReportesComandas(ControlNavegacionReportes control, IComandasBO comandasBO, LocalDate fechaInicio, LocalDate fechaFin) {
        initComponents();
        this.control = control;
        this.comandasBO = comandasBO;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.llenarTablaComandas();
        this.ponerFechaLabel();
        setLocationRelativeTo(null);
        setTitle("Reporte de comandas");
    }

    private void llenarTablaComandas() {
        List<NuevaComandaDTO> comandas = comandasBO.consultarComandasPorRangoFechas(fechaInicio, fechaFin);
        DefaultTableModel modeloTabla = (DefaultTableModel) this.tblTabla.getModel();
        modeloTabla.setRowCount(0);

        for (NuevaComandaDTO comanda : comandas) {
            String nombreCliente = "Publico General";
            if (comanda.getClienteFrecuente() != null) {
                nombreCliente = comanda.getClienteFrecuente().getNombre() + " "
                        + comanda.getClienteFrecuente().getApellidoPaterno();
            } // concatenar el nombre con apellido
            String totalFormateado = String.format("$%,.2f", comanda.getTotal()); //darle formato al total

            // Obtener los productos de la comanda
            List<ProductoComandaDTO> productos = comandasBO.obtenerProductosComanda(comanda.getFolio());
            String productosConcatenados = "";
            for (ProductoComandaDTO producto : productos) {
                productosConcatenados += " " + producto.getCantidad() + " x " + producto.getProducto() + "\n ";
            }

            // Agregar la fila a la tabla
            Object[] fila = {
                comanda.getFolio(),
                comanda.getFechaHora(),
                comanda.getNumeroMesa().getNumeroMesa(),
                comanda.getEstado(),
                nombreCliente,
                productosConcatenados, // Mostrar los productos concatenados
                totalFormateado
            };
            modeloTabla.addRow(fila);
        }
    }

    /**
     *
     * Metodo que genera PDF a partir de los datos obtenidos en la tabla segun
     * el rango de fechas proporcionado Al pdf se le da formato, se genera y se
     * almacena en la carpeta del proyecto bajo presentacion.
     *
     * @param comandas Recibe un listado de comandas (obtenido en base a la
     * fechas proporcionadas)
     */
    public void generarPDFComandas(List<NuevaComandaDTO> comandas) {
        Document documento = new Document();
        try {
            PdfWriter.getInstance(documento, new FileOutputStream("ReporteComandas.pdf"));
            documento.open();

            // Título
            Font fontTitulo = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);
            Paragraph titulo = new Paragraph("Reporte de Comandas", fontTitulo);
            titulo.setAlignment(Element.ALIGN_CENTER);
            documento.add(titulo);
            // Rango de fechas
            Paragraph rangoFechasParagraph = new Paragraph(lblRangoFechas.getText(), FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12));
            rangoFechasParagraph.setAlignment(Element.ALIGN_CENTER);
            documento.add(rangoFechasParagraph);
            documento.add(new Paragraph(" ")); // Espacio en blanco

            // Tabla
            PdfPTable tabla = new PdfPTable(7);
            tabla.setWidthPercentage(110);
            tabla.setSpacingBefore(5f);
            tabla.setSpacingAfter(5f);

            // Encabezados
            String[] encabezados = {"Folio", "Fecha", "Mesa", "Estado", "Cliente", "Productos", "Total"};
            for (String encabezado : encabezados) {
                PdfPCell celda = new PdfPCell(new Phrase(encabezado));
                celda.setBackgroundColor(BaseColor.LIGHT_GRAY);
                tabla.addCell(celda);
            }

            // Llenado de la tabla
            for (NuevaComandaDTO comanda : comandas) {
                String nombreCliente = "Sin cliente";
                if (comanda.getClienteFrecuente() != null) {
                    nombreCliente = comanda.getClienteFrecuente().getNombre() + " "
                            + comanda.getClienteFrecuente().getApellidoPaterno();
                }
                String totalFormateado = String.format("$%,.2f", comanda.getTotal());

                // Obtener los productos de la comanda
                List<ProductoComandaDTO> productos = comandasBO.obtenerProductosComanda(comanda.getFolio());
                String productosConcatenados = "";
                for (ProductoComandaDTO producto : productos) {
                    productosConcatenados += producto.getCantidad() + " x " + producto.getProducto() + "\n";
                }

                // Agregar las celdas a la tabla
                tabla.addCell(String.valueOf(comanda.getFolio()));
                tabla.addCell(comanda.getFechaHora().toString());
                tabla.addCell(String.valueOf(comanda.getNumeroMesa().getNumeroMesa()));
                tabla.addCell(comanda.getEstado().toString());
                tabla.addCell(nombreCliente);
                tabla.addCell(productosConcatenados);  // productos concatenados con cantidad
                tabla.addCell(totalFormateado);
            }

            documento.add(tabla);
            documento.close();

            JOptionPane.showMessageDialog(null, "PDF generado exitosamente!, lo puedes encontrar en la carpeta del proyecto (presentacion).");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al generar el PDF.");
        }
    }

    private void ponerFechaLabel() {
        this.lblRangoFechas.setText(fechaInicio + "   -   " + fechaFin);
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
        lblRangoFechas = new javax.swing.JLabel();
        tblTablaComandas = new javax.swing.JScrollPane();
        tblTabla = new javax.swing.JTable();
        btnPDF = new javax.swing.JButton();
        btnRegresar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(241, 209, 165));

        jPanel2.setBackground(new java.awt.Color(241, 209, 165));

        jLabel1.setFont(new java.awt.Font("Verdana", 1, 36)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("COMANDAS");

        lblRangoFechas.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        lblRangoFechas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblRangoFechas.setText("N/A");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 850, Short.MAX_VALUE)
            .addComponent(lblRangoFechas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblRangoFechas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tblTabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Folio", "Fecha / Hora", "Mesa", "Estado", "Cliente", "Productos", "Total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblTablaComandas.setViewportView(tblTabla);
        if (tblTabla.getColumnModel().getColumnCount() > 0) {
            tblTabla.getColumnModel().getColumn(0).setResizable(false);
            tblTabla.getColumnModel().getColumn(1).setResizable(false);
            tblTabla.getColumnModel().getColumn(2).setResizable(false);
            tblTabla.getColumnModel().getColumn(3).setResizable(false);
            tblTabla.getColumnModel().getColumn(4).setResizable(false);
            tblTabla.getColumnModel().getColumn(5).setResizable(false);
            tblTabla.getColumnModel().getColumn(6).setResizable(false);
        }

        btnPDF.setBackground(new java.awt.Color(171, 118, 46));
        btnPDF.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        btnPDF.setText("GENERAR PDF");
        btnPDF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPDFActionPerformed(evt);
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
            .addComponent(tblTablaComandas)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(324, 324, 324)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnPDF, javax.swing.GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE)
                    .addComponent(btnRegresar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(324, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tblTablaComandas, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(btnPDF, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addComponent(btnRegresar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
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

    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed
        control.regresarSeleccionFecha();
    }//GEN-LAST:event_btnRegresarActionPerformed

    private void btnPDFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPDFActionPerformed
        List<NuevaComandaDTO> comandas = comandasBO.consultarComandasPorRangoFechas(fechaInicio, fechaFin);
        generarPDFComandas(comandas);
    }//GEN-LAST:event_btnPDFActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnPDF;
    private javax.swing.JButton btnRegresar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lblRangoFechas;
    private javax.swing.JTable tblTabla;
    private javax.swing.JScrollPane tblTablaComandas;
    // End of variables declaration//GEN-END:variables
}
