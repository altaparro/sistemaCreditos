/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemacreditos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author matias
 */
public class Clientes {
    
    public void MostrarClientes(JTable tablaClientes){
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
        
        sql="SELECT * FROM clientes";
        
        String[] datos = new String[12];
        Statement st;
        try {
            st= objetoConexion.establecerConexion().createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            while(rs.next()){
                
                datos[0]= rs.getString(1);
                datos[1]= rs.getString(2);
                datos[2]= rs.getString(3);
                datos[3]= rs.getString(4); 
                datos[4]= rs.getString(5); 
                datos[5]= rs.getString(6); 
                datos[6]= rs.getString(7); 
                datos[7]= rs.getString(8); 
                datos[8]= rs.getString(9); 
                datos[9]= rs.getString(10); 
                datos[10]= rs.getString(11); 
                datos[11]= rs.getString(12); 
                modelo.addRow(datos);
            }
            tablaClientes.setModel(modelo);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al mostrar los datos "+ e);
            System.out.println(e);
        }
        finally{
            objetoConexion.cerrarConexion();
        }
                
    }
    
        public void InsertarCliente(JTextField dni, JTextField nombres, JTextField apellidos, JTextField localidad, JTextField barrio, JTextField calle, JTextField numero, JTextField entre_calles, JTextField email, String fecha_alta, int calificacion){
            Conexion objetoConexion = new Conexion();
            
            String consulta = "insert into clientes(dni, nombres, apellidos, localidad, barrio, calle, numero, entre_calles, email, fecha_alta, calificacion) values (?,?,?,?,?,?,?,?,?,?,?)";
             
            try {
                 PreparedStatement ps = objetoConexion.establecerConexion().prepareStatement(consulta);
                 ps.setString(1, dni.getText());
                 ps.setString(2, nombres.getText());
                 ps.setString(3, apellidos.getText());
                 ps.setString(4, localidad.getText());
                 ps.setString(5, barrio.getText());
                 ps.setString(6, calle.getText());
                 ps.setString(7, numero.getText());
                 ps.setString(8, entre_calles.getText());
                 ps.setString(9, email.getText());
                 ps.setString(10, fecha_alta);
                 ps.setInt(11, calificacion);
                 
                 ps.execute();
                 
                 JOptionPane.showMessageDialog(null, "Se guardo correctamente el cliente");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "No se guardo el cliente"+ e.toString());
            } finally{
                objetoConexion.cerrarConexion();
            }
        }
    
}
