/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.sistemacreditos;

import static com.mycompany.sistemacreditos.Clientes.user_update;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;

public class FormDeudores extends javax.swing.JFrame {

    private JTable table;
    private JScrollPane scrollPane;
    private Conexion conexion;
    private JTextField buscarTxt;
    public static String user_update = "";

    public FormDeudores() {
        initComponentsCustom();
        conexion = new Conexion();
        setupTable();
        loadData();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        ImageIcon icon = new ImageIcon("icono.png"); // Cambia la ruta según tu estructura de proyecto
        setIconImage(icon.getImage());
    }
    
    

    private void initComponentsCustom() {
        setTitle("Listado de Cuotas Vencidas");
        getContentPane().setBackground(new Color(240, 240, 240));
        setLayout(new GridBagLayout());

        // Panel para el título y la búsqueda
        JPanel titlePanel = new JPanel(new GridBagLayout());
        titlePanel.setOpaque(false);

        // Título
        JLabel titleLabel = new JLabel("LISTADO DE CUOTAS VENCIDAS");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setForeground(new Color(0, 102, 204));
        titlePanel.add(titleLabel, createGridBagConstraints(0, 0, 1, 1, GridBagConstraints.CENTER));

        // Campo de búsqueda
        JLabel searchLabel = new JLabel("BUSCAR:");
        searchLabel.setFont(new Font("sansserif", Font.BOLD, 14));
        titlePanel.add(searchLabel, createGridBagConstraints(0, 1, 1, 1, GridBagConstraints.WEST));

        buscarTxt = new JTextField(20);
        buscarTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                String textoBusqueda = buscarTxt.getText();
                filterTable(textoBusqueda);
            }
        });
        titlePanel.add(buscarTxt, createGridBagConstraints(0, 2, 1, 1, GridBagConstraints.WEST));

        add(titlePanel, createGridBagConstraints(0, 0, 1, 1, GridBagConstraints.CENTER));

        // Tabla
        table = new JTable();
        setupTableDesign();

        scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(1200, 400));
        add(scrollPane, createGridBagConstraints(0, 1, 1, 1, GridBagConstraints.CENTER));

        pack();
    }

    private void setupTable() {
        String[] columnNames = {"DNI", "APELLIDO", "NOMBRES", "NUM CREDITO", "NUM CUOTA", "FECHA VENCIMIENTO", "MONTO CUOTA"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        table.setModel(model);
                   table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int fila_point = table.rowAtPoint(e.getPoint());
                int columna_point = 0;

                if (fila_point > -1) {
                    user_update = (String) model.getValueAt(fila_point, columna_point);
                    FormTelefonos formTelefonos = new FormTelefonos();
                    formTelefonos.setVisible(true);
                    System.out.println("asdas"+ user_update);
                }
            }
        });
    }

    private void setupTableDesign() {
        table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        table.setRowHeight(30);
        table.setFillsViewportHeight(true);

        for (int i = 0; i < table.getColumnCount(); i++) {
            TableColumn column = table.getColumnModel().getColumn(i);
            column.setPreferredWidth(150);
        }

        DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
        headerRenderer.setBackground(new Color(0, 102, 204));
        headerRenderer.setForeground(Color.WHITE);
        headerRenderer.setFont(new Font("Segoe UI", Font.BOLD, 16));
        headerRenderer.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.WHITE));

        table.getTableHeader().setDefaultRenderer(headerRenderer);

        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public java.awt.Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                java.awt.Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                if (isSelected) {
                    cell.setBackground(new Color(0, 102, 204));
                    cell.setForeground(Color.WHITE);
                } else {
                    cell.setBackground(row % 2 == 0 ? new Color(220, 220, 220) : Color.WHITE);
                    cell.setForeground(Color.BLACK);
                }
                return cell;
            }
        });
    }

    private void loadData() {
        String query = "SELECT c.dni, c.apellidos, c.nombres, cr.num_credito, cu.num_cuota, cu.vencimiento, cu.importe_actualizado "
                + "FROM clientes c "
                + "JOIN credito cr ON c.id = cr.id_cliente "
                + "JOIN cuotas cu ON cr.num_credito = cu.id_credito "
                + "WHERE date(strftime('%Y-%m-%d', substr(cu.vencimiento, 7, 4) || '-' || substr(cu.vencimiento, 4, 2) || '-' || substr(cu.vencimiento, 1, 2))) < date('now') "
                + "AND cu.pago_realizado = 0 "
                + "ORDER BY c.dni";

        try (Connection conn = conexion.establecerConexion(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {

            DefaultTableModel model = (DefaultTableModel) table.getModel();
            model.setRowCount(0);

            while (rs.next()) {
                double importeActualizado = Math.round(rs.getDouble("importe_actualizado") * 100.0) / 100.0;
                Object[] row = {
                    rs.getString("dni"),
                    rs.getString("apellidos"),
                    rs.getString("nombres"),
                    rs.getInt("num_credito"),
                    rs.getInt("num_cuota"),
                    rs.getString("vencimiento"),
                    importeActualizado
                };
                model.addRow(row);
            }

            if (model.getRowCount() == 0) {
                JOptionPane.showMessageDialog(this, "No se encontraron cuotas vencidas sin pagar.", "Información", JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al cargar los datos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void filterTable(String searchText) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        table.setRowSorter(sorter);

        if (searchText.length() == 0) {
            sorter.setRowFilter(null);
        } else {
            try {
                sorter.setRowFilter(RowFilter.regexFilter("(?i)" + searchText));
            } catch (java.util.regex.PatternSyntaxException pse) {
                System.err.println("Bad regex pattern");
            }
        }
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

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("LISTADO DE CUOTAS VENCIDAS");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 700, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(85, 85, 85)
                        .addComponent(jLabel1)))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(FormDeudores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormDeudores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormDeudores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormDeudores.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormDeudores().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
