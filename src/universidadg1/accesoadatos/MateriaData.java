/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package universidadg1.accesoadatos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import universidadg1.entidades.Materia;

/**
 *
 * @author Gonza
 */
public class MateriaData {
    private Connection con=null;

    public MateriaData() {
        this.con= Conexion.getConexion();
    }
    
    public void guardarMateria(Materia materia){
        
        String sql ="INSERT INTO `materia`(`nombre`, `año`, `estado`)"
                + "VALUES(?,?,?)";

        try {
            PreparedStatement ps = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, materia.getNombre());
            ps.setInt(2, materia.getAnioMateria());
            ps.setBoolean(3, materia.isActivo());
            ps.executeUpdate();
            
            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next()){
                materia.setIdMateria(1);
                JOptionPane.showMessageDialog(null, "Materia agregada");
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(MateriaData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
   public void actualizarMateria(Materia materia) {
        String query = "UPDATE materia SET nombre = ?, año = ?, estado = ? WHERE idMateria = ?";
        
        try {
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, materia.getNombre());
            preparedStatement.setInt(2, materia.getAnioMateria());
            preparedStatement.setBoolean(3, materia.isActivo());
            preparedStatement.setInt(4, materia.getIdMateria());
            preparedStatement.executeUpdate();
            
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            
        }
    }

    
 public void eliminarMateria(int idMateria) {
        String query = "UPDATE materia SET estado = 0 WHERE idMateria = ?";
        
        try {
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, idMateria);
            preparedStatement.executeUpdate();
            JOptionPane.showMessageDialog(null, "materia eliminada logicamente");
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "No se pudo eliminar la materia", "Error", JOptionPane.ERROR_MESSAGE);
            
        }
    }
    
    
    
    
    
      public Materia buscarMateriaPorId(int idMateria) {
        Materia materia = null;
        String query = "SELECT * FROM materia WHERE idMateria = ? AND estado = 1";
        
        try {
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, idMateria);
            
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String nombre = resultSet.getString("nombre");                
                int año = resultSet.getInt("año");
                boolean estado = resultSet.getBoolean("estado");

                materia = new Materia();
                materia.setIdMateria(idMateria);
                materia.setNombre(nombre);
                materia.setAnioMateria(año);
                materia.setActivo(estado);
            }
            
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return materia;
    }

    
    public List<Materia> obtenerMateriasActivas() {
        List<Materia> materiasActivas = new ArrayList<>();
        String query = "SELECT * FROM materia WHERE estado = 1";
        
        try {
            PreparedStatement preparedStatement = con.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()) {
                int idMateria = resultSet.getInt("idMateria");
                String nombre = resultSet.getString("nombre");
                int año = resultSet.getInt("año");
                boolean activo = resultSet.getBoolean("estado");

                Materia materia = new Materia();
                materia.setIdMateria(idMateria);
                materia.setNombre(nombre);
                materia.setAnioMateria(año);
                materia.setActivo(activo);
                
                materiasActivas.add(materia);
            }            
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
                        JOptionPane.showMessageDialog(null, "No se pudo mostrar la losta, fijate que onda", "Error", JOptionPane.ERROR_MESSAGE);
        }
        
        return materiasActivas;
    }
    
}
