/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package universidad;

import Modelo.Alumno;
import Modelo.AlumnoData;
import Modelo.Conexion;
import Modelo.Cursada;
import Modelo.CursadaData;
import Modelo.Materia;
import Modelo.MateriaData;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author programador
 */
public class UNIVERSIDAD {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            // TODO code application logic here
            Conexion con = new Conexion();
           // Alumno a1 = new Alumno("Juan","Mitre 34",123,LocalDate.of(2015, 11, 23));
            //Alumno a2 = new Alumno("Jose","Pringles 67",567,LocalDate.of(1981, 05, 26));
           // Alumno a3 = new Alumno();
            Materia m1 = new Materia("Matematicas","Profe Sergio","Primer Cuatrimestre");
            Materia m2 = new Materia("Lengua","Profe Juana","Segundo Cuatrimestre");
           // Cursada c1 = new Cursada(a1,m1,10.0);
           // Cursada c2 = new Cursada(a2,m2,8);*/
           // AlumnoData ad = new AlumnoData(con);
            MateriaData md = new MateriaData(con);
           // CursadaData cd = new CursadaData(con);
            
            //ad.guardarAlumno(a1);
           // ad.guardarAlumno(a2);
            //System.out.println("hola"+a1.getNombre());
            
            md.guardarMateria(m1);
            md.guardarMateria(m2);
            
            //cd.guardarCursada(c1);
            //cd.guardarCursada(c2);
            //ad.guardarAlumno(a1);
            //ad.guardarAlumno(a2);
            
            /*List<Materia> materias = new ArrayList<Materia>();
            materias = md.obtenerMaterias();
            for(int i= 0; i<materias.size(); i++)
            {
                System.out.println("Nombre: "+materias.get(i).getNombre()+" Responsable: "+materias.get(i).getResponsable()+" Periodo: "+materias.get(i).getPeriodo());
            }
            */
            Materia mat = new Materia();
            mat.setNombre("Informatica");
            mat.setResponsable("Lorena");
            mat.setPeriodo("Primer Cuatrimestre");
            mat.setCod(2);
            md.actualizarMateria(mat);
           
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UNIVERSIDAD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
