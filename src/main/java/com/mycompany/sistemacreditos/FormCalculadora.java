/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.sistemacreditos;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class FormCalculadora extends javax.swing.JFrame {
    private JLabel logoLabel;

    public FormCalculadora() {
        initComponentsCustom();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        Creditos objetoCreditos = new Creditos();
        objetoCreditos.LlenarPlanPagoComboBox(planPagosComboBox);
        ImageIcon icon = new ImageIcon("icono.png"); // Cambia la ruta según tu estructura de proyecto
        setIconImage(icon.getImage());
    }
    
private void initComponentsCustom() {
    jLabel1 = new JLabel();
    planPagosComboBox = new JComboBox<>();
    jLabel2 = new JLabel();
    jLabel3 = new JLabel();
    importeTxt = new JTextField(15); // Ancho más amplio
    jLabel4 = new JLabel();
    importeCuotaTxt = new JTextField(15); // Ancho más amplio
    logoLabel = new JLabel();

    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    getContentPane().setBackground(new Color(240, 240, 240));
    
    // Crear un JPanel con un borde y agregarlo al JFrame
    JPanel panel = new JPanel();
    panel.setLayout(new GridBagLayout());
    panel.setBackground(new Color(240, 240, 240));
    panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30)); // Espacio de 30px en todos los bordes

    // Configuración de la etiqueta principal
    jLabel1.setFont(new Font("Segoe UI", Font.BOLD, 24));
    jLabel1.setForeground(new Color(0, 102, 204));
    jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
    jLabel1.setText("CALCULADORA DE CREDITOS");

    // Configuración del logo
    logoLabel.setIcon(new ImageIcon("logo.png")); // Asegúrate de que la ruta sea correcta

    // Usar GridBagLayout para agregar componentes
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.insets = new Insets(10, 10, 10, 10); // Espaciado entre componentes
    gbc.fill = GridBagConstraints.HORIZONTAL; // Alinear horizontalmente

    // Añadir logo centrado
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.gridwidth = 2; // El logo ocupa 2 columnas
    gbc.anchor = GridBagConstraints.CENTER; // Centrar el logo
    panel.add(logoLabel, gbc);

    // Título
    gbc.gridx = 0;
    gbc.gridy = 1;
    gbc.gridwidth = 2; // Centrar el título
    gbc.anchor = GridBagConstraints.CENTER; // Centrar el texto
    panel.add(jLabel1, gbc);

    // Etiqueta y campo de texto para importe
    jLabel2.setText("Importe:");
    gbc.gridx = 0;
    gbc.gridy = 2;
    gbc.gridwidth = 1; // Usar 1 columna
    gbc.anchor = GridBagConstraints.WEST; // Alinear a la izquierda
    panel.add(jLabel2, gbc);

    gbc.gridx = 1;
    gbc.gridy = 2; 
    panel.add(importeTxt, gbc);

    // Etiqueta y comboBox para plan de pagos
    jLabel3.setText("Plan de Pagos:");
    gbc.gridx = 0;
    gbc.gridy = 3;
    panel.add(jLabel3, gbc);

    gbc.gridx = 1;
    gbc.gridy = 3;
    panel.add(planPagosComboBox, gbc);

    // Etiqueta y campo de texto para importe de cuota
    jLabel4.setText("Importe de Cuota:");
    gbc.gridx = 0;
    gbc.gridy = 4;
    panel.add(jLabel4, gbc);

    gbc.gridx = 1;
    gbc.gridy = 4; 
    panel.add(importeCuotaTxt, gbc);

    // Botón calcular usando el método createStyledButton
    calcularBtn = createStyledButton("CALCULAR"); // Usar el método para crear el botón
    gbc.gridx = 1;
    gbc.gridy = 5;
    gbc.anchor = GridBagConstraints.EAST; // Alinear el botón a la derecha
    panel.add(calcularBtn, gbc);

    // Agregar el panel al JFrame
    add(panel);
    
    // Ajustar el tamaño de la ventana
    pack();
    setLocationRelativeTo(null); // Centrar la ventana en la pantalla
}

