
package com.mycompany.sistemacreditos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;


public class FormHistorico extends javax.swing.JFrame {

    int id_cliente;

    public FormHistorico() {
        initComponents();
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        ImageIcon icon = new ImageIcon("icono.png"); // Cambia la ruta según tu estructura de proyecto
        setIconImage(icon.getImage());
        DetalleCliente detalle = new DetalleCliente();
        id_cliente = detalle.id_cliente;
        cargarTablaCuotas();
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
    String sql = "SELECT id_credito, id_cuota, importe_cuota, vencimiento, ultimo_mov FROM cuotas WHERE id_cliente = ?";

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
