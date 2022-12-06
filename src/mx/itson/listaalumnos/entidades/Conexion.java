/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.itson.listaalumnos.entidades;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 *
 * @author Fernanda Barrón
 */
class Conexion {

    /**
     * Crear la conexión de la base de datos
     */
    
    
    Connection conectar = null;
    
    /** 
     * Declaramos las variables de la conexión de la base de datos
     */
    
    String usuario = "root";
    String contraseña = "palomitajun1";
    String bd = "bdescuela";
    String ip = "localhost";
    String puerto = "3308";
    
    /**
     * Ruta de la conexión
     */
    String cadena = "jdbc:mysql://"+ip+":"+puerto+"/"+bd;
    
    /**
     * Método de la conexión
     * @return Conexión abierta
     */
    public Connection estableceConexion(){
    
        try {
            
            Class.forName("com.mysql.cj.jdbc.Driver");
            conectar = DriverManager.getConnection(cadena,usuario,contraseña);
            JOptionPane.showMessageDialog(null,"La conexión se ha realizado con éxito");
            
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error al conectarse a la base de datos, erro: "+ e.toString());
        }
        return conectar;
    }
    
}
