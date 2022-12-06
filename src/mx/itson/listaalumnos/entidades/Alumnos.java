/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.itson.listaalumnos.entidades;


import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Fernanda Barrón
 */
public class Alumnos {
    
    int codigo;
    String nombreALumnos;
    String apellidosAlumnos;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombreALumnos() {
        return nombreALumnos;
    }

    public void setNombreALumnos(String nombreALumnos) {
        this.nombreALumnos = nombreALumnos;
    }

    public String getApellidosAlumnos() {
        return apellidosAlumnos;
    }

    public void setApellidosAlumnos(String apellidosAlumnos) {
        this.apellidosAlumnos = apellidosAlumnos;
    }
    
    /**
     * Método para enlazar los datos y establecer la conexión con la base de datos para que se muestren en la aplicación
     * @param paramNombres
     * @param paramApellidos 
     */
    
    public void InsertarAlumno(JTextField paramNombres, JTextField paramApellidos){
    
        /**
         * Lo que recibe los parámetros lo envía a los "set"
         */
        
        setNombreALumnos(paramNombres.getText());
        setApellidosAlumnos(paramApellidos.getText());
        
        Conexion objetoConexion = new Conexion();
        
        /**
         * Consulta para insertar a la base de datos
         */
        
        String consulta ="insert into Alumnos (nombres,apellidos) values (?,?);";
        
        try {
            
            /**
             * Llama a los procedimientos almacenados
             */
            
            CallableStatement cs = objetoConexion.estableceConexion().prepareCall(consulta);
            
            cs.setString(1, getNombreALumnos());
            cs.setString(2, getApellidosAlumnos());
            
            cs.execute();
            
            
            JOptionPane.showMessageDialog(null, "Se insertó correctamente el alumno");
            
            
        } catch (Exception e) {
             JOptionPane.showMessageDialog(null, "No Se insertó correctamente el alumno, error: "+e.toString());
        }
        
        
    
    }
    
    public void MostrarAlumnos(JTable paramTablaTotalAlumnos){
    
        Conexion objetoConexion = new Conexion();
        
        DefaultTableModel modelo = new DefaultTableModel();
        
        TableRowSorter<TableModel> OrdenarTabla= new TableRowSorter<TableModel>(modelo);
        paramTablaTotalAlumnos.setRowSorter(OrdenarTabla);
        
        String sql="";
        
        modelo.addColumn("Id");
        modelo.addColumn("Nombres");
        modelo.addColumn("Apellidos");
        
        paramTablaTotalAlumnos.setModel(modelo); 
        
        
        sql ="select * from Alumnos;";
        
        /**
         * Creamos un vector y usamos Statement para procesar la sentencia estática y obtener sus resultados
         */
        String[] datos = new String[3];
        Statement st;
        
        try {
            st= objetoConexion.estableceConexion().createStatement();
            
            
            ResultSet rs = st.executeQuery(sql);
            
            while(rs.next()){
            
                datos[0]=rs.getString(1);
                datos[1]=rs.getString(2);
                datos[2]=rs.getString(3);
                
                modelo.addRow(datos);
            }
            
            paramTablaTotalAlumnos.setModel(modelo);
            
        } catch (Exception e) {
            
            JOptionPane.showMessageDialog(null,"No se pudo mostrar los registros, error: "+ e.toString());
        }
     
    }
    
    public void SeleccionarAlumno(JTable paramTablaAlumnos, JTextField paramId, JTextField paramNombres, JTextField paramApellidos){
    
        try {
            int fila = paramTablaAlumnos.getSelectedRow();
            
            if (fila >=0) {
                
                paramId.setText((paramTablaAlumnos.getValueAt(fila, 0).toString()));
                paramNombres.setText((paramTablaAlumnos.getValueAt(fila, 1).toString()));
                paramApellidos.setText((paramTablaAlumnos.getValueAt(fila, 2).toString()));
            }
            
            else
            {
                JOptionPane.showMessageDialog(null,"Fila no seleccionada");
            }
        } catch (Exception e) {
            
                JOptionPane.showMessageDialog(null,"Error de seleccion, error: "+ e.toString());
        }
        
    }
    
    
    public void ModificarAlumnos (JTextField paramCodigo, JTextField paramNombres, JTextField paramApellidos){
    
        setCodigo(Integer.parseInt(paramCodigo.getText()));
        setNombreALumnos(paramNombres.getText());
        setApellidosAlumnos(paramApellidos.getText());
        
        
        Conexion objetoConexion = new Conexion();
        
        String consulta = "UPDATE Alumnos SET alumnos.nombres = ?, alumnos.apellidos =? WHERE alumnos.id=?;";
        
        try {
            
            CallableStatement cs = objetoConexion.estableceConexion().prepareCall(consulta);
            
            cs.setString(1, getNombreALumnos());
            cs.setString(2, getApellidosAlumnos());
            cs.setInt(3, getCodigo());
            
            
            cs.execute();
            
            JOptionPane.showMessageDialog(null,"Modificación Exitosa");
            
            
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"No se modificó, error:"+ e.toString());
        }
    }
    
    public void EliminarAlumnos(JTextField paramCodigo){
    
        /**
         * Convertir el valor int a string
         */
        
        setCodigo(Integer.parseInt(paramCodigo.getText()));
        
        Conexion objetoConexion = new Conexion();
        
        String consulta = "DELETE FROM Alumnos WHERE alumnos.id=?;";
        
        try {
             CallableStatement cs = objetoConexion.estableceConexion().prepareCall(consulta);
             cs.setInt(1, getCodigo());
             cs.execute();
             
             JOptionPane.showMessageDialog(null,"Se eliminó correctamente el Alumno");
            
            
        } catch (SQLException e) {
            
            JOptionPane.showMessageDialog(null,"No se pudo eliminar, error: "+ e.toString());
        }
        
    }
    
    
}
