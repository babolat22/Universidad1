/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.time.LocalDate;

/**
 *
 * @author programador
 */
public class Alumno {
    private String nombre, domicilio;
    private int id, dni;
    private LocalDate fecnac;

    public Alumno() {
        this.id = -1;
    }

    public Alumno(String nombre, String domicilio, int dni, LocalDate fecnac) {
        this.nombre = nombre;
        this.domicilio = domicilio;
        this.dni = dni;
        this.fecnac = fecnac;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public LocalDate getFecnac(){
        return fecnac;
    }
    
    public void setFecnac(LocalDate fecnac){
        this.fecnac = fecnac;
    }
    
}
