/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.sistemacreditos;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class DetalleCliente extends javax.swing.JFrame {

    private Conexion conexion;
    public int id_cliente;
    String user_update = "";

    public DetalleCliente() {
        initComponentsCustom();
        conexion = new Conexion(); // Inicializa el objeto conexion
        user_update = Clientes.user_update;
        ImageIcon icon = new ImageIcon("icono.png"); // Cambia la ruta según tu estructura de proyecto
        setIconImage(icon.getImage());
        cargarDatosCliente();
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

    }

    private void initComponentsCustom() {
        setTitle("Editar Cliente");
        getContentPane().setBackground(new Color(240, 240, 240));  // Fondo del formulario
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);  // Espacios entre componentes
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Panel para el título
        JLabel titleLabel = new JLabel("EDITAR CLIENTE");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setForeground(new Color(0, 102, 204));

        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(titleLabel, gbc);

        // Primer fila: DNI y Email
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("DNI:"), gbc);
        gbc.gridx = 1;
        dniTxt = new JTextField(20);
        add(dniTxt, gbc);

        gbc.gridx = 2;
        add(new JLabel("Email:"), gbc);
        gbc.gridx = 3;
        emailTxt = new JTextField(20);
        add(emailTxt, gbc);

        // Segunda fila: Nombres y Teléfono 1
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(new JLabel("Nombres:"), gbc);
        gbc.gridx = 1;
        nombresTxt = new JTextField(20);
        add(nombresTxt, gbc);

        gbc.gridx = 2;
        add(new JLabel("Teléfono 1:"), gbc);
        gbc.gridx = 3;
        JPanel phone1Panel = new JPanel(new BorderLayout());
        cod1Txt = new JTextField(3);
        tel1Txt = new JTextField(15);
        phone1Panel.add(cod1Txt, BorderLayout.WEST);
        phone1Panel.add(tel1Txt, BorderLayout.CENTER);
        add(phone1Panel, gbc);

        // Tercera fila: Apellido y Teléfono 2
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(new JLabel("Apellido:"), gbc);
        gbc.gridx = 1;
        apellidoTxt = new JTextField(20);
        add(apellidoTxt, gbc);

        gbc.gridx = 2;
        add(new JLabel("Teléfono 2:"), gbc);
        gbc.gridx = 3;
        JPanel phone2Panel = new JPanel(new BorderLayout());
        cod2Txt = new JTextField(3);
        tel2Txt = new JTextField(15);
        phone2Panel.add(cod2Txt, BorderLayout.WEST);
        phone2Panel.add(tel2Txt, BorderLayout.CENTER);
        add(phone2Panel, gbc);

        // Cuarta fila: Localidad y Teléfono 3
        gbc.gridx = 0;
        gbc.gridy = 4;
        add(new JLabel("Localidad:"), gbc);
        gbc.gridx = 1;
        localidadTxt = new JTextField(20);
        add(localidadTxt, gbc);

        gbc.gridx = 2;
        add(new JLabel("Teléfono 3:"), gbc);
        gbc.gridx = 3;
        JPanel phone3Panel = new JPanel(new BorderLayout());
        cod3Txt = new JTextField(3);
        tel3Txt = new JTextField(15);
        phone3Panel.add(cod3Txt, BorderLayout.WEST);
        phone3Panel.add(tel3Txt, BorderLayout.CENTER);
        add(phone3Panel, gbc);

        // Quinta fila: Barrio y Calle
        gbc.gridx = 0;
        gbc.gridy = 5;
        add(new JLabel("Barrio:"), gbc);
        gbc.gridx = 1;
        barrioTxt = new JTextField(20);
        add(barrioTxt, gbc);

        gbc.gridx = 2;
        add(new JLabel("Calle:"), gbc);
        gbc.gridx = 3;
        calleTxt = new JTextField(20);
        add(calleTxt, gbc);

        // Sexta fila: Número y Entre Calles
        gbc.gridx = 0;
        gbc.gridy = 6;
        add(new JLabel("Número:"), gbc);
        gbc.gridx = 1;
        numeroTxt = new JTextField(20);
        add(numeroTxt, gbc);

        gbc.gridx = 2;
        add(new JLabel("Entre Calles:"), gbc);
        gbc.gridx = 3;
        entreCallesTxt = new JTextField(20);
        add(entreCallesTxt, gbc);

        // Botones de acción
        gbc.gridwidth = 1;
        gbc.gridx = 2;
        gbc.gridy = 7;
        actualizarBtn = createStyledButton("ACTUALIZAR");
        add(actualizarBtn, gbc);
        actualizarBtn.addActionListener(evt -> actualizarBtnActionPerformed(evt));

        gbc.gridx = 3;
        historicoBtn = createStyledButton("HISTORICO");
        add(historicoBtn, gbc);
        historicoBtn.addActionListener(evt -> historicoBtnActionPerformed(evt));

        pack();  // Ajusta la ventana al contenido
        setLocationRelativeTo(null);  // Centra la ventana
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

    private void cargarDatosCliente() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = conexion.establecerConexion();
            String sql = "SELECT * FROM clientes WHERE dni = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, user_update);

            rs = pstmt.executeQuery();

            if (rs.next()) {
                id_cliente = rs.getInt("id");
                dniTxt.setText(rs.getString("dni"));
                nombresTxt.setText(rs.getString("nombres"));
                apellidoTxt.setText(rs.getString("apellidos"));
                localidadTxt.setText(rs.getString("localidad"));
                barrioTxt.setText(rs.getString("barrio"));
                calleTxt.setText(rs.getString("calle"));
                numeroTxt.setText(rs.getString("numero"));
                entreCallesTxt.setText(rs.getString("entre_calles"));
                emailTxt.setText(rs.getString("email"));

                cargarTelefonos(id_cliente);
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró ningún cliente con el DNI proporcionado.");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al buscar el cliente: " + e.toString());
        } finally {
            cerrarRecursos(rs, pstmt, conn);
        }
    }

    private void cerrarRecursos(ResultSet rs, PreparedStatement pstmt, Connection conn) {
        try {
            if (rs != null) {
                rs.close();
            }
            if (pstmt != null) {
                pstmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al cerrar la conexión: " + e.toString());
        }
    }

    private void cargarTelefonos(int id_cliente) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = conexion.establecerConexion();
            String sql = "SELECT * FROM telefonos WHERE id_cliente = ? ORDER BY id_telefono LIMIT 3";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id_cliente);

            rs = pstmt.executeQuery();

            int count = 0;
            while (rs.next() && count < 3) {
                switch (count) {
                    case 0:
                        cod1Txt.setText(rs.getString("cod_area"));
                        tel1Txt.setText(rs.getString("numero"));
                        break;
                    case 1:
                        cod2Txt.setText(rs.getString("cod_area"));
                        tel2Txt.setText(rs.getString("numero"));
                        break;
                    case 2:
                        cod3Txt.setText(rs.getString("cod_area"));
                        tel3Txt.setText(rs.getString("numero"));
                        break;
                }
                count++;
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al cargar los teléfonos: " + e.toString());
        } finally {
            cerrarRecursos(rs, pstmt, conn);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        actualizarBtn = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        dniTxt = new javax.swing.JTextField();
        nombresTxt = new javax.swing.JTextField();
        apellidoTxt = new javax.swing.JTextField();
        localidadTxt = new javax.swing.JTextField();
        barrioTxt = new javax.swing.JTextField();
        calleTxt = new javax.swing.JTextField();
        numeroTxt = new javax.swing.JTextField();
        entreCallesTxt = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        emailTxt = new javax.swing.JTextField();
        cod1Txt = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        cod2Txt = new javax.swing.JTextField();
        cod3Txt = new javax.swing.JTextField();
        tel1Txt = new javax.swing.JTextField();
        tel2Txt = new javax.swing.JTextField();
        tel3Txt = new javax.swing.JTextField();
        historicoBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        actualizarBtn.setText("ACTUALIZAR");
        actualizarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actualizarBtnActionPerformed(evt);
            }
        });

        jLabel1.setText("DNI:");

        jLabel2.setText("NOMBRES:");

        jLabel3.setText("APELLIDO:");

        jLabel4.setText("LOCALIDAD:");

        jLabel5.setText("BARRIO:");

        jLabel6.setText("CALLE:");

        jLabel7.setText("NUMERO:");

        jLabel8.setText("ENTRE CALLES:");

        dniTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dniTxtActionPerformed(evt);
            }
        });

        jLabel9.setText("EMAIL:");

        jLabel10.setText("TELEFONO 1:");

        jLabel11.setText("TELEFONO 2:");

        jLabel12.setText("TELEFONO 3:");

        jLabel13.setText("COD AREA:");

        jLabel14.setText("COD AREA:");

        jLabel15.setText("COD AREA:");

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(0, 102, 204));
        jLabel16.setText("EDITAR CLIENTE");

        historicoBtn.setText("HISTORICO");
        historicoBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                historicoBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(entreCallesTxt, javax.swing.GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel7))
                                .addGap(23, 23, 23)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(localidadTxt)
                                    .addComponent(barrioTxt)
                                    .addComponent(calleTxt)
                                    .addComponent(numeroTxt, javax.swing.GroupLayout.DEFAULT_SIZE, 239, Short.MAX_VALUE)
                                    .addComponent(dniTxt)
                                    .addComponent(nombresTxt)
                                    .addComponent(apellidoTxt))))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(94, 94, 94)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel13)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(cod1Txt, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel10)
                                                .addGap(18, 18, 18)
                                                .addComponent(tel1Txt))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel12)
                                                .addGap(18, 18, 18)
                                                .addComponent(tel3Txt))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel11)
                                                .addGap(18, 18, 18)
                                                .addComponent(tel2Txt))))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel15)
                                            .addComponent(jLabel14)
                                            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING))
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(10, 10, 10)
                                                .addComponent(emailTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 337, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addGroup(layout.createSequentialGroup()
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(cod3Txt, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                    .addGap(12, 12, 12)
                                                    .addComponent(cod2Txt, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(105, 105, 105)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(actualizarBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
                                    .addComponent(historicoBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(331, 331, 331)
                        .addComponent(jLabel16)))
                .addContainerGap(33, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel16)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(dniTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(emailTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(nombresTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13)
                    .addComponent(jLabel10)
                    .addComponent(cod1Txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tel1Txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(apellidoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addComponent(jLabel11)
                    .addComponent(cod2Txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tel2Txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(localidadTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15)
                    .addComponent(jLabel12)
                    .addComponent(cod3Txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tel3Txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(barrioTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(calleTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(actualizarBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(numeroTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(entreCallesTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(historicoBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(61, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void actualizarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_actualizarBtnActionPerformed

        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = conexion.establecerConexion();
            conn.setAutoCommit(false);

            // Verificar si el DNI ha cambiado
            String newDni = dniTxt.getText().trim();
            if (!newDni.equals(user_update)) {
                // Verificar si el nuevo DNI ya existe
                if (dniExiste(conn, newDni)) {
                    JOptionPane.showMessageDialog(null, "El nuevo DNI ya existe en la base de datos.");
                    return;
                }
            }

            // Actualizar datos del cliente
            String sqlCliente = "UPDATE clientes SET dni=?, nombres=?, apellidos=?, localidad=?, barrio=?, calle=?, numero=?, "
                    + "entre_calles=?, email=? WHERE id=?";
            pstmt = conn.prepareStatement(sqlCliente);
            pstmt.setString(1, newDni);
            pstmt.setString(2, nombresTxt.getText());
            pstmt.setString(3, apellidoTxt.getText());
            pstmt.setString(4, localidadTxt.getText());
            pstmt.setString(5, barrioTxt.getText());
            pstmt.setString(6, calleTxt.getText());
            pstmt.setString(7, numeroTxt.getText());
            pstmt.setString(8, entreCallesTxt.getText());
            pstmt.setString(9, emailTxt.getText());
            pstmt.setInt(10, id_cliente);

            int filasAfectadas = pstmt.executeUpdate();

            if (filasAfectadas > 0) {
                // Actualizar teléfonos
                actualizarTelefonos(conn, id_cliente);

                conn.commit();
                JOptionPane.showMessageDialog(null, "Cliente actualizado correctamente.");

                // Actualizar el user_update si el DNI ha cambiado
                if (!newDni.equals(user_update)) {
                    user_update = newDni;
                }
            } else {
                throw new Exception("No se pudo actualizar el cliente. Verifique el ID del cliente.");
            }
        } catch (Exception e) {
            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error al hacer rollback: " + ex.toString());
            }
            JOptionPane.showMessageDialog(null, "Error al actualizar el cliente: " + e.toString());
        } finally {
            cerrarRecursos(null, pstmt, conn);
        }

    }//GEN-LAST:event_actualizarBtnActionPerformed

    private boolean dniExiste(Connection conn, String dni) throws Exception {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT COUNT(*) FROM clientes WHERE dni = ? AND id != ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, dni);
            pstmt.setInt(2, id_cliente);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
            return false;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pstmt != null) {
                pstmt.close();
            }
        }
    }

    private void actualizarTelefonos(Connection conn, int id_cliente) throws Exception {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // Obtener los teléfonos existentes
            String sqlSelect = "SELECT id_telefono FROM telefonos WHERE id_cliente = ? ORDER BY id_telefono LIMIT 3";
            pstmt = conn.prepareStatement(sqlSelect);
            pstmt.setInt(1, id_cliente);
            rs = pstmt.executeQuery();

            List<Integer> idTelefonos = new ArrayList<>();
            while (rs.next()) {
                idTelefonos.add(rs.getInt("id_telefono"));
            }

            // Actualizar o insertar teléfonos
            String sqlUpsert = "INSERT OR REPLACE INTO telefonos (id_telefono, id_cliente, cod_area, numero) VALUES (?, ?, ?, ?)";
            pstmt = conn.prepareStatement(sqlUpsert);

            actualizarTelefono(pstmt, idTelefonos, 0, id_cliente, cod1Txt.getText(), tel1Txt.getText());
            actualizarTelefono(pstmt, idTelefonos, 1, id_cliente, cod2Txt.getText(), tel2Txt.getText());
            actualizarTelefono(pstmt, idTelefonos, 2, id_cliente, cod3Txt.getText(), tel3Txt.getText());

        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pstmt != null) {
                pstmt.close();
            }
        }
    }

    private void actualizarTelefono(PreparedStatement pstmt, List<Integer> idTelefonos, int index, int id_cliente, String codArea, String numero) throws Exception {
        if (!codArea.isEmpty() && !numero.isEmpty()) {
            pstmt.setInt(1, index < idTelefonos.size() ? idTelefonos.get(index) : 0); // 0 para nuevo registro
            pstmt.setInt(2, id_cliente);
            pstmt.setString(3, codArea);
            pstmt.setString(4, numero);
            pstmt.executeUpdate();
        }
    }

    private void dniTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dniTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dniTxtActionPerformed

    private void historicoBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_historicoBtnActionPerformed
        FormHistorico formHistorico = new FormHistorico();
        formHistorico.setVisible(true);
    }//GEN-LAST:event_historicoBtnActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DetalleCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DetalleCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DetalleCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DetalleCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DetalleCliente().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton actualizarBtn;
    private javax.swing.JTextField apellidoTxt;
    private javax.swing.JTextField barrioTxt;
    private javax.swing.JTextField calleTxt;
    private javax.swing.JTextField cod1Txt;
    private javax.swing.JTextField cod2Txt;
    private javax.swing.JTextField cod3Txt;
    private javax.swing.JTextField dniTxt;
    private javax.swing.JTextField emailTxt;
    private javax.swing.JTextField entreCallesTxt;
    private javax.swing.JButton historicoBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField localidadTxt;
    private javax.swing.JTextField nombresTxt;
    private javax.swing.JTextField numeroTxt;
    private javax.swing.JTextField tel1Txt;
    private javax.swing.JTextField tel2Txt;
    private javax.swing.JTextField tel3Txt;
    // End of variables declaration//GEN-END:variables
}
