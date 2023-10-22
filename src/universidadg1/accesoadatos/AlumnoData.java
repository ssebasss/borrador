/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package universidadg1.accesoadatos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import static java.sql.Statement.RETURN_GENERATED_KEYS;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import universidadg1.entidades.Alumno;

/**
 *
 * @author MI EQUIPO
 */
public class AlumnoData {

    
    private Connection con=null;
    
    
    public AlumnoData(){
        con=Conexion.getConexion();
      
    }
    
    
    
    public void guardarAlumno(Alumno alumno){
    
            String query = "INSERT INTO alumno (dni, apellido,nombre, fechaNacimiento, estado) "
                    + "VALUES (?, ?,?,?,?)";
            
                try{
       PreparedStatement preparedStatement = con.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
            
        preparedStatement.setInt(1, alumno.getDni());
        preparedStatement.setString(2, alumno.getApellido());
        preparedStatement.setString(3, alumno.getNombre());
        preparedStatement.setDate(4, Date.valueOf(alumno.getFechaNac())); 
        preparedStatement.setBoolean(5, alumno.isActivo());
        preparedStatement.executeUpdate();
        
        
        ResultSet rs =preparedStatement.getGeneratedKeys();
        if(rs.next()){
            alumno.setIdAlumno(rs.getInt(1));
             JOptionPane.showMessageDialog(null, "Se agregó el alumno");
        }
        
        preparedStatement.close();
        
    } catch (SQLException e) {
             JOptionPane.showMessageDialog(null, "error al acceder a la tabla alumno");
    }}
    
    
    public void modificarAlumno(Alumno alumno) {
        String query = "UPDATE alumno SET dni=?, apellido=?, nombre=?, fechaNacimiento=?"
                + " WHERE idAlumno=?";
        
        try {
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, alumno.getDni());
            preparedStatement.setString(2, alumno.getApellido());
            preparedStatement.setString(3, alumno.getNombre());
            preparedStatement.setDate(4, Date.valueOf(alumno.getFechaNac()));       
            preparedStatement.setInt(5, alumno.getIdAlumno()); 
            
            int filasModificadas = preparedStatement.executeUpdate();
            
            if (filasModificadas > 0) {
                JOptionPane.showMessageDialog(null, "Se modificaron datos del alumno");
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró el alumno con el ID que ingresaste");
            }
            
            preparedStatement.close();
        } catch (SQLException e) {
         
            JOptionPane.showMessageDialog(null, "Error para modificar datos del alumno"+ e.getMessage());
        }
    }

     public void borradoLogicoAlumno(int idAlumno) {
        String query = "UPDATE alumno SET estado = false WHERE idAlumno = ?";
        
        try {
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, idAlumno); // Asumiendo que idAlumno es el ID del alumno que deseas borrar
            
            int filasModificadas = preparedStatement.executeUpdate();
            
            if (filasModificadas > 0) {
                JOptionPane.showMessageDialog(null, "El alumno fue borrado");
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró el alumno con el ID proporcionado");
            }
            
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al borrar el alumno");
        }
    }
     
     
       
     public Alumno buscarAlumnoPorId(int idAlumno) {
        Alumno alumno = null;
        String query = "SELECT * FROM alumno WHERE idAlumno = ?";
        
        try {
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, idAlumno);
            
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int dni = resultSet.getInt("dni");
                String apellido = resultSet.getString("apellido");
                String nombre = resultSet.getString("nombre");
                java.sql.Date fechaNacimiento = resultSet.getDate("fechaNacimiento");
                boolean estado = resultSet.getBoolean("estado");

          
                alumno = new Alumno(dni, apellido, nombre, fechaNacimiento.toLocalDate(), estado);
                alumno.setIdAlumno(idAlumno);
            }
            
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
           
        }
        
        return alumno;
    }
     
     
     
        
   public Alumno buscarAlumnoPorDni(int dni) {
        Alumno alumno = null;
        String query = "SELECT * FROM alumno WHERE dni = ?";
        
        try {
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, dni);
            
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int idAlumno = resultSet.getInt("iDalumno");
                String apellido = resultSet.getString("apellido");
                String nombre = resultSet.getString("nombre");
                java.sql.Date fechaNacimiento = resultSet.getDate("fechaNacimiento");
                boolean estado = resultSet.getBoolean("estado");

                // Construyo objeto Alumno con los datos que saque de la base de datos
                alumno = new Alumno();
                alumno.setDni(dni);
                alumno.setApellido(apellido);
                alumno.setNombre(nombre);
                alumno.setFechaNac(fechaNacimiento.toLocalDate());
                alumno.setActivo(estado);
                alumno.setIdAlumno(idAlumno);
            }
            
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            
        }
        
        return alumno;
    }
        
        
    
      public List<Alumno> listaAlumnos() {
        List<Alumno> listaAlumnos = new ArrayList<>();
        String query = "SELECT * FROM alumno WHERE estado = 1";
        
        try {
            PreparedStatement preparedStatement = con.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()) {
                int idAlumno = resultSet.getInt("iDalumno");
                int dni = resultSet.getInt("dni");
                String apellido = resultSet.getString("apellido");
                String nombre = resultSet.getString("nombre");
                java.sql.Date fechaNacimiento = resultSet.getDate("fechaNacimiento");
                boolean estado = resultSet.getBoolean("estado");

                Alumno alumno = new Alumno();
                alumno.setIdAlumno(idAlumno);
                alumno.setDni(dni);
                alumno.setApellido(apellido);
                alumno.setNombre(nombre);
                alumno.setFechaNac(fechaNacimiento.toLocalDate());
                alumno.setActivo(estado);
                
                listaAlumnos.add(alumno);
            }
            
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return listaAlumnos;
    }
      
          
        
        
        
        
        
        
        
        
        
}
   
   

