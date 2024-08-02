package com.mycompany.sistemacreditos;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;


public class Conexion {
 Connection conectar = null;
 
 String bd = "BDUsuarios.db";
 String cadena = "jdbc:sqlite:"+ System.getProperty("user.dir")+"/"+bd;
 
 public Connection establecerConexion () {
     
     Connection conectar = null;
     
     try {
         Class.forName("org.sqlite.JDBC");
         conectar = DriverManager.getConnection(cadena);
         JOptionPane.showMessageDialog(null, "Se conecto la base correctamente");
     } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Error en la conexion de la base de datos, "+ e.toString());
     }
     return conectar;
 }
 
 public void cerrarConexion(){
     try {
         if(conectar != null){
             conectar.close();
             JOptionPane.showMessageDialog(null, "La base se cerro correctamente");
         }
     } catch (Exception e) {
         JOptionPane.showMessageDialog(null, "No se cerro la base de datos, "+ e.toString());
     }
 }
 
}
