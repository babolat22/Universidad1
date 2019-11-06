/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author programador
 */
public class Cursada {
    private int id_cursa;
    private double nota;
    private Alumno alumno;
    private Materia materia;

    public Cursada() {
    }

    public Cursada(Alumno alumno, Materia materia, double nota) {
        this.nota = nota;
        this.alumno = alumno;
        this.materia = materia;
    }

    public int getId_cursa() {
        return id_cursa;
    }

    public void setId_cursa(int id_cursa) {
        this.id_cursa = id_cursa;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }

    
    
    
}
