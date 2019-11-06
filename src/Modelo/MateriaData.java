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

/**
 *
 * @author alumno
 */
public class MateriaData {
    private Connection con = null;

    public MateriaData(Conexion conexion) {
        try {
            con = conexion.getConexion();
        } catch (SQLException ex) {
            System.out.println("Error al abrir al obtener la conexion");
        }
    }
    
    public void guardarMateria(Materia materia){
        try {
            
            String sql = "INSERT INTO materia (nombre, periodo, responsable) VALUES (?, ?, ? );";

            PreparedStatement statement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, materia.getNombre());
            statement.setString(2, materia.getPeriodo());
            statement.setString(3, materia.getResponsable());
            statement.executeUpdate();
            
            ResultSet rs = statement.getGeneratedKeys();

            if (rs.next()) {
                materia.setCod(rs.getInt(1));
            } else {
                System.out.println("No se pudo obtener el id luego de insertar una materia");
            }
            statement.close();
    
        } catch (SQLException ex) {
            System.out.println("Error al insertar una materia: " + ex.getMessage());
        }
    }
    
    public List<Materia> obtenerMaterias(){
        List<Materia> materias = new ArrayList<Materia>();
            

        try {
            String sql = "SELECT * FROM materia;";
            PreparedStatement statement = con.prepareStatement(sql);
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
            System.out.println("Error al obtener la materia: " + ex.getMessage());
        }
        
        
        return materias;
    }
    
    public Materia buscarMateria(int id){
    
        Materia materia=null;
    try {
            
            String sql = "SELECT * FROM materia WHERE cod =?;";

            PreparedStatement statement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, id);
           
            ResultSet resultSet=statement.executeQuery();
            
            while(resultSet.next()){
                materia = new Materia();
                materia.setNombre(resultSet.getString("nombre"));
                materia.setPeriodo(resultSet.getString("periodo"));
                materia.setResponsable(resultSet.getString("responsable"));
            }      
            statement.close();
            
            } catch (SQLException ex) {
            System.out.println("Error al insertar una materia: " + ex.getMessage());
        }
        
        return materia;
    }
    public void borrarMateria (int id){
    
        
    try {
            
            String sql = "DELETE FROM materia WHERE cod =?;";

            PreparedStatement statement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1,id);
           
            statement.executeUpdate();
            
            statement.close();
    
        } catch (SQLException ex) {
            System.out.println("Error al borrar una materia: " + ex.getMessage());
        }
        
    
    
    }
    public void actualizarMateria(Materia materia){
    try {
            
            String sql = "UPDATE materia SET nombre = ?, periodo = ?, responsable = ?, WHERE cod = ?;";

            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, materia.getNombre());
            ps.setString(2, materia.getPeriodo());
            ps.setString(3, materia.getResponsable());
            ps.setInt(4, materia.getCod());
           
            ps.executeUpdate();
            
            ps.close();
    
        } catch (SQLException ex) {
            System.out.println("Error al insertar una materia: " + ex.getMessage());
        }
     
        
    
    }
}
