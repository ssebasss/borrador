
package universidadg1.accesoadatos;

import java.sql.Connection;
import java.sql.*;
import java.time.LocalDate;
import universidadg1.entidades.Inscripcion;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import universidadg1.entidades.Alumno;
import universidadg1.entidades.Materia;
/**
 *
 * @author MI EQUIPO
 */
public class InscripcionData {
    private Connection con= null;
    private AlumnoData ad=new AlumnoData();
    private MateriaData md= new MateriaData();
    
            

    public InscripcionData() {
        this.con= Conexion.getConexion();
    }
    
    public void guardarInscripcion(Inscripcion insc){
        String sql= "INSERT INTO inscripcion(nota, idAlumno, idMateria)"
                +"VALUES(?,?,?)";
        
        try {
            PreparedStatement ps = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
             ps.setDouble(1, insc.getNota());
            ps.setInt(2, insc.getAlumno().getIdAlumno());
            ps.setInt(3, insc.getMateria().getIdMateria());
           
            ps.executeUpdate();
            ResultSet rs= ps.getGeneratedKeys();
            if(rs.next()){
                insc.setIdInscripcion(rs.getInt(1));//solo hay una
                JOptionPane.showMessageDialog(null, "Inscripcion registrada");
            }
            
            ps.close();
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Inscripcion");
        }
 
    } 
 
    
    public void actualizarNota(double ingresadaNota, int idAlumno, int idMateria) {
        String query = "UPDATE inscripcion SET nota = ? WHERE idAlumno = ? AND idMateria = ?";      
        try {
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setDouble(1, ingresadaNota);
            preparedStatement.setInt(2, idAlumno);
            preparedStatement.setInt(3, idMateria);
          
            
            int filas=preparedStatement.executeUpdate();
            
            if (filas>0) {
                   JOptionPane.showMessageDialog(null, "Nota cambiada exitosamente che");
                
            }else{
                JOptionPane.showMessageDialog(null, "solo podes cambiar la nota. no seas   gil");

            }
            
            preparedStatement.close();
            
        } catch (SQLException e) {
            e.printStackTrace();           
        }
    }
    
    
    public void borrarInscripcion(int idAlumno, int idMateria) {
        String borrar = "DELETE FROM inscripcion WHERE idAlumno = ? AND idMateria = ?";
        
        try {
            PreparedStatement preparedStatement = con.prepareStatement(borrar);
            preparedStatement.setInt(1, idAlumno);
            preparedStatement.setInt(2, idMateria);
            int filas =preparedStatement.executeUpdate();
            
            if (filas >0) {
                JOptionPane.showInternalMessageDialog(null, "se borro la inscripcion seleccionada");
            }
            
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
             JOptionPane.showMessageDialog(null, "no pudiste borrar che...");
        }
    }
    
  public List<Inscripcion> listaInscripciones() {
        ArrayList<Inscripcion> listaInscripciones = new ArrayList<>();
        String query = "SELECT * FROM inscripcion";
        
        try {
            PreparedStatement preparedStatement = con.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            //https://youtu.be/3EIHnL7f2LI?list=PL8PA79VfAV0jlG5pQ1yReOzDTtQeBSiEj&t=372
            while (resultSet.next()) {
                int idInscripto = resultSet.getInt("idInscripto");
                double nota = resultSet.getDouble("nota");
                int idAlumno = resultSet.getInt("idAlumno");
                int idMateria = resultSet.getInt("idMateria");
             
                Alumno alumno = ad.buscarAlumnoPorId(idAlumno);
                Materia materia = md.buscarMateriaPorId(idMateria);

                Inscripcion inscripcion = new Inscripcion(idInscripto, nota, alumno, materia);
                System.out.println(inscripcion);
                
                listaInscripciones.add(inscripcion);
            }
            
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
           e.printStackTrace();
        }
        
        return listaInscripciones;
    }
    
  
  public List<Inscripcion> inscripcionesIDAlumno(int idAlumno) {
   
    List<Inscripcion> inscripcionesPorAlumno = new ArrayList<>();
    
   
    String query = "SELECT * FROM inscripcion WHERE idAlumno = ?";
    
    try {
       
        PreparedStatement preparedStatement = con.prepareStatement(query);
        preparedStatement.setInt(1, idAlumno);                
        ResultSet resultSet = preparedStatement.executeQuery();               
        while (resultSet.next()) {

            int idInscripcion = resultSet.getInt("idInscripto");
            double nota = resultSet.getDouble("nota");
            int idMateria = resultSet.getInt("idMateria");                     
            Alumno alumno = ad.buscarAlumnoPorId(idAlumno);
            Materia materia = md.buscarMateriaPorId(idMateria);                       
            Inscripcion inscripcion = new Inscripcion(idInscripcion, nota, alumno, materia);
            inscripcionesPorAlumno.add(inscripcion);
        }        
        resultSet.close();
        preparedStatement.close();
    } catch (SQLException e) {       
        JOptionPane.showMessageDialog(null, "Error al acceder a la tabla inscripcion por ID");
        e.printStackTrace();
    }
    return inscripcionesPorAlumno;
}

  
 public List<Materia> materiasInscritasPorAlumno(int idAlumno) {
        List<Materia> materiasInscritas = new ArrayList<>();
        String query = "SELECT m.idMateria, m.nombre, m.año, m.estado " +
                       "FROM materia m " +
                       "JOIN inscripcion i ON m.idMateria = i.idMateria " +
                       "WHERE i.idAlumno = ?";
        try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
            preparedStatement.setInt(1, idAlumno);
            ResultSet resultSet = preparedStatement.executeQuery();           
            while (resultSet.next()) {
                int idMateria = resultSet.getInt("idMateria");
                String nombre = resultSet.getString("nombre");
                int anioMateria = resultSet.getInt("año");
                boolean activo = resultSet.getBoolean("estado");

                Materia materia = new Materia(idMateria, nombre, anioMateria, activo);
                materiasInscritas.add(materia);
            }

        } catch (SQLException e) {            
            e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error al acceder a la tabla inscripcion por ID");

        }
        return materiasInscritas;
    }


