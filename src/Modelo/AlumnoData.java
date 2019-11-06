/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import static java.lang.String.valueOf;
import java.sql.Connection;
import java.sql.Date;
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
 * @author programador
 */
public class AlumnoData {
    private Connection con = null;
    
    public AlumnoData(Conexion conexion){
        try {
            con = conexion.getConexion();
        } catch (SQLException ex) {
            System.out.println("Error al abrir y buscar una conexión");
        }
    }

    public void guardarAlumno(Alumno alu){
        
        try {
            String sql = "INSERT INTO alumno (dni,nombre,domicilio,fecnac) VALUES (?,?,?,?); ";
            PreparedStatement ps = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, alu.getDni());
            ps.setString(2, alu.getNombre());
            ps.setString(3, alu.getDomicilio());
            ps.setDate(4, Date.valueOf(alu.getFecnac()));
            
            ps.executeUpdate();
            
            ResultSet rs = ps.getGeneratedKeys();
            
            if(rs.next()){
                alu.setId(rs.getInt(1));
            }
            else {
                    System.out.println("No se pudo obtener el ID luego de insertar");
            }
            
            ps.close();
        } catch (SQLException ex) {
            System.out.println("Error al insertar un alumno: " + ex.getMessage());
        }
        
    }
    
    public List<Alumno> obtenerAlumnos(){
        List<Alumno> alumnos = new ArrayList<Alumno>();
        
        try {
            String sql = "SELECT * FROM alumno;";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            Alumno alu;
            while(rs.next()){
                alu = new Alumno();
                alu.setDni(rs.getInt("dni"));
                alu.setNombre(rs.getString("nombre"));
                alu.setDomicilio(rs.getString("domicilio"));
                alu.setFecnac(rs.getDate("fecnac").toLocalDate());
                
                
                alumnos.add(alu);
            }
            ps.close();
        } catch (SQLException ex) {
           System.out.println("Error al listar los alumnos");
        }
        return alumnos;
    }
    
    public Alumno buscarAlumno(int id){
    Alumno alumno=null;
    try {
            
            String sql = "SELECT * FROM alumno WHERE id =?;";

            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, id);
           
            
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                alumno = new Alumno();
                alumno.setDni(rs.getInt("dni"));
                alumno.setNombre(rs.getString("nombre"));
                alumno.setDomicilio(rs.getString("domicilio"));
                alumno.setFecnac(rs.getDate("fecnac").toLocalDate());
            }      
            ps.close();
            } catch (SQLException ex) {
            System.out.println("Error al insertar un alumno: " + ex.getMessage());
        }
        return alumno;
    }
    
    public void borrarAlumno(int id){
    try {
            
            String sql = "DELETE FROM alumno WHERE id =?;";

            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, id);
                      
            ps.executeUpdate();
                        
            ps.close();
    
        } catch (SQLException ex) {
            System.out.println("Error al insertar un alumno: " + ex.getMessage());
        }
        
    
    }
    
    public void actualizarAlumno(Alumno alumno){
    
        try {
            
            String sql = "UPDATE alumno SET dni = ?, nombre = ?, domicilio = ?, fecnac = ? WHERE id = ?;";

            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, alumno.getDni());
            ps.setString(2, alumno.getNombre());
            ps.setString(3, alumno.getDomicilio());
            ps.setDate(4, Date.valueOf(alumno.getFecnac()));
            ps.setInt(5, alumno.getId());
           
            ps.executeUpdate();
            
            ps.close();
    
        } catch (SQLException ex) {
            System.out.println("Error al insertar un alumno: " + ex.getMessage());
        }
    
    }
}
