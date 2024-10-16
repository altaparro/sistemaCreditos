package com.mycompany.sistemacreditos;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class FormHistorico extends javax.swing.JFrame {

    int id_cliente;

    public FormHistorico() {
        initComponentsCustom();
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        ImageIcon icon = new ImageIcon("icono.png"); // Cambia la ruta según tu estructura de proyecto
        setIconImage(icon.getImage());
        DetalleCliente detalle = new DetalleCliente();
        id_cliente = detalle.id_cliente;
        cargarTablaCuotas();
    }

    private void initComponentsCustom() {
        setTitle("Histórico de Cuotas");
        getContentPane().setBackground(new Color(240, 240, 240));
        setLayout(new GridBagLayout());

        // Panel para el título
        JPanel titlePanel = new JPanel(new GridBagLayout());
        titlePanel.setOpaque(false);

        // Título
        JLabel titleLabel = new JLabel("HISTÓRICO DE CUOTAS");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setForeground(new Color(0, 102, 204));
        titlePanel.add(titleLabel, createGridBagConstraints(0, 0, 1, 1, GridBagConstraints.CENTER));

        add(titlePanel, createGridBagConstraints(0, 0, 1, 1, GridBagConstraints.CENTER));

        // Tabla de historial
        tablaHistorico = new JTable(new DefaultTableModel(
                new Object[][]{},
                new String[]{"ID Crédito", "ID Cuota", "Importe Cuota", "Vencimiento", "Fecha de Pago"}
        ));

        // Cambiar la fuente de la tabla
        tablaHistorico.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        tablaHistorico.setRowHeight(30);
        tablaHistorico.setFillsViewportHeight(true);

        // Ajustar el tamaño de todas las columnas
        for (int i = 0; i < tablaHistorico.getColumnCount(); i++) {
            TableColumn column = tablaHistorico.getColumnModel().getColumn(i);
            column.setPreferredWidth(150); // Ajusta el ancho de todas las columnas
        }

        // Personaliza el renderer para los encabezados
        DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
        headerRenderer.setBackground(new Color(0, 102, 204)); // Color del encabezado
        headerRenderer.setForeground(Color.WHITE); // Color del texto
        headerRenderer.setFont(new Font("Segoe UI", Font.BOLD, 16)); // Fuente para el encabezado
        headerRenderer.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.WHITE));

        // Aplicar el renderer a los encabezados
        tablaHistorico.getTableHeader().setDefaultRenderer(headerRenderer);

        // Personaliza el renderer para las filas
        tablaHistorico.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public java.awt.Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                java.awt.Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                if (isSelected) {
                    cell.setBackground(new Color(0, 102, 204)); // Color de selección
                    cell.setForeground(Color.WHITE);
                } else {
                    // Alternar colores de filas para mayor legibilidad
                    cell.setBackground(row % 2 == 0 ? new Color(220, 220, 220) : Color.WHITE);
                    cell.setForeground(Color.BLACK);
                }
                return cell;
            }
        });

        // Agrega un JScrollPane para la tabla
        JScrollPane jScrollPane1 = new JScrollPane(tablaHistorico);
        jScrollPane1.setPreferredSize(new Dimension(1200, 400)); // Ajusta el tamaño aquí
        add(jScrollPane1, createGridBagConstraints(0, 1, 1, 1, GridBagConstraints.CENTER));

        pack();
        setLocationRelativeTo(null);
    }

    private GridBagConstraints createGridBagConstraints(int gridx, int gridy, int gridwidth, int gridheight, int anchor) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = gridx;
        gbc.gridy = gridy;
        gbc.gridwidth = gridwidth;
        gbc.gridheight = gridheight;
        gbc.anchor = anchor;
        gbc.insets = new java.awt.Insets(10, 10, 10, 10);
        return gbc;
    }

    public void cargarTablaCuotas() {
        // Definir el modelo de la tabla
        DefaultTableModel model = new DefaultTableModel();

        model.addColumn("ID Credito");
        model.addColumn("ID Cuota");
        model.addColumn("Importe Cuota");
        model.addColumn("Vencimiento");
        model.addColumn("Fecha pago");

        // Consulta SQL para obtener las cuotas del cliente
        String sql = "SELECT id_credito, id_cuota, importe_cuota, vencimiento, ultimo_mov FROM cuotas WHERE id_cliente = ? AND pago_realizado = 1";

        // Crear una instancia de la clase Conexion
        Conexion conexion = new Conexion();
        Connection conn = null;

        try {
            // Establecer la conexión a la base de datos usando SQLite
            conn = conexion.establecerConexion();
            PreparedStatement stmt = conn.prepareStatement(sql);

            // Asignar el valor de id_cliente al parámetro de la consulta
            stmt.setInt(1, id_cliente);

            // Ejecutar la consulta
            ResultSet rs = stmt.executeQuery();

            // Recorrer los resultados y agregar cada fila al modelo de la tabla
            while (rs.next()) {
                int idCredito = rs.getInt("id_credito");
                int idCuota = rs.getInt("id_cuota");
                double importeCuota = rs.getDouble("importe_cuota");
                String vencimiento = rs.getString("vencimiento");
                String fecha_pago = rs.getString("ultimo_mov");

                // Agregar la fila al modelo de la tabla
                model.addRow(new Object[]{idCredito, idCuota, importeCuota, vencimiento, fecha_pago});
            }

            // Asignar el modelo actualizado a la tabla
            tablaHistorico.setModel(model);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al cargar los datos: " + e.toString());
            e.printStackTrace();
        } finally {
            // Cerrar la conexión a la base de datos
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error al cerrar la conexión: " + e.toString());
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaHistorico = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("HISTORICO DE CREDITOS");

        tablaHistorico.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tablaHistorico);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(96, 96, 96)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 727, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(17, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FormHistorico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormHistorico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormHistorico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormHistorico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormHistorico().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaHistorico;
    // End of variables declaration//GEN-END:variables
}
