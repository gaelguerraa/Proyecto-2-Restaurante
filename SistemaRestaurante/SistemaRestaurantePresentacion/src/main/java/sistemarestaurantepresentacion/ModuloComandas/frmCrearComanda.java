/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package sistemarestaurantepresentacion.ModuloComandas;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.logging.Logger;
import javax.swing.DefaultCellEditor;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import sistemarestaurantedominio.ClienteFrecuente;
import sistemarestaurantedominio.Comanda;
import sistemarestaurantedominio.EstadoComanda;
import sistemarestaurantedominio.Mesa;
import sistemarestaurantedominio.Producto;
import sistemarestaurantedominio.dtos.NuevaComandaDTO;
import sistemarestaurantedominio.dtos.NuevoDetalleComandaDTO;
import sistemarestaurantenegocio.IComandasBO;
import sistemarestaurantenegocio.IDetallesComandasBO;
import sistemarestaurantenegocio.IProductosBO;
import sistemarestaurantenegocio.excepciones.NegocioException;




/**
 *
 * @author gael_
 */
public class frmCrearComanda extends javax.swing.JFrame {

    Comanda comandaActual;
    ControlNavegacionComandas controlador;
    IComandasBO comandasBO;
    IProductosBO productosBO;
    ClienteFrecuente cliente;
    Producto producto;
    private static final Logger LOG = Logger.getLogger(frmCrearComanda.class.getName());
    
    
    NuevoDetalleComandaDTO detalleComanda;
    IDetallesComandasBO detallesComandasBO;
    

    /**
     * Creates new form frmCrearComanda
     */
    public frmCrearComanda(ControlNavegacionComandas controlador, IComandasBO comandasBO, IProductosBO productosBO, IDetallesComandasBO detallesComandasBO ) {
        initComponents();
        this.controlador=controlador;
        this.comandasBO = comandasBO;
        this.productosBO = productosBO;
        this.detallesComandasBO = detallesComandasBO;
        setLocationRelativeTo(null);
        this.LlenarComboboxMesa();
        configurarListenersTabla();
        txtTotal.setEditable(false);

    }
    
    public void LlenarComboboxMesa(){
        List<Mesa> mesas = comandasBO.obtenerMesas();
        
        for(Mesa m : mesas){
            ComboboxMesa.addItem(String.valueOf(m.getNumeroMesa()));
        }
    }
       
    public String generarFolio(){
        LocalDate hoy = LocalDate.now();
        String fechaFormateada = hoy.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        int consecutivo = comandasBO.obtenerConsecutivoDelDia();
        String consecutivoFormateado = String.format("%03d", consecutivo);
        return "OB-" + fechaFormateada + "-" + consecutivoFormateado;
    }
    
