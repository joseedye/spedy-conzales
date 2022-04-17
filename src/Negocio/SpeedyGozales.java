/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

import Modelo.*;
import ufps.util.ArchivoLeerURL;
import ufps.util.colecciones_seed.*;

/**
 *
 * @author jose eduardo
 */
public class SpeedyGozales {

    private static final String webMaterias = "http://madarme.megaterios.co/materias.txt";
    private static final String webEstudiantes = "http://madarme.megaterios.co/estudiante-spedy.txt";
    private static final String webEnsayo = "http://madarme.megaterios.co/ensayo-spedy.txt";

    Cola<Materia> materias = new Cola();
    ListaCD<Ensayo> todosEnsayos = new ListaCD();

    
    public SpeedyGozales() {
        this.cargarMaterias();
        this.cargarEstudiantes();
        this.cargarEnsayos();
        this.asignarEnsayo();
        this.calcularNota();
//        ListaCD<Ensayo> os =  this.obtenerEnsayos(1151619);

//        while (!materias.esVacia()) {
//            System.out.println(materias.deColar());
//        }
//        IteratorLCD it = (IteratorLCD) os.iterator();
//        while(it.hasNext()){
//        System.out.println(it.next());}

    }

    private void cargarMaterias() {
        ArchivoLeerURL a = new ArchivoLeerURL(this.webMaterias);
        Object v[] = a.leerArchivo();
        for (int i = 1; i < v.length; i++) {
            String datos[] = v[i].toString().split(";");
            long codigo = Long.parseLong(datos[0]);

            Materia m = new Materia(codigo, datos[1]);
            materias.enColar(m);

        }
    }

    private void cargarEstudiantes() {
        ListaCD<Estudiante> tempEstud = new ListaCD();
        ArchivoLeerURL a = new ArchivoLeerURL(this.webEstudiantes);
        Object v[] = a.leerArchivo();
        for (int i = 1; i < v.length; i++) {
            String datos[] = v[i].toString().split(";");
            long codigo = Long.parseLong(datos[0]);
            Estudiante e = new Estudiante(codigo, datos[1], i % 2 == 0);
            tempEstud.insertarAlFinal(e);
        }
        //mandar una lisat
        matricularEstudiantes(tempEstud);
    }

    private void matricularEstudiantes(ListaCD<Estudiante> temp) {
        //copiar la pila

        int tam = materias.getTamanio();

        for (int i = 0; i < tam; i++) {
            Materia tempmateria = materias.deColar();
            Pila<Estudiante> temp2 = new Pila();
            IteratorLCD<Estudiante> it = (IteratorLCD<Estudiante>) temp.iterator();

            while (it.hasNext()) {
                temp2.apilar(it.next());
            }

            tempmateria.setEstudiantes(temp2);
            materias.enColar(tempmateria);
        }
    }

    private void cargarEnsayos() {

        ArchivoLeerURL a = new ArchivoLeerURL(this.webEnsayo);
        Object v[] = a.leerArchivo();

        for (int i = 1; i < v.length; i++) {
            String datos[] = v[i].toString().split(";");
            int codigo = Integer.parseInt(datos[2]);
            long codigo2 = Long.parseLong(datos[0]);

            Ensayo ensayo = new Ensayo(datos[1], codigo, codigo2);
            todosEnsayos.insertarAlFinal(ensayo);

        }
    }

    //asigna cada ensayo a  los estudiantes
    //problema que se borran los estudiantes de las dos ultimas matrerias
    private void asignarEnsayo() {

        int tam = materias.getTamanio();

        for (int i = 0; i < tam; i++) {

            Materia tempmateria = materias.deColar();
            Pila<Estudiante> nuevaPila = new Pila();

            int tamanio = tempmateria.getEstudiantes().getTamanio();

            for (int j = 0; j < tamanio; j++) {

                Estudiante estudTemp = tempmateria.getEstudiantes().desapilar();
                estudTemp.setEnsayos(obtenerEnsayos(estudTemp.getCodEstudiante()));
                nuevaPila.apilar(estudTemp);

            }
            tempmateria.setEstudiantes(nuevaPila);
            materias.enColar(tempmateria);
        }
    }

    private ListaCD<Ensayo> obtenerEnsayos(long codigoEstudiante) {
        ListaCD<Ensayo> misEnsayos = new ListaCD();
        IteratorLCD it = (IteratorLCD) todosEnsayos.iterator();
        while (it.hasNext()) {
            Ensayo en = (Ensayo) it.next();
            if (en.getCodigo() == codigoEstudiante) {
                misEnsayos.insertarAlFinal(en);
            }
        }
        return misEnsayos;
    }

    public void calcularNota() {

        int tam = materias.getTamanio();
        for (int i = 0; i < tam; i++) {
            Materia tempmateria = materias.deColar();
            System.out.println("\n notas de la materia de "+tempmateria.getNombreMateria()+"\n");
            int tamanio = tempmateria.getEstudiantes().getTamanio();
            for (int j = 0; j < tamanio; j++) {
                Estudiante estudTemp = tempmateria.getEstudiantes().desapilar();
                
                System.out.println("señor "+estudTemp.getNombre() +" su nota es : " + tot(estudTemp));
            }
        }
    }

    private Float tot(Estudiante es) {
        
        ListaCD<Float> nota = new ListaCD();
        IteratorLCD it = (IteratorLCD) es.getEnsayos().iterator();
        while (it.hasNext()) {

            int notas = calificacion((Ensayo) it.next());
            float rta = 0;
            if (!es.isSexo()) {
//             mujer
                switch (notas) {
                    case 1:
                        rta = 3.0F;
                        break;
                    case 2:
                        rta = 4.0F;
                        break;
                    case 3:
                        rta = 5.0F;
                        break;

                }
            } else {
                switch (notas) {
                    case 1:
                        rta = 2.0F;
                        break;
                    case 2:
                        rta = 3.0F;
                        break;
                    case 3:
                        rta = 4.0F;
                        break;
                }
            }
            nota.insertarAlFinal(rta);

        }
        borrarMenores(nota);
        borrarMenores(nota);

        return promedio(nota);
    }

    private void borrarMenores(ListaCD<Float> lista) {

        IteratorLCD<Float> it = (IteratorLCD<Float>) lista.iterator();
        Float menor = it.next();
        Float temp ;
        while (it.hasNext()) {
            temp = it.next();
            if (temp < menor) {
                menor = temp;
            }
        }
        lista.eliminar(lista.getIndice(menor));
    }

    private int calificacion(Ensayo est) {

        int total = est.getCantidadHojas();
        if (total >= 0 && total <= 3) {
            return 1;
        }
        if (total > 3 && total <= 5) {
            return 2;
        }
        if (total > 5) {
            return 3;
        }
        return 0;

    }

    
    private Float promedio(ListaCD<Float> est) {
        Float rta = 0F;
        IteratorLCD<Float> it = (IteratorLCD<Float>) est.iterator();
        while (it.hasNext()) {

            rta += it.next();
        }

        return (rta / est.getTamanio());
    }

}
