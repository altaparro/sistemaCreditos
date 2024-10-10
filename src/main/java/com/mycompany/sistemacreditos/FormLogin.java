package com.mycompany.sistemacreditos;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class FormLogin extends javax.swing.JFrame {

    public FormLogin() {
        initComponentsCustom();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(500, 500);
    }

    private void initComponentsCustom() {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sistema de Créditos - Deportes 7");
        getContentPane().setBackground(new java.awt.Color(240, 240, 240));
        setLayout(new java.awt.GridBagLayout());

        // Panel para el logo y el título
        JPanel titlePanel = new JPanel(new GridBagLayout());
        titlePanel.setOpaque(false);

        // Añadir logo
        ImageIcon logoIcon = new ImageIcon("logo.png");
        Image scaledImage = logoIcon.getImage().getScaledInstance(283, 66, Image.SCALE_SMOOTH);
        JLabel logoLabel = new JLabel(new ImageIcon(scaledImage));
        titlePanel.add(logoLabel, createGridBagConstraints(0, 0, 1, 1, GridBagConstraints.CENTER));

        // Configuración del título
        JLabel titleLabel = new JLabel("SISTEMA DE CRÉDITOS");
        titleLabel.setFont(new java.awt.Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setForeground(new java.awt.Color(0, 102, 204));
        titlePanel.add(titleLabel, createGridBagConstraints(0, 1, 1, 1, GridBagConstraints.CENTER));

        add(titlePanel, createGridBagConstraints(0, 0, 3, 1, GridBagConstraints.CENTER));

        // Etiquetas y campos de texto
        JLabel usuarioLabel = new JLabel("USUARIO:");
        usuarioLabel.setFont(new java.awt.Font("Segoe UI", Font.BOLD, 18));
        add(usuarioLabel, createGridBagConstraints(0, 1, 1, 1, GridBagConstraints.EAST));

        usuarioTxt = new javax.swing.JTextField(15);
        usuarioTxt.setPreferredSize(new java.awt.Dimension(250, 30));
        add(usuarioTxt, createGridBagConstraints(1, 1, 2, 1, GridBagConstraints.WEST));

        JLabel passwordLabel = new JLabel("CONTRASEÑA:");
        passwordLabel.setFont(new java.awt.Font("Segoe UI", Font.BOLD, 18));
        add(passwordLabel, createGridBagConstraints(0, 2, 1, 1, GridBagConstraints.EAST));

        passwordTxt = new javax.swing.JPasswordField(15);
        passwordTxt.setPreferredSize(new java.awt.Dimension(250, 30));
        add(passwordTxt, createGridBagConstraints(1, 2, 2, 1, GridBagConstraints.WEST));

        // Botón de ingreso
        JButton ingresarBtn = createStyledButton("INGRESAR");
        ingresarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ingresarBtnActionPerformed(evt);
            }
        });
        add(ingresarBtn, createGridBagConstraints(1, 3, 1, 1, GridBagConstraints.CENTER));

        pack();
        setLocationRelativeTo(null);
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                int width = getWidth();
                int height = getHeight();

                // Crear gradiente para el fondo
                Color topColor = new Color(30, 144, 255);
                Color bottomColor = new Color(0, 91, 234);

                if (getModel().isPressed()) {
                    topColor = new Color(0, 114, 255);
                    bottomColor = new Color(0, 71, 214);
                } else if (getModel().isRollover()) {
                    topColor = new Color(60, 174, 255);
                    bottomColor = new Color(30, 121, 254);
                }

                GradientPaint gradient = new GradientPaint(0, 0, topColor, 0, height, bottomColor);
                g2.setPaint(gradient);
                g2.fillRoundRect(0, 0, width, height, 10, 10);

                // Agregar brillo en la parte superior
                g2.setPaint(new Color(255, 255, 255, 50));
                g2.fillRoundRect(0, 0, width, height / 2, 10, 10);

                // Dibujar borde
                g2.setColor(new Color(0, 0, 0, 50));
                g2.drawRoundRect(0, 0, width - 1, height - 1, 10, 10);

                // Agregar sombra en la parte inferior
                g2.setPaint(new Color(0, 0, 0, 30));
                g2.fillRoundRect(0, height - 5, width, 5, 10, 10);

                g2.dispose();

                super.paintComponent(g);
            }
        };

        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setForeground(Color.WHITE);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setPreferredSize(new Dimension(160, 40));

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.getModel().setRollover(true);
                button.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.getModel().setRollover(false);
                button.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });

        return button;
    }

    private GridBagConstraints createGridBagConstraints(int gridx, int gridy, int gridwidth, int gridheight, int anchor) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = gridx;
        gbc.gridy = gridy;
        gbc.gridwidth = gridwidth;
        gbc.gridheight = gridheight;
        gbc.anchor = anchor;
        gbc.insets = new java.awt.Insets(10, 10, 10, 10);
        return gbc;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ingresarBtn = new javax.swing.JButton();
        usuarioTxt = new javax.swing.JTextField();
        passwordTxt = new javax.swing.JPasswordField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ingresarBtn.setText("INGRESAR");
        ingresarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ingresarBtnActionPerformed(evt);
            }
        });
        getContentPane().add(ingresarBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 290, 100, 60));
        getContentPane().add(usuarioTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 160, 140, -1));
        getContentPane().add(passwordTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 220, 140, -1));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("USUARIO:");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 160, -1, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setText("CONTRASEÑA:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 220, -1, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel3.setText("SISTEMA CREDITOS - DEPORTES 7");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ingresarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ingresarBtnActionPerformed
        // Obtener el usuario y la contraseña
        String usuario = usuarioTxt.getText();
        String password = new String(passwordTxt.getPassword());

        // Llamar a la función login de la clase Login
        if (Login.login(usuario, password)) {
            // Si el login es exitoso, abrir el FormPrincipal
            ActualizarCuotas actualizarCuotas = new ActualizarCuotas();
            actualizarCuotas.actualizarCuotasVencidas();
            System.out.println("cuotas actualizadas");
            new FormPrincipal().setVisible(true);
            this.dispose(); // Cerrar el formulario de login
        }
    }//GEN-LAST:event_ingresarBtnActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormLogin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ingresarBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPasswordField passwordTxt;
    private javax.swing.JTextField usuarioTxt;
    // End of variables declaration//GEN-END:variables
}
