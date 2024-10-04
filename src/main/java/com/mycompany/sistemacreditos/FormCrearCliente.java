package com.mycompany.sistemacreditos;

import javax.swing.JFrame;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;

public class FormCrearCliente extends javax.swing.JFrame {

    public FormCrearCliente() {
        initComponents();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(1150, 600);
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
        if (dniTxt.getText().equals("") ||nombresTxt.getText().equals("") || apellidoTxt.getText().equals("") || localidadTxt.getText().equals("") || barrioTxt.getText().equals("") || calleTxt.getText().equals("") || numeroTxt.getText().equals("") || entreCallesTxt.getText().equals("") || cod1Txt.getText().equals("") || telefonoTxt.getText().equals("")) {
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
