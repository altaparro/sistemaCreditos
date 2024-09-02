package com.mycompany.sistemacreditos;

import java.awt.Component;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class FormCobrarCuota extends javax.swing.JFrame {

public FormCobrarCuota() {
    initComponents();
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setLocationRelativeTo(null);
    
    // Configuración para la columna de CheckBox (5ta columna en el índice 4)
    int checkboxColumnIndex = 4; // Índice de la columna del CheckBox (5ta columna)
    TableColumn checkboxColumn = jTable1.getColumnModel().getColumn(checkboxColumnIndex);
    checkboxColumn.setCellEditor(new DefaultCellEditor(new JCheckBox()));
    checkboxColumn.setCellRenderer(new DefaultTableCellRenderer() {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            JCheckBox checkbox = new JCheckBox();
            if (value != null && value instanceof Boolean) {
                checkbox.setSelected((Boolean) value);
            } else {
                checkbox.setSelected(false); // Valor predeterminado si es null
            }
            checkbox.setHorizontalAlignment(SwingConstants.CENTER);
            return checkbox;
        }
    });

    // Configuración para la columna de Pago Parcial (6ta columna en el índice 5)
    int partialPaymentColumnIndex = 5; // Índice de la columna de Pago Parcial (6ta columna)
    TableColumn partialPaymentColumn = jTable1.getColumnModel().getColumn(partialPaymentColumnIndex);
    partialPaymentColumn.setCellEditor(new DefaultCellEditor(new JTextField()) {
        @Override
        public Object getCellEditorValue() {
            String text = (String) super.getCellEditorValue();
            try {
                return Double.parseDouble(text);
            } catch (NumberFormatException e) {
                return 0.0; // Valor predeterminado en caso de error
            }
        }
    });
    partialPaymentColumn.setCellRenderer(new DefaultTableCellRenderer() {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            JTextField textField = new JTextField();
            if (value != null && value instanceof Double) {
                textField.setText(String.format("%.2f", (Double) value));
            } else {
                textField.setText(""); // Valor predeterminado si es null
            }
            textField.setHorizontalAlignment(SwingConstants.RIGHT);
            return textField;
        }
    });
}


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "id credito", "id cuota", "importe cuota", "importe actualizado", "pago total", "pago parcial"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class, java.lang.Float.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
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
                                .addGap(569, 569, 569))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 770, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(333, 333, 333)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(344, 344, 344)
                        .addComponent(cobrarBtn)))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(dniTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(nombresTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(apellidoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addComponent(cobrarBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
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
    }//GEN-LAST:event_buscarBtnActionPerformed

    private void cobrarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cobrarBtnActionPerformed
      DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
    int rowCount = model.getRowCount();

    CobrarCuota objetoCobrarCuota = new CobrarCuota(); // Crear la instancia aquí

    for (int i = 0; i < rowCount; i++) {
       Boolean isSelected = (Boolean) model.getValueAt(i, 3); // Columna CheckBox

        if (isSelected != null && isSelected) {
            Integer idCuota = (Integer) model.getValueAt(i, 1); // Columna id_cuota
            Double importeCuota = (Double) model.getValueAt(i, 2); // Columna importe_cuota
            Double importeParcial = (Double) model.getValueAt(i, 5); // Columna pago parcial

            if (importeParcial == null || importeParcial <= 0) {
                importeParcial = importeCuota; // Si no hay un pago parcial, se cobra el total
            }

            // Aquí llamas al método para procesar el pago
            objetoCobrarCuota.procesarPago(idCuota, importeParcial);
        }
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField nombresTxt;
    // End of variables declaration//GEN-END:variables
}
