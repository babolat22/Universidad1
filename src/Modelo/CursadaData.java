/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alumno
 */
public class CursadaData {
    private Connection con = null;
    private Conexion conexion;
    
    public CursadaData(Conexion conexion){
        try {
            this.conexion = conexion;
            con = conexion.getConexion();
        } catch (SQLException ex) {
            Logger.getLogger(CursadaData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void guardarCursada(Cursada cursada){
       
        String sql = "INSERT INTO cursa (id, cod, nota) VALUES ( ? , ? , ? );";
        try {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, cursada.getAlumno().getId());
            ps.setInt(2, cursada.getMateria().getCod());
            ps.setDouble(3, cursada.getNota());
            
            
            ps.executeUpdate();
            
            ResultSet rs = ps.getGeneratedKeys();

            if (rs.next()) {
                cursada.setId_cursa(rs.getInt(1));
            } else {
                System.out.println("No se pudo obtener el id luego de insertar una cursada");
            }
            ps.close();
    
        } catch (SQLException ex) {
            System.out.println("Error al insertar una cursada: " + ex.getMessage());
        }
    }
    
    public void borrarCursada(int id){    
        try {            
            String sql = "DELETE FROM Cursada  WHERE id=?;";          
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, id);
            ps.executeUpdate();
            ps.close();        
        } catch (SQLException ex) {
            Logger.getLogger(CursadaData.class.getName()).log(Level.SEVERE, null, ex);
        }        
        }
    
    public List<Cursada> obtenerCursadas(){
        List<Cursada> cursadas = new ArrayList<Cursada>();
            

        try {
            String sql = "SELECT * FROM cursa;";
            PreparedStatement statement = con.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            Cursada cursada;
            while(resultSet.next()){
                cursada = new Cursada();
                cursada.setId_cursa(resultSet.getInt("id_cursa"));
                
                Alumno a=buscarAlumno(resultSet.getInt("id"));
                cursada.setAlumno(a);
                
                Materia m=buscarMateria(resultSet.getInt("cod"));
                cursada.setMateria(m);
                cursada.setNota(resultSet.getInt("nota"));
               

                cursadas.add(cursada);
            }      
            statement.close();
        } catch (SQLException ex) {
            System.out.println("Error al obtener las cursadas: " + ex.getMessage());
        }
        
        
        return cursadas;
    }
    public List<Cursada> obtenerCursadasXAlumno(int id){
        List<Cursada> cursadas = new ArrayList<Cursada>();
            

        try {
            String sql = "SELECT * FROM cursa WHERE id = ?;";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1,id);
            ResultSet resultSet = statement.executeQuery();
            Cursada cursada;
            while(resultSet.next()){
                cursada = new Cursada();
                cursada.setId_cursa(resultSet.getInt("id_cursa"));
                
                Alumno a=buscarAlumno(resultSet.getInt("id"));
                cursada.setAlumno(a);
                
                Materia m=buscarMateria(resultSet.getInt("cod"));
                cursada.setMateria(m);
                cursada.setNota(resultSet.getInt("nota"));
               

                cursadas.add(cursada);
            }      
            statement.close();
        } catch (SQLException ex) {
            System.out.println("Error al obtener los alumnos: " + ex.getMessage());
        }
        
        
        return cursadas;
    }
    
    public List<Cursada> obtenerCursadasXMateria(int id){
        List<Cursada> cursadas = new ArrayList<Cursada>();
            

        try {
            String sql = "SELECT * FROM cursa WHERE cod = ?;";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1,id);
            ResultSet resultSet = statement.executeQuery();
            Cursada cursada;
            while(resultSet.next()){
                cursada = new Cursada();
                cursada.setId_cursa(resultSet.getInt("id_cursa"));
                
                Alumno a=buscarAlumno(resultSet.getInt("id"));
                cursada.setAlumno(a);
                
                Materia m=buscarMateria(resultSet.getInt("cod"));
                cursada.setMateria(m);
                cursada.setNota(resultSet.getInt("nota"));
               

                cursadas.add(cursada);
            }      
            statement.close();
        } catch (SQLException ex) {
            System.out.println("Error al obtener los alumnos: " + ex.getMessage());
        }
        
        
        return cursadas;
    }
    
    public void actualizarCursada(Cursada cursada){
       
       
        try {
            String sql = "UPDATE cursada SET id_alumno=?, id_materia =?, nota=? WHERE id=?;";
            
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
              ps.setInt(1, cursada.getAlumno().getId());
              ps.setInt(2, cursada.getMateria().getCod());
              ps.setDouble(3, cursada.getNota());
              ps.setInt(4, cursada.getId_cursa());                   
              ps.executeUpdate();                    
              ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(CursadaData.class.getName()).log(Level.SEVERE, null, ex);
        }
   }     
    
    public Materia buscarMateria(int id){
    
        MateriaData md=new MateriaData(conexion);
        return md.buscarMateria(id);
    
    }
    
    public Alumno buscarAlumno(int id)
    {
        AlumnoData ad = new AlumnoData(conexion);
        return ad.buscarAlumno(id);
    }
    
   /* public List<Materia> obtenerMateriasCursadas(int id){
    List<Materia> materias = new ArrayList<Materia>();
            

        try {
            String sql = "SELECT materia.cod, materia.nombre FROM cursa, materia WHERE cursa.cod = materia.cod\n" +
"and cursa.id = ?;";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            Materia materia;
            while(resultSet.next()){
                materia = new Materia();
                materia.setNombre(resultSet.getString("nombre"));
                materia.setPeriodo(resultSet.getString("periodo"));
                materia.setResponsable(resultSet.getString("responsable"));
                materias.add(materia);
            }      
            statement.close();
        } catch (SQLException ex) {
            System.out.println("Error al obtener las materias cursadas: " + ex.getMessage());
        }
        
        
        return materias;
      
    }
  
    public void actualizarNotaCursada(int idAlumno,int idMateria, int nota){
    
        try {
            
            String sql = "UPDATE cursa SET nota = ? WHERE id =? and cod =?;";

            PreparedStatement statement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1,nota);
            statement.setInt(2, idAlumno);
            statement.setInt(3, idMateria);
           
            
            statement.executeUpdate();
            
            
            statement.close();
    
        } catch (SQLException ex) {
            System.out.println("Error al actualizar: " + ex.getMessage());
        }
        
        
        
        
    
    }*/
}
