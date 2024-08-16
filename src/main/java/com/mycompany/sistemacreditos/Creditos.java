package com.mycompany.sistemacreditos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;

public class Creditos {

    private Map<String, Integer> planPagoMap = new HashMap<>();

    public void InsertarCredito(JTextField dni_cliente, JTextPane observacion, JTextField monto, String fecha, int id_plan_pago) {
        Conexion objetoConexion = new Conexion();
        String consultaIDCliente = "SELECT id FROM clientes WHERE dni = ?";
        String consultaCreditos = "INSERT INTO credito(id_cliente, observacion, monto, fecha, id_plan_pago) VALUES (?,?,?,?,?)";

        Connection conexion = null;
        PreparedStatement psBuscarCliente = null;
        PreparedStatement psInsertarCredito = null;
        ResultSet rs = null;

        try {
            // Establecer la conexión
            conexion = objetoConexion.establecerConexion();

            // Buscar el ID del cliente usando el DNI
            psBuscarCliente = conexion.prepareStatement(consultaIDCliente);
            psBuscarCliente.setString(1, dni_cliente.getText());
            rs = psBuscarCliente.executeQuery();

            if (rs.next()) {
                int id_cliente = rs.getInt("id");

                // Insertar el crédito usando el ID del cliente
                psInsertarCredito = conexion.prepareStatement(consultaCreditos);
                psInsertarCredito.setInt(1, id_cliente);
                psInsertarCredito.setString(2, observacion.getText());
                psInsertarCredito.setString(3, monto.getText());
                psInsertarCredito.setString(4, fecha);
                psInsertarCredito.setInt(5, id_plan_pago);
                psInsertarCredito.execute();

                JOptionPane.showMessageDialog(null, "Crédito creado exitosamente");
            } else {
                JOptionPane.showMessageDialog(null, "Cliente no encontrado");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se pudo guardar el crédito: " + e.toString());
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
                if (conexion != null) {
                    conexion.close();
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error al cerrar recursos: " + e.toString());
            }
        }
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
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (con != null) con.close();
        } catch (Exception e) {
            System.err.println("Error al cerrar los recursos: " + e.getMessage());
        }
    }
    return idPlanPago;
}

}
