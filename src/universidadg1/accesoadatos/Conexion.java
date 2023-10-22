package universidadg1.accesoadatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Conexion {

    private static final String URL = "jdbc:mariadb://localhost:3308/tp13_ulp";
    private static final String USUARIO = "root";
    private static  String PASSWORD = "";
    private static Connection conexion;


    private Conexion() {
    }
    
    public static Connection getConexion(){
        if(conexion == null){
             try{                           
           // cargar driver
            Class.forName("org.mariadb.jdbc.Driver");
           //conectar BD
          conexion = DriverManager.getConnection(URL , USUARIO, PASSWORD); 
           System.out.println("conectada BD");
        }catch(ClassNotFoundException cnf){
            JOptionPane.showMessageDialog(null,"Error al cargar driver");                     
        } catch (SQLException ex) {
           JOptionPane.showMessageDialog(null,"Error al conectar BD");
           if (ex.getErrorCode() == 1062) {
                    JOptionPane.showMessageDialog(null, "Ya exite un alumno con ese DNI");
                }
        }      
        }
        return conexion;      
    }
}