    //poner todo dentro de un try y hacer el BO de comanda para manejar errores
    public void crearComanda() {
        String folio = generarFolio(); 
        EstadoComanda estado = EstadoComanda.ABIERTA;
        LocalDateTime fechaHora = LocalDateTime.now();
        Float importeTotal = 0.0f;
        
        int numeroMesaSeleccionada = Integer.parseInt((String) ComboboxMesa.getSelectedItem());
        Mesa mesa = comandasBO.buscarMesaPorNumero(numeroMesaSeleccionada);
        
        NuevaComandaDTO nuevaComanda;
        if ("Publico General".equals(txtCliente.getText())) {
            nuevaComanda = new NuevaComandaDTO(folio, estado, fechaHora, importeTotal, mesa);
        } else {
            nuevaComanda = new NuevaComandaDTO(folio, estado, fechaHora, importeTotal, mesa, cliente);
        }
        
        comandasBO.registrarComanda(nuevaComanda);
        this.comandaActual = comandasBO.buscarPorFolio(folio);
        JOptionPane.showMessageDialog(null, "¡Comanda creada con folio: " + folio + "!");
        
        //para detallesComanda
        DefaultTableModel model = (DefaultTableModel) TablaComanda.getModel();
        for (int i = 0; i < model.getRowCount(); i++) {
            try {
                String nombreProducto = (String) model.getValueAt(i, 0); // Asumir que la primera columna es String
                Object cantidadObj = model.getValueAt(i, 1); // Obtenemos el valor como Object
                int cantidad = 0;
                if (cantidadObj instanceof Integer) {
                    cantidad = (Integer) cantidadObj;
                } else if (cantidadObj instanceof String) {
                    cantidad = Integer.parseInt((String) cantidadObj);
                }

                String nota = (String) model.getValueAt(i, 2); // Asumir que la tercera columna es String
                Object precioObj = model.getValueAt(i, 3); // Obtenemos el valor como Object
                float precio = 0.0f;
                if (precioObj instanceof Float) {
                    precio = (Float) precioObj;
                } else if (precioObj instanceof String) {
                    precio = Float.parseFloat((String) precioObj);
                }

                Object totalObj = model.getValueAt(i, 4); // Obtenemos el valor como Object
                float total = 0.0f;
                if (totalObj instanceof Float) {
                    total = (Float) totalObj;
                } else if (totalObj instanceof String) {
                    total = Float.parseFloat((String) totalObj);
                }
                
                Producto producto = this.productosBO.consultarProductoPorNombre(nombreProducto); // Asumiendo que exista este método

                NuevoDetalleComandaDTO detalle = new NuevoDetalleComandaDTO(producto, comandaActual, precio, nota, cantidad, total);
                detallesComandasBO.guardarDetalleComanda(detalle);

            } catch (ClassCastException e) {
                JOptionPane.showMessageDialog(null, "Error de tipo en la fila " + (i + 1) + ": " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Error de formato de número en la fila " + (i + 1) + ": " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error en la fila " + (i + 1) + ": " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        }
}

    
    
    private void configurarListenersTabla() {
        TablaComanda.getModel().addTableModelListener(e -> {
            // Solo responder a cambios en la columna de cantidad (columna 1)
            if (e.getColumn() == 1) {
                actualizarImporteFila(e.getFirstRow());
                calcularTotalComanda();
            }
        });
    }
    
    private void actualizarImporteFila(int fila) {
        DefaultTableModel model = (DefaultTableModel) TablaComanda.getModel();
        try {
            // Obtener el valor de la celda de forma segura
            Object valor = model.getValueAt(fila, 1);
            int cantidad;

            // Convertir el valor a entero sin importar su tipo original
            if (valor instanceof Number) {
                cantidad = ((Number) valor).intValue();
            } else if (valor instanceof String) {
                cantidad = Integer.parseInt((String) valor);
            } else {
                throw new NumberFormatException();
            }

            // Validar que la cantidad sea positiva
            if (cantidad <= 0) {
                throw new NumberFormatException();
            }

            float precioUnitario = (float) model.getValueAt(fila, 3);
            float importe = cantidad * precioUnitario;
            model.setValueAt(importe, fila, 4);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, 
                "Ingrese un número entero positivo válido", 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
            model.setValueAt(1, fila, 1); // Restablecer a cantidad 1
            model.setValueAt(model.getValueAt(fila, 3), fila, 4); // Restablecer importe
        }
    }
    
    private void calcularTotalComanda() {
        DefaultTableModel model = (DefaultTableModel) TablaComanda.getModel();
        float total = 0;

        for (int i = 0; i < model.getRowCount(); i++) {
            total += (float) model.getValueAt(i, 4); // Sumar columna de importe
        }

        // Actualizar el campo de texto del total
        txtTotal.setText(String.format("$%,.2f", total));
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
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        ComboboxMesa = new javax.swing.JComboBox<>();
        btnBuscarCliente = new javax.swing.JButton();
        BotonBuscarProducto = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        TablaComanda = new javax.swing.JTable();
        BotonRegistrarComanda = new javax.swing.JButton();
        txtCliente = new javax.swing.JTextField();
        btnRegresar = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(850, 550));

        jPanel1.setBackground(new java.awt.Color(241, 209, 165));

        jPanel3.setBackground(new java.awt.Color(241, 209, 165));

        jLabel1.setFont(new java.awt.Font("Segoe UI Semibold", 1, 36)); // NOI18N
        jLabel1.setText("CREAR COMANDA");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(298, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(270, 270, 270))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(17, 17, 17))
        );

        jLabel2.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        jLabel2.setText("Cliente:");