public List<Materia> materiasNoInscritasPorAlumno(int idAlumno) {
    List<Materia> materiasNoInscritas = new ArrayList<>();

    // Consulta SQL para seleccionar las materias que no están en la tabla de inscripciones para el alumno específico
    String query = "SELECT idMateria, nombre, año, estado " +
                   "FROM materia " +
                   "WHERE idMateria NOT IN (SELECT idMateria FROM inscripcion WHERE idAlumno = ?)";

    try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
        // Establecer el valor del parámetro idAlumno en la consulta SQL
        preparedStatement.setInt(1, idAlumno);

        // Ejecutar la consulta y obtener el conjunto de resultados
        ResultSet resultSet = preparedStatement.executeQuery();

        // Iterar sobre los resultados y agregar las materias a la lista
        while (resultSet.next()) {
            int idMateria = resultSet.getInt("idMateria");
            String nombre = resultSet.getString("nombre");
            int anioMateria = resultSet.getInt("año");
            boolean activo = resultSet.getBoolean("estado");

            // Crear un objeto Materia con los datos obtenidos de la base de datos
            Materia materia = new Materia(idMateria, nombre, anioMateria, activo);
            
            // Agregar la materia a la lista de materias no inscritas
            materiasNoInscritas.add(materia);
        }

    } catch (SQLException e) {
        // Manejar excepciones, mostrar mensajes de error, etc.
        e.printStackTrace();
    }

    // Devolver la lista de materias no inscritas por el alumno específico
    return materiasNoInscritas;
}

public List<Alumno> alumnosPorMateria(int idMateria) {
    List<Alumno> alumnosCursandoMateria = new ArrayList<>();

    // Consulta SQL para seleccionar los alumnos que están cursando la materia con el idMateria dado
    String query = "SELECT a.idAlumno, a.dni, a.apellido, a.nombre, a.fechaNacimiento, a.estado " +
                   "FROM alumno a " +
                   "JOIN inscripcion i ON a.idAlumno = i.idAlumno " +
                   "WHERE i.idMateria = ? AND a.estado = 1";

    try (PreparedStatement preparedStatement = con.prepareStatement(query)) {    
        preparedStatement.setInt(1, idMateria);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            int idAlumno = resultSet.getInt("idAlumno");
            int dni = resultSet.getInt("dni");
            String apellido = resultSet.getString("apellido");
            String nombre = resultSet.getString("nombre");
            LocalDate fechaNacimiento = resultSet.getDate("fechaNacimiento").toLocalDate();
            boolean estado = resultSet.getBoolean("estado");

            Alumno alumno = new Alumno(idAlumno, dni, apellido, nombre, fechaNacimiento, estado);

            alumnosCursandoMateria.add(alumno);
        }

    } catch (SQLException e) {
       JOptionPane.showMessageDialog(null, "Error al acceder a la tabla");
        e.printStackTrace();
    }
    return alumnosCursandoMateria;
}




  
}
