package com.mycompany.sistemacreditos;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class ActualizarCuotas {

    public void actualizarCuotasVencidas() {
        Conexion objetoConexion = new Conexion();
        Connection conexion = null;
        PreparedStatement pstmtSelect = null;
        PreparedStatement pstmtUpdate = null;
        ResultSet rs = null;

        try {
            conexion = objetoConexion.establecerConexion();
            // SQL para obtener las cuotas donde pago_realizado = 0
            String sqlSelect = "SELECT id_cuota, importe_actualizado, vencimiento, ultimo_mov, fecha_actualizacion "
                    + "FROM cuotas "
                    + "WHERE pago_realizado = 0";

            pstmtSelect = conexion.prepareStatement(sqlSelect);
            rs = pstmtSelect.executeQuery();

            // Fecha actual
            LocalDate fechaActual = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy"); // Formato de la fecha DD-MM-AAAA

            // Preparar la consulta para actualizar el importe_actualizado
            String sqlUpdate = "UPDATE cuotas SET importe_actualizado = ?, fecha_actualizacion = ? WHERE id_cuota = ?";

            pstmtUpdate = conexion.prepareStatement(sqlUpdate);

            while (rs.next()) {
                // Obtener los datos de la cuota
                int idCuota = rs.getInt("id_cuota");
                double importeActualizado = rs.getDouble("importe_actualizado");

                // Parsear las fechas de vencimiento, último movimiento y última actualización
                LocalDate fechaVencimiento = LocalDate.parse(rs.getString("vencimiento"), formatter);
                LocalDate ultimoMov = LocalDate.parse(rs.getString("ultimo_mov"), formatter);
                LocalDate fechaUltimaActualizacion = rs.getDate("fecha_actualizacion") != null
                        ? rs.getDate("fecha_actualizacion").toLocalDate()
                        : ultimoMov; // Usar último movimiento si no hay fecha de actualización previa

                // Comparar la fecha de vencimiento con la fecha actual
                if (fechaVencimiento.isBefore(fechaActual)) {
                    // Calcular el número de días de retraso desde la última actualización
                    long diasRetraso = ChronoUnit.DAYS.between(fechaUltimaActualizacion, fechaActual);

                    // Si hay días de retraso, aplicar una penalización del 1% por cada día de retraso
                    if (diasRetraso > 0) {
                        double nuevoImporte = importeActualizado * Math.pow(1.01, diasRetraso);

                        // Actualizar el importe_actualizado y la fecha de actualización en la base de datos
                        pstmtUpdate.setDouble(1, nuevoImporte);
                        pstmtUpdate.setDate(2, java.sql.Date.valueOf(fechaActual)); // Establecer la fecha actual
                        pstmtUpdate.setInt(3, idCuota);
                        pstmtUpdate.executeUpdate();
                    }
                }
            }

            JOptionPane.showMessageDialog(null, "Cuotas vencidas actualizadas correctamente.");

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al actualizar cuotas vencidas: " + e.getMessage());
        } catch (DateTimeParseException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error en el formato de fecha: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmtSelect != null) {
                    pstmtSelect.close();
                }
                if (pstmtUpdate != null) {
                    pstmtUpdate.close();
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