/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author JOSE EDUARDO ROZO
 */
public class Ensayo {
    private String cuerpoDelTrabajo;
    private int cantidadHojas;
    private long codigo;

    public Ensayo() {
    }

    public Ensayo(String cuerpoDelTrabajo, int cantidadHojas, long codigo) {
        this.cuerpoDelTrabajo = cuerpoDelTrabajo;
        this.cantidadHojas = cantidadHojas;
        this.codigo = codigo;
    }
    
    

    public Ensayo(String cuerpoDelTrabajo, int cantidadHojas) {
        this.cuerpoDelTrabajo = cuerpoDelTrabajo;
        this.cantidadHojas = cantidadHojas;
    }

    public String getCuerpoDelTrabajo() {
        return cuerpoDelTrabajo;
    }

    public void setCuerpoDelTrabajo(String cuerpoDelTrabajo) {
        this.cuerpoDelTrabajo = cuerpoDelTrabajo;
    }

    public int getCantidadHojas() {
        return cantidadHojas;
    }

    public void setCantidadHojas(int cantidadHojas) {
        this.cantidadHojas = cantidadHojas;
    }

    public long getCodigo() {
        return codigo;
    }

    public void setCodigo(long codigo) {
        this.codigo = codigo;
    }

    
    @Override
    public String toString() {
        return "Ensayo{" + "cuerpoDelTrabajo=" + cuerpoDelTrabajo + ", cantidadHojas=" + cantidadHojas + '}';
    }
    
    
}
