
package com.mycompany.sistemacreditos;

import javax.swing.JFrame;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class FormCrearCliente extends javax.swing.JFrame {

    public FormCrearCliente() {
        initComponents();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(800, 600);
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(emailTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 340, 150, -1));
        getContentPane().add(apellidoTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 220, 150, -1));
        getContentPane().add(dniTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 140, 150, -1));
        getContentPane().add(nombresTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 180, 150, -1));
        getContentPane().add(telefonoTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 260, 150, -1));
        getContentPane().add(telefono2Txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 300, 150, -1));
        getContentPane().add(telefono3Txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 340, 150, -1));
        getContentPane().add(localidadTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 140, 150, -1));
        getContentPane().add(barrioTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 180, 150, -1));
        getContentPane().add(calleTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 220, 150, -1));
        getContentPane().add(numeroTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 260, 150, -1));
        getContentPane().add(entreCallesTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 300, 150, -1));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("DNI:");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 140, -1, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setText("NOMBRES:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 180, -1, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setText("APELLIDO:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 220, -1, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setText("LOCALIDAD:");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 140, -1, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setText("BARRIO:");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 180, -1, -1));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel6.setText("CALLE:");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 220, -1, -1));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel7.setText("NUMERO:");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 260, -1, -1));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel8.setText("ENTRE CALLES:");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 300, -1, -1));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel9.setText("TELEFONO:");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 260, -1, -1));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel10.setText("TELEFONO 2:");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 300, -1, -1));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel11.setText("TELEFONO 3:");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 340, -1, -1));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel12.setText("EMAIL:");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 340, -1, -1));

        confirmarBtn.setText("CONFIRMAR");
        confirmarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmarBtnActionPerformed(evt);
            }
        });
        getContentPane().add(confirmarBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 410, 110, 60));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel13.setText("CREAR CLIENTE");
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 50, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void confirmarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmarBtnActionPerformed
        Clientes objetoCliente = new Clientes();
         LocalDate fechaActual = LocalDate.now();
         DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
         String fecha_alta = fechaActual.format(formatter);
        objetoCliente.InsertarCliente(dniTxt, nombresTxt, apellidoTxt, localidadTxt, barrioTxt, calleTxt, numeroTxt, entreCallesTxt, emailTxt, fecha_alta, 10);
    }//GEN-LAST:event_confirmarBtnActionPerformed


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
    private javax.swing.JButton confirmarBtn;
    private javax.swing.JTextField dniTxt;
    private javax.swing.JTextField emailTxt;
    private javax.swing.JTextField entreCallesTxt;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
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
