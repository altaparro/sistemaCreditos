package com.mycompany.sistemacreditos;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JFrame;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class FormCrearCliente extends javax.swing.JFrame {

    public FormCrearCliente() {
        initComponentsCustom();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(1150, 600);
                ImageIcon icon = new ImageIcon("icono.png"); // Cambia la ruta según tu estructura de proyecto
        setIconImage(icon.getImage());
    }

private void initComponentsCustom() {
    setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    setTitle("Crear Cliente - Sistema de Créditos");
    getContentPane().setBackground(new java.awt.Color(240, 240, 240));
    setLayout(new GridBagLayout());

    // Inicializar todos los campos de texto
    dniTxt = new JTextField();
    nombresTxt = new JTextField();
    apellidoTxt = new JTextField();
    emailTxt = new JTextField();
    telefonoTxt = new JTextField();
    telefono2Txt = new JTextField();
    telefono3Txt = new JTextField();
    localidadTxt = new JTextField();
    barrioTxt = new JTextField();
    calleTxt = new JTextField();
    numeroTxt = new JTextField();
    entreCallesTxt = new JTextField();
    cod1Txt = new JTextField();
    cod2Txt = new JTextField();
    cod3Txt = new JTextField();

    // Título
    JLabel titleLabel = new JLabel("CREAR CLIENTE");
    titleLabel.setFont(new java.awt.Font("Segoe UI", Font.BOLD, 24));
    titleLabel.setForeground(new java.awt.Color(0, 102, 204));
    add(titleLabel, createGridBagConstraints(0, 0, 4, 1, GridBagConstraints.CENTER));

    // Panel principal para contener las dos columnas
    JPanel mainPanel = new JPanel(new GridBagLayout());
    mainPanel.setOpaque(false);

    // Panel izquierdo
    JPanel leftPanel = new JPanel(new GridBagLayout());
    leftPanel.setOpaque(false);

    // Panel derecho
    JPanel rightPanel = new JPanel(new GridBagLayout());
    rightPanel.setOpaque(false);

    // Campos de la izquierda
    String[] leftLabels = {"DNI:", "NOMBRES:", "APELLIDO:", "EMAIL:", "LOCALIDAD:"};
    JTextField[] leftFields = {dniTxt, nombresTxt, apellidoTxt, emailTxt, localidadTxt};

    addFieldsToPanel(leftPanel, leftLabels, leftFields);

    // Campos de la derecha
    String[] rightLabels = {"BARRIO:", "CALLE:", "NÚMERO:", "ENTRE CALLES:"};
    JTextField[] rightFields = {barrioTxt, calleTxt, numeroTxt, entreCallesTxt};

    addFieldsToPanel(rightPanel, rightLabels, rightFields);

    // Añadir paneles izquierdo y derecho al panel principal
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.weightx = 1;
    gbc.weighty = 1;
    gbc.fill = GridBagConstraints.BOTH;
    gbc.insets = new Insets(10, 10, 10, 10);
    mainPanel.add(leftPanel, gbc);

    gbc.gridx = 1;
    mainPanel.add(rightPanel, gbc);

    // Añadir el panel principal al formulario
    add(mainPanel, createGridBagConstraints(0, 1, 4, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH));

    // Panel para los campos de teléfono
    JPanel phonePanel = new JPanel(new GridBagLayout());
    phonePanel.setOpaque(false);

    // Campos de teléfono y código de área
    String[] phoneLabels = {"TELÉFONO:", "TELÉFONO 2:", "TELÉFONO 3:"};
    JTextField[] phoneFields = {telefonoTxt, telefono2Txt, telefono3Txt};
    JTextField[] codeFields = {cod1Txt, cod2Txt, cod3Txt};

    addPhoneFieldsToPanel(phonePanel, phoneLabels, phoneFields, codeFields);

    // Añadir el panel de teléfonos al formulario
    add(phonePanel, createGridBagConstraints(0, 2, 4, 1, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL));

    // Botones
    confirmarBtn = createStyledButton("CONFIRMAR");
    cancelarBtn = createStyledButton("CANCELAR");
    
    // Añadir ActionListeners a los botones
    confirmarBtn.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            confirmarBtnActionPerformed(evt);
        }
    });
    
    cancelarBtn.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            cancelarBtnActionPerformed(evt);
        }
    });
    
    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
    buttonPanel.setOpaque(false);
    buttonPanel.add(confirmarBtn);
    buttonPanel.add(cancelarBtn);
    
    add(buttonPanel, createGridBagConstraints(0, 3, 4, 1, GridBagConstraints.CENTER));

    pack();
    setLocationRelativeTo(null);
}

