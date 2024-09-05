package com.mycompany.sistemacreditos;

import java.awt.Component;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class FormCobrarCuota extends javax.swing.JFrame {

    public FormCobrarCuota() {
        initComponents();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Configuración de la tabla
        configurarTabla();

        // Agregar el TableModelListener
        jTable1.getModel().addTableModelListener(e -> {
            if (e.getColumn() == 4 || e.getColumn() == 5) { // Columnas de pago total y parcial
                updateTotalCobrar();
            }
        });
    }

    private void configurarTabla() {
        // Configuración para la columna de CheckBox (5ta columna en el índice 4)
        int checkboxColumnIndex = 4;
        TableColumn checkboxColumn = jTable1.getColumnModel().getColumn(checkboxColumnIndex);
        checkboxColumn.setCellEditor(new DefaultCellEditor(new JCheckBox()));
        checkboxColumn.setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                JCheckBox checkbox = new JCheckBox();
                checkbox.setSelected(value != null && value instanceof Boolean && (Boolean) value);
                checkbox.setHorizontalAlignment(SwingConstants.CENTER);
                return checkbox;
            }
        });

        // Configuración para la columna de Pago Parcial (6ta columna en el índice 5)
        int partialPaymentColumnIndex = 5;
        TableColumn partialPaymentColumn = jTable1.getColumnModel().getColumn(partialPaymentColumnIndex);
        partialPaymentColumn.setCellEditor(new DefaultCellEditor(new JTextField()) {
            @Override
            public boolean stopCellEditing() {
                try {
                    Double.parseDouble((String) getCellEditorValue());
                    return super.stopCellEditing();
                } catch (NumberFormatException e) {
                    return false;
                }
            }
        });
        partialPaymentColumn.setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                JTextField textField = new JTextField();
                if (value instanceof Number) {
                    textField.setText(String.format("%.2f", ((Number) value).doubleValue()));
                } else if (value instanceof String) {
                    textField.setText((String) value);
                }
                textField.setHorizontalAlignment(SwingConstants.RIGHT);
                return textField;
            }
        });
    }

    private void updateTotalCobrar() {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        double total = 0.0;
        for (int i = 0; i < model.getRowCount(); i++) {
            Object pagoTotalObj = model.getValueAt(i, 4);
            boolean pagoTotal = pagoTotalObj instanceof Boolean && (Boolean) pagoTotalObj;

            if (pagoTotal) {
                Object importeActualizadoObj = model.getValueAt(i, 3);
                if (importeActualizadoObj instanceof Number) {
                    total += ((Number) importeActualizadoObj).doubleValue();
                } else if (importeActualizadoObj instanceof String) {
                    try {
                        total += Double.parseDouble((String) importeActualizadoObj);
                    } catch (NumberFormatException ex) {
                        System.err.println("Error al parsear el importe actualizado: " + ex.getMessage());
                    }
                }
            } else {
                Object pagoParcialObj = model.getValueAt(i, 5);
                if (pagoParcialObj instanceof Number) {
                    total += ((Number) pagoParcialObj).doubleValue();
                } else if (pagoParcialObj instanceof String) {
                    try {
                        total += Double.parseDouble((String) pagoParcialObj);
                    } catch (NumberFormatException ex) {
                        System.err.println("Error al parsear el pago parcial: " + ex.getMessage());
                    }
                }
            }
        }
        totalCobrarTxt.setText(String.format("%.2f", total));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        buscarTxt = new javax.swing.JTextField();
        buscarBtn = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        dniTxt = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        nombresTxt = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        apellidoTxt = new javax.swing.JTextField();
        cobrarBtn = new javax.swing.JToggleButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        totalCobrarTxt = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("COBRAR CUOTA");

        buscarTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarTxtActionPerformed(evt);
            }
        });

        buscarBtn.setText("BUSCAR");
        buscarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarBtnActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("DATOS CLIENTE:");

        jLabel3.setText("DNI:");

        dniTxt.setEditable(false);

        jLabel4.setText("NOMBRES:");

        nombresTxt.setEditable(false);

        jLabel5.setText("APELLIDO:");

        apellidoTxt.setEditable(false);

        cobrarBtn.setText("COBRAR");
        cobrarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cobrarBtnActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID CREDITO", "ID CUOTA", "IMPORTE CUOTA", "IMPORTE ACTUALIZADO", "PAGO TOTAL", "PAGO PARCIAL"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Double.class, java.lang.Double.class, java.lang.Boolean.class, java.lang.Double.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jTable1);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel6.setText("TOTAL A COBRAR:");

        totalCobrarTxt.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 794, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(42, 42, 42)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(9, 9, 9)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel2)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(buscarTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(buscarBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGroup(layout.createSequentialGroup()
                                                    .addComponent(jLabel4)
                                                    .addGap(18, 18, 18)
                                                    .addComponent(nombresTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGap(18, 18, 18)
                                                    .addComponent(jLabel5)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                    .addComponent(apellidoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel3)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(dniTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(569, 569, 569))))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(333, 333, 333)
                            .addComponent(jLabel1))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(113, 113, 113)
                            .addComponent(jLabel6)
                            .addGap(18, 18, 18)
                            .addComponent(totalCobrarTxt)
                            .addGap(280, 280, 280)
                            .addComponent(cobrarBtn))))
                .addGap(32, 32, 32))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buscarTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buscarBtn))
                .addGap(26, 26, 26)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(dniTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(nombresTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(apellidoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cobrarBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(totalCobrarTxt))
                .addGap(69, 69, 69))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buscarTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buscarTxtActionPerformed

    private void buscarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarBtnActionPerformed
        Creditos objetoCreditos = new Creditos();
        CobrarCuota objetoCobrarCuota = new CobrarCuota();
        objetoCreditos.MostrarCliente(buscarTxt, dniTxt, nombresTxt, apellidoTxt);
        objetoCobrarCuota.MostrarCuotasCliente(buscarTxt, jTable1);
        updateTotalCobrar();
    }//GEN-LAST:event_buscarBtnActionPerformed

    private void cobrarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cobrarBtnActionPerformed
 DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        CobrarCuota objetoCobrarCuota = new CobrarCuota();
        boolean algunPagoProcesado = false;

        for (int i = 0; i < model.getRowCount(); i++) {
            Object pagoTotalObj = model.getValueAt(i, 4);
            boolean pagoTotal = pagoTotalObj instanceof Boolean && (Boolean) pagoTotalObj;

            Double montoPago = 0.0;
            if (pagoTotal) {
                Object importeActualizadoObj = model.getValueAt(i, 3);
                if (importeActualizadoObj instanceof Number) {
                    montoPago = ((Number) importeActualizadoObj).doubleValue();
                } else if (importeActualizadoObj instanceof String) {
                    try {
                        montoPago = Double.parseDouble((String) importeActualizadoObj);
                    } catch (NumberFormatException ex) {
                        System.err.println("Error al parsear el importe actualizado: " + ex.getMessage());
                        continue;
                    }
                }
            } else {
                Object pagoParcialObj = model.getValueAt(i, 5);
                if (pagoParcialObj instanceof Number) {
                    montoPago = ((Number) pagoParcialObj).doubleValue();
                } else if (pagoParcialObj instanceof String) {
                    try {
                        montoPago = Double.parseDouble((String) pagoParcialObj);
                    } catch (NumberFormatException ex) {
                        System.err.println("Error al parsear el pago parcial: " + ex.getMessage());
                        continue;
                    }
                }
            }

            Object idCuotaObj = model.getValueAt(i, 1);
            Integer idCuota = null;
            if (idCuotaObj instanceof Number) {
                idCuota = ((Number) idCuotaObj).intValue();
            } else if (idCuotaObj instanceof String) {
                try {
                    idCuota = Integer.parseInt((String) idCuotaObj);
                } catch (NumberFormatException ex) {
                    System.err.println("Error al parsear el ID de la cuota: " + ex.getMessage());
                    continue;
                }
            }

            if (montoPago > 0 && idCuota != null) {
                objetoCobrarCuota.procesarPago(idCuota, montoPago);
                algunPagoProcesado = true;
            }
        }

        if (algunPagoProcesado) {
            buscarBtnActionPerformed(null);
            updateTotalCobrar();
            JOptionPane.showMessageDialog(this, "Pagos procesados con éxito.");
        } else {
            JOptionPane.showMessageDialog(this, "No se seleccionó ningún pago para procesar.");
        }
    }//GEN-LAST:event_cobrarBtnActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormCobrarCuota().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField apellidoTxt;
    private javax.swing.JButton buscarBtn;
    private javax.swing.JTextField buscarTxt;
    private javax.swing.JToggleButton cobrarBtn;
    private javax.swing.JTextField dniTxt;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField nombresTxt;
    private javax.swing.JLabel totalCobrarTxt;
    // End of variables declaration//GEN-END:variables
}