        jLabel3.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        jLabel3.setText("Mesa:");

        ComboboxMesa.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        ComboboxMesa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboboxMesaActionPerformed(evt);
            }
        });

        btnBuscarCliente.setBackground(new java.awt.Color(171, 118, 46));
        btnBuscarCliente.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        btnBuscarCliente.setText("BUSCAR CLIENTE");
        btnBuscarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarClienteActionPerformed(evt);
            }
        });

        BotonBuscarProducto.setBackground(new java.awt.Color(171, 118, 46));
        BotonBuscarProducto.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        BotonBuscarProducto.setText("BUSCAR PRODUCTO");
        BotonBuscarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonBuscarProductoActionPerformed(evt);
            }
        });

        TablaComanda.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Producto", "Cantidad", "Nota", "Precio Unitario", "Importe Total"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.Float.class, java.lang.Float.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, true, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(TablaComanda);

        BotonRegistrarComanda.setBackground(new java.awt.Color(171, 118, 46));
        BotonRegistrarComanda.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        BotonRegistrarComanda.setText("REGISTRAR COMANDA");
        BotonRegistrarComanda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonRegistrarComandaActionPerformed(evt);
            }
        });

        txtCliente.setEditable(false);
        txtCliente.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        txtCliente.setText("Publico General");

        btnRegresar.setBackground(new java.awt.Color(171, 118, 46));
        btnRegresar.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        btnRegresar.setText("REGRESAR");
        btnRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        jLabel4.setText("Total:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 815, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(ComboboxMesa, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(53, 53, 53)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnBuscarCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(BotonBuscarProducto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(28, 28, 28))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(BotonRegistrarComanda, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
                    .addComponent(btnRegresar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(320, 320, 320))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnBuscarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(jLabel3)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE, false)
                        .addComponent(BotonBuscarProducto, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                        .addComponent(ComboboxMesa))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(txtTotal, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtCliente, javax.swing.GroupLayout.Alignment.LEADING)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(BotonRegistrarComanda, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnRegresar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BotonRegistrarComandaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonRegistrarComandaActionPerformed
            crearComanda();


    }//GEN-LAST:event_BotonRegistrarComandaActionPerformed

    private void ComboboxMesaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboboxMesaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ComboboxMesaActionPerformed

    private void btnBuscarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarClienteActionPerformed

        this.cliente = controlador.obtenerCliente();
        if(cliente != null){
            txtCliente.setText(cliente.getNombre() + " " + cliente.getApellidoPaterno());
        }
        else{
            txtCliente.setText("Publico General");
        }
        
        
    }//GEN-LAST:event_btnBuscarClienteActionPerformed

    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed
        controlador.regresarMenuCrearComanda();
    }//GEN-LAST:event_btnRegresarActionPerformed

    private void BotonBuscarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonBuscarProductoActionPerformed
        this.producto = controlador.obtenerProducto();
        
        if (producto != null) {
            DefaultTableModel model = (DefaultTableModel) TablaComanda.getModel();
            Object[] row = new Object[]{
                producto.getNombre(), // Nombre del producto
                1,                    // Cantidad predeterminada
                "",                   // Nota vacía
                producto.getPrecio(), // Precio unitario
                producto.getPrecio() // Importe total (inicialmente igual al precio unitario)
            };
            model.addRow(row);

        // Configurar editor para la columna de cantidad
        TablaComanda.getColumnModel().getColumn(1).setCellEditor(new DefaultCellEditor(new JTextField()) {
            @Override
            public boolean stopCellEditing() {
                try {
                    // Validar que sea un número entero
                    Integer.parseInt(getCellEditorValue().toString());
                    return super.stopCellEditing();
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Ingrese un número válido", "Error", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
            }
        });
    }
        
    }//GEN-LAST:event_BotonBuscarProductoActionPerformed



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BotonBuscarProducto;
    private javax.swing.JButton BotonRegistrarComanda;
    private javax.swing.JComboBox<String> ComboboxMesa;
    private javax.swing.JTable TablaComanda;
    private javax.swing.JButton btnBuscarCliente;
    private javax.swing.JButton btnRegresar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txtCliente;
    private javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables
}
