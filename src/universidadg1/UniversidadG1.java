/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package universidadg1;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import universidadg1.accesoadatos.AlumnoData;
import universidadg1.accesoadatos.Conexion;
import universidadg1.accesoadatos.InscripcionData;
import universidadg1.accesoadatos.MateriaData;
import universidadg1.entidades.Alumno;
import universidadg1.entidades.Inscripcion;
import universidadg1.entidades.Materia;

/**
 *
 * @author MI EQUIPO
 */
public class UniversidadG1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //  Connection con= Conexion.getConnection();   

        //AGREGAR WACHOS
      //  Alumno a1 = new Alumno(32, "gutierrez", "ana", LocalDate.of(1991, 8, 21), true);
//        Alumno a2 = new Alumno(66651777, "gomez", "sandra", LocalDate.of(1997, 3, 6), true);
//  AlumnoData alumnoData = new AlumnoData();
//          alumnoData.guardarAlumno(a1);
//          alumnoData.guardarAlumno(a2);

        //  MODIFICAR ALUMNOS
//      
//      Alumno sebAlumno=new Alumno(1,32751482, "castro", "sebas", LocalDate.of(1987,7,1), true);
//      AlumnoData alumnoData= new AlumnoData();
//      alumnoData.modificarAlumno(sebAlumno);;


        //borrar ALUMNOS
        // alumnoData.borradoLogicoAlumno(1);
        
        
        //mostrar ALUMNOS por ID
//        Alumno alumnoEncontradoID=alumnoData.buscarAlumnoPorId(1);
//        System.out.println("-------------DATOS ALUMNO ENCONTRADO ID-----------------");
//        System.out.println("    ");
//        System.out.println("DNI"+ alumnoEncontradoID.getDni()); 
//        System.out.println("Nombre: "+ alumnoEncontradoID.getNombre());
//        System.out.println("Apellido: "+ alumnoEncontradoID.getApellido());
//        System.out.println("    ");


        //  mostrar ALUMNOS por DNI
//        Alumno alumnoEncontradoDNI = alumnoData.buscarAlumnoPorDni(32751482);
//        if (alumnoEncontradoDNI != null) {
//            System.out.println("------------DATOS ALUMNO ENCONTRADO DNI-----------------");
//            System.out.println("    ");
//            System.out.println("Nombre: " + alumnoEncontradoDNI.getNombre());
//            System.out.println("Apellido: " + alumnoEncontradoDNI.getApellido());
//            System.out.println("DNI: " + alumnoEncontradoDNI.getDni());
//        } else {
//            System.out.println("");
//            System.out.println(" No encontré nada che por DNI...");
//        }

//          mostrar LISTA ALUMNOS
//       AlumnoData alumnosData= new AlumnoData();
//        List<Alumno> listaAlumnos = alumnosData.listaAlumnos();
//
//      for (Alumno alumno : listaAlumnos) {
//          System.out.println("Nombre: " + alumno.getNombre() + ", Apellido "+ alumno.getApellido()+" ID: " + alumno.getIdAlumno() + ", DNI: " + alumno.getDni());
//}
//----------------------------MATERIA DATA----------------------------------------------------------------------
        // Para guardar Materia
//
//        Materia m1 = new Materia("Ciberseguridad", 2023, true);
//        MateriaData materiaData = new MateriaData();
//        materiaData.guardarMateria(m1);
        
     //------------------------------------------- Para buscar Materia por id

//        MateriaData data= new MateriaData();
//        Materia materiaEncontrada=data.buscarMateriaPorId(3);
//        if(materiaEncontrada!=null){
//            System.out.println("Nombre: "+ materiaEncontrada.getNombre());
//        }else{
//            System.out.println("no se encntró naranja");
//        }
        

//------------------------------------------------ Para actualizar Materia por id



//MateriaData materiaData= new MateriaData();

//Materia materia= new Materia(1, "lab 1",2023, true);
//materiaData.actualizarMateria(materia);


//---------------------------------------------para eliminado logico de materia

//materiaData.eliminarMateria(1);






//-------------------------------------- mostrar lista de materia
//MateriaData materiaData= new MateriaData();
//List<Materia> materiasActivas= materiaData.obtenerMateriasActivas();
//
//   for (Materia materia : materiasActivas) {
//    System.out.println("ID: " + materia.getIdMateria() + ", Nombre: " + materia.getNombre() + ", Año: " + materia.getAnioMateria());
//    }


