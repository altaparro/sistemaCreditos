package com.mycompany.sistemacreditos;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;

public class Creditos {

    private Map<String, Integer> planPagoMap = new HashMap<>();

  public void generarPDFCredito(int idCredito) {
    Conexion objetoConexion = new Conexion();
    Connection conexion = null;
    Document documento = new Document();
    String rutaArchivo = "Credito_" + idCredito + ".pdf";

    try {
        conexion = objetoConexion.establecerConexion();
        PdfWriter.getInstance(documento, new FileOutputStream(rutaArchivo));
        documento.open();

        // Agregar logo
        Image logo = Image.getInstance("logo.png");
        logo.scaleToFit(150, 150);
        logo.setAlignment(Element.ALIGN_CENTER);
        documento.add(logo);

        // Datos del negocio
        Font fuenteNormal = FontFactory.getFont(FontFactory.HELVETICA, 12, BaseColor.BLACK);
        documento.add(new Paragraph("Dirección: Avenida 7 Número 2278", fuenteNormal));
        documento.add(new Paragraph("Teléfono: (0221) 457-7707", fuenteNormal));
        documento.add(new Paragraph("\n"));

        // Datos del cliente y crédito
        String sqlCliente = "SELECT c.dni, c.nombres, c.apellidos, cr.monto, cr.fecha, cr.observacion "
                + "FROM clientes c "
                + "JOIN credito cr ON c.id = cr.id_cliente "
                + "WHERE cr.num_credito = ?";

        try (PreparedStatement pstmt = conexion.prepareStatement(sqlCliente)) {
            pstmt.setInt(1, idCredito);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    documento.add(new Paragraph("Datos del Cliente:", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14)));
                    documento.add(new Paragraph("Nombre: " + rs.getString("nombres") + " " + rs.getString("apellidos"), fuenteNormal));
                    documento.add(new Paragraph("DNI: " + rs.getString("dni"), fuenteNormal));
                    documento.add(new Paragraph("\n"));

                    documento.add(new Paragraph("Datos del Crédito:", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14)));
                    documento.add(new Paragraph("Numero de credito: " + idCredito, fuenteNormal));
                    documento.add(new Paragraph("Monto: $" + rs.getDouble("monto"), fuenteNormal));
                    documento.add(new Paragraph("Fecha: " + rs.getString("fecha"), fuenteNormal));
                    documento.add(new Paragraph("Observación: " + rs.getString("observacion"), fuenteNormal));
                    documento.add(new Paragraph("\n"));
                }
            }
        }

        // Tabla de cuotas
        PdfPTable tabla = new PdfPTable(5);
        tabla.setWidthPercentage(100);
        tabla.addCell("N° Cuota");
        tabla.addCell("Importe");
        tabla.addCell("Vencimiento");
        tabla.addCell("Recargo");
        tabla.addCell("Estado");

        String sqlCuotas = "SELECT num_cuota, importe_cuota, vencimiento, importe_actualizado, pago_realizado "
                + "FROM cuotas WHERE id_credito = ?";

        try (PreparedStatement pstmt = conexion.prepareStatement(sqlCuotas)) {
            pstmt.setInt(1, idCredito);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    tabla.addCell(String.valueOf(rs.getInt("num_cuota")));
                    tabla.addCell("$" + rs.getDouble("importe_cuota"));
                    tabla.addCell(rs.getString("vencimiento"));
                    tabla.addCell("$" + rs.getDouble("importe_actualizado"));
                    tabla.addCell(rs.getBoolean("pago_realizado") ? "Pagada" : "Pendiente");
                }
            }
        }
        documento.add(tabla);
        documento.add(new Paragraph("\n\n\n"));

        // Texto legal
        documento.add(new Paragraph(" Conforme al artículo 44, tercer párrafo de la citada norma, el pago deberá hacerse efectivo en la moneda expresada. Se amplía el plazo de presentación a 6 años, según el artículo 36 del Decreto Ley 5685/63. La falta de pago en término hará caducar todos los plazos, siendo exigible desde ese momento el saldo, más intereses punitorios equivalentes a dos veces la tasa del Banco de la Nación Argentina (BNA) para operaciones en descubierto.", fuenteNormal));
        documento.add(new Paragraph(" Acepto que el presente sea dado en garantía o se transfiera el crédito emergente. De optarse por la cesión del crédito, excepto que se modifique el domicilio de pago, podrá hacerse sin necesidad de notificación. A los efectos de cumplir con el artículo 36 de la Ley 24.240, se deja manifestado que el negocio causal del presente pagaré es un contrato de mutuo.", fuenteNormal));
        
        // Espacio para firma en la primera página
        documento.add(new Paragraph("\n\n\n"));
        documento.add(new Paragraph("______________________", fuenteNormal));
        documento.add(new Paragraph("Firma", fuenteNormal));
        documento.add(new Paragraph("\n"));
        documento.add(new Paragraph("______________________", fuenteNormal));
        documento.add(new Paragraph("Aclaración", fuenteNormal));
        documento.add(new Paragraph("\n"));
        documento.add(new Paragraph("______________________", fuenteNormal));
        documento.add(new Paragraph("DNI", fuenteNormal));

        // Nueva página (duplicado)
        documento.newPage();
        
        // Agregar el logo en la segunda página
        documento.add(logo);

        // Datos del negocio (duplicados en la segunda página)
        documento.add(new Paragraph("Dirección: Avenida 7 Número 2278", fuenteNormal));
        documento.add(new Paragraph("Teléfono: (0221) 457-7707", fuenteNormal));
        documento.add(new Paragraph("\n"));

        // Datos del cliente y crédito (duplicados en la segunda página)
        try (PreparedStatement pstmt = conexion.prepareStatement(sqlCliente)) {
            pstmt.setInt(1, idCredito);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    documento.add(new Paragraph("Datos del Cliente:", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14)));
                    documento.add(new Paragraph("Nombre: " + rs.getString("nombres") + " " + rs.getString("apellidos"), fuenteNormal));
                    documento.add(new Paragraph("DNI: " + rs.getString("dni"), fuenteNormal));
                    documento.add(new Paragraph("\n"));

                    documento.add(new Paragraph("Datos del Crédito:", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14)));
                    documento.add(new Paragraph("Numero de credito: " + idCredito, fuenteNormal));
                    documento.add(new Paragraph("Monto: $" + rs.getDouble("monto"), fuenteNormal));
                    documento.add(new Paragraph("Fecha: " + rs.getString("fecha"), fuenteNormal));
                    documento.add(new Paragraph("Observación: " + rs.getString("observacion"), fuenteNormal));
                    documento.add(new Paragraph("\n"));
                }
            }
        }

        // Tabla de cuotas (duplicada en la segunda página)
        documento.add(tabla);
        documento.add(new Paragraph("\n\n\n"));

        // Texto legal (duplicado en la segunda página)
        documento.add(new Paragraph(" Conforme al art 44 3° parrafo de la citada norma, el pago deberá hacerse efectivo en la moneda expresada. Se amplía el plazo de presentación a 6 años, según el art 36 Decreto Ley 5685/63. La Falta de pago en termino hará caducar todos los plazos, siento exigible desde ese momento el saldo, más intereses punitorios equivalentes a dos veces la tasa del Banco de La Nación Argentina (BNA) para operaciones en descubierto.", fuenteNormal));
        documento.add(new Paragraph(" Acepto que el presente sea dado en garantía o se transfiera el crédito emergente. De optarse por la cesión del crédito, excepto que se modifique el domicilio de pago, podrá hacerse sin necesidad de notificacion. A los efectos de cumplir con el art. 36 de la Ley 24.240 se deja manifestado que el negocio causal del presente pagare es un cotrato de mutuo.", fuenteNormal));

        // En la segunda página NO agregamos el espacio para firma, aclaración y DNI.

        JOptionPane.showMessageDialog(null, "PDF generado con éxito: " + rutaArchivo);

        // Abrir el archivo PDF
        abrirArchivoPDF(rutaArchivo);
    } catch (DocumentException | IOException | SQLException e) {
        JOptionPane.showMessageDialog(null, "Error al generar el PDF: " + e.getMessage());
        e.printStackTrace();
    } finally {
        documento.close();
        if (conexion != null) {
            try {
                conexion.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
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

  public int insertarCredito(JTextField dni_cliente, JTextPane observacion, JTextField monto, String fecha, int id_plan_pago) {
    Conexion objetoConexion = new Conexion();
    Connection conexion = null;
    PreparedStatement psBuscarCliente = null;
    PreparedStatement psInsertarCredito = null;
    PreparedStatement psInsertarCuota = null;
    PreparedStatement psPlanPago = null;
    ResultSet rs = null;
    int idCreditoInsertado = -1;

    try {
        conexion = objetoConexion.establecerConexion();
        conexion.setAutoCommit(false);  // Iniciar transacción

        // Buscar el ID del cliente usando el DNI
        String consultaIDCliente = "SELECT id FROM clientes WHERE dni = ?";
        psBuscarCliente = conexion.prepareStatement(consultaIDCliente);
        psBuscarCliente.setString(1, dni_cliente.getText());
        rs = psBuscarCliente.executeQuery();

        if (rs.next()) {
            int id_cliente = rs.getInt("id");

            // Obtener la cantidad de cuotas, el interés y el tipo de plan de pago seleccionado
            String consultaPlanPago = "SELECT cant_cuotas, interes, tipo FROM plan_pago WHERE id_plan_pago = ?";
            psPlanPago = conexion.prepareStatement(consultaPlanPago);
            psPlanPago.setInt(1, id_plan_pago);
            ResultSet rsPlan = psPlanPago.executeQuery();

            if (rsPlan.next()) {
                int cant_cuotas = rsPlan.getInt("cant_cuotas");
                double interes = rsPlan.getDouble("interes");
                String tipo = rsPlan.getString("tipo");

                // Insertar el crédito
                String consultaCreditos = "INSERT INTO credito(id_cliente, observacion, monto, fecha, id_plan_pago) VALUES (?,?,?,?,?)";
                psInsertarCredito = conexion.prepareStatement(consultaCreditos, Statement.RETURN_GENERATED_KEYS);
                psInsertarCredito.setInt(1, id_cliente);
                psInsertarCredito.setString(2, observacion.getText());
                psInsertarCredito.setDouble(3, Double.parseDouble(monto.getText()));
                psInsertarCredito.setString(4, fecha);
                psInsertarCredito.setInt(5, id_plan_pago);
                psInsertarCredito.executeUpdate();

                // Obtener el ID del crédito recién creado
                rs = psInsertarCredito.getGeneratedKeys();
                if (rs.next()) {
                    idCreditoInsertado = rs.getInt(1);

                    // Calcular el importe de cada cuota y aplicar el interés
                    double importeCuotaBase = Double.parseDouble(monto.getText()) / cant_cuotas;
                    double importeCuotaConInteres = importeCuotaBase * (1 + interes / 100);

                    // Insertar las cuotas
                    String consultaCuotas = "INSERT INTO cuotas(id_cliente, id_credito, id_plan_pago, num_cuota, importe_cuota, importe_actualizado, vencimiento, ultimo_mov, pago_realizado) VALUES (?,?,?,?,?,?,?,?,?)";
                    psInsertarCuota = conexion.prepareStatement(consultaCuotas);

                    LocalDate fechaVencimiento = LocalDate.parse(fecha, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                    for (int i = 0; i < cant_cuotas; i++) {
                        psInsertarCuota.setInt(1, id_cliente);
                        psInsertarCuota.setInt(2, idCreditoInsertado);
                        psInsertarCuota.setInt(3, id_plan_pago);
                        psInsertarCuota.setInt(4, i + 1);
                        psInsertarCuota.setDouble(5, importeCuotaConInteres);
                        psInsertarCuota.setDouble(6, importeCuotaConInteres);
                        String fechaVencimientoStr = fechaVencimiento.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                        psInsertarCuota.setString(7, fechaVencimientoStr);
                        psInsertarCuota.setString(8, fechaVencimientoStr);  // Establecer ultimo_mov igual a vencimiento
                        psInsertarCuota.setBoolean(9, tipo.equalsIgnoreCase("primera en el momento") && i == 0);
                        psInsertarCuota.executeUpdate();

                        fechaVencimiento = fechaVencimiento.plusMonths(1);
                    }

                    conexion.commit();  // Confirmar transacción
                    JOptionPane.showMessageDialog(null, "Crédito y cuotas creadas exitosamente");
                }
            } else {
                throw new SQLException("Plan de pago no encontrado");
            }
        } else {
            throw new SQLException("Cliente no encontrado");
        }
    } catch (SQLException e) {
        try {
            if (conexion != null) {
                conexion.rollback();  // Deshacer transacción en caso de error
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        JOptionPane.showMessageDialog(null, "Error al insertar el crédito: " + e.getMessage());
        e.printStackTrace();
    } finally {
        try {
            if (rs != null) {
                rs.close();
            }
            if (psBuscarCliente != null) {
                psBuscarCliente.close();
            }
            if (psInsertarCredito != null) {
                psInsertarCredito.close();
            }
            if (psInsertarCuota != null) {
                psInsertarCuota.close();
            }
            if (psPlanPago != null) {
                psPlanPago.close();
            }
            if (conexion != null) {
                conexion.setAutoCommit(true);  // Restaurar auto-commit
                conexion.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    return idCreditoInsertado;
}

    public void MostrarCliente(JTextField dni_buscar, JTextField dni_cliente, JTextField nombres, JTextField apellidos) {
        Conexion objetoConexion = new Conexion();
        Connection conexion = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conexion = objetoConexion.establecerConexion();
            String sql = "SELECT dni, nombres, apellidos FROM clientes WHERE dni = ?";
            pstmt = conexion.prepareStatement(sql);
            pstmt.setString(1, dni_buscar.getText());
            rs = pstmt.executeQuery();

            if (rs.next()) {
                dni_cliente.setText(rs.getString("dni"));
                nombres.setText(rs.getString("nombres"));
                apellidos.setText(rs.getString("apellidos"));
            } else {
                // Si no se encuentra el cliente, limpiar los campos o mostrar un mensaje.
                dni_cliente.setText("");
                nombres.setText("");
                apellidos.setText("");
                // Puedes usar JOptionPane para notificar que no se encontró el cliente
                javax.swing.JOptionPane.showMessageDialog(null, "Cliente no encontrado");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Puedes mostrar un mensaje de error usando JOptionPane
            javax.swing.JOptionPane.showMessageDialog(null, "Error al buscar cliente: " + e.getMessage());
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

    public void LlenarPlanPagoComboBox(JComboBox<String> planPagoComboBox) {
        Conexion objetoConexion = new Conexion();
        Connection conexion = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conexion = objetoConexion.establecerConexion();
            String sql = "SELECT id_plan_pago, tipo, cant_cuotas FROM plan_pago";
            pstmt = conexion.prepareStatement(sql);
            rs = pstmt.executeQuery();

            planPagoComboBox.removeAllItems();  // Limpia el comboBox antes de agregar los nuevos items
            planPagoMap.clear();  // Limpia el mapa

            while (rs.next()) {
                int id = rs.getInt("id_plan_pago");
                String descripcion = rs.getInt("cant_cuotas") + " cuotas " + " - " + rs.getString("tipo");
                planPagoComboBox.addItem(id + " - " + descripcion);
                planPagoMap.put(descripcion, id);  // Almacena la descripción con su respectivo id
            }

        } catch (SQLException e) {
            e.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(null, "Error al llenar el comboBox: " + e.getMessage());
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

    public int obtenerIdPlanPagoSeleccionado(String planPagoSeleccionado) {
        Conexion conn = new Conexion();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int idPlanPago = -1;

        try {
            con = conn.establecerConexion();
            String sql = "SELECT id_plan_pago FROM plan_pago WHERE (tipo || ' ' || cant_cuotas || ' - ' || descripcion) =?";

            ps = con.prepareStatement(sql);
            ps.setString(1, planPagoSeleccionado);
            rs = ps.executeQuery();

            if (rs.next()) {
                idPlanPago = rs.getInt("id_plan_pago");
            }
        } catch (Exception e) {
            System.err.println("Error al obtener el ID del plan de pago: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                System.err.println("Error al cerrar los recursos: " + e.getMessage());
            }
        }
        return idPlanPago;
    }

}
