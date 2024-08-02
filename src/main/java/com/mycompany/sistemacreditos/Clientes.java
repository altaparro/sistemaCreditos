/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemacreditos;

import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
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
        modelo.addColumn("NOMBRES");
        modelo.addColumn("APELLIDO");
        modelo.addColumn("LOCALIDAD");
        
        tablaClientes.setModel(modelo);
        
        sql="SELECT * FROM clientes";
        
        String[] datos = new String[4];
        Statement st;
        try {
            st= objetoConexion.establecerConexion().createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            while(rs.next()){
                
                datos[0]= rs.getString(1);
                datos[1]= rs.getString(2);
                datos[2]= rs.getString(3);
                datos[3]= rs.getString(4);             
                
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
    
}
