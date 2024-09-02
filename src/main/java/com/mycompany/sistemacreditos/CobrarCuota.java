package com.mycompany.sistemacreditos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class CobrarCuota {

    public void MostrarCuotasCliente(JTextField dni_buscar, JTable tablaCuotas) {
        Conexion objetoConexion = new Conexion();
        Connection conexion = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conexion = objetoConexion.establecerConexion();
            String sql = "SELECT c.num_credito, cu.id_cuota, cu.importe_cuota, cu.recargo, cu.vencimiento "
                    + "FROM cuotas cu "
                    + "INNER JOIN credito c ON cu.id_credito = c.num_credito "
                    + "INNER JOIN clientes cl ON c.id_cliente = cl.id "
                    + "WHERE cl.dni = ?";
            pstmt = conexion.prepareStatement(sql);
            pstmt.setString(1, dni_buscar.getText());
            rs = pstmt.executeQuery();

            // Obtener la estructura del DefaultTableModel
            DefaultTableModel model = (DefaultTableModel) tablaCuotas.getModel();

            // Limpiar la tabla antes de agregar nuevos datos
            model.setRowCount(0);

            while (rs.next()) {
                // Convertir la fecha a un formato de string para evitar problemas de parsing
                String fechaVencimiento = rs.getString("vencimiento");

                // Agregar los datos a la tabla
                model.addRow(new Object[]{
                    rs.getInt("num_credito"),
                    rs.getInt("id_cuota"),
                    rs.getDouble("importe_cuota"),
                    rs.getDouble("recargo"),
                    fechaVencimiento
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Mostrar un mensaje de error usando JOptionPane
            javax.swing.JOptionPane.showMessageDialog(null, "Error al buscar cuotas del cliente: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conexion != null) {
                    conexion.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
public void procesarPago(Integer idCuota, Double montoPago) {
    Conexion objetoConexion = new Conexion();
    Connection conexion = null;
    PreparedStatement pstmtActualizarRecargo = null;
    PreparedStatement pstmtInsertarPago = null;

    try {
        conexion = objetoConexion.establecerConexion();
        conexion.setAutoCommit(false); // Comenzar la transacción

        String sqlActualizarRecargo = "UPDATE cuotas SET recargo = recargo - ? WHERE id_cuota = ?";
        pstmtActualizarRecargo = conexion.prepareStatement(sqlActualizarRecargo);

        String sqlInsertarPago = "INSERT INTO pagos (id_cuota, monto) VALUES (?, ?)";
        pstmtInsertarPago = conexion.prepareStatement(sqlInsertarPago);

        // Actualizar el recargo de la cuota
        pstmtActualizarRecargo.setDouble(1, montoPago);
        pstmtActualizarRecargo.setInt(2, idCuota);
        pstmtActualizarRecargo.executeUpdate();

        // Insertar el pago
        pstmtInsertarPago.setInt(1, idCuota);
        pstmtInsertarPago.setDouble(2, montoPago);
        pstmtInsertarPago.executeUpdate();

        conexion.commit(); // Confirmar la transacción
    } catch (SQLException e) {
        try {
            if (conexion != null) {
                conexion.rollback(); // Revertir la transacción en caso de error
            }
        } catch (SQLException rollbackEx) {
            rollbackEx.printStackTrace();
        }
        e.printStackTrace();
        javax.swing.JOptionPane.showMessageDialog(null, "Error al procesar el pago: " + e.getMessage());
    } finally {
        try {
            if (pstmtActualizarRecargo != null) {
                pstmtActualizarRecargo.close();
            }
            if (pstmtInsertarPago != null) {
                pstmtInsertarPago.close();
            }
            if (conexion != null) {
                conexion.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}




}
