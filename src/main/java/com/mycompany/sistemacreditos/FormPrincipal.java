package com.mycompany.sistemacreditos;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;

public class FormPrincipal extends javax.swing.JFrame {

    public FormPrincipal() {
        initComponentsCustom();
        setSize(800, 480);
        setResizable(false);
        setLocationRelativeTo(null);
    }

    private void initComponentsCustom() {
        // Configuración de la ventana principal
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

        // Array de botones y sus textos
        String[] buttonTexts = {"CREAR CLIENTE", "COBRAR CUOTA", "LISTADO CLIENTES",
            "GENERAR CRÉDITO", "CALCULADORA", "DEUDORES"};
        JButton[] buttons = new JButton[buttonTexts.length];

        // Crear y configurar botones
        for (int i = 0; i < buttonTexts.length; i++) {
            buttons[i] = createStyledButton(buttonTexts[i]);
            int row = 1 + (i / 3);
            int col = i % 3;
            add(buttons[i], createGridBagConstraints(col, row, 1, 1, GridBagConstraints.CENTER));
        }

        // Botón Salir
        JButton salirBtn = createStyledButton("SALIR");
        salirBtn.setBackground(new java.awt.Color(255, 99, 71));
        add(salirBtn, createGridBagConstraints(1, 3, 1, 1, GridBagConstraints.CENTER));

        // Añadir ActionListeners a los botones (mantén los existentes)
        buttons[0].addActionListener(evt -> crearClienteBtnActionPerformed(evt));
        buttons[1].addActionListener(evt -> cobrarCuotaBtnActionPerformed(evt));
        buttons[2].addActionListener(evt -> listadoClientesBtnActionPerformed(evt));
        buttons[3].addActionListener(evt -> generarCreditoBtnActionPerformed(evt));
        buttons[4].addActionListener(evt -> calculadoraBtnActionPerformed(evt));
        buttons[5].addActionListener(evt -> deudoresBtnActionPerformed(evt));
        salirBtn.addActionListener(evt -> salirBtnActionPerformed(evt));

        pack();
        setLocationRelativeTo(null);
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new java.awt.Font("Segoe UI", Font.BOLD, 14));
        button.setForeground(java.awt.Color.WHITE);
        button.setBackground(text.equals("SALIR") ? new java.awt.Color(255, 99, 71) : new java.awt.Color(0, 123, 255));
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setPreferredSize(new java.awt.Dimension(180, 60));

        // Add hover effect
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new java.awt.Color(0, 105, 217));
                button.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(text.equals("SALIR") ? new java.awt.Color(255, 99, 71) : new java.awt.Color(0, 123, 255));
                button.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
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

        crearClienteBtn = new javax.swing.JButton();
        cobrarCuotaBtn = new javax.swing.JButton();
        listadoClientesBtn = new javax.swing.JButton();
        generarCreditoBtn = new javax.swing.JButton();
        calculadoraBtn = new javax.swing.JButton();
        salirBtn = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        deudoresBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAutoRequestFocus(false);
        setBackground(new java.awt.Color(51, 0, 204));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        crearClienteBtn.setText("CREAR CLIENTE");
        crearClienteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                crearClienteBtnActionPerformed(evt);
            }
        });
        getContentPane().add(crearClienteBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 126, 148, 72));

        cobrarCuotaBtn.setText("COBRAR CUOTA");
        cobrarCuotaBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cobrarCuotaBtnActionPerformed(evt);
            }
        });
        getContentPane().add(cobrarCuotaBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 123, 160, 80));

        listadoClientesBtn.setText("LISTADO CLIENTES");
        listadoClientesBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                listadoClientesBtnActionPerformed(evt);
            }
        });
        getContentPane().add(listadoClientesBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 240, 148, 65));

        generarCreditoBtn.setText("GENERAR CREDITO");
        generarCreditoBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generarCreditoBtnActionPerformed(evt);
            }
        });
        getContentPane().add(generarCreditoBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 240, 160, 65));

        calculadoraBtn.setText("CALCULADORA");
        calculadoraBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                calculadoraBtnActionPerformed(evt);
            }
        });
        getContentPane().add(calculadoraBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(562, 126, 153, 72));

        salirBtn.setText("SALIR");
        salirBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salirBtnActionPerformed(evt);
            }
        });
        getContentPane().add(salirBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 370, 115, 43));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("SISTEMA CREDITOS - DEPORTES 7");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 40, -1, -1));

        deudoresBtn.setText("DEUDORES");
        deudoresBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deudoresBtnActionPerformed(evt);
            }
        });
        getContentPane().add(deudoresBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 240, 140, 60));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void crearClienteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_crearClienteBtnActionPerformed
        FormCrearCliente nuevaVentana = new FormCrearCliente();
        nuevaVentana.setVisible(true);
    }//GEN-LAST:event_crearClienteBtnActionPerformed

    private void cobrarCuotaBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cobrarCuotaBtnActionPerformed
        FormCobrarCuota cobrarCuota = new FormCobrarCuota();
        cobrarCuota.setVisible(true);
    }//GEN-LAST:event_cobrarCuotaBtnActionPerformed

    private void listadoClientesBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_listadoClientesBtnActionPerformed
        FormListadoClientes listadoClientes = new FormListadoClientes();
        listadoClientes.setVisible(true);
    }//GEN-LAST:event_listadoClientesBtnActionPerformed

    private void calculadoraBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_calculadoraBtnActionPerformed
        FormCalculadora calculadora = new FormCalculadora();
        calculadora.setVisible(true);
    }//GEN-LAST:event_calculadoraBtnActionPerformed

    private void generarCreditoBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generarCreditoBtnActionPerformed
        FormGenerarCredito generarCredito = new FormGenerarCredito();
        generarCredito.setVisible(true);
    }//GEN-LAST:event_generarCreditoBtnActionPerformed

    private void salirBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salirBtnActionPerformed
        System.exit(0);
    }//GEN-LAST:event_salirBtnActionPerformed

    private void deudoresBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deudoresBtnActionPerformed
        FormDeudores deudores = new FormDeudores();
        deudores.setVisible(true);
    }//GEN-LAST:event_deudoresBtnActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(() -> {
            new FormPrincipal().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton calculadoraBtn;
    private javax.swing.JButton cobrarCuotaBtn;
    private javax.swing.JButton crearClienteBtn;
    private javax.swing.JButton deudoresBtn;
    private javax.swing.JButton generarCreditoBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton listadoClientesBtn;
    private javax.swing.JButton salirBtn;
    // End of variables declaration//GEN-END:variables
}