private JButton createStyledButton(String text) {
    JButton button = new JButton(text);
    button.setBackground(new java.awt.Color(30, 144, 255));
    button.setForeground(Color.WHITE);
    button.setFocusPainted(false);
    button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
    button.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseEntered(java.awt.event.MouseEvent evt) {
            button.setBackground(new java.awt.Color(0, 105, 217));
            button.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        }

        public void mouseExited(java.awt.event.MouseEvent evt) {
            button.setBackground(new java.awt.Color(30, 144, 255));
            button.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        }
    });
    return button;
}




    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        planPagosComboBox = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        importeTxt = new javax.swing.JTextField();
        calcularBtn = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        importeCuotaTxt = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("CALCULADORA DE CREDITOS");

        planPagosComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel2.setText("PLAN DE PAGOS:");

        jLabel3.setText("IMPORTE:");

        calcularBtn.setText("CALCULAR");
        calcularBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                calcularBtnActionPerformed(evt);
            }
        });

        jLabel4.setText("IMPORTE DE CUOTA:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(planPagosComboBox, 0, 193, Short.MAX_VALUE)
                    .addComponent(importeTxt)
                    .addComponent(calcularBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(importeCuotaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(96, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(110, 110, 110))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(jLabel1)
                .addGap(44, 44, 44)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(planPagosComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(importeTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addComponent(calcularBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(importeCuotaTxt, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(69, 69, 69))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void calcularCuota(java.awt.event.ActionEvent evt) {
        Conexion conexion = new Conexion(); // Instancia de tu clase de conexión
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            // Obtén el importe ingresado
            double importe = Double.parseDouble(importeTxt.getText());

            // Obtén el plan de pago seleccionado
            String planSeleccionado = (String) planPagosComboBox.getSelectedItem();

            if (planSeleccionado != null && !planSeleccionado.isEmpty()) {
                // Separa el ID del plan de la descripción (asumiendo que el ID está antes del guion)
                String[] partesPlan = planSeleccionado.split(" - ");
                int idPlanPago = Integer.parseInt(partesPlan[0]);

                // Consulta a la base de datos para obtener el interés y la cantidad de cuotas del plan
                String sql = "SELECT interes, cant_cuotas FROM plan_pago WHERE id_plan_pago = ?";
                conn = conexion.establecerConexion(); // Conectar a la base de datos
                stmt = conn.prepareStatement(sql);
                stmt.setInt(1, idPlanPago); // Pasar el ID del plan de pago
                rs = stmt.executeQuery();

                if (rs.next()) {
                    double interes = rs.getDouble("interes");
                    int cantCuotas = rs.getInt("cant_cuotas");

                    // Calcular el importe con el interés aplicado
                    double importeConInteres = importe * (1 + (interes / 100));

                    // Calcular el importe por cuota
                    double importePorCuota = importeConInteres / cantCuotas;

                    // Mostrar el importe de la cuota en el campo correspondiente
                    importeCuotaTxt.setText(String.format("%.2f", importePorCuota));
                } else {
                    JOptionPane.showMessageDialog(this, "No se encontró el plan de pago en la base de datos.");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Seleccione un plan de pago válido.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Ingrese un importe válido.");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al consultar la base de datos: " + e.getMessage());
        } finally {
            // Cerrar los recursos
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    private void calcularBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_calcularBtnActionPerformed
        calcularCuota(evt);
    }//GEN-LAST:event_calcularBtnActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormCalculadora().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton calcularBtn;
    private javax.swing.JTextField importeCuotaTxt;
    private javax.swing.JTextField importeTxt;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JComboBox<String> planPagosComboBox;
    // End of variables declaration//GEN-END:variables
}
