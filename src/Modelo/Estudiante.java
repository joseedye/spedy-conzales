/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import ufps.util.colecciones_seed.*;

/**
 *
 * @author acer
 */
public class Estudiante {
    
    long codEstudiante;
    String nombre;
    ListaCD<Ensayo>ensayos = new ListaCD();
    boolean sexo;

    public Estudiante() {
    }

    public Estudiante(long codEstudiante, String nombre, boolean sexo) {
        this.codEstudiante = codEstudiante;
        this.nombre = nombre;
        this.sexo = sexo;
    }

    public long getCodEstudiante() {
        return codEstudiante;
    }

    public void setCodEstudiante(long codEstudiante) {
        this.codEstudiante = codEstudiante;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ListaCD<Ensayo> getEnsayos() {
        return ensayos;
    }

    public void setEnsayos(ListaCD<Ensayo> ensayos) {
        this.ensayos = ensayos;
    }

    public boolean isSexo() {
        return sexo;
    }

    public void setSexo(boolean sexo) {
        this.sexo = sexo;
    }

    @Override
    public String toString() {
        return "\n Estudiante{" + "codEstudiante=" + codEstudiante + ", nombre=" + nombre + ", ensayos=" + ensayos + ", sexo=" + sexo + '}';
    }
    
    
}
