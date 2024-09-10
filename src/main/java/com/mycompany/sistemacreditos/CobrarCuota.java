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
            String sql = "SELECT c.num_credito, cu.id_cuota, cu.num_cuota, cu.importe_cuota, cu.importe_actualizado, cu.vencimiento "
                    + "FROM cuotas cu "
                    + "INNER JOIN credito c ON cu.id_credito = c.num_credito "
                    + "INNER JOIN clientes cl ON c.id_cliente = cl.id "
                    + "WHERE cl.dni = ? AND cu.pago_realizado = 0";
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
                    rs.getInt("num_cuota"),
                    rs.getDouble("importe_cuota"),
                    rs.getDouble("importe_actualizado"),
                    false,  // Columna de pago total (inicialmente false)
                    0.0     // Columna de pago parcial (inicialmente 0.0)
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

    public void procesarPago(int idCuota, double montoPago) {
        Conexion objetoConexion = new Conexion();
        Connection conexion = null;
        PreparedStatement pstmtConsulta = null;
        PreparedStatement pstmtActualizar = null;
        PreparedStatement pstmtInsertarPago = null;
        ResultSet rs = null;
        boolean pagoExitoso = false;

        try {
            conexion = objetoConexion.establecerConexion();
            conexion.setAutoCommit(false);  // Iniciamos una transacción

            // Obtener el importe actualizado de la cuota
            String sqlConsulta = "SELECT importe_actualizado FROM cuotas WHERE id_cuota = ?";
            pstmtConsulta = conexion.prepareStatement(sqlConsulta);
            pstmtConsulta.setInt(1, idCuota);
            rs = pstmtConsulta.executeQuery();

            if (rs.next()) {
                double importeActualizado = rs.getDouble("importe_actualizado");

                // Validar que el monto del pago no sea mayor que el importe actualizado
                if (montoPago > importeActualizado) {
                    javax.swing.JOptionPane.showMessageDialog(null, "El monto del pago no puede ser mayor al importe actualizado.");
                    return; // Salir del método si la validación falla
                }

                // Calcular el nuevo importe actualizado después del pago
                double nuevoImporteActualizado = importeActualizado - montoPago;

                // Actualizar el importe actualizado en la tabla cuotas
                String sqlActualizar = "UPDATE cuotas SET importe_actualizado = ? WHERE id_cuota = ?";
                pstmtActualizar = conexion.prepareStatement(sqlActualizar);
                pstmtActualizar.setDouble(1, nuevoImporteActualizado);
                pstmtActualizar.setInt(2, idCuota);
                int filasActualizadas = pstmtActualizar.executeUpdate();

                if (filasActualizadas > 0) {
                    // Si el importe actualizado es 0, marcar el pago como realizado
                    if (nuevoImporteActualizado == 0) {
                        String sqlActualizarPagoRealizado = "UPDATE cuotas SET pago_realizado = 1 WHERE id_cuota = ?";
                        pstmtActualizar = conexion.prepareStatement(sqlActualizarPagoRealizado);
                        pstmtActualizar.setInt(1, idCuota);
                        pstmtActualizar.executeUpdate();
                    }

                    // Registrar el pago en la tabla pagos
                    String sqlInsertarPago = "INSERT INTO pagos (id_cuota, monto) VALUES (?, ?)";
                    pstmtInsertarPago = conexion.prepareStatement(sqlInsertarPago);
                    pstmtInsertarPago.setInt(1, idCuota);
                    pstmtInsertarPago.setDouble(2, montoPago);
                    int filasInsertadas = pstmtInsertarPago.executeUpdate();

                    if (filasInsertadas > 0) {
                        pagoExitoso = true;
                        conexion.commit();  // Confirmar la transacción
                    } else {
                        conexion.rollback();  // Revertir la transacción si la inserción falla
                    }
                } else {
                    conexion.rollback();  // Revertir la transacción si la actualización falla
                }
            } else {
                javax.swing.JOptionPane.showMessageDialog(null, "No se encontró la cuota con el ID especificado.");
            }
        } catch (SQLException e) {
            try {
                if (conexion != null) {
                    conexion.rollback();  // Revertir la transacción en caso de excepción
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(null, "Error al procesar el pago: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmtConsulta != null) {
                    pstmtConsulta.close();
                }
                if (pstmtActualizar != null) {
                    pstmtActualizar.close();
                }
                if (pstmtInsertarPago != null) {
                    pstmtInsertarPago.close();
                }
                if (conexion != null) {
                    conexion.setAutoCommit(true);  // Restaurar el auto-commit
                    conexion.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        // Mostrar el mensaje de éxito solo si todo el proceso fue exitoso
        if (pagoExitoso) {
            javax.swing.JOptionPane.showMessageDialog(null, "Pago procesado correctamente.");
        }
    }
}