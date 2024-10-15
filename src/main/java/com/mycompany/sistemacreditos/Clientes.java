package com.mycompany.sistemacreditos;


import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Clientes {
       public static String user_update = "";
    public void MostrarClientesGeneral(JTable tablaClientes) {
        Conexion objetoConexion = new Conexion();

        DefaultTableModel modelo = new DefaultTableModel();

        String sql = "";
        modelo.addColumn("ID");
        modelo.addColumn("DNI");
        modelo.addColumn("NOMBRES");
        modelo.addColumn("APELLIDO");
        modelo.addColumn("LOCALIDAD");
        modelo.addColumn("BARRIO");
        modelo.addColumn("CALLE");
        modelo.addColumn("NUMERO");
        modelo.addColumn("ENTRE CALLES");
        modelo.addColumn("EMAIL                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                             ");
        modelo.addColumn("FECHA ALTA");
        modelo.addColumn("CALIFICACION");

        tablaClientes.setModel(modelo);

        sql = "SELECT * FROM clientes";

        String[] datos = new String[12];
        Statement st;
        try {
            st = objetoConexion.establecerConexion().createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {

                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);
                datos[3] = rs.getString(4);
                datos[4] = rs.getString(5);
                datos[5] = rs.getString(6);
                datos[6] = rs.getString(7);
                datos[7] = rs.getString(8);
                datos[8] = rs.getString(9);
                datos[9] = rs.getString(10);
                datos[10] = rs.getString(11);
                datos[11] = rs.getString(12);
                modelo.addRow(datos);
            }
            tablaClientes.setModel(modelo);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al mostrar los datos " + e);
            System.out.println(e);
        } finally {
            objetoConexion.cerrarConexion();
        }
        
           tablaClientes.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int fila_point = tablaClientes.rowAtPoint(e.getPoint());
                int columna_point = 1;

                if (fila_point > -1) {
                    user_update = (String) modelo.getValueAt(fila_point, columna_point);
                    DetalleCliente detalleCliente = new DetalleCliente();
                    detalleCliente.setVisible(true);
                }
            }
        });
           
    }

    public void MostrarClientes(JTable tablaClientes, String filtro) {
        Conexion objetoConexion = new Conexion();
        DefaultTableModel modelo = new DefaultTableModel();

        modelo.addColumn("ID");
        modelo.addColumn("DNI");
        modelo.addColumn("NOMBRES");
        modelo.addColumn("APELLIDO");
        modelo.addColumn("LOCALIDAD");
        modelo.addColumn("BARRIO");
        modelo.addColumn("CALLE");
        modelo.addColumn("NUMERO");
        modelo.addColumn("ENTRE CALLES");
        modelo.addColumn("EMAIL");
        modelo.addColumn("FECHA ALTA");
        modelo.addColumn("CALIFICACION");

        tablaClientes.setModel(modelo);

        String sql = "SELECT * FROM clientes";

        // Si el filtro no está vacío, agregar la condición de búsqueda
        if (!filtro.isEmpty()) {
            sql += " WHERE nombres LIKE '%" + filtro + "%' OR apellidos LIKE '%" + filtro + "%' OR dni LIKE '%" + filtro + "%'";
        }

        String[] datos = new String[12];
        Statement st;
        try {
            st = objetoConexion.establecerConexion().createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                datos[0] = rs.getString(1); // ID
                datos[1] = rs.getString(2); // DNI
                datos[2] = rs.getString(3); // Nombres
                datos[3] = rs.getString(4); // Apellido
                datos[4] = rs.getString(5); // Localidad
                datos[5] = rs.getString(6); // Barrio
                datos[6] = rs.getString(7); // Calle
                datos[7] = rs.getString(8); // Número
                datos[8] = rs.getString(9); // Entre calles
                datos[9] = rs.getString(10); // Email
                datos[10] = rs.getString(11); // Fecha Alta
                datos[11] = rs.getString(12); // Calificación
                modelo.addRow(datos);
            }
            tablaClientes.setModel(modelo);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al mostrar los datos: " + e);
            System.out.println(e);
        } finally {
            objetoConexion.cerrarConexion();
        }
    }

    public void InsertarCliente(JTextField dni, JTextField nombres, JTextField apellidos, JTextField localidad, JTextField barrio, JTextField calle, JTextField numero, JTextField entre_calles, JTextField email, String fecha_alta, int calificacion, JTextField cod_area1, JTextField numero1, JTextField cod_area2, JTextField numero2, JTextField cod_area3, JTextField numero3) {
        Conexion objetoConexion = new Conexion();
        Connection conexion = null;
        PreparedStatement psVerificarDni = null;
        PreparedStatement psCliente = null;
        PreparedStatement psTelefono = null;
        ResultSet rsDni = null;
        ResultSet rs = null;

        try {
            conexion = objetoConexion.establecerConexion();
            conexion.setAutoCommit(false);  // Start transaction

            // Verificar si el DNI ya existe
            String consultaDni = "SELECT COUNT(*) FROM clientes WHERE dni = ?";
            psVerificarDni = conexion.prepareStatement(consultaDni);
            psVerificarDni.setString(1, dni.getText());
            rsDni = psVerificarDni.executeQuery();
            rsDni.next();
            int cuentaDni = rsDni.getInt(1);
            if (cuentaDni > 0) {
                JOptionPane.showMessageDialog(null, "El cliente con DNI " + dni.getText() + " ya está registrado.");
                return;
            }

            // Insertar el cliente
            String consultaCliente = "INSERT INTO clientes(dni, nombres, apellidos, localidad, barrio, calle, numero, entre_calles, email, fecha_alta, calificacion) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
            psCliente = conexion.prepareStatement(consultaCliente, Statement.RETURN_GENERATED_KEYS);
            psCliente.setString(1, dni.getText());
            psCliente.setString(2, nombres.getText());
            psCliente.setString(3, apellidos.getText());
            psCliente.setString(4, localidad.getText());
            psCliente.setString(5, barrio.getText());
            psCliente.setString(6, calle.getText());
            psCliente.setString(7, numero.getText());
            psCliente.setString(8, entre_calles.getText());
            psCliente.setString(9, email.getText());
            psCliente.setString(10, fecha_alta);
            psCliente.setInt(11, calificacion);
            psCliente.executeUpdate();

            // Obtener el ID del cliente insertado
            rs = psCliente.getGeneratedKeys();
            if (rs.next()) {
                int idCliente = rs.getInt(1);
                // Insertar los números de teléfono
                String consultaTelefono = "INSERT INTO telefonos(id_cliente, cod_area, numero) VALUES (?,?,?)";
                psTelefono = conexion.prepareStatement(consultaTelefono);

                // Teléfono 1
                insertarTelefono(psTelefono, idCliente, cod_area1, numero1);

                // Teléfono 2
                insertarTelefono(psTelefono, idCliente, cod_area2, numero2);

                // Teléfono 3
                insertarTelefono(psTelefono, idCliente, cod_area3, numero3);

                psTelefono.executeBatch();
            }

            conexion.commit();  // Commit transaction
            JOptionPane.showMessageDialog(null, "Cliente y teléfonos guardados correctamente");
        } catch (Exception e) {
            try {
                if (conexion != null) {
                    conexion.rollback();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            JOptionPane.showMessageDialog(null, "No se pudo guardar el cliente: " + e.toString());
            e.printStackTrace();
        } finally {
            cerrarRecursos(rsDni, rs, psVerificarDni, psCliente, psTelefono, conexion);
        }
    }

    private void insertarTelefono(PreparedStatement ps, int idCliente, JTextField codArea, JTextField numero) throws SQLException {
        if (!codArea.getText().isEmpty() && !numero.getText().isEmpty()) {
            ps.setInt(1, idCliente);
            ps.setString(2, codArea.getText());
            ps.setString(3, numero.getText());
            ps.addBatch();
        }
    }

    private void cerrarRecursos(AutoCloseable... recursos) {
        for (AutoCloseable recurso : recursos) {
            if (recurso != null) {
                try {
                    recurso.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