private void addFieldsToPanel(JPanel panel, String[] labels, JTextField[] fields) {
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.anchor = GridBagConstraints.WEST;
    gbc.insets = new Insets(5, 5, 5, 5);

    for (int i = 0; i < labels.length; i++) {
        gbc.gridx = 0;
        gbc.gridy = i;
        JLabel label = new JLabel(labels[i]);
        label.setFont(new java.awt.Font("Segoe UI", Font.BOLD, 14));
        panel.add(label, gbc);

        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        JTextField field = fields[i];
        field.setPreferredSize(new java.awt.Dimension(200, 30));
        panel.add(field, gbc);
    }
}

private void addPhoneFieldsToPanel(JPanel panel, String[] labels, JTextField[] phoneFields, JTextField[] codeFields) {
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.anchor = GridBagConstraints.WEST;
    gbc.insets = new Insets(5, 5, 5, 5);

    for (int i = 0; i < labels.length; i++) {
        gbc.gridx = 0;
        gbc.gridy = i;
        JLabel label = new JLabel(labels[i]);
        label.setFont(new java.awt.Font("Segoe UI", Font.BOLD, 14));
        panel.add(label, gbc);

        gbc.gridx = 1;
        JPanel phonePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        phonePanel.setOpaque(false);

        JLabel codeLabel = new JLabel("Cód. Área:");
        codeLabel.setFont(new java.awt.Font("Segoe UI", Font.PLAIN, 12));
        phonePanel.add(codeLabel);

        JTextField codeField = codeFields[i];
        codeField.setPreferredSize(new java.awt.Dimension(60, 30));
        phonePanel.add(codeField);

        JTextField phoneField = phoneFields[i];
        phoneField.setPreferredSize(new java.awt.Dimension(140, 30));
        phonePanel.add(phoneField);

        panel.add(phonePanel, gbc);
    }
}

private JButton createStyledButton(String text) {
    JButton button = new JButton(text);
    button.setFont(new java.awt.Font("Segoe UI", Font.BOLD, 14));
    button.setForeground(java.awt.Color.WHITE);
    button.setBackground(text.equals("CANCELAR") ? new java.awt.Color(255, 99, 71) : new java.awt.Color(0, 123, 255));
    button.setFocusPainted(false);
    button.setBorderPainted(false);
    button.setPreferredSize(new java.awt.Dimension(130, 40));

    button.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseEntered(java.awt.event.MouseEvent evt) {
            button.setBackground(new java.awt.Color(0, 105, 217));
            button.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        }

        public void mouseExited(java.awt.event.MouseEvent evt) {
            button.setBackground(text.equals("CANCELAR") ? new java.awt.Color(255, 99, 71) : new java.awt.Color(0, 123, 255));
            button.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        }
    });

    return button;
}

private GridBagConstraints createGridBagConstraints(int gridx, int gridy, int gridwidth, int gridheight, int anchor) {
    return createGridBagConstraints(gridx, gridy, gridwidth, gridheight, anchor, GridBagConstraints.NONE);
}

