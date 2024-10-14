package com.mycompany.sistemacreditos;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class FormListadoClientes extends javax.swing.JFrame {

    public FormListadoClientes() {
        initComponentsCustom();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Muestra los clientes inicialmente
        Clientes objetoClientes = new Clientes();
        objetoClientes.MostrarClientesGeneral(tablaClientes);
        ImageIcon icon = new ImageIcon("icono.png"); // Cambia la ruta según tu estructura de proyecto
        setIconImage(icon.getImage());
    }

    private void initComponentsCustom() {
        setTitle("Listado de Clientes");
        getContentPane().setBackground(new Color(240, 240, 240));
        setLayout(new GridBagLayout());

        // Panel para el título y la búsqueda
        JPanel titlePanel = new JPanel(new GridBagLayout());
        titlePanel.setOpaque(false);

        // Título
        JLabel titleLabel = new JLabel("LISTADO DE CLIENTES");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setForeground(new Color(0, 102, 204));
        titlePanel.add(titleLabel, createGridBagConstraints(0, 0, 1, 1, GridBagConstraints.CENTER));

        // Campo de búsqueda
        JLabel jLabel2 = new JLabel("BUSCAR:");
        jLabel2.setFont(new Font("sansserif", Font.BOLD, 14));
        titlePanel.add(jLabel2, createGridBagConstraints(0, 1, 1, 1, GridBagConstraints.WEST));

        // Inicializar buscarTxt
        buscarTxt = new JTextField(20);
        buscarTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                String textoBusqueda = buscarTxt.getText();
                Clientes objetoClientes = new Clientes();
                objetoClientes.MostrarClientes(tablaClientes, textoBusqueda);
            }
        });
        titlePanel.add(buscarTxt, createGridBagConstraints(0, 2, 1, 1, GridBagConstraints.WEST));

        add(titlePanel, createGridBagConstraints(0, 0, 1, 1, GridBagConstraints.CENTER));

        // Tabla de clientes
        tablaClientes = new JTable(new DefaultTableModel(
                new Object[][]{},
                new String[]{"DNI", "Nombre", "Apellido", "Teléfono", "Email", "Dirección", "Ciudad", "Provincia", "Código Postal", "Fecha de Nacimiento", "Género", "Notas"} // Nombres de las columnas
        ));

        // Cambiar la fuente de la tabla
        tablaClientes.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        tablaClientes.setRowHeight(30); // Ajustar la altura de las filas
        tablaClientes.setFillsViewportHeight(true);

        // Ajustar el tamaño de todas las columnas
        for (int i = 0; i < tablaClientes.getColumnCount(); i++) {
            TableColumn column = tablaClientes.getColumnModel().getColumn(i);
            column.setPreferredWidth(150); // Ajusta el ancho de todas las columnas
        }

        // Personaliza el renderer para los encabezados
        DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
        headerRenderer.setBackground(new Color(0, 102, 204)); // Color del encabezado (igual que el título)
        headerRenderer.setForeground(Color.WHITE); // Color del texto
        headerRenderer.setFont(new Font("Segoe UI", Font.BOLD, 16)); // Fuente para el encabezado
        headerRenderer.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.WHITE));

        // Aplicar el renderer a los encabezados
        tablaClientes.getTableHeader().setDefaultRenderer(headerRenderer);

        // Personaliza el renderer para las filas
        tablaClientes.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public java.awt.Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                java.awt.Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                if (isSelected) {
                    cell.setBackground(new Color(0, 102, 204)); // Color de selección
                    cell.setForeground(Color.WHITE);
                } else {
                    // Alternar colores de filas para mayor legibilidad
                    cell.setBackground(row % 2 == 0 ? new Color(220, 220, 220) : Color.WHITE);
                    cell.setForeground(Color.BLACK);
                }
                return cell;
            }
        });

        // Agrega un JScrollPane para la tabla
        JScrollPane jScrollPane1 = new JScrollPane(tablaClientes);
        jScrollPane1.setPreferredSize(new Dimension(1200, 400)); // Ajusta el tamaño aquí
        add(jScrollPane1, createGridBagConstraints(0, 1, 1, 1, GridBagConstraints.CENTER));

        pack();
        setLocationRelativeTo(null);
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
        tablaClientes = new javax.swing.JTable();
        buscarTxt = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tablaClientes.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tablaClientes);

        buscarTxt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buscarTxtMouseClicked(evt);
            }
        });
        buscarTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                buscarTxtKeyReleased(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("LISTADO DE CLIENTES");

        jLabel2.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel2.setText("BUSCAR:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 767, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(281, 281, 281)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(buscarTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(44, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel1)
                .addGap(7, 7, 7)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buscarTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buscarTxtMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buscarTxtMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_buscarTxtMouseClicked

    private void buscarTxtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_buscarTxtKeyReleased
        String textoBusqueda = buscarTxt.getText(); // Obtener el texto del campo de búsqueda
        Clientes objetoClientes = new Clientes();
        objetoClientes.MostrarClientes(tablaClientes, textoBusqueda); // Filtrar clientes en la tabla según el texto  
    }//GEN-LAST:event_buscarTxtKeyReleased

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormListadoClientes().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField buscarTxt;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaClientes;
    // End of variables declaration//GEN-END:variables
}
