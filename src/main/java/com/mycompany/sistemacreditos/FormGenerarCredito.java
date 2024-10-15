package com.mycompany.sistemacreditos;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class FormGenerarCredito extends javax.swing.JFrame {

    public FormGenerarCredito() {
        initComponentsCustom();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        Creditos objetoCreditos = new Creditos();
        objetoCreditos.LlenarPlanPagoComboBox(planPagoComboBox);
        ImageIcon icon = new ImageIcon("icono.png"); // Cambia la ruta según tu estructura de proyecto
        setIconImage(icon.getImage());
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
        JLabel titleLabel = new JLabel("GENERAR CRÉDITO");
        titleLabel.setFont(new java.awt.Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setForeground(new java.awt.Color(0, 102, 204));
        titlePanel.add(titleLabel, createGridBagConstraints(0, 1, 1, 1, GridBagConstraints.CENTER));

        add(titlePanel, createGridBagConstraints(0, 0, 3, 1, GridBagConstraints.CENTER));

        // Panel para el contenido del formulario
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setOpaque(false);
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Espaciado

        // Componentes del formulario
        buscarTxt = new javax.swing.JTextField(15);
        btnBuscar = createStyledButton("BUSCAR");
        btnBuscar.addActionListener(evt -> btnBuscarActionPerformed(evt));

        jLabel2 = new javax.swing.JLabel("DATOS CLIENTE:");
        jLabel3 = new javax.swing.JLabel("DNI:");
        dniTxt = new javax.swing.JTextField(15);
        dniTxt.setEditable(false);

        jLabel4 = new javax.swing.JLabel("NOMBRES:");
        nombresTxt = new javax.swing.JTextField(15);
        nombresTxt.setEditable(false);

        jLabel5 = new javax.swing.JLabel("APELLIDO:");
        apellidoTxt = new javax.swing.JTextField(15);
        apellidoTxt.setEditable(false);

        jLabel6 = new javax.swing.JLabel("OBSERVACIONES:");
        observacionesText = new javax.swing.JTextPane();
        JScrollPane observacionesScroll = new JScrollPane(observacionesText);
        observacionesScroll.setPreferredSize(new Dimension(300, 100));

        jLabel7 = new javax.swing.JLabel("PLAN DE PAGO:");
        planPagoComboBox = new javax.swing.JComboBox<>(new String[]{"Item 1", "Item 2", "Item 3", "Item 4"});

        jLabel8 = new javax.swing.JLabel("MONTO:");
        montoTxt = new javax.swing.JTextField(15);

        jButton2 = createStyledButton("GENERAR CREDITO");
        jButton2.addActionListener(evt -> jButton2ActionPerformed(evt));

        // Agregar componentes al panel del formulario
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(buscarTxt, gbc);
        gbc.gridx = 1;
        formPanel.add(btnBuscar, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(jLabel2, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(jLabel3, gbc);
        gbc.gridx = 1;
        formPanel.add(dniTxt, gbc);
        gbc.gridx = 0;
        gbc.gridy = 3;
        formPanel.add(jLabel4, gbc);
        gbc.gridx = 1;
        formPanel.add(nombresTxt, gbc);
        gbc.gridx = 0;
        gbc.gridy = 4;
        formPanel.add(jLabel5, gbc);
        gbc.gridx = 1;
        formPanel.add(apellidoTxt, gbc);
        gbc.gridx = 0;
        gbc.gridy = 5;
        formPanel.add(jLabel6, gbc);
        gbc.gridx = 1;
        formPanel.add(observacionesScroll, gbc);
        gbc.gridx = 0;
        gbc.gridy = 6;
        formPanel.add(jLabel7, gbc);
        gbc.gridx = 1;
        formPanel.add(planPagoComboBox, gbc);
        gbc.gridx = 0;
        gbc.gridy = 7;
        formPanel.add(jLabel8, gbc);
        gbc.gridx = 1;
        formPanel.add(montoTxt, gbc);
        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.gridwidth = 2;
        formPanel.add(jButton2, gbc);

        add(formPanel, createGridBagConstraints(0, 1, 3, 1, GridBagConstraints.CENTER));

        pack();
        setLocationRelativeTo(null);
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
                button.setBackground(text.equals("CANCELAR") ? new java.awt.Color(255, 99, 71) : new java.awt.Color(0, 123, 255));
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
        gbc.fill = GridBagConstraints.NONE;
        gbc.insets = new Insets(10, 10, 10, 10);
        return gbc;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        btnBuscar = new javax.swing.JButton();
        buscarTxt = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        dniTxt = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        nombresTxt = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        apellidoTxt = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        observacionesText = new javax.swing.JTextPane();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        planPagoComboBox = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        montoTxt = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("GENERAR CREDITO");

        btnBuscar.setText("BUSCAR");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("DATOS CLIENTE:");

        jLabel3.setText("DNI:");

        dniTxt.setEditable(false);

        jLabel4.setText("NOMBRES:");

        nombresTxt.setEditable(false);

        jLabel5.setText("APELLIDO:");

        apellidoTxt.setEditable(false);

        jScrollPane1.setViewportView(observacionesText);

        jLabel6.setText("OBSERVACIONES:");

        jLabel7.setText("PLAN DE PAGO:");

        planPagoComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel8.setText("MONTO:");

        jButton2.setText("GENERAR CREDITO");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(102, 102, 102)
                        .addComponent(buscarTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnBuscar))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(228, 228, 228)
                                    .addComponent(jLabel4)
                                    .addGap(18, 18, 18)
                                    .addComponent(nombresTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(jLabel5)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(apellidoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addComponent(jLabel3)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(dniTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(569, 569, 569)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(70, 70, 70)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel7)
                                    .addComponent(planPagoComboBox, 0, 231, Short.MAX_VALUE)
                                    .addComponent(jLabel8)
                                    .addComponent(montoTxt)
                                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(279, 279, 279)
                        .addComponent(jLabel1)))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel1)
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBuscar)
                    .addComponent(buscarTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addComponent(jLabel2)
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(dniTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(nombresTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(apellidoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(43, 43, 43)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(planPagoComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel8)
                        .addGap(18, 18, 18)
                        .addComponent(montoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1))
                .addGap(49, 49, 49)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(50, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        if (dniTxt.getText().equals("") || observacionesText.getText().equals("") || montoTxt.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Complete todos los campos obligatorios");
        } else {
            Creditos objetoCreditos = new Creditos();
            LocalDate fechaActual = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            String fecha = fechaActual.format(formatter);

            // Obtener el ítem seleccionado del combo box
            String seleccion = (String) planPagoComboBox.getSelectedItem();

            // Extraer el id_plan_pago
            int idPlanPago = Integer.parseInt(seleccion.split(" - ")[0]);

            // Insertar el crédito y obtener el ID del crédito insertado
            int idCreditoInsertado = objetoCreditos.insertarCredito(dniTxt, observacionesText, montoTxt, fecha, idPlanPago);

            if (idCreditoInsertado != -1) {
                // Si la inserción fue exitosa, generar el PDF
                objetoCreditos.generarPDFCredito(idCreditoInsertado);
                JOptionPane.showMessageDialog(this, "Crédito insertado y PDF generado con éxito.");
            } else {
                JOptionPane.showMessageDialog(this, "Error al insertar el crédito. No se pudo generar el PDF.");
            }
        }

    }//GEN-LAST:event_jButton2ActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        if (buscarTxt.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Complete todos los campos obligatorios");
        } else {
            Creditos objetoCreditos = new Creditos();
            objetoCreditos.MostrarCliente(buscarTxt, dniTxt, nombresTxt, apellidoTxt);
        }
    }//GEN-LAST:event_btnBuscarActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormGenerarCredito().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField apellidoTxt;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JTextField buscarTxt;
    private javax.swing.JTextField dniTxt;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField montoTxt;
    private javax.swing.JTextField nombresTxt;
    private javax.swing.JTextPane observacionesText;
    private javax.swing.JComboBox<String> planPagoComboBox;
    // End of variables declaration//GEN-END:variables
}
