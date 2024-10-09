
package com.mycompany.sistemacreditos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

public class Login {
        public static boolean login(String usuario, String password) {
        Conexion objetoConexion = new Conexion();
        Connection conexion = objetoConexion.establecerConexion();
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // Consulta para verificar usuario y contraseña
            String sql = "SELECT * FROM usuarios WHERE usuario = ? AND contraseña = ?";
            pstmt = conexion.prepareStatement(sql);
            pstmt.setString(1, usuario);
            pstmt.setString(2, password);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                // Si las credenciales son correctas
                return true;
            } else {
                // Si las credenciales son incorrectas
                JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos.");
                return false;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al realizar el login: " + e.getMessage());
            return false;
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                objetoConexion.cerrarConexion(); // Cerrar conexión aquí
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