//-------------------------------------------guardar inscripcion ---------------------------------
        AlumnoData ad = new AlumnoData();
        MateriaData md = new MateriaData();
        InscripcionData id = new InscripcionData();

       
        Alumno sebaAlumno = ad.buscarAlumnoPorId(2);
        Materia lab2Materia = md.buscarMateriaPorId(1);

       
        if (sebaAlumno != null && lab2Materia != null) {
            
            Inscripcion inscripcion = new Inscripcion(7, sebaAlumno, lab2Materia);

            id.guardarInscripcion(inscripcion);
            
      
        } else {
            System.out.println("El ID del alumno o el de la materia ingresada está dado de baja o no existe.");
        }
    }



//--------------------------------------------actualizar nota----------------------------------------


//InscripcionData id= new InscripcionData();
//
//id.actualizarNota(7, 1, 2);


//---------------------------------------borrar inscripcion con delete
//InscripcionData id= new InscripcionData();
//id.borrarInscripcion(1, 2);


//--------------------------------------Lista de inscripcion
        
//        InscripcionData id= new InscripcionData();
//        List <Inscripcion> inscripcions= id.listaInscripciones();        
//            for (Inscripcion inscripcion : inscripcions){
//            System.out.println("ID Inscripción: " + inscripcion.getIdInscripcion());
//            System.out.println("Nota: " + inscripcion.getNota());
//            System.out.println("ID Alumno: " + inscripcion.getAlumno().getIdAlumno());
//            System.out.println("ID Materia: " + inscripcion.getMateria().getIdMateria());
//            System.out.println("-----------------------------------");
//        }


  //--------------------------------------Lista de inscripcion x alumnos
  
//  
//          InscripcionData id = new InscripcionData();
//          List<Inscripcion> listaInscripcionesPorAlumno  =id.inscripcionesIDAlumno(9);// 
//          if (listaInscripcionesPorAlumno.isEmpty()!=true){
//               for (Inscripcion x: listaInscripcionesPorAlumno) {//              
//              System.out.println("Nombre alumno/a " + x.getAlumno().getNombre() + " "+ x.getAlumno().getApellido()+ " se inscribió a " +x.getMateria().getNombre());
//          }
//  
//        }else{
//              System.out.println("El id alumno que ingresaste no está inscripto en ninguna materia");
//          }
            

//--------------------------------------Lista de materias cursadas    
            
//            InscripcionData id = new InscripcionData();
//            
//            List<Materia> materias=id.materiasInscritasPorAlumno(5);
//            if (materias.isEmpty()!=true){
//                for (Materia x: materias){
//                System.out.println("nombre de materia: "+ x.getNombre() + " cuyo Id es: "+x.getIdMateria() + " y pertenece al año  "+x.getAnioMateria());
//            }
//            }else{
//                 System.out.println("El id alumno es incorrecto");
//            }
            
//--------------------------------------Lista de materias NO cursadas    
//            
//            InscripcionData id = new InscripcionData();
//            
//            List<Materia> materias=id.materiasNoInscritasPorAlumno(2);
//            if (materias.isEmpty()!=true){
//                for (Materia x: materias){
//                System.out.println("la materia que no cursa es: "+ x.getNombre() + " cuyo Id es: "+x.getIdMateria() + " y pertenece al año  "+x.getAnioMateria());
//            }
//            }else{
//                 System.out.println("El id alumno es incorrecto");
//            }
//--------------------------------------Lista de alumnos por materias    

//
//    InscripcionData id = new InscripcionData();
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("ingresa id de materia");
//        System.out.println("");
//int numero= scanner.nextInt();
//
//  List<Alumno> alumnosCursandoMateria = id.alumnosPorMateria(numero);
//
//        if (alumnosCursandoMateria.isEmpty()) {
//            System.out.println("No hay alumnos cursando la materia con idMateria ingresado ");
//        } else {
//            System.out.println("Alumnos cursando la materia con idMateria " + numero + ":");
//            for (Alumno alumno : alumnosCursandoMateria) {
//                System.out.println("ID: " + alumno.getIdAlumno() +
//                                   ", DNI: " + alumno.getDni() +
//                                   ", Nombre: " + alumno.getNombre() +
//                                   ", Apellido: " + alumno.getApellido() +
//                                   ", Fecha de Nacimiento: " + alumno.getFechaNac()+
//                                   ", Estado: " + (alumno.isActivo() ? "Activo" : "Inactivo"));
//            }
//        }






}
    
    
    
    

