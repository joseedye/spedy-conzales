/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import ufps.util.colecciones_seed.*;

/**
 *
 * @author jose eduardo rozo
 */
public class Materia {
   long CodMateria;
   String NombreMateria;
   Pila <Estudiante> estudiantes = new Pila();

    public Materia() {
    }

   
   
    public Materia(long CodMateria, String NombreMateria) {
        this.CodMateria = CodMateria;
        this.NombreMateria = NombreMateria;
    }

    public long getCodMateria() {
        return CodMateria;
    }

    public void setCodMateria(long CodMateria) {
        this.CodMateria = CodMateria;
    }

    public String getNombreMateria() {
        return NombreMateria;
    }

    public void setNombreMateria(String NombreMateria) {
        this.NombreMateria = NombreMateria;
    }

    public Pila<Estudiante> getEstudiantes() {
        return estudiantes;
    }

    public void setEstudiantes(Pila<Estudiante> estudiantes) {
        this.estudiantes = estudiantes;
    }

    @Override
    public String toString() {
        return "Materia{" + "CodMateria=" + CodMateria + ", NombreMateria=" + NombreMateria + ", estudiantes=" + estudiantes + '}';
    }
   
    
   
   
}
