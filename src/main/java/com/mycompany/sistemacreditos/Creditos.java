package com.mycompany.sistemacreditos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
}
