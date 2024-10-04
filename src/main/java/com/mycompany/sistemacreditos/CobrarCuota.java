package com.mycompany.sistemacreditos;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.stream.Stream;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class CobrarCuota {

    public void generarPdf(String dni, String nombres, String apellido) {
        Conexion objetoConexion = new Conexion();
        Connection conexion = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String fechaPago = sdf.format(new Date());

        try {
            conexion = objetoConexion.establecerConexion();
            String sql = "SELECT c.num_credito, cu.num_cuota, cu.importe_cuota, p.monto, p.fecha_pago "
                    + "FROM pagos p "
                    + "INNER JOIN cuotas cu ON p.id_cuota = cu.id_cuota "
                    + "INNER JOIN credito c ON cu.id_credito = c.num_credito "
                    + "INNER JOIN clientes cl ON c.id_cliente = cl.id "
                    + "WHERE cl.dni = ? "
                    + "AND p.fecha_pago = ? "
                    + // Filtrar por pagos realizados hoy
                    "ORDER BY c.num_credito, cu.num_cuota";
            pstmt = conexion.prepareStatement(sql);
            pstmt.setString(1, dni);
            pstmt.setString(2, fechaPago);
            rs = pstmt.executeQuery();

            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream("cuotas_pagadas_" + dni + ".pdf"));
            document.open();

            // Agregar logo
            Image logo = Image.getInstance("logo.png");
            logo.scaleToFit(150, 150);
            logo.setAlignment(Element.ALIGN_CENTER);
            document.add(logo);

            // Datos del negocio
            Font fuenteNormal = FontFactory.getFont(FontFactory.HELVETICA, 12, BaseColor.BLACK);
            document.add(new Paragraph("Dirección: Avenida 7 Número 2278", fuenteNormal));
            document.add(new Paragraph("Teléfono: (0221) 457-7707", fuenteNormal));
            document.add(new Paragraph("\n"));

            document.add(new Paragraph("Datos del Cliente:", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14)));
            document.add(new Paragraph("Nombre: " + nombres + " " + apellido, fuenteNormal));
            document.add(new Paragraph("DNI: " + dni, fuenteNormal));
            document.add(new Paragraph("\n"));

            // Título del documento
            Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, BaseColor.BLACK);
            Paragraph title = new Paragraph("Cuotas Pagadas - DNI: " + dni, titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);
            document.add(new Paragraph("\n"));

            // Crear tabla
            PdfPTable table = new PdfPTable(5);
            table.setWidthPercentage(100);
            table.setWidths(new float[]{2, 2, 3, 3, 3});

            Stream.of("Nº Crédito", "Nº Cuota", "Importe Cuota", "Monto Pagado", "Fecha Pago")
                    .forEach(columnTitle -> {
                        PdfPCell header = new PdfPCell();
                        header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                        header.setBorderWidth(2);
                        header.setPhrase(new Phrase(columnTitle, FontFactory.getFont(FontFactory.HELVETICA_BOLD)));
                        header.setHorizontalAlignment(Element.ALIGN_CENTER);
                        header.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        header.setPadding(5);
                        table.addCell(header);
                    });

            // Añadir datos a la tabla
            double totalPagado = 0;
            while (rs.next()) {
                table.addCell(new PdfPCell(new Phrase(rs.getString("num_credito"))));
                table.addCell(new PdfPCell(new Phrase(rs.getString("num_cuota"))));
                table.addCell(new PdfPCell(new Phrase("$" + String.format("%.2f", rs.getDouble("importe_cuota")))));
                double montoPagado = rs.getDouble("monto");
                table.addCell(new PdfPCell(new Phrase("$" + String.format("%.2f", montoPagado))));
                table.addCell(new PdfPCell(new Phrase(rs.getString("fecha_pago"))));
                totalPagado += montoPagado;
            }

            document.add(table);

            // Agregar el total pagado
            Paragraph total = new Paragraph("Total Pagado: $" + String.format("%.2f", totalPagado),
                    FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14));
            total.setAlignment(Element.ALIGN_RIGHT);
            document.add(new Paragraph("\n"));
            document.add(total);
            document.add(new Paragraph("\n"));
            document.add(new Paragraph("\n"));

            //duplicado
            // Agregar logo
            document.add(logo);

            // Datos del negocio
            document.add(new Paragraph("Dirección: Avenida 7 Número 2278", fuenteNormal));
            document.add(new Paragraph("Teléfono: (0221) 457-7707", fuenteNormal));
            document.add(new Paragraph("\n"));

            document.add(new Paragraph("Datos del Cliente:", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14)));
            document.add(new Paragraph("Nombre: " + nombres + " " + apellido, fuenteNormal));
            document.add(new Paragraph("DNI: " + dni, fuenteNormal));
            document.add(new Paragraph("\n"));

            // Título del documento
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);
            document.add(new Paragraph("\n"));
            document.add(table);

            System.out.println("PDF generado: cuotas_pagadas_" + dni + ".pdf");
            String rutaArchivo = "cuotas_pagadas_" + dni + ".pdf";
            document.add(new Paragraph("\n"));
            document.add(total);
            document.close();
            abrirArchivoPDF(rutaArchivo);

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error al generar el PDF: " + e.getMessage());
        } finally {
            // Cerrar conexiones
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

    private void abrirArchivoPDF(String rutaArchivo) {
        try {
            // Ejecutar el comando para abrir el PDF
            File archivo = new File(rutaArchivo);
            if (archivo.exists()) {
                // Comando específico para el sistema operativo
                String os = System.getProperty("os.name").toLowerCase();
                if (os.contains("win")) {
                    // Windows
                    Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + rutaArchivo);
                } else if (os.contains("mac")) {
                    // macOS
                    Runtime.getRuntime().exec("open " + rutaArchivo);
                } else if (os.contains("nix") || os.contains("nux")) {
                    // Linux
                    Runtime.getRuntime().exec("xdg-open " + rutaArchivo);
                }
            } else {
                JOptionPane.showMessageDialog(null, "El archivo PDF no existe: " + rutaArchivo);
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al abrir el archivo PDF: " + e.getMessage());
            e.printStackTrace();
        }
    }

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
                    rs.getString("vencimiento"),
                    false, // Columna de pago total (inicialmente false)
                    0.0 // Columna de pago parcial (inicialmente 0.0)
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
                    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                    String fechaPago = sdf.format(new Date());

                    // Registrar el pago en la tabla pagos con la fecha actual
                    String sqlInsertarPago = "INSERT INTO pagos (id_cuota, monto, fecha_pago) VALUES (?, ?, ?)";
                    pstmtInsertarPago = conexion.prepareStatement(sqlInsertarPago);
                    pstmtInsertarPago.setInt(1, idCuota);
                    pstmtInsertarPago.setDouble(2, montoPago);
                    pstmtInsertarPago.setString(3, fechaPago);
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
