
package com.mycompany.sistemacreditos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
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
        // SQL para obtener las cuotas con pago_realizado = 0
        String sqlSelect = "SELECT id_cuota, importe_actualizado, vencimiento "
                         + "FROM cuotas "
                         + "WHERE pago_realizado = 0";

        pstmtSelect = conexion.prepareStatement(sqlSelect);
        rs = pstmtSelect.executeQuery();

        // Fecha actual
        LocalDate fechaActual = LocalDate.now();

        // Preparar la consulta para actualizar el importe_actualizado
        String sqlUpdate = "UPDATE cuotas SET importe_actualizado = ? WHERE id_cuota = ?";

        pstmtUpdate = conexion.prepareStatement(sqlUpdate);

        while (rs.next()) {
            // Obtener los datos de la cuota
            int idCuota = rs.getInt("id_cuota");
            double importeActualizado = rs.getDouble("importe_actualizado");
            LocalDate fechaVencimiento = LocalDate.parse(rs.getString("vencimiento")); // Asegúrate de que el formato de la fecha sea compatible

            // Comparar la fecha de vencimiento con la fecha actual
            if (fechaActual.isAfter(fechaVencimiento)) {
                // Calcular el número de días de retraso
                long diasRetraso = ChronoUnit.DAYS.between(fechaVencimiento, fechaActual);

                // Incrementar el importe_actualizado en un 2% por cada día de retraso
                double nuevoImporte = importeActualizado * Math.pow(1.02, diasRetraso);

                // Actualizar el importe_actualizado en la base de datos
                pstmtUpdate.setDouble(1, nuevoImporte);
                pstmtUpdate.setInt(2, idCuota);
                pstmtUpdate.executeUpdate();
            }
        }

        JOptionPane.showMessageDialog(null, "Cuotas vencidas actualizadas correctamente.");
        
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error al actualizar cuotas vencidas: " + e.getMessage());
    } finally {
        try {
            if (rs != null) rs.close();
            if (pstmtSelect != null) pstmtSelect.close();
            if (pstmtUpdate != null) pstmtUpdate.close();
            if (conexion != null) conexion.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
    
}
