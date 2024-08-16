package com.mycompany.sistemacreditos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;

public class Creditos {
    public void InsertarCredito(JTextField dni_cliente, JTextPane observacion, JTextField monto, String fecha) {
        Conexion objetoConexion = new Conexion();
        String consultaIDCliente = "SELECT id FROM clientes WHERE dni = ?";
        String consultaCreditos = "INSERT INTO credito(id_cliente, observacion, monto, fecha) VALUES (?,?,?,?)";
        
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
                psInsertarCredito.execute();
                
                JOptionPane.showMessageDialog(null, "Crédito creado exitosamente");
            } else {
                JOptionPane.showMessageDialog(null, "Cliente no encontrado");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se pudo guardar el crédito: " + e.toString());
        } finally {
            try {
                if (rs != null) rs.close();
                if (psBuscarCliente != null) psBuscarCliente.close();
                if (psInsertarCredito != null) psInsertarCredito.close();
                if (conexion != null) conexion.close();
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
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conexion != null) conexion.close();
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
            String sql = "SELECT tipo, cant_cuotas FROM plan_pago";
            pstmt = conexion.prepareStatement(sql);
            rs = pstmt.executeQuery();

            planPagoComboBox.removeAllItems();  // Limpia el comboBox antes de agregar los nuevos items

            while (rs.next()) {
                String item = rs.getInt("cant_cuotas")+ " cuotas " + " - " + rs.getString("tipo");
                planPagoComboBox.addItem(item);
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
}
