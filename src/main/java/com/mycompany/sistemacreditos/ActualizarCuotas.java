package com.mycompany.sistemacreditos;

import java.time.LocalDate;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import javax.swing.JOptionPane;

public class ActualizarCuotas {

    private static final double INTERES_DIARIO = 0.01; // 1% diario

    // INTERES COMPUESTO:
    /*
    public void actualizarCuotasVencidas() {
        Conexion objetoConexion = new Conexion();
        Connection conexion = null;
        PreparedStatement pstmtSelect = null;
        PreparedStatement pstmtUpdate = null;
        ResultSet rs = null;

        // Define el formato de fecha que usarás
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        try {
            conexion = objetoConexion.establecerConexion();
            // SQL para obtener las cuotas donde pago_realizado = 0
            String sqlSelect = "SELECT id_cuota, importe_actualizado, vencimiento, ultimo_mov, fecha_actualizacion "
                    + "FROM cuotas "
                    + "WHERE pago_realizado = 0";

            pstmtSelect = conexion.prepareStatement(sqlSelect);
            rs = pstmtSelect.executeQuery();

            // Fecha actual formateada
            LocalDate fechaActual = LocalDate.now();
            String fechaActualFormateada = fechaActual.format(dateFormatter);

            // Modificar la consulta UPDATE para usar texto en lugar de DATE
            String sqlUpdate = "UPDATE cuotas SET importe_actualizado = ?, fecha_actualizacion = ? WHERE id_cuota = ?";

            pstmtUpdate = conexion.prepareStatement(sqlUpdate);

            while (rs.next()) {
                // Obtener los datos de la cuota
                int idCuota = rs.getInt("id_cuota");
                double importeActualizado = rs.getDouble("importe_actualizado");

                // Función auxiliar para convertir timestamp a LocalDate
                LocalDate fechaVencimiento = convertirTimestampALocalDate(rs.getString("vencimiento"));
                LocalDate ultimoMov = convertirTimestampALocalDate(rs.getString("ultimo_mov"));

                // Obtener la fecha_actualizacion
                LocalDate fechaActualizacion;
                String fechaActualizacionStr = rs.getString("fecha_actualizacion");
                if (fechaActualizacionStr != null && !fechaActualizacionStr.isEmpty()) {
                    try {
                        fechaActualizacion = convertirTimestampALocalDate(fechaActualizacionStr);
                    } catch (Exception e) {
                        fechaActualizacion = fechaActual;
                    }
                } else {
                    fechaActualizacion = fechaActual;
                }

                // Comparar la fecha de vencimiento con la fecha actual
                if (fechaVencimiento.isBefore(fechaActual)) {
                    // Calcular el número de días de retraso desde la fecha de vencimiento
                    long diasRetraso = ChronoUnit.DAYS.between(fechaVencimiento, fechaActual);
                    System.out.println("Días de retraso para cuota ID " + idCuota + ": " + diasRetraso);

                    // Si hay días de retraso, aplicar una penalización del 1% por cada día de retraso
                    if (diasRetraso > 0) {
                        // Calcular el nuevo importe con interés compuesto
                        BigDecimal nuevoImporte = BigDecimal.valueOf(importeActualizado)
                                .multiply(BigDecimal.valueOf(Math.pow(1.01, diasRetraso)))
                                .setScale(2, RoundingMode.HALF_UP);

                        System.out.println("Importe original: " + importeActualizado);
                        System.out.println("Nuevo importe calculado: " + nuevoImporte);

                        // Actualizar el importe_actualizado y la fecha de actualización en la base de datos
                        pstmtUpdate.setDouble(1, nuevoImporte.doubleValue());
                        pstmtUpdate.setString(2, fechaActualFormateada);
                        pstmtUpdate.setInt(3, idCuota);
                        pstmtUpdate.executeUpdate();
                    }
                }
            }

            JOptionPane.showMessageDialog(null, "Cuotas vencidas actualizadas correctamente.");

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al actualizar cuotas vencidas: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
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

    private LocalDate convertirTimestampALocalDate(String timestamp) {
        try {
            // Primero intentamos parsear como fecha formateada
            return LocalDate.parse(timestamp, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        } catch (Exception e) {
            try {
                // Si falla, intentamos convertir desde timestamp
                long timestampLong = Long.parseLong(timestamp);
                return Instant.ofEpochMilli(timestampLong)
                        .atZone(ZoneId.systemDefault())
                        .toLocalDate();
            } catch (Exception e2) {
                // Si ambos métodos fallan, retornamos la fecha actual
                return LocalDate.now();
            }
        }
    }
}
    
  // INTERES DIARIO SOBRE CUOTA PURA
    
    
     */
    public void actualizarCuotasVencidas() {
        Conexion objetoConexion = new Conexion();
        Connection conexion = null;
        PreparedStatement pstmtSelect = null;
        PreparedStatement pstmtUpdate = null;
        ResultSet rs = null;

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        try {
            conexion = objetoConexion.establecerConexion();
            // Modificado para incluir importe_cuota
            String sqlSelect = "SELECT id_cuota, importe_cuota, importe_actualizado, vencimiento, ultimo_mov, fecha_actualizacion "
                    + "FROM cuotas "
                    + "WHERE pago_realizado = 0";

            pstmtSelect = conexion.prepareStatement(sqlSelect);
            rs = pstmtSelect.executeQuery();

            LocalDate fechaActual = LocalDate.now();
            String fechaActualFormateada = fechaActual.format(dateFormatter);

            String sqlUpdate = "UPDATE cuotas SET importe_actualizado = ?, fecha_actualizacion = ? WHERE id_cuota = ?";
            pstmtUpdate = conexion.prepareStatement(sqlUpdate);

            while (rs.next()) {
                int idCuota = rs.getInt("id_cuota");
                double importeCuota = rs.getDouble("importe_cuota"); // Usando importe_cuota en lugar de importe_actualizado

                LocalDate fechaVencimiento = convertirTimestampALocalDate(rs.getString("vencimiento"));

                if (fechaVencimiento.isBefore(fechaActual)) {
                    long diasRetraso = ChronoUnit.DAYS.between(fechaVencimiento, fechaActual);

                    if (diasRetraso > 0) {
                        // Cálculo de interés simple sobre el importe_cuota
                        BigDecimal interes = BigDecimal.valueOf(importeCuota)
                                .multiply(BigDecimal.valueOf(INTERES_DIARIO))
                                .multiply(BigDecimal.valueOf(diasRetraso));

                        BigDecimal nuevoImporte = BigDecimal.valueOf(importeCuota)
                                .add(interes)
                                .setScale(2, RoundingMode.HALF_UP);

                        System.out.println("ID Cuota: " + idCuota);
                        System.out.println("Importe cuota pura: " + importeCuota);
                        System.out.println("Días de retraso: " + diasRetraso);
                        System.out.println("Interés calculado: " + interes);
                        System.out.println("Nuevo importe calculado: " + nuevoImporte);

                        pstmtUpdate.setDouble(1, nuevoImporte.doubleValue());
                        pstmtUpdate.setString(2, fechaActualFormateada);
                        pstmtUpdate.setInt(3, idCuota);
                        pstmtUpdate.executeUpdate();
                    }
                }
            }

            JOptionPane.showMessageDialog(null, "Cuotas vencidas actualizadas correctamente.");

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al actualizar cuotas vencidas: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
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

    private LocalDate convertirTimestampALocalDate(String timestamp) {
        try {
            return LocalDate.parse(timestamp, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        } catch (Exception e) {
            try {
                long timestampLong = Long.parseLong(timestamp);
                return Instant.ofEpochMilli(timestampLong)
                        .atZone(ZoneId.systemDefault())
                        .toLocalDate();
            } catch (Exception e2) {
                return LocalDate.now();
            }
        }
    }

}
