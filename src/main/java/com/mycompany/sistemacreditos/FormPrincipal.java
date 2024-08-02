package com.mycompany.sistemacreditos;

import javax.swing.JTable;

public class FormPrincipal extends javax.swing.JFrame {


    public FormPrincipal() {
        initComponents();
        setSize(800, 480);
        setResizable(false);
        setLocationRelativeTo(null);
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
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

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(() -> {
            new FormPrincipal().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton calculadoraBtn;
    private javax.swing.JButton cobrarCuotaBtn;
    private javax.swing.JButton crearClienteBtn;
    private javax.swing.JButton generarCreditoBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton listadoClientesBtn;
    private javax.swing.JButton salirBtn;
    // End of variables declaration//GEN-END:variables
}