private GridBagConstraints createGridBagConstraints(int gridx, int gridy, int gridwidth, int gridheight, int anchor, int fill) {
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.gridx = gridx;
    gbc.gridy = gridy;
    gbc.gridwidth = gridwidth;
    gbc.gridheight = gridheight;
    gbc.anchor = anchor;
    gbc.fill = fill;
    gbc.insets = new java.awt.Insets(5, 5, 5, 5);
    return gbc;
}
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        emailTxt = new javax.swing.JTextField();
        apellidoTxt = new javax.swing.JTextField();
        dniTxt = new javax.swing.JTextField();
        nombresTxt = new javax.swing.JTextField();
        telefonoTxt = new javax.swing.JTextField();
        telefono2Txt = new javax.swing.JTextField();
        telefono3Txt = new javax.swing.JTextField();
        localidadTxt = new javax.swing.JTextField();
        barrioTxt = new javax.swing.JTextField();
        calleTxt = new javax.swing.JTextField();
        numeroTxt = new javax.swing.JTextField();
        entreCallesTxt = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        confirmarBtn = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        cod1Txt = new javax.swing.JTextField();
        cod2Txt = new javax.swing.JTextField();
        cod3Txt = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        cancelarBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(emailTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 280, 380, -1));
        getContentPane().add(apellidoTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 240, 380, -1));
        getContentPane().add(dniTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 160, 380, -1));
        getContentPane().add(nombresTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 200, 380, -1));
        getContentPane().add(telefonoTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 320, 150, -1));
        getContentPane().add(telefono2Txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 360, 150, -1));
        getContentPane().add(telefono3Txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 400, 150, -1));
        getContentPane().add(localidadTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 160, 300, -1));
        getContentPane().add(barrioTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 200, 300, -1));
        getContentPane().add(calleTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 240, 300, -1));
        getContentPane().add(numeroTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 280, 300, -1));
        getContentPane().add(entreCallesTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 320, 300, -1));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("DNI:");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 160, -1, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setText("NOMBRES:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 200, -1, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setText("APELLIDO:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 240, -1, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setText("LOCALIDAD:");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 160, -1, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setText("BARRIO:");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 200, -1, -1));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel6.setText("CALLE:");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 240, -1, -1));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel7.setText("NUMERO:");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 280, -1, -1));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel8.setText("ENTRE CALLES:");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 320, -1, -1));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel9.setText("TELEFONO:");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 320, -1, -1));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel10.setText("TELEFONO 2:");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 360, -1, -1));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel11.setText("TELEFONO 3:");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 400, -1, -1));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel12.setText("EMAIL:");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 280, -1, -1));

        confirmarBtn.setText("CONFIRMAR");
        confirmarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmarBtnActionPerformed(evt);
            }
        });
        getContentPane().add(confirmarBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 470, 110, 60));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel13.setText("CREAR CLIENTE");
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 40, -1, -1));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel14.setText("COD AREA:");
        getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 400, -1, -1));

        cod1Txt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cod1TxtActionPerformed(evt);
            }
        });
        getContentPane().add(cod1Txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 320, 80, -1));
        getContentPane().add(cod2Txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 360, 80, -1));
        getContentPane().add(cod3Txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 400, 80, -1));

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel15.setText("COD AREA:");
        getContentPane().add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 320, -1, -1));

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel16.setText("COD AREA:");
        getContentPane().add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 360, -1, -1));

        cancelarBtn.setText("CANCELAR");
        cancelarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarBtnActionPerformed(evt);
            }
        });
        getContentPane().add(cancelarBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 470, 110, 60));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void confirmarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmarBtnActionPerformed
        if (dniTxt.getText().equals("") || nombresTxt.getText().equals("") || apellidoTxt.getText().equals("") || localidadTxt.getText().equals("") || barrioTxt.getText().equals("") || calleTxt.getText().equals("") || numeroTxt.getText().equals("") || entreCallesTxt.getText().equals("") || cod1Txt.getText().equals("") || telefonoTxt.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Complete todos los campos obligatorios");
        } else {
            Clientes objetoCliente = new Clientes();
            LocalDate fechaActual = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            String fecha_alta = fechaActual.format(formatter);
            objetoCliente.InsertarCliente(dniTxt, nombresTxt, apellidoTxt, localidadTxt, barrioTxt, calleTxt, numeroTxt, entreCallesTxt, emailTxt, fecha_alta, 10, cod1Txt, telefonoTxt, cod2Txt, telefono2Txt, cod3Txt, telefono3Txt);
            limpiarFormulario();
        }

    }//GEN-LAST:event_confirmarBtnActionPerformed

    private void cod1TxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cod1TxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cod1TxtActionPerformed

    private void cancelarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarBtnActionPerformed
        limpiarFormulario();
    }//GEN-LAST:event_cancelarBtnActionPerformed

    private void limpiarFormulario() {
        dniTxt.setText("");
        nombresTxt.setText("");
        apellidoTxt.setText("");
        localidadTxt.setText("");
        barrioTxt.setText("");
        calleTxt.setText("");
        numeroTxt.setText("");
        entreCallesTxt.setText("");
        emailTxt.setText("");
        entreCallesTxt.setText("");
        cod1Txt.setText("");
        cod2Txt.setText("");
        cod3Txt.setText("");
        telefonoTxt.setText("");
        telefono2Txt.setText("");
        telefono3Txt.setText("");

    }

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormCrearCliente().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField apellidoTxt;
    private javax.swing.JTextField barrioTxt;
    private javax.swing.JTextField calleTxt;
    private javax.swing.JButton cancelarBtn;
    private javax.swing.JTextField cod1Txt;
    private javax.swing.JTextField cod2Txt;
    private javax.swing.JTextField cod3Txt;
    private javax.swing.JButton confirmarBtn;
    private javax.swing.JTextField dniTxt;
    private javax.swing.JTextField emailTxt;
    private javax.swing.JTextField entreCallesTxt;
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
    private javax.swing.JTextField telefono2Txt;
    private javax.swing.JTextField telefono3Txt;
    private javax.swing.JTextField telefonoTxt;
    // End of variables declaration//GEN-END:variables
}
